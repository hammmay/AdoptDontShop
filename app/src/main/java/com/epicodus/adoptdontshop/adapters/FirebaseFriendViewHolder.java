package com.epicodus.adoptdontshop.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.adoptdontshop.Constants;
import com.epicodus.adoptdontshop.R;
import com.epicodus.adoptdontshop.models.Friend;
import com.epicodus.adoptdontshop.ui.FriendDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import static com.epicodus.adoptdontshop.R.id.ageTextView;
import static com.epicodus.adoptdontshop.R.id.animalTextView;

public class FirebaseFriendViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//    private static final int MAX_WIDTH = 200;
//    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;

    public FirebaseFriendViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindFriend(Friend friend) {
        ImageView friendImageView = (ImageView) mView.findViewById(R.id.friendImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.friendNameTextView);
        TextView animalTextView = (TextView) mView.findViewById(R.id.animalTextView);
        TextView ageTextView = (TextView) mView.findViewById(R.id.ageTextView);


//        List<String> ImageURLList = (friend.getImageURL());
//        String largeImageURL = ImageURLList.get(0).substring(0, ImageURLList.get(0).length() - 34).concat("o.jpg");
//        Picasso.with(mContext)
//                .load(largeImageURL)
//                .resize(MAX_WIDTH, MAX_HEIGHT)
//                .centerCrop()
//                .into(friendImageView);

        nameTextView.setText(friend.getName());
        animalTextView.setText(friend.getAnimal());
        ageTextView.setText("Age: " + friend.getAge());
        List<String> ImageURLList = (friend.getImageURL());
        Picasso.with(mContext).load(ImageURLList.get(0)).into(friendImageView);
    }




    @Override
    public void onClick(View view) {
        final ArrayList<Friend> friends = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_FRIENDS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    friends.add(snapshot.getValue(Friend.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, FriendDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("friends", Parcels.wrap(friends));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
