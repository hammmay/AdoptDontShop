package com.epicodus.adoptdontshop.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.adoptdontshop.models.Friend;
import com.epicodus.adoptdontshop.ui.FriendDetailFragment;

import java.util.ArrayList;

public class FriendPagerAdapter extends FragmentPagerAdapter{
    private ArrayList<Friend> mFriends;

    public FriendPagerAdapter(FragmentManager fm, ArrayList<Friend> friends) {
        super(fm);
        mFriends = friends;
    }

    @Override
    public Fragment getItem(int position) {
        return FriendDetailFragment.newInstance(mFriends.get(position));
    }

    @Override
    public int getCount() {
        return mFriends.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFriends.get(position).getName();
    }
}
