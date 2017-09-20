package com.epicodus.adoptdontshop;

import java.util.ArrayList;

public class Friend {
    private String mName;
    private String mAnimal;
    private String mLocation;
    private String mSize;
    private String mSex;
    private String mAge;
//    private ArrayList<String> mImageURL = new ArrayList<>();
//    private String mBreed;

    public Friend(String name, String animal, String size,
                  String sex, String age, String location
//            , ArrayList<String> imageURL, String breed
    ) {
        this.mName = name;
        this.mAnimal = animal;
        this.mLocation = location;
        this.mSize = size;
        this.mSex = sex;
        this.mAge = age;
//        this.mImageURL = imageURL;
//        this.mBreed = breed;
    }

    public String getName() {
        return mName;
    }

    public String getAnimal() { return mAnimal; }

    public String getLocation() {
        return mLocation;
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

//    public ArrayList<String> getImageURL() {
//        return mImageURL;
//    }
//
//    public String getBreed() {
//        return  mBreed;
//    }

}
