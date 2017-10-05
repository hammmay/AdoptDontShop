package com.epicodus.adoptdontshop.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.epicodus.adoptdontshop.adapters.FirebaseFriendViewHolder;
import com.epicodus.adoptdontshop.ui.FriendDetailActivity;
import com.epicodus.adoptdontshop.models.Friend;
import com.epicodus.adoptdontshop.util.ItemTouchHelperAdapter;
import com.epicodus.adoptdontshop.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import org.parceler.Parcels;
import java.util.Collections;
import java.util.ArrayList;

public class FirebaseFriendListAdapter extends FirebaseRecyclerAdapter<Friend, FirebaseFriendViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private ChildEventListener mChildEventListener;
    private Context mContext;
    private ArrayList<Friend> mFriends = new ArrayList<>();

    public FirebaseFriendListAdapter(Class<Friend> modelClass, int modelLayout,
                                     Class<FirebaseFriendViewHolder> viewHolderClass,
                                     Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mFriends.add(dataSnapshot.getValue(Friend.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    protected void populateViewHolder(final FirebaseFriendViewHolder viewHolder, Friend model, int position) {
        viewHolder.bindFriend (model);
        viewHolder.mFriendImageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, FriendDetailActivity.class);
                intent.putExtra("position", viewHolder.getAdapterPosition());
                intent.putExtra("friends", Parcels.wrap(mFriends));
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mFriends, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mFriends.remove(position);
        getRef(position).removeValue();
    }

    private void setIndexInFirebase() {
        for (Friend friend : mFriends) {
            int index = mFriends.indexOf(friend);
            DatabaseReference ref = getRef(index);
            friend.setIndex(Integer.toString(index));
            ref.setValue(friend);
        }
    }

    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }
}