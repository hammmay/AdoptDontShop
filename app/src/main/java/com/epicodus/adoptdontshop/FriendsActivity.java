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


public class FriendsActivity extends AppCompatActivity {
    private TextView mLocationTextView;
    private ListView mListView;
    private String[] friends = new String[] {"Harry - Cat", "Fred - Dog",
            "Cheesis - Hamster", "Gary - Bird", "Sausage - Dog", "Franky - Dog",
            "Sock - Snake", "Wilber - Pig", "Memers - Cat", "Stinky - Rat",
            "Sniffers - Bunny", "Tuxedo - Cat", "Muggers - Cat",
            "CarrieB - Dog", "Elmer - Hamster", "Puddles - Dog"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        mListView = (ListView) findViewById(R.id.listView);
        mLocationTextView = (TextView) findViewById(R.id.locationTextView);

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
    }
}
