package com.drew.tikitihub.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.os.Bundle;

import com.drew.tikitihub.R;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameAccountContainer, new AccountFragment()).commit();
    }
}