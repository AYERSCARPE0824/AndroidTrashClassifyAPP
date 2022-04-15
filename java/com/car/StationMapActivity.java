package com.car;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;
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
import com.car.pojo.Station;
import com.car.request.StationsRequest;

import java.util.ArrayList;
import java.util.List;

public class StationMapActivity extends AppCompatActivity {
    private MapView mMapView = null;
    private List<Station> stations = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_map);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        BaiduMap mBaiduMap = mMapView.getMap();
        double longitude = 121.167820;
        double latitude = 28.878371;
        //经纬度坐标点
        LatLng myPoint = new LatLng(latitude, longitude);

        StationsRequest stationsRequest = new StationsRequest();
        stationsRequest.setLatitude(latitude);
        stationsRequest.setLongitude(longitude);
        NecessaryInformation.stationsStatus = false;
        SocketClient socketClient = new SocketClient(stationsRequest);
        socketClient.start();

        List<OverlayOptions> options = null;
        OverlayOptions option = null;

        try {
            new Thread().sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //判断是否与服务器通信成功
        if (NecessaryInformation.stationsStatus==false||NecessaryInformation.stationsResult==null){
            Toast.makeText(StationMapActivity.this,"网络错误",Toast.LENGTH_SHORT).show();
        } else if(NecessaryInformation.stationsResult.getNum() == 0){
            Toast.makeText(StationMapActivity.this,"附近没有垃圾站",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(StationMapActivity.this,"查询成功",Toast.LENGTH_SHORT).show();
            stations = NecessaryInformation.stationsResult.getStations();
            //创建OverlayOptions的集合
            options = new ArrayList<OverlayOptions>();
            //构建Marker图标
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.icon_zuobiao);
            LatLng point = null;

            for (Station station :
                    stations) {
                //定义Maker坐标点
                point = new LatLng(station.getDoubleSlatitude()/1000000,station.getDoubleSlongitude()/1000000);
                //构建MarkerOption，用于在地图上添加Marker
                option = new MarkerOptions()
                        .position(point)
                        .icon(bitmap);
                options.add(option);
            }
        }
        option = new MarkerOptions()
                .position(myPoint)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_zuobiao1));
        options.add(option);
        mBaiduMap.addOverlays(options);
        //将地图移至我现在的位置
        MapStatus mMapStatus = new MapStatus.Builder().target(myPoint).zoom(20).build();
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
}