package bravostudio.togetherinspired.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import bravostudio.togetherinspired.Fragment.TopicFragment;
import bravostudio.togetherinspired.Fragment.NewsFragment;

/**
 * Created by jouvyap on 4/27/16.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position){
            case 0:
                return TopicFragment.newInstance();
            case 1:
                return NewsFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "FORUM";
            case 1:
                return "NEWS";
        }
        return null;
    }
}