package com.car;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.car.information.NecessaryInformation;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText set_temp_max;
    private EditText set_temp_min;
    private EditText set_humidity_max;
    private EditText set_humidity_min;
    private EditText set_co2_max;
    private EditText set_co2_min;
    private Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initWindow();

        set_temp_max = (EditText)findViewById(R.id.set_temp_max);
        set_temp_min = (EditText)findViewById(R.id.set_temp_min);
        set_humidity_max = (EditText)findViewById(R.id.set_humidity_max);
        set_humidity_min = (EditText)findViewById(R.id.set_humidity_min);
        set_co2_max = (EditText)findViewById(R.id.set_co2_max);
        set_co2_min = (EditText)findViewById(R.id.set_co2_min);
        btn_submit = (Button)findViewById(R.id.btn_submit);

        if (NecessaryInformation.temp_setted){
            set_temp_max.setText(String.valueOf(NecessaryInformation.temp_Max));
            set_temp_min.setText(String.valueOf(NecessaryInformation.temp_Min));
        }
        if (NecessaryInformation.humidity_setted){
            set_humidity_max.setText(String.valueOf(NecessaryInformation.humidity_Max));
            set_humidity_min.setText(String.valueOf(NecessaryInformation.humidity_Min));
        }
        if (NecessaryInformation.co2_setted){
            set_co2_max.setText(String.valueOf(NecessaryInformation.co2_Max));
            set_co2_min.setText(String.valueOf(NecessaryInformation.co2_Min));
        }

        btn_submit.setOnClickListener(this);
    }

    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    public void onClick(View v) {
        NecessaryInformation.temp_Max = Double.parseDouble(set_temp_max.getText().toString());
        NecessaryInformation.temp_Min = Double.parseDouble(set_temp_min.getText().toString());
        NecessaryInformation.humidity_Max = Integer.parseInt(set_humidity_max.getText().toString());
        NecessaryInformation.humidity_Min = Integer.parseInt(set_humidity_min.getText().toString());
        NecessaryInformation.co2_Max = Integer.parseInt(set_co2_max.getText().toString());
        NecessaryInformation.co2_Min = Integer.parseInt(set_co2_min.getText().toString());

        NecessaryInformation.temp_setted = true;
        NecessaryInformation.humidity_setted = true;
        NecessaryInformation.co2_setted = true;

        NecessaryInformation.hasSet = true;

        Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
    }
}