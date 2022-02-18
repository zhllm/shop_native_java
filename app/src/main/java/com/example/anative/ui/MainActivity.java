package com.example.anative.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.example.anative.R;
import com.example.anative.ui.base.BaseActivity;
import com.example.anative.ui.car.CarFragment;
import com.example.anative.ui.home.HomeFragment;
import com.example.anative.ui.mine.MineFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Objects;

public class MainActivity extends BaseActivity implements NavigationBarView.OnItemSelectedListener {

    private BottomNavigationView bottomNavigationView;

    private Fragment[] fragments;

    private int lastFragment = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Native);
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
    }

    @Override
    protected void initViews() {
        bottomNavigationView = findViewById(R.id.main_button_navigation);
        bottomNavigationView.setOnItemSelectedListener(this);
        fragments = new Fragment[]{
                new HomeFragment(),
                new CarFragment(),
                new MineFragment()
        };
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_frame_layout, fragments[0])
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_nav_item_home:
                Log.d("hello", "main_nav_item_home");
                switchFragment(0);
                break;
            case R.id.main_nav_item_car:
                Log.d("hello", "main_nav_item_car");
                switchFragment(1);
                break;
            case R.id.main_nav_item_mine:
                Log.d("hello", "main_nav_item_mine");
                switchFragment(2);
                break;
            default:
                break;
        }
        return true;
    }

    private void switchFragment(int to) {
        if (lastFragment == to) {
            return;
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (!fragments[to].isAdded()) {
            fragmentTransaction.add(R.id.main_frame_layout, fragments[to]);
        } else {
            fragmentTransaction.show(fragments[to]);
        }
        fragmentTransaction.hide(fragments[lastFragment])
            .commitAllowingStateLoss();

        lastFragment = to;
    }
}