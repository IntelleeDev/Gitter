package com.mikeoye.gitter.data.source;

import android.support.annotation.NonNull;

import com.mikeoye.gitter.data.model.Gitter;
import com.mikeoye.gitter.data.model.GitterProfile;

import java.util.List;

/**
 * Created by lami on 4/21/2017.
 */

public interface GitterDataSource {

    interface LoadGittersCallback {

        void onGittersLoaded(List<Gitter> gitters, int totalCount);

        void onDataLoadFailed();

    }

    interface GetGitterCallback {

        void onGitterLoaded(GitterProfile gitterProfile);

        void onDataLoadFailed();
    }

    void getGitters(@NonNull LoadGittersCallback callback);

    void getGitter(String requestUrl, @NonNull GetGitterCallback callback);

}
