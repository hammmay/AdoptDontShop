package com.epicodus.adoptdontshop;

import android.util.Log;

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
                JSONObject petFinderJSON = new JSONObject(jsonData);
                JSONArray petsJSON = petFinderJSON.getJSONArray("pets");
                for (int i = 0; i < petsJSON.length(); i++) {
                    JSONObject friendJSON = petsJSON.getJSONObject(i);
                    String name = friendJSON.optString("name", "No name yet");
                    String animal = friendJSON.getString("animal");
                    String breed = friendJSON.optString("breed", "No known breed");
                    String size = friendJSON.optString("size", "Size unknown");
                    String sex = friendJSON.optString("sex", "Sex unknown");
                    String age = friendJSON.optString("age", "Age unknown");
                    String photo = friendJSON.getString("photo");
                    String zip = friendJSON.optString("zip", "Zip code unknown");

                    ArrayList<String> pet = new ArrayList<>();
                    JSONArray petJSON = friendJSON.getJSONArray("pet");

                    for (int y = 0; y < petJSON.length(); y++) {
                        pet.add(petJSON.getJSONArray(y).get(0).toString());
                    }
                    Friend friend = new Friend(name, animal, breed, size, sex, age, photo, zip);
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
