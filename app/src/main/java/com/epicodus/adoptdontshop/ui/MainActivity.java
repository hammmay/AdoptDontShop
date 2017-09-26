package com.epicodus.adoptdontshop.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.epicodus.adoptdontshop.Constants;
import com.epicodus.adoptdontshop.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    private DatabaseReference mSearchedLocationReference;

    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.findFriendsButton) Button mFindFriendsButton;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.findMissionButton) Button mFindMissionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedLocationReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        Typeface pacificoFont = Typeface.createFromAsset(getAssets(), "fonts/pacifico.ttf");
        mAppNameTextView.setTypeface(pacificoFont);
        mFindFriendsButton.setOnClickListener(this);
        mFindMissionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mFindFriendsButton) {

            String location = mLocationEditText.getText().toString();

            saveLocationToFirebase(location);

//                if(!(location).equals("")) {
//                    addToSharedPreferences(location);
//                }
            Intent intent = new Intent(MainActivity.this, FriendsListActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        }

//            if( mLocationEditText.getText().length() < 5 ) {
//                mLocationEditText.setError( "We can't find you a friend if we don't know where you are" );
//            }
//            else {
//                addToSharedPreferences(location);
//                Intent intent = new Intent(MainActivity.this, FriendsListActivity.class);
//                intent.putExtra("location", location);
//                startActivity(intent);
//            }
//        }
        if(v == mFindMissionButton) {
            Intent intent = new Intent(MainActivity.this, MissionActivity.class);
            startActivity(intent);
        }
    }

    public void saveLocationToFirebase(String location) {
        mSearchedLocationReference.push().setValue(location);
    }

//    private void addToSharedPreferences(String location) {
//        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
//    }
}