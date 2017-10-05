package com.epicodus.adoptdontshop.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.adoptdontshop.Constants;
import com.epicodus.adoptdontshop.R;
import com.epicodus.adoptdontshop.models.Friend;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FriendDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.friendImageView) ImageView mImageLabel;
    @Bind(R.id.friendNameTextView) TextView mNameLabel;
    @Bind(R.id.animalTextView) TextView mAnimalLabel;
    @Bind(R.id.ageTextView) TextView mAgeLabel;
    @Bind(R.id.emailTextView) TextView mEmailLabel;
    @Bind(R.id.saveFriendButton) TextView mSaveFriendButton;

    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    private Friend mFriend;
    private ArrayList<Friend> mFriends;
    private int mPosition;

    public static FriendDetailFragment newInstance(ArrayList<Friend> friends, Integer position) {
        FriendDetailFragment friendDetailFragment = new FriendDetailFragment();
        Bundle args = new Bundle();

        args.putParcelable(Constants.EXTRA_KEY_FRIENDS, Parcels.wrap(friends));
        args.putInt(Constants.EXTRA_KEY_POSITION, position);

        friendDetailFragment.setArguments(args);
        return friendDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFriends = Parcels.unwrap(getArguments().getParcelable(Constants.EXTRA_KEY_FRIENDS));
        mPosition = getArguments().getInt(Constants.EXTRA_KEY_POSITION);
        mFriend = mFriends.get(mPosition);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend_detail, container, false);
        ButterKnife.bind(this, view);

        List<String> ImageURLList = (mFriend.getImageURL());
        String largeImageURL = ImageURLList.get(0).substring(0, ImageURLList.get(0).length() - 34).concat("o.jpg");
        Picasso.with(view.getContext())
                .load(largeImageURL)
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mImageLabel);

        mNameLabel.setText(mFriend.getName());
        mAnimalLabel.setText(mFriend.getAnimal());
        mAgeLabel.setText("Age: " + mFriend.getAge());

        mEmailLabel.setOnClickListener(this);

        mSaveFriendButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mEmailLabel) {
            Intent emailIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("mailto:" + mFriend.getEmail()));
            startActivity(emailIntent);
        }
        if (v == mSaveFriendButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference friendRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_FRIENDS)
                    .child(uid);

            DatabaseReference pushRef = friendRef.push();
            String pushId = pushRef.getKey();
            mFriend.setPushId(pushId);
            pushRef.setValue(mFriend);

            Toast.makeText(getContext(), "You Saved A Friend!", Toast.LENGTH_SHORT).show();
        }
    }



}