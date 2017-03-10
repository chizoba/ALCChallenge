package com.github.chizoba.alcchallenge;

/**
 * Created by Chizoba on 3/8/2017.
 */

public class User {
    String username;
    String avatarUrl;
    String profileUrl;

    public User(String username, String avatarUrl, String profileUrl){
        this.username = username;
        this.avatarUrl = avatarUrl;
        this.profileUrl = profileUrl;
    }
}
