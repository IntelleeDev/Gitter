package com.mikeoye.gitter.gitterdetail;

import android.content.Intent;
import android.os.Bundle;

import com.mikeoye.gitter.BasePresenter;
import com.mikeoye.gitter.BaseView;
import com.mikeoye.gitter.data.model.GitterProfile;

/**
 * Created by lami on 4/21/2017.
 */

public interface GitterDetailContract {

    interface View extends BaseView<Presenter> {

        Bundle getBundleExtra();

        void showLoadingIndicator();

        void hideLoadingIndicator();

        void displayProfileImage(String url);

        void openGitterProfileLinkInBrowser(Intent browserIntent);

        void showShareDialog(Intent shareIntent, String title);

        void bindProfileDataToFields(GitterProfile gitterProfile);

    }

    interface Presenter extends BasePresenter {

        void shareUserProfile();

        void createProfileLinkIntent();

    }
}
