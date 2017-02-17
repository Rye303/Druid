package com.truiton.activitytofragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private final SparseArray<WeakReference<Fragment>> instantiatedFragments = new SparseArray<>();
    private ArrayList<String> mTabHeader;
    private static final String LOG_TAG = "tag";


    public PagerAdapter(FragmentManager fm, ArrayList<String> tabHeader) {
        super(fm);
        this.mTabHeader = tabHeader;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                travelTab tab1 = new travelTab();
                return tab1;
            case 1:
                locationTab tab2 = new locationTab();
                return tab2;
            case 2:
                inventoryTab tab3 = new inventoryTab();
                return tab3;
            case 3:
                statsTab tab4 = new statsTab();
                return tab4;
            case 4:
                fightTab tab5 = new fightTab();
                return tab5;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabHeader.size();
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final Fragment fragment = (Fragment) super.instantiateItem(container, position);
        instantiatedFragments.put(position, new WeakReference<>(fragment));
        return fragment;
    }
/*
    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        Log.i(LOG_TAG, "destroy item"+position);
        instantiatedFragments.remove(position);
        super.destroyItem(container, position, object);
    }
*/
    @Nullable
    public Fragment getFragment(final int position) {
        final WeakReference<Fragment> wr = instantiatedFragments.get(position);
        Log.i(LOG_TAG, "getFragment"+position);
        if (wr != null) {
            Log.i(LOG_TAG, "not null");
            return wr.get();
        } else {
            Log.i(LOG_TAG, "null as hell");
            return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabHeader.get(position);
    }
}
