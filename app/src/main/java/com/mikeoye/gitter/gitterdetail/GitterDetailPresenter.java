package com.mikeoye.gitter.gitterdetail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.mikeoye.gitter.BasePresenter;
import com.mikeoye.gitter.data.model.GitterProfile;
import com.mikeoye.gitter.data.source.GitterDataSource;
import com.mikeoye.gitter.data.source.GitterRepository;
import com.mikeoye.gitter.utils.Constants;

/**
 * Created by lami on 4/21/2017.
 */

public class GitterDetailPresenter implements GitterDetailContract.Presenter {

    private static String MESSAGE_PREFIX = "Check out this awesome developer";

    private static String USERNAME;

    private static String AVATAR_URL;

    private static String API_PROFILE_URL;

    private static String HTML_PROFILE_URL;

    private GitterRepository repository;

    private GitterDetailContract.View gitterDetailView;

    public GitterDetailPresenter(GitterRepository repository, GitterDetailContract.View gitterDetailView) {
        this.repository = repository;
        this.gitterDetailView = gitterDetailView;

        gitterDetailView.setPresenter(this);
    }

    @Override
    public void start() {
        sendProfileRequest();
    }

    private void sendProfileRequest() {
        retrievePassedState();
        gitterDetailView.showLoadingIndicator();

        gitterDetailView.displayProfileImage(AVATAR_URL);
        repository.getGitter(API_PROFILE_URL, new GitterDataSource.GetGitterCallback() {

            @Override
            public void onGitterLoaded(GitterProfile gitterProfile) {
                gitterDetailView.hideLoadingIndicator();
                gitterDetailView.bindProfileDataToFields(gitterProfile);
            }

            @Override
            public void onDataLoadFailed() {
                gitterDetailView.hideLoadingIndicator();
            }
        });
    }

    private void retrievePassedState() {
        Bundle bundle = gitterDetailView.getBundleExtra();
        USERNAME = bundle.getString(Constants.Gitter.USERNAME);
        AVATAR_URL = bundle.getString(Constants.Gitter.AVATAR_URL);
        API_PROFILE_URL = bundle.getString(Constants.Gitter.API_PROFILE_URL);
        HTML_PROFILE_URL = bundle.getString(Constants.Gitter.HTML_PROFILE_URL);
    }

    @Override
    public void shareUserProfile() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, buildShareIntentMessage());

        gitterDetailView.showShareDialog(shareIntent, "Share");
    }

    @Override
    public void createProfileLinkIntent() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(HTML_PROFILE_URL));
        gitterDetailView.openGitterProfileLinkInBrowser(browserIntent);
    }

    private String buildShareIntentMessage() {
        return new StringBuilder()
                .append(MESSAGE_PREFIX)
                .append(" @")
                .append(USERNAME)
                .append(", ")
                .append(HTML_PROFILE_URL)
                .append(".")
                .toString();
    }
}
