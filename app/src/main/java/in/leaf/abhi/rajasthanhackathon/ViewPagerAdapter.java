package in.leaf.abhi.rajasthanhackathon;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.concurrent.CancellationException;

/**
 * Created by 500060150 on 21-03-2018.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        if(position==0) {
            CategorySelect cs=new CategorySelect();
            return cs;
        }
        else {
            VendorFragment vf=new VendorFragment();
            return vf;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
