package com.mikeoye.gitter.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lami on 4/23/2017.
 */

public class GitterProfile {

    @SerializedName("id")
    private String id;

    @SerializedName("login")
    private String username;

    @SerializedName("name")
    private String fullname;

    @SerializedName("html_url")
    private String profileUrl;

    @SerializedName("public_repos")
    private String repositories;

    @SerializedName("bio")
    private String bio;

    @SerializedName("location")
    private String location;

    @SerializedName("email")
    private String email;

    @SerializedName("following")
    private String following;

    @SerializedName("followers")
    private String followers;

    public GitterProfile(String id, String username, String fullname,
                         String profileUrl, String repositories, String bio,
                         String location, String email, String following, String followers)
    {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.profileUrl = profileUrl;
        this.repositories = repositories;
        this.bio = bio;
        this.location = location;
        this.email = email;
        this.following = following;
        this.followers = followers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getRepositories() {
        return repositories;
    }

    public void setRepositories(String repositories) {
        this.repositories = repositories;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }
}
