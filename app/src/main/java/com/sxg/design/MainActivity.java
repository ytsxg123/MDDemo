package com.sxg.design;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static int INDEX=0;
    TabLayout mTabLayout;
    ViewPager mViewPager;
    List<MyFragment> mFragments=new ArrayList<>();


    Toolbar toolbar;

    /**
     * 分支添加注释
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabLayout = (TabLayout) findViewById(R.id.tab);
        mViewPager= (ViewPager) findViewById(R.id.viewpager);
        toolbar= (Toolbar) findViewById(R.id.toolbar);

        String[] tabs={"tab1","tab2","tab3","tab4","tab5","tab6","tab7","tab8"};

        MyFragment myFragment;
        Bundle bundle;
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i=0 ,length=tabs.length;i<length;i++) {
            //初始化Tab标签
            if (i == 0) {
                mTabLayout.addTab(mTabLayout.newTab().setText(tabs[i]),true);
            } else {
                mTabLayout.addTab(mTabLayout.newTab().setText(tabs[i]),false);
            }
            myFragment=new MyFragment();
            bundle=new Bundle();
            bundle.putInt("key",i);
            myFragment.setArguments(bundle);
            mFragments.add(myFragment);

        }
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        for (int i=0,length=tabs.length;i<length;i++) {
            mTabLayout.getTabAt(i).setText(tabs[i]);
        }


        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(meunItemClickListener);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ScrollingActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    private Toolbar.OnMenuItemClickListener meunItemClickListener =new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_edit01:
                    Snackbar.make(toolbar,"标题一点击",Snackbar.LENGTH_LONG).setAction("action1", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(MainActivity.this,BannerActivity.class));
                        }
                    }).show();
                    break;
                case R.id.action_edit02:
                    Toast.makeText(MainActivity.this,"标题2",Toast.LENGTH_LONG).show();
                    break;
                case R.id.action_edit03:
                    Toast.makeText(MainActivity.this,"标题3",Toast.LENGTH_LONG).show();
                    break;
            }
            return true;
        }
    };
}

