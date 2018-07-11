package com.example.lzc.myspms.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;

/**
 * Created by Administrator on 2017/2/20.
 */

public class BaseFragment extends Fragment {
    protected int mWidth;
    protected int mHeight;
    protected View view;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Display display = getActivity().getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        mWidth = dm.widthPixels;
        mHeight = dm.heightPixels;
    }
}
