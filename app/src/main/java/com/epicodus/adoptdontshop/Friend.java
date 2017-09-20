package com.epicodus.adoptdontshop;

public class Friend {
    private String mName;
    private String mAnimal;
    private String mBreed;
    private String mSize;
    private String mSex;
    private String mAge;
    private String mPhoto;
    private String mLocation;

    public Friend(String name, String animal, String breed, String size,
                  String sex, String age, String photo, String location) {
        this.mName = name;
        this.mAnimal = animal;
        this.mBreed = breed;
        this.mSize = size;
        this.mSex = sex;
        this.mAge = age;
        this.mPhoto = photo;
        this.mLocation = location;
    }

    public String getName() {
        return mName;
    }

    public String getAnimal() { return mAnimal; }

    public String getBreed() {
        return  mBreed;
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

    public String getPhoto() {
        return mPhoto;
    }

    public String getLocation() {
        return mLocation;
    }

}
