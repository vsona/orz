package net.vsona.baselibrary.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;


public class SimpleFragmentPagerAdapter<T extends Fragment> extends FragmentPagerAdapter {
    private SparseArray<T> mFragments;

    public SimpleFragmentPagerAdapter(FragmentManager fm, SparseArray<T> mFragments) {
        super(fm);
        this.mFragments = mFragments;
    }

    public T getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

}
