package com.example.driverondemandapp.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.driverondemandapp.AdminLoginFragement;
import com.example.driverondemandapp.ClientLoginFragement;
import com.example.driverondemandapp.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
switch (position)
{
    case 0:
        ClientLoginFragement client=new ClientLoginFragement();
        return client;
    case 1:
        AdminLoginFragement admin=new AdminLoginFragement();
        return admin;

        default:
            return null;
}
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
       switch (position)
       {
           case 0:
               return "Section 1";
           case 1:
               return "Section 2";

       }
       return null;
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}