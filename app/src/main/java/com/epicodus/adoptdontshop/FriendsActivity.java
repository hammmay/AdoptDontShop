package com.epicodus.adoptdontshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FriendsActivity extends AppCompatActivity {
    public static final String TAG = FriendsActivity.class.getSimpleName();

    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.listView) ListView mListView;

    public ArrayList<Friend> mFriends = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("These friends are near: " + location);

        getFriends(location);

    }

    private void getFriends(String location) {
        final PetFinderService petFinderService = new PetFinderService();
        petFinderService.findFriends(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mFriends = petFinderService.processResults(response);

                FriendsActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        String[] friendNames = new String[mFriends.size()];
                        for (int i = 0; i < friendNames.length; i++) {
                            friendNames[i] = mFriends.get(i).getName();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(FriendsActivity.this,
                                android.R.layout.simple_list_item_1, friendNames);
                        mListView.setAdapter(adapter);

                        for (Friend friend : mFriends) {
                            Log.d(TAG, "Name: " + friend.getName());
                            Log.d(TAG, "Animal: " + friend.getAnimal());
                            Log.d(TAG, "Breed: " + friend.getBreed());
                            Log.d(TAG, "Size: " + friend.getSize());
                            Log.d(TAG, "Sex: " + friend.getSex());
                            Log.d(TAG, "Age: " + friend.getAge());
                            Log.d(TAG, "Photo: " + friend.getPhoto());
                            Log.d(TAG, "Location: " + friend.getLocation());
//                            Log.d(TAG, "Pet: " + (friend.getLocation()));
                        }
                    }
                });
            }
        });
    }
}
