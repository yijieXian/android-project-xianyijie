package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by steven on 2017/7/4.
 */
public class VpFragmentAdapter extends FragmentPagerAdapter{
    private List<Fragment> list;
    private List<String> titles;



    // private Context context;

    public VpFragmentAdapter(FragmentManager fm, List<Fragment> list, List<String> titles){
        super(fm);
        this.list = list;
        this.titles = titles;

    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

}
