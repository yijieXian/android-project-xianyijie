package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.ngag.ngag.R;

import java.util.ArrayList;
import java.util.List;

import adapter.MyFragmentAdapter;
import adapter.VpFragmentAdapter;
import viewpager.MyViewPager;


/**
 * Created by steven on 2017/7/3.
 */

public class OneFragment extends Fragment {

    private MyViewPager mViewPager;
    private ArrayList<Fragment> mFragments;
    private MyFragmentAdapter myAdapter;
    private PagerSlidingTabStrip pst;

//    public OneFragment(){
//        initView();
//        MainTabActivity.addFragment(mFragments);
//    }






    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view=inflater.inflate(R.layout.fragment_one,null);
       initView();
        return view;
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initDatas();
    }

    private void initDatas() {

        myAdapter = new MyFragmentAdapter(getChildFragmentManager(), mFragments);// 初始化adapter
        mViewPager.setAdapter(myAdapter);

    }
    private void initView(){
        mFragments = new ArrayList<>();
        mFragments.add(new RecyclerViewFragment());
        mFragments.add(new One_SecondFragment());



        mViewPager = (MyViewPager)view.findViewById(R.id.Home_ViewPager);
        pst = (PagerSlidingTabStrip)view.findViewById(R.id.pst);
        pst.setIndicatorColor(view.getResources().getColor(R.color.colorPrimary));
        pst.setDividerColor(view.getResources().getColor(R.color.splitline));
        pst.setIndicatorHeight(5);
        pst.setAllCaps(true);

        // pst.setShouldExpand(true); //不隐藏的话会导致单元格内的字体无法显示完整
        pst.setTextSize(45);

        pst.setTextColor(getResources().getColor(R.color.colorAccent));


        List<String> title = new ArrayList<>();
        title.add("Popular");
        title.add("New");

        VpFragmentAdapter ma= new VpFragmentAdapter(getChildFragmentManager(), mFragments, title);
        mViewPager.setAdapter(ma);
        pst.setViewPager(mViewPager);
    }
}
