package com.epicodus.adoptdontshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;

public class FriendsActivity extends AppCompatActivity {
    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.listView) ListView mListView;

    private String[] friends = new String[] {"Harry", "Fred",
            "Cheesis", "Gary", "Sausage", "Franky",
            "Sock", "Wilber", "Memers", "Stinky",
            "Sniffers", "Tuxedo", "Muggers",
            "CarrieB", "Elmer", "Puddles" };

    private String[] types = new String[] {"Cat", "Dog",
            "Hamster", "Bird", "Dog", "Dog",
            "Snake", "Pig", "Cat", "Rat",
            "Bunny", "Cat", "Cat",
            "Dog", "Hamster", "Dog" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        ButterKnife.bind(this);

        mListView = (ListView) findViewById(R.id.listView);
        mLocationTextView = (TextView) findViewById(R.id.locationTextView);

//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, friends);
        MyFriendsArrayAdapter adapter = new MyFriendsArrayAdapter(this, android.R.layout.simple_list_item_1, friends, types);
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
    }


}
