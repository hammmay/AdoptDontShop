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

import com.epicodus.adoptdontshop.R;
import com.epicodus.adoptdontshop.models.Friend;
//import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FriendDetailFragment extends Fragment
        //implements View.OnClickListener
        {
    @Bind(R.id.friendImageView) ImageView mImageLabel;
    @Bind(R.id.friendNameTextView) TextView mNameLabel;
    @Bind(R.id.animalTextView) TextView mAnimalLabel;
    @Bind(R.id.ageTextView) TextView mAgeLabel;
    @Bind(R.id.emailTextView) TextView mEmailLabel;
    @Bind(R.id.saveFriendButton) TextView mSaveFriendButton;

    private Friend mFriend;

    public static FriendDetailFragment newInstance(Friend friend) {
        FriendDetailFragment friendDetailFragment = new FriendDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("friend", Parcels.wrap(friend));
        friendDetailFragment.setArguments(args);
        return friendDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFriend = Parcels.unwrap(getArguments().getParcelable("friend"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend_detail, container, false);
        ButterKnife.bind(this, view);

//        Picasso.with(view.getContext()).load(mFriend.getImageUrl()).into(mImageLabel);

        mNameLabel.setText(mFriend.getName());
        mAnimalLabel.setText(mFriend.getAnimal());
        mAgeLabel.setText("Age: " + mFriend.getAge());


//        mCategoriesLabel.setText(android.text.TextUtils.join(", ", mFriend.getCategories()));
//        mRatingLabel.setText(Double.toString(mFriend.getRating()) + "/5");
//        mPhoneLabel.setText(mFriend.getPhone());
//        mAddressLabel.setText(android.text.TextUtils.join(", ", mFriend.getAddress()));

//        mEmailLabel.setOnClickListener(this);

        return view;
    }

//    @Override
//    public void onClick(View v) {
//        if (v == mEmailLabel) {
//            Intent emailIntent = new Intent(Intent.ACTION_VIEW,
//                    Uri.parse("mailto:" + mFriend.getEmail()));
//            startActivity(emailIntent);
//        }
//
//    }



}