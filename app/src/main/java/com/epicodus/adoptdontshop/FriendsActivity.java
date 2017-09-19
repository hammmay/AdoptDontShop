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

    private String[] friends = new String[] {"Harry", "Fred",
            "Cheesis", "Gary", "Sausage", "Franky",
            "Sock", "Wilber", "Memers", "Stinky",
            "Sniffers", "Tuxedo", "Muggers",
            "CarrieB", "Elmer", "Puddles" };

//    private String[] types = new String[] {"Cat", "Dog",
//            "Hamster", "Bird", "Dog", "Dog",
//            "Snake", "Pig", "Cat", "Rat",
//            "Bunny", "Cat", "Cat",
//            "Dog", "Hamster", "Dog" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        ButterKnife.bind(this);

//        mListView = (ListView) findViewById(R.id.listView);
//        mLocationTextView = (TextView) findViewById(R.id.locationTextView);
//        MyFriendsArrayAdapter adapter = new MyFriendsArrayAdapter(this, android.R.layout.simple_list_item_1, friends, types);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, friends);

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String friend = ((TextView)view).getText().toString();
                Toast.makeText(FriendsActivity.this, friend, Toast.LENGTH_LONG).show();
            }
        });

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
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                    mFriends = petFinderService.processResults(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}