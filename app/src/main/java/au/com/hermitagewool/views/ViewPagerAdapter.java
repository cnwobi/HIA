package au.com.hermitagewool.views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * This class provides the transition effect between the two tabs
 * which are two fragment in the MainActivity.
 * The FragmentPagerAdapter keeps the data in memory.
 */
class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Execute the fragment depending on the tab index.
     * @param i is the tab index.
     * @return the fragment to execute
     */
    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case  0: return new NewsFragment();
            case  1: return new GraphFragment();
            default: return new NewsFragment();
        }
    }

    /**
     * Return the number of tabs
     * @return
     */
    @Override
    public int getCount() {
        return 2;
    }

    /**
     * Return the title depending on the tab index.
     * @param position is the tab index.
     * @return the tab title
     */
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: return "Newsletter";
            case 1: return "Smart Quilt";
            default: return null;
        }
    }


}
