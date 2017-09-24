package com.epicodus.adoptdontshop.services;

import android.util.Log;

import com.epicodus.adoptdontshop.Constants;
import com.epicodus.adoptdontshop.models.Friend;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PetFinderService {

    public static void findFriends(String location, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.PETFINDER_BASE_URL).newBuilder();
        urlBuilder.addEncodedQueryParameter("key", Constants.PETFINDER_CONSUMER_KEY);
        urlBuilder.addQueryParameter(Constants.PETFINDER_LOCATION_QUERY_PARAMETER, location);
        urlBuilder.addQueryParameter("output", "full");
        urlBuilder.addQueryParameter("format", "json");
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Log.v("Therequest looks like: ", request + "");

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Friend> processResults(Response response) {
        ArrayList<Friend> friends = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {

                JSONObject readerJSON = new JSONObject(jsonData);

                JSONObject petFinderJSON = readerJSON.getJSONObject("petfinder");

                JSONObject petsJSON = petFinderJSON.getJSONObject("pets");

                JSONArray petJSON = petsJSON.getJSONArray("pet");

                for (int i = 0; i < petJSON.length(); i++) {
                    JSONObject friendJSON = petJSON.getJSONObject(i);
                    String name = friendJSON.getJSONObject("name").getString("$t");
                    String animal = friendJSON.getJSONObject("animal").getString("$t");
                    String size = friendJSON.getJSONObject("size").getString("$t");
                    String sex = friendJSON.getJSONObject("sex").getString("$t");
                    String age = friendJSON.getJSONObject("age").getString("$t");
                    String zip = friendJSON.getJSONObject("contact").getJSONObject("zip").getString("$t");
                    String email = friendJSON.getJSONObject("contact").getJSONObject("email").getString("$t");


// Missing Photo array

                    JSONArray imageURLJSON = friendJSON.getJSONObject("media").getJSONObject("photos").getJSONArray("photo");
                       .getLength().get(0).getString("$t");
                        .getJSONArray("photo");

                    ArrayList<String> imageURL = new ArrayList<>();
                    for (int y = 0; y < photoJSON.length(); y++) {
                        imageURL.add(photoJSON.getJSONArray(y).get(0).toString());
                    }
// Photo array



                    Friend friend = new Friend(name, animal, size, sex, age, zip, email
 , imageURL
 );
                    friends.add(friend);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return friends;
    }
}
