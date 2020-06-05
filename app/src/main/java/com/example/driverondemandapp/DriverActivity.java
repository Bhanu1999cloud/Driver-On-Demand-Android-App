package com.example.driverondemandapp;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.Nullable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

public class DriverActivity extends Fragment {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment=null;
            switch (item.getItemId()) {
                case R.id.navigation_addd:
                    fragment=new AddDriverFragment();
                    break;
                case R.id.navigation_viewd:
                    fragment=new ViewDriverFragment();
                    break;
                case R.id.calld:
                    fragment=new CallDriver();

            }
            getFragmentManager().beginTransaction().replace(R.id.frame3,fragment).addToBackStack(null).commit();
            return true;
        }
    };
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_driver, null, false);
        getFragmentManager().beginTransaction().replace(R.id.frame3,new AddDriverFragment()).addToBackStack(null).commit();
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();

        BottomNavigationView navView = getView().findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
