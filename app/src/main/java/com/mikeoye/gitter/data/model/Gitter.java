package com.mikeoye.gitter.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lami on 4/21/2017.
 */

public class Gitter {

    @SerializedName("id")
    private String id;

    @SerializedName("login")
    private String username;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("url")
    private String profileUrl;

    @SerializedName("html_url")
    private String profileHtmlUrl;

    public Gitter(String id, String username, String avatarUrl, String profileUrl, String profileHtmlUrl) {
        this.id = id;
        this.username = username;
        this.avatarUrl = avatarUrl;
        this.profileUrl = profileUrl;
        this.profileHtmlUrl = profileHtmlUrl;
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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getProfileHtmlUrl() {
        return profileHtmlUrl;
    }

    public void setProfileHtmlUrl(String profileHtmlUrl) {
        this.profileHtmlUrl = profileHtmlUrl;
    }
}
