package com.epicodus.adoptdontshop.models;

import java.util.ArrayList;

public class Friend {
    private String mName;
    private String mAnimal;
    private String mZip;
    private String mSize;
    private String mSex;
    private String mAge;
    private String mEmail;
//    private String mImageURL;
// or    private ArrayList<String> mImageURL = new ArrayList<>();


    public Friend(String name, String animal, String size,
                  String sex, String age, String zip, String email
//            , ArrayList<String> imageURL
    ) {
        this.mName = name;
        this.mAnimal = animal;
        this.mZip = zip;
        this.mSize = size;
        this.mSex = sex;
        this.mAge = age;
        this.mEmail = email;
//        this.mImageURL = imageURL.get(0);
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

//    public ArrayList<String> getImageURL() {
//        return mImageURL;
//    }

}