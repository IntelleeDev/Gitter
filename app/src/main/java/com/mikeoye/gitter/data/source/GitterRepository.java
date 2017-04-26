package com.mikeoye.gitter.data.source;

import android.support.annotation.NonNull;

import com.mikeoye.gitter.data.model.Gitter;
import com.mikeoye.gitter.data.model.GitterProfile;
import com.mikeoye.gitter.data.source.remote.GittersRemoteDataSource;
import com.mikeoye.gitter.data.source.remote.GittersRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lami on 4/21/2017.
 */

public class GitterRepository implements GitterDataSource {

    private static GitterRepository INSTANCE = null;

    private boolean cachePopulated = false;

    private Map<String, Gitter> cachedGitters;

    private GittersRemoteDataSource remoteDataSource;

    private GitterRepository(GittersRemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public static GitterRepository getInstance(GittersRemoteDataSource remoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new GitterRepository(remoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getGitters(@NonNull LoadGittersCallback callback) {
        if (cachedGitters != null && cachePopulated) {
            callback.onGittersLoaded(new ArrayList<Gitter>(cachedGitters.values()), cachedGitters.size());
            return;
        }
        if (!cachePopulated) {
            getGittersFromRemoteSource(callback);
        }
    }

    @Override
    public void getGitter(String requestUrl, @NonNull final GetGitterCallback callback) {
        getGitterFromRemoteSource(requestUrl, callback);
    }

    private void getGittersFromRemoteSource(@NonNull final LoadGittersCallback callback) {
        remoteDataSource.getGitters(new LoadGittersCallback() {
            @Override
            public void onGittersLoaded(List<Gitter> gitters, int totalCount) {
                refreshCachedGitters(gitters);
                callback.onGittersLoaded(gitters, totalCount);
            }

            @Override
            public void onDataLoadFailed() {
                callback.onDataLoadFailed();
            }
        });
    }

    private void getGitterFromRemoteSource(String requestUrl, @NonNull final GetGitterCallback callback) {
        remoteDataSource.getGitter(requestUrl, new GetGitterCallback() {
            @Override
            public void onGitterLoaded(GitterProfile gitterProfile) {
                callback.onGitterLoaded(gitterProfile);
            }

            @Override
            public void onDataLoadFailed() {
                callback.onDataLoadFailed();
            }
        });
    }

    private void refreshCachedGitters(List<Gitter> gitters) {
        if (cachedGitters == null) {
            cachedGitters = new LinkedHashMap<>();
        }
        cachedGitters.clear();
        for (Gitter gitter : gitters) {
            cachedGitters.put(gitter.getId(), gitter);
        }
        cachePopulated = true;
    }
    
}
