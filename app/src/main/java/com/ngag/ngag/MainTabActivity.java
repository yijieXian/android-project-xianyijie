package com.ngag.ngag;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import adapter.MyFragmentAdapter;
import fragment.FourFragment;
import fragment.OneFragment;
import fragment.ThreeFragment;
import fragment.TwoFragment;
import viewpager.MyViewPager;

import static com.ngag.ngag.R.id.person;

public class MainTabActivity extends AppCompatActivity implements View.OnClickListener{


    private static List<Fragment> mlist;
    private MyViewPager mViewPager;

    private MyFragmentAdapter myAdapter;

    private List<ChangeIconView> mTabIndicators = new ArrayList<>();
    private ArrayList<Fragment> mFragments;

    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;
    private FourFragment fourFragment;
    private  Fragment fragment;

    private int locationFragment;

   //private IsOpenNetwork isOpenNetwork;//监测网络连接

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


        // 设置默认的Fragment  
        setDefaultFragment();


    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        Bundle b=getIntent().getExtras();
//        locationFragment=b.getInt("Fragment");
//
//
//    }

    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        oneFragment = new OneFragment();
        transaction.replace(R.id.framelayout, oneFragment);
        transaction.commit();
    }


    private void initView() {

        //设置底部4个图标
        ChangeIconView person = (ChangeIconView) findViewById(R.id.person);
        ChangeIconView car = (ChangeIconView) findViewById(R.id.car);
        ChangeIconView patrol = (ChangeIconView) findViewById(R.id.patrol);
        ChangeIconView set = (ChangeIconView) findViewById(R.id.set);
        mTabIndicators.add(person);
        mTabIndicators.add(car);
        mTabIndicators.add(patrol);
        mTabIndicators.add(set);

        person.setOnClickListener(this);
        car.setOnClickListener(this);
        patrol.setOnClickListener(this);
        set.setOnClickListener(this);

        person.setIconAlpha(1.0f);//默认将第一个置为亮
    }



    /**
     * 重置其他的Tab的顔色
     */
    private void resetOtherTabs() {
        for (int i = 0; i < mTabIndicators.size(); i++) {
            mTabIndicators.get(i).setIconAlpha(0);
        }

    }


    @Override
    public void onClick(View v) {
        FragmentManager fm = getSupportFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();


        resetOtherTabs();
        switch (v.getId()) {
            case person:
                mTabIndicators.get(0).setIconAlpha(1.0f);
               // mViewPager.setCurrentItem(0, false);

                oneFragment=new OneFragment();
                transaction.replace(R.id.framelayout, oneFragment);

                break;
            case R.id.car:
                mTabIndicators.get(1).setIconAlpha(1.0f);
//                mViewPager.setCurrentItem(1, false);

                twoFragment=new TwoFragment();
                transaction.replace(R.id.framelayout, twoFragment);

                break;
            case R.id.patrol:
                mTabIndicators.get(2).setIconAlpha(1.0f);
//                mViewPager.setCurrentItem(2, false);

                threeFragment=new ThreeFragment();
                transaction.replace(R.id.framelayout, threeFragment);

                break;
            case R.id.set:
                mTabIndicators.get(3).setIconAlpha(1.0f);
//                mViewPager.setCurrentItem(3, false);

                fourFragment=new FourFragment();
                transaction.replace(R.id.framelayout, fourFragment);

                break;
        }
        transaction.commit();
    }

//    public static void  addFragment(List<Fragment> list){
//        mlist = new ArrayList<>();
//        mlist=list;
//        Log.e("啊啊啊啊",list.get(0)+"");
//
//    }
//@Override
//    public  boolean onKeyDown(int keyCode, KeyEvent keyEvent){
//    if(fragment)
//}


}
