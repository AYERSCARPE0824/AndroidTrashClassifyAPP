package com.car;

import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.car.client.SocketClient;
import com.car.information.NecessaryInformation;
import com.car.pojo.CarHouse;
import com.car.request.CarHouseRequest;

public class MapActivity extends AppCompatActivity {
    private MapView mMapView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        initWindow();

        double longitude = 121.167820;
        double latitude = 28.878371;

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
            Toast.makeText(this,"网络错误",Toast.LENGTH_SHORT).show();
        }else{
            CarHouse carHouse = NecessaryInformation.carHouseResult.getCarHouse();
            longitude = carHouse.getLongitude();
            latitude = carHouse.getLatitude();
        }

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        BaiduMap mBaiduMap = mMapView.getMap();

        LatLng point = new LatLng(latitude, longitude);
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_zuobiao);
        OverlayOptions options = new MarkerOptions().position(point).icon(bitmap);
        mBaiduMap.addOverlay(options);

        MapStatus mMapStatus = new MapStatus.Builder().target(point).zoom(20).build();
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        mBaiduMap.setMapStatus(mapStatusUpdate);
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}