package com.car;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextPaint;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    private TextView tv_welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
        setContentView(R.layout.activity_welcome);
        tv_welcome = findViewById(R.id.tv_welcome);
        TextPaint tp = tv_welcome.getPaint();
        tp.setFakeBoldText(true);
        findViewById(R.id.llout_welcome).getBackground().setAlpha(220);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(WelcomeActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },1500);
    }

    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}