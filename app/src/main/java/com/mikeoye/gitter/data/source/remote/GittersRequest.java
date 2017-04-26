package com.mikeoye.gitter.data.source.remote;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lami on 4/22/2017.
 */

public class GittersRequest {

    private static GittersRequest INSTANCE = null;

    private Request request;

    private OkHttpClient httpClient;

    private GittersRequest() {
        httpClient = new OkHttpClient();
    }

    private void buildDataRequest(String url) {
        request = new Request.Builder()
                .url(url)
                .build();
    }

    public void fetchGitters(String url, final RequestListener requestListener) {
        buildDataRequest(url);

        httpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        requestListener.onFailure(e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (!response.isSuccessful()) {
                            throw new IOException("Unexpected code" + response);
                        }
                        requestListener.onSuccess(response.body().string());
                    }
                });
    }

    public void fetchGitterProfile(String url, final RequestListener requestListener) {
        buildDataRequest(url);

        httpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        requestListener.onFailure(e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (!response.isSuccessful()) {
                            throw new IOException("Unexcpected code" + response);
                        }
                        requestListener.onSuccess(response.body().string());
                    }
                });
    }

    public interface RequestListener {

        void onSuccess(String responseString);

        void onFailure(String errorMessage);

    }

    public static GittersRequest getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GittersRequest();
        }
        return INSTANCE;
    }

}
