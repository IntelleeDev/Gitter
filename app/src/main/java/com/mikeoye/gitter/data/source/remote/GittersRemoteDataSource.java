package com.mikeoye.gitter.data.source.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.mikeoye.gitter.data.model.Gitter;
import com.mikeoye.gitter.data.model.GitterProfile;
import com.mikeoye.gitter.data.model.ServerResponse;
import com.mikeoye.gitter.data.source.GitterDataSource;
import com.mikeoye.gitter.utils.JsonUtil;

import java.util.ArrayList;

/**
 * Created by lami on 4/22/2017.
 */

public class GittersRemoteDataSource implements GitterDataSource {

    private static String BASE_URL = "https://api.github.com/search/users?q=language:java+location:lagos&sort=repositories";

    private static GittersRemoteDataSource INSTANCE = null;

    private GittersRequest gittersRequest;

    private GittersRemoteDataSource() {
        gittersRequest = GittersRequest.getInstance();
    }

    public static GittersRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GittersRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getGitters(@NonNull final LoadGittersCallback callback) {
        gittersRequest.fetchGitters(BASE_URL, new GittersRequest.RequestListener() {
            @Override
            public void onSuccess(String responseString) {
                ServerResponse response =
                        JsonUtil.getObjectFromJsonString(responseString, ServerResponse.class);
                callback.onGittersLoaded(response.getGittersReturned(), Integer.parseInt(response.getTotalCount()));
            }

            @Override
            public void onFailure(String errorMessage) {
                callback.onDataLoadFailed();
            }
        });
    }

    @Override
    public void getGitter(String requestUrl, @NonNull final GetGitterCallback callback) {
        gittersRequest.fetchGitterProfile(requestUrl, new GittersRequest.RequestListener() {
            @Override
            public void onSuccess(String responseString) {
                GitterProfile gitterProfile =
                        JsonUtil.getObjectFromJsonString(responseString, GitterProfile.class);
                callback.onGitterLoaded(gitterProfile);
            }

            @Override
            public void onFailure(String errorMessage) {
                callback.onDataLoadFailed();
            }
        });
    }

}
