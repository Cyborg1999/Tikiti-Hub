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


public class LogInFragment extends Fragment {

    private View view;
    private EditText txtEmail, txtPassword;
    private TextView signupLink, loginErrors;
    private Button btnLogin;
    private ProgressDialog dialog;
    private RequestQueue requestQueue;
    private StringRequest request;


    public LogInFragment() {
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
        view = inflater.inflate(R.layout.fragment_log_in, container, false);
        init();
        return view;
    }

    private void init() {
            txtEmail = view.findViewById(R.id.log_in_email);
            txtPassword = view.findViewById(R.id.log_in_password);
            loginErrors = view.findViewById(R.id.log_in_errors);
            signupLink = view.findViewById(R.id.sign_up_link);
            btnLogin = view.findViewById(R.id.log_in_btn);
            dialog = new ProgressDialog(getContext());
            dialog.setCancelable(false);

            signupLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameAuthContainer, new SignUpFragment()).commit();;
                }
            });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginErrors.setVisibility(View.INVISIBLE);
                if(validate()){
                    loginErrors.setText("");
                    login();
                }
            }
        });
    }

    private void login() {
        dialog.setMessage("Signing in...");
        dialog.show();
        request = new StringRequest(Request.Method.POST, Constant.LOGIN,
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
                                Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                            }else{
                                loginErrors.setText("Incorrect Email/Password");
                                loginErrors.setVisibility(View.VISIBLE);
                                txtPassword.setText("");
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
        if(txtEmail.getText().toString().isEmpty() || txtPassword.getText().toString().isEmpty()){
            loginErrors.setText("Please fill in all the fields");
            loginErrors.setVisibility(View.VISIBLE);
            return false;
        }
        return true;
    }
}