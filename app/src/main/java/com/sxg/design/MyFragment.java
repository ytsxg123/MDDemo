package com.sxg.design;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.R.attr.fragment;
import static android.R.attr.icon;

/**
 * 描述：
 * Created by sxg on 2016/12/29.
 */

public class MyFragment extends Fragment {

    View rootView;
    AppBarLayout appBarLayout;

    int index;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_my,null);
        setNum(index);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        Log.i("TAG","==="+(index=bundle.getInt("key"))+rootView);
//        setNum(bundle.getInt("key"));
    }

    public MyFragment setNum(int num) {
        appBarLayout= (AppBarLayout) rootView.findViewById(R.id.app_bar);
        for (int i=0;i<num;i++) {
            appBarLayout.getChildAt(i).setVisibility(View.GONE);
        }
        return this;
    }
}
