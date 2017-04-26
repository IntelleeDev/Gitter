package com.mikeoye.gitter.gitters;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.mikeoye.gitter.data.model.Gitter;
import com.mikeoye.gitter.data.source.GitterDataSource;
import com.mikeoye.gitter.data.source.GitterRepository;
import com.mikeoye.gitter.utils.Constants;

import java.util.List;

/**
 * Created by lami on 4/21/2017.
 */

public class GittersPresenter implements GittersContract.Presenter {

    private GitterRepository repository;

    private GittersContract.View gittersView;

    public GittersPresenter(GitterRepository repository, GittersContract.View gittersView) {
        this.repository = repository;
        this.gittersView = gittersView;

        gittersView.setPresenter(this);
    }

    @Override
    public void start() {
        gittersView.showLoadingIndicator();
        retrieveListOfGitters();
    }

    @Override
    public void openGitterDetails(@NonNull Gitter gitter) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.Gitter.USERNAME, gitter.getUsername());
        bundle.putString(Constants.Gitter.AVATAR_URL, gitter.getAvatarUrl());
        bundle.putString(Constants.Gitter.API_PROFILE_URL, gitter.getProfileUrl());
        bundle.putString(Constants.Gitter.HTML_PROFILE_URL, gitter.getProfileHtmlUrl());

        gittersView.showGitterDetailsUi(bundle);
    }

    private void retrieveListOfGitters() {
        repository.getGitters(new GitterDataSource.LoadGittersCallback() {
            @Override
            public void onGittersLoaded(List<Gitter> gitters, int totalCount) {
                gittersView.hideLoadingIndicator();
                gittersView.showListOfGitters(gitters);
            }

            @Override
            public void onDataLoadFailed() {
                gittersView.hideLoadingIndicator();
            }
        });
    }

}
