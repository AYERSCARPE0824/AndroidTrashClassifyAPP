package com.car;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.car.client.SocketClient;
import com.car.information.NecessaryInformation;
import com.car.request.DriverRequest;

public class LoginActivity extends AppCompatActivity {
    private TextView username;
    private TextView password;
    private TextView ed_host;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initWindow();
        findViewById(R.id.welcomllout).getBackground().setAlpha(100);
        ed_host = (TextView)findViewById(R.id.ed_host);
        ed_host.setText(NecessaryInformation.host);
    }

    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public void login(View view) {
        username=(TextView)findViewById(R.id.user_name);
        password=(TextView)findViewById(R.id.user_password);
        ed_host = (TextView)findViewById(R.id.ed_host);
        DriverRequest driverRequest = new DriverRequest();
        driverRequest.setUsername(username.getText().toString());
        driverRequest.setPassword(password.getText().toString());
        driverRequest.setRequestType(DriverRequest.LOGIN_REQUEST);
        NecessaryInformation.driverStatus = false;
        NecessaryInformation.host = ed_host.getText().toString();
        SocketClient socketClient = new SocketClient(driverRequest);
        socketClient.start();
        try {
            new Thread().sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(NecessaryInformation.driverStatus==false|| NecessaryInformation.driverResult==null){
            Toast.makeText(LoginActivity.this,"网络错误",Toast.LENGTH_SHORT).show();
        }else if(!NecessaryInformation.driverResult.isRes()){
            Toast.makeText(LoginActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
            NecessaryInformation.hasSet = false;
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void registerChooseHandler(View view) {}
    public void sinaLoginChooseHandeler(View view) {}
}