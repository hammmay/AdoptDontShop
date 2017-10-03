package com.epicodus.adoptdontshop.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.epicodus.adoptdontshop.Constants;
import com.epicodus.adoptdontshop.R;
import com.epicodus.adoptdontshop.adapters.FirebaseFriendListAdapter;
import com.epicodus.adoptdontshop.adapters.FirebaseFriendViewHolder;
import com.epicodus.adoptdontshop.models.Friend;

import com.epicodus.adoptdontshop.util.OnStartDragListener;
import com.epicodus.adoptdontshop.util.SimpleItemTouchHelperCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedFriendListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_friends_list);
    }

}