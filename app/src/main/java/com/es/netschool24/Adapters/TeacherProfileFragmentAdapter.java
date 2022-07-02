package com.es.netschool24.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.es.netschool24.Fragment.CourseFragment;
import com.es.netschool24.Fragment.InformationFragment;
import com.es.netschool24.Fragment.PaymentFragment;

public class TeacherProfileFragmentAdapter extends FragmentPagerAdapter {
    String [] names = {"Information"};

    public TeacherProfileFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new InformationFragment();
            case 1:
                return new CourseFragment();
            case 2:
                return new PaymentFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return names[position];
    }
}
