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

public class ClientActivity extends Fragment {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment=null;
            switch (item.getItemId()) {
                case R.id.navigation_add:
                   fragment=new AddClientFragement();
                   break;
                case R.id.navigation_view:
             fragment=new ViewClientFragement();
             break;
                case R.id.call:
                    fragment=new CallClient();
                    break;

            }
            getFragmentManager().beginTransaction().replace(R.id.frame2,fragment).addToBackStack(null).commit();
            return true;
        }
    };
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_client, null, false);
        getFragmentManager().beginTransaction().replace(R.id.frame2,new AddClientFragement()).addToBackStack(null).commit();
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();

        BottomNavigationView navView = getView().findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
