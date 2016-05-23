package com.example.afanasenko.mypictures;

/**
 * Created by Afanasenko on 22.05.2016.
 */
public enum InstagramAppTags {

    TAG_USERNAME("TAG_USERNAME"),
    TAG_FOLLOWS("TAG_FOLLOWS"),
    TAG_FOLLOWED_BY("TAG_FOLLOWED_BY"),
    TAG_PROFILE_PICTURE("TAG_PROFILE_PICTURE"),
    TAG_MEDIA("TAG_MEDIA");

    public String name;

    InstagramAppTags(String name) {
        this.name = name;
    }

    public String toString() {
       return name;
    }
}
