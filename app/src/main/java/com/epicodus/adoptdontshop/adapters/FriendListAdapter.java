package com.epicodus.adoptdontshop.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.adoptdontshop.R;
import com.epicodus.adoptdontshop.models.Friend;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.FriendViewHolder> {
    private ArrayList<Friend> mFriends = new ArrayList<>();
    private Context mContext;

    public FriendListAdapter(Context context, ArrayList<Friend> friends) {
        mContext = context;
        mFriends = friends;
    }

    @Override
    public FriendListAdapter.FriendViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_list_item, parent, false);
        FriendViewHolder viewHolder = new FriendViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FriendListAdapter.FriendViewHolder holder, int position) {
        holder.bindFriend(mFriends.get(position));
    }

    @Override
    public int getItemCount() {
        return mFriends.size();
    }

    public class FriendViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.friendImageView) ImageView mFriendImageView;
        @Bind(R.id.friendNameTextView) TextView mNameTextView;
        @Bind(R.id.animalTextView) TextView mAnimalTextView;
        @Bind(R.id.ageTextView) TextView mAgeTextView;
        private Context mContext;

        public FriendViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindFriend(Friend friend) {
// picasso image loader            Picasso.with(mContext).load(friend.getImageUrl()).into(mFriendImageView);
            mNameTextView.setText(friend.getName());
            mAnimalTextView.setText(friend.getAnimal());
            mAgeTextView.setText("Age: " + friend.getAge());
        }
    }
}

//category = animal?
//was address