package com.mikeoye.gitter.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lami on 4/23/2017.
 */

public class ServerResponse {

    @SerializedName("total_count")
    private String totalCount;

    @SerializedName("incomplete_results")
    private String incompleteResults;

    @SerializedName("items")
    private List<Gitter> gittersReturned;

    public ServerResponse(String totalCount, String incompleteResults, List<Gitter> gittersReturned) {
        this.totalCount = totalCount;
        this.incompleteResults = incompleteResults;
        this.gittersReturned = new ArrayList<>(gittersReturned);
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String isIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(String incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<Gitter> getGittersReturned() {
        return gittersReturned;
    }

    public void setGittersReturned(List<Gitter> gittersReturned) {
        this.gittersReturned = gittersReturned;
    }

}
