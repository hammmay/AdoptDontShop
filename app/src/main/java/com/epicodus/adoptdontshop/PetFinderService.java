package com.epicodus.adoptdontshop;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

public class PetFinderService {

    public static void findFriends(String location, Callback callback) {
        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(Constants.PETFINDER_KEY, Constants.PETFINDER_SECRET);

//        consumer.setTokenWithSecret(Constants.YELP_TOKEN, Constants.YELP_TOKEN_SECRET);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new SigningInterceptor(consumer))
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.PETFINDER_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.PETFINDER_LOCATION_QUERY_PARAMETER, location);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

}
