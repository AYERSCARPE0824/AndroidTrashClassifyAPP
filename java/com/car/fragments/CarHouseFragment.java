package com.car.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.car.MapActivity;
import com.car.R;
import com.car.SettingActivity;
import com.car.client.SocketClient;
import com.car.information.NecessaryInformation;
import com.car.pojo.CarHouse;
import com.car.pojo.Lights;
import com.car.request.CarHouseRequest;
import com.car.request.LightsRequest;
import com.car.view.CompletedView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CarHouseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarHouseFragment extends Fragment {
    private ImageButton ib_switch_temp;
    private ImageButton ib_switch_humidity;
    private ImageButton ib_switch_co2;
    private ImageButton ib_map;
    private ImageButton ib_refresh;
    private TextView tv_switch_temp;
    private TextView tv_switch_humidity;
    private TextView tv_switch_co2;
    private TextView tv_last_refresh;
    private TextView tv_temp_now;
    private TextView tv_humidity_now;
    private TextView tv_co2_now;
    private TextView tv_shake_now;
    private Button btn_setting;
    private boolean ison_temp = false;
    private boolean ison_humidity = false;
    private boolean ison_co2 = false;

    private CompletedView mTasksView;
    private int mTotalProgress =0 ;
    private int mCurrentProgress = 0;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CarHouseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DriverFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CarHouseFragment newInstance(String param1, String param2) {
        CarHouseFragment fragment = new CarHouseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        initWindow();
    }

    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carhouse, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ib_switch_temp = (ImageButton) getActivity().findViewById(R.id.ib_switch_temp);
        ib_switch_humidity = (ImageButton) getActivity().findViewById(R.id.ib_switch_humidity);
        ib_switch_co2 = (ImageButton) getActivity().findViewById(R.id.ib_switch_co2);
        ib_map = (ImageButton) getActivity().findViewById(R.id.ib_map);
        ib_refresh = (ImageButton) getActivity().findViewById(R.id.ib_refresh);
        tv_switch_temp = (TextView) getActivity().findViewById(R.id.tv_switch_temp);
        tv_switch_humidity = (TextView) getActivity().findViewById(R.id.tv_switch_humidity);
        tv_switch_co2 = (TextView) getActivity().findViewById(R.id.tv_switch_co2);
        tv_last_refresh = (TextView) getActivity().findViewById(R.id.tv_last_refresh);
        tv_temp_now = (TextView) getActivity().findViewById(R.id.tv_temp_now);
        tv_humidity_now = (TextView) getActivity().findViewById(R.id.tv_humidity_now);
        tv_co2_now = (TextView) getActivity().findViewById(R.id.tv_co2_now);
        tv_shake_now = (TextView) getActivity().findViewById(R.id.tv_shake_now);
        btn_setting = (Button) getActivity().findViewById(R.id.btn_setting);

        //开关按钮
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.ib_switch_temp:
                        ison_temp = !ison_temp;
                        if (ison_temp) {
                            ib_switch_temp.setImageResource(R.drawable.switch2);
                            tv_switch_temp.setText("on");
                            NecessaryInformation.lights.setLightOne(false);

                            LightsRequest lightsRequest = new LightsRequest();
                            lightsRequest.setLights(new Lights(true, NecessaryInformation.lights.getLightTwo(), NecessaryInformation.lights.getLightThree()));
                            SocketClient socketClient = new SocketClient(lightsRequest);
                            socketClient.start();
                            try {
                                new Thread().sleep(1200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ib_switch_temp.setImageResource(R.drawable.switch4);
                            tv_switch_temp.setText("off");
                            NecessaryInformation.lights.setLightOne(false);

                            LightsRequest lightsRequest = new LightsRequest();
                            lightsRequest.setLights(new Lights(false, NecessaryInformation.lights.getLightTwo(), NecessaryInformation.lights.getLightThree()));
                            SocketClient socketClient = new SocketClient(lightsRequest);
                            socketClient.start();
                            try {
                                new Thread().sleep(1200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case R.id.ib_switch_humidity:
                        ison_humidity = !ison_humidity;
                        if (ison_humidity) {
                            ib_switch_humidity.setImageResource(R.drawable.switch2);
                            tv_switch_humidity.setText("on");
                            NecessaryInformation.lights.setLightTwo(true);

                            LightsRequest lightsRequest = new LightsRequest();
                            lightsRequest.setLights(new Lights(NecessaryInformation.lights.getLightOne(),true, NecessaryInformation.lights.getLightThree()));
                            SocketClient socketClient = new SocketClient(lightsRequest);
                            socketClient.start();
                            try {
                                new Thread().sleep(1200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ib_switch_humidity.setImageResource(R.drawable.switch4);
                            tv_switch_humidity.setText("off");
                            NecessaryInformation.lights.setLightTwo(false);

                            LightsRequest lightsRequest = new LightsRequest();
                            lightsRequest.setLights(new Lights(NecessaryInformation.lights.getLightOne(),false, NecessaryInformation.lights.getLightThree()));
                            SocketClient socketClient = new SocketClient(lightsRequest);
                            socketClient.start();
                            try {
                                new Thread().sleep(1200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case R.id.ib_switch_co2:
                        ison_co2 = !ison_co2;
                        if (ison_co2) {
                            ib_switch_co2.setImageResource(R.drawable.switch2);
                            tv_switch_co2.setText("on");
                            NecessaryInformation.lights.setLightThree(true);

                            LightsRequest lightsRequest = new LightsRequest();
                            lightsRequest.setLights(new Lights(NecessaryInformation.lights.getLightOne(), NecessaryInformation.lights.getLightTwo(),true));
                            SocketClient socketClient = new SocketClient(lightsRequest);
                            socketClient.start();
                            try {
                                new Thread().sleep(1200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ib_switch_co2.setImageResource(R.drawable.switch4);
                            tv_switch_co2.setText("off");
                            NecessaryInformation.lights.setLightThree(false);

                            LightsRequest lightsRequest = new LightsRequest();
                            lightsRequest.setLights(new Lights(NecessaryInformation.lights.getLightOne(), NecessaryInformation.lights.getLightTwo(),false));
                            SocketClient socketClient = new SocketClient(lightsRequest);
                            socketClient.start();
                            try {
                                new Thread().sleep(1200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                }
            }
        };
        ib_switch_temp.setOnClickListener(listener);
        ib_switch_humidity.setOnClickListener(listener);
        ib_switch_co2.setOnClickListener(listener);

        //地图
        ib_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),MapActivity.class);
                startActivity(intent);
            }
        });

        //刷新按钮
        ib_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double temp;
                int humidity;
                int co2;
                int shake;

                boolean freshSuccess = false;

                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                Date curDate = new Date(System.currentTimeMillis());
                String str = dateFormat.format(curDate);
                tv_last_refresh.setText(str);

                CarHouseRequest carHouseRequest = new CarHouseRequest();
                carHouseRequest.setId(1);
                NecessaryInformation.carHouseStatus = false;
                SocketClient socketClient = new SocketClient(carHouseRequest);
                socketClient.start();
                try {
                    new Thread().sleep(1200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (NecessaryInformation.carHouseStatus==false|| NecessaryInformation.carHouseResult==null){
                    Toast.makeText(getActivity(),"网络错误",Toast.LENGTH_SHORT).show();
                }else{
                    freshSuccess = true;

                    CarHouse carHouse = NecessaryInformation.carHouseResult.getCarHouse();

                    temp = carHouse.getTemp();
                    humidity = (int)carHouse.getHumidity();
                    co2 = (int)(carHouse.getCo2());
                    shake = (int)carHouse.getShake();

                    tv_temp_now.setText(String.valueOf(temp));
                    tv_humidity_now.setText(String.valueOf(humidity));
                    tv_co2_now.setText(String.valueOf(co2));
                    tv_shake_now.setText(String.valueOf(shake));
                    mTotalProgress = carHouse.getGrade();
//                    tv_temp_now.setText("35.6");
//                    tv_humidity_now.setText("59.5");
//                    tv_co2_now.setText("1.2");
//                    tv_shake_now.setText("4");
//                    mTotalProgress = 48;

                    mCurrentProgress = 0;
                    new Thread(new ProgressRunable()).start();

                    if (freshSuccess&& NecessaryInformation.hasSet) {
                        LightsRequest lightsRequest = new LightsRequest();
                        lightsRequest.setLights(new Lights());
                        if (temp > NecessaryInformation.co2_Max || temp < NecessaryInformation.temp_Min) {
                            lightsRequest.getLights().setLightOne(true);
                            NecessaryInformation.lights.setLightOne(true);
                        }else{
                            lightsRequest.getLights().setLightOne(false);
                            NecessaryInformation.lights.setLightOne(false);
                        }
                        if (humidity > NecessaryInformation.humidity_Max || humidity < NecessaryInformation.humidity_Min) {
                            lightsRequest.getLights().setLightTwo(true);
                            NecessaryInformation.lights.setLightTwo(true);
                        }else{
                            lightsRequest.getLights().setLightTwo(false);
                            NecessaryInformation.lights.setLightTwo(false);
                        }
                        if (co2 > NecessaryInformation.co2_Max || co2 < NecessaryInformation.co2_Min) {
                            lightsRequest.getLights().setLightThree(true);
                            NecessaryInformation.lights.setLightThree(true);
                        }else{
                            lightsRequest.getLights().setLightThree(false);
                            NecessaryInformation.lights.setLightThree(false);
                        }
                        SocketClient socketClient1 = new SocketClient(lightsRequest);
                        socketClient1.start();
                        try {
                            new Thread().sleep(1200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if (NecessaryInformation.lights.getLightOne()){
                        ison_temp = true;
                        ib_switch_temp.setImageResource(R.drawable.switch2);
                        tv_switch_temp.setText("on");
                    } else {
                        ison_temp = false;
                        ib_switch_temp.setImageResource(R.drawable.switch4);
                        tv_switch_temp.setText("off");
                    }
                    if (NecessaryInformation.lights.getLightTwo()){
                        ison_humidity = true;
                        ib_switch_humidity.setImageResource(R.drawable.switch2);
                        tv_switch_humidity.setText("on");
                    } else {
                        ison_humidity = false;
                        ib_switch_humidity.setImageResource(R.drawable.switch4);
                        tv_switch_humidity.setText("off");
                    }
                    if (NecessaryInformation.lights.getLightThree()){
                        ison_co2 = true;
                        ib_switch_co2.setImageResource(R.drawable.switch2);
                        tv_switch_co2.setText("on");
                    } else {
                        ison_co2 = false;
                        ib_switch_co2.setImageResource(R.drawable.switch4);
                        tv_switch_co2.setText("off");
                    }
                }
            }
        });

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String str = dateFormat.format(curDate);
        tv_last_refresh.setText(str);

        //设置按钮
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });

        //首次拿数据
        CarHouseRequest carHouseRequest = new CarHouseRequest();
        carHouseRequest.setId(1);
        NecessaryInformation.carHouseStatus = false;
        SocketClient socketClient = new SocketClient(carHouseRequest);
        socketClient.start();
        try {
            new Thread().sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (NecessaryInformation.carHouseStatus==false|| NecessaryInformation.carHouseResult==null){
            Toast.makeText(getActivity(),"网络错误",Toast.LENGTH_SHORT).show();
        }else{
            CarHouse carHouse = NecessaryInformation.carHouseResult.getCarHouse();
            tv_temp_now.setText(String.valueOf(carHouse.getTemp()));
            tv_humidity_now.setText(String.valueOf((int)carHouse.getHumidity()));
            tv_co2_now.setText(String.valueOf((int)carHouse.getCo2()));
            tv_shake_now.setText(String.valueOf((int)carHouse.getShake()));
            mTotalProgress = carHouse.getGrade();
//            tv_temp_now.setText("26.3");
//            tv_humidity_now.setText("62.3");
//            tv_co2_now.setText("1.3");
//            tv_shake_now.setText("4");
//            mTotalProgress = 81;
        }

        mTasksView = (CompletedView) getActivity().findViewById(R.id.tasks_view);
//        mTotalProgress = 92;
        new Thread(new ProgressRunable()).start();
    }

    class ProgressRunable implements Runnable {
        @Override
        public void run() {
            while (mCurrentProgress < mTotalProgress) {
                mCurrentProgress += 1;
                mTasksView.setProgress(mCurrentProgress);
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}