package com.m0n0l0c0.LinkedChronos;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.m0n0l0c0.LinkedChronos.base.BaseActivity;
import com.m0n0l0c0.LinkedChronos.ui.MainActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        goToNextActivity();

    }

    private void goToNextActivity(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, 1500);
    }

}
