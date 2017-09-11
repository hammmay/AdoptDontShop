package com.epicodus.adoptdontshop;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MyFriendsArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mFriends;
    private String[] mTypes;

    public MyFriendsArrayAdapter(Context mContext, int resource, String[] mFriends, String[] mTypes) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mFriends = mFriends;
        this.mTypes = mTypes;
    }

    @Override
    public Object getItem(int position) {
        String friend = mFriends[position];
        String type = mTypes[position];
        return String.format("%s \nType of Friend - %s", friend, type);
    }

    @Override
    public int getCount() {
        return mFriends.length;
    }
}
