package com.epicodus.adoptdontshop;

public class Friend {
    private String mName;
    private String mAnimal;
    private String mBreed;
    private String mSize;
    private Character mSex;
    private String mAge;
    private String mPhoto;
    private String mCity;

    public Friend(String name, String animal, String breed, String size,
                  Character sex, String age, String photo, String city) {
        this.mName = name;
        this.mAnimal = animal;
        this.mBreed = breed;
        this.mSize = size;
        this.mSex = sex;
        this.mAge = age;
        this.mPhoto = photo;
        this.mCity = city;
    }

    public String getName() {
        return mName;
    }

    public String getAnimal() {
        return mAnimal;
    }

    public String getBreed() {
        return  mBreed;
    }

    public String getSize() {
        return mSize;
    }

    public Character getSex(){
        return mSex;
    }

    public String getAge() {
        return mAge;
    }

    public String getPhoto() {
        return mPhoto;
    }

    public String getCity() {
        return mCity;
    }
}
