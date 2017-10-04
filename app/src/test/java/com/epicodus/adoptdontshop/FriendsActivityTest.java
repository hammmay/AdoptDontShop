package com.epicodus.adoptdontshop;

import android.os.Build;
import android.widget.ListView;

import com.epicodus.adoptdontshop.ui.FriendListActivity;

import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)


public class FriendsActivityTest {
    private FriendListActivity activity;
    private ListView mFriendListView;

    @Before
    public void setup() {
        activity = Robolectric.setupActivity(FriendListActivity.class);
//        mFriendListView = (ListView) activity.findViewById(R.id.listView);
    }

    @Test
    public void friendListViewPopulates() {
        assertNotNull(mFriendListView.getAdapter());
        assertEquals(mFriendListView.getAdapter().getCount(), 16);
    }
}
