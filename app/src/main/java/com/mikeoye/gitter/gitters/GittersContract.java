package com.mikeoye.gitter.gitters;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.mikeoye.gitter.BasePresenter;
import com.mikeoye.gitter.BaseView;
import com.mikeoye.gitter.data.model.Gitter;

import java.util.List;

/**
 * Created by lami on 4/21/2017.
 */

public interface GittersContract {

    public interface View extends BaseView<Presenter> {

        void showGitterDetailsUi(@NonNull Bundle bundle);

        void showListOfGitters(@NonNull List<Gitter> gitters);

        void showTotalCount(int totalCount);

        void showLoadingIndicator();

        void hideLoadingIndicator();

    }

    public interface Presenter extends BasePresenter {

        void openGitterDetails(@NonNull Gitter gitter);

    }

}
