package com.example.anhki.tradingbook.View;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.anhki.tradingbook.R;

public class SlashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_slashscreen);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent iLogin = new Intent(SlashScreenActivity.this, LoginActivity.class);
                startActivity(iLogin);
                finish();
            }
        }, 2000);
    }
}
