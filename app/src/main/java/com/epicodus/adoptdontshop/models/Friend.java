package com.epicodus.adoptdontshop.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Friend {
    String mName;
    String mAnimal;
    String mZip;
    String mSize;
    String mSex;
    String mAge;
    String mEmail;
    ArrayList<String> mImageURL = new ArrayList<>();

    public Friend() {}

    public Friend(String name, String animal, String size,
                  String sex, String age, String zip, String email, ArrayList<String> imageURL)
    {

        this.mName = name;
        this.mAnimal = animal;
        this.mZip = zip;
        this.mSize = size;
        this.mSex = sex;
        this.mAge = age;
        this.mEmail = email;
        this.mImageURL = imageURL;
//        this.mImageURL = getLargeImageURL(imageURL);
    }

    public String getName() {
        return mName;
    }

    public String getAnimal() { return mAnimal; }

    public String getZip() {
        return mZip;
    }

    public String getSize() {
        return mSize;
    }

    public String getSex(){
        return mSex;
    }

    public String getAge() {
        return mAge;
    }

    public String getEmail() {
        return mEmail;
    }

    public ArrayList<String> getImageURL() {
        return mImageURL;
    }

//    public String getLargeImageUrl(ArrayList<String> imageURL) {
//        String largeImageUrl = imageURL.substring(0, imageURL.length() - 34).concat("o.jpg");
//        return largeImageUrl;
//    }

}