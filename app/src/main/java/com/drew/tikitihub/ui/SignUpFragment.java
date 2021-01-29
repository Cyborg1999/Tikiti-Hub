package com.drew.tikitihub.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.drew.tikitihub.R;
import com.drew.tikitihub.extra.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class SignUpFragment extends Fragment {
    private View view;
    private EditText txtFirstName, txtLastName, txtEmail, txtPassword;
    private TextView loginLink, signupErrors;
    private Button btnSignUp;
    private ProgressDialog dialog;
    private RequestQueue requestQueue;
    private StringRequest request;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        init();
        return view;
    }

    private void init() {
        txtFirstName = view.findViewById(R.id.sign_up_first_name);
        txtLastName = view.findViewById(R.id.sign_up_last_name);
        txtEmail = view.findViewById(R.id.sign_up_email);
        txtPassword = view.findViewById(R.id.sign_up_password);
        signupErrors = view.findViewById(R.id.sign_up_errors);
        loginLink = view.findViewById(R.id.log_in_link);
        btnSignUp = view.findViewById(R.id.sign_up_btn);
        dialog = new ProgressDialog(getContext());
        dialog.setCancelable(false);


        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameAuthContainer, new LogInFragment()).commit();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupErrors.setVisibility(View.INVISIBLE);
                if(validate()){
                    signupErrors.setText("");
                    register();
                }
            }
        });
    }

    private void register() {
        dialog.setMessage("Signing up...");
        dialog.show();
        request = new StringRequest(Request.Method.POST, Constant.REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Response", response);

                        try {
                            JSONObject object = new JSONObject(response);
                            if(object.getBoolean("success")){
                                JSONObject user = object.getJSONObject("user");

                                SharedPreferences userPref = getActivity().getApplicationContext().getSharedPreferences("user",getContext().MODE_PRIVATE);
                                SharedPreferences.Editor editor = userPref.edit();
                                editor.putString("token",object.getString("token"));
                                editor.putString("name",user.getString("name"));
                                editor.putString("email",user.getString("email"));
                                editor.putBoolean("isLoggedIn",true);
                                editor.apply();

                                startActivity(new Intent((AuthActivity)getContext(), HomeActivity.class));
                                ((AuthActivity)getContext()).finish();
                                Toast.makeText(getContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Response", error.toString());
                        dialog.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", txtFirstName.getText().toString()+" "+txtLastName.getText().toString());
                params.put("email", txtEmail.getText().toString());
                params.put("password", txtPassword.getText().toString());
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }


    private boolean validate() {
        if(txtFirstName.getText().toString().isEmpty() || txtLastName.getText().toString().isEmpty() || txtEmail.getText().toString().isEmpty() || txtPassword.getText().toString().isEmpty()){
            signupErrors.setText("Please fill in all the fields");
            signupErrors.setVisibility(View.VISIBLE);
            return false;
        }else if(txtPassword.getText().toString().length()<6){
            signupErrors.setText("Password should be a minimum of 6 characters");
            signupErrors.setVisibility(View.VISIBLE);
            return false;
        }
        return true;
    }
}