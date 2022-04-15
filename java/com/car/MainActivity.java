package com.car;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.car.fragments.CarHouseFragment;
import com.car.fragments.InformationFragment;
import com.car.fragments.MainFragmentpagerAdapter;
import com.car.view.ViewPagerSlide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPagerSlide viewPager;
    private TextView textView;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_pig:
//                    textView.setText("车厢状况");
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_setting:
//                    textView.setText("信息修改");
                    viewPager.setCurrentItem(1);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();

    }
    private void initView() {
        final BottomNavigationView mBottomNav = findViewById(R.id.nav_view);
        mBottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager = findViewById(R.id.viewPager);
        ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new CarHouseFragment());
        fragmentList.add(new InformationFragment());
        //给ViewPager设置适配器
        MainFragmentpagerAdapter adapter = new MainFragmentpagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);//设置当前显示标签页为第一页
    }
}