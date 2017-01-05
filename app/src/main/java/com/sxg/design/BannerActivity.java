package com.sxg.design;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

public class BannerActivity extends AppCompatActivity implements BGABanner.Adapter<ImageView,String>,BGABanner.Delegate<ImageView,String>{

    BGABanner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        banner= (BGABanner) findViewById(R.id.banner);

        banner.setAdapter(this);



        List<String> mList=new ArrayList<>();
        mList.add("http://master.huliandaojia.cn/upload_php_file/201611/2511244659.png");
        mList.add("http://master.huliandaojia.cn/upload_php_file/201611/2511244659.png");
        mList.add("http://master.huliandaojia.cn/upload_php_file/201611/2511244659.png");
        mList.add("http://master.huliandaojia.cn/upload_php_file/201611/2511244659.png");
        mList.add("http://master.huliandaojia.cn/upload_php_file/201611/2511244659.png");
        banner.setData(mList,null);
    }


    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
        Glide.with(this).load(model).into(itemView);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
        Toast.makeText(this,"点击了"+position,Toast.LENGTH_LONG).show();
    }
}
