package com.mikeoye.gitter.gitterdetail;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.mikeoye.gitter.R;
import com.mikeoye.gitter.data.source.GitterRepository;
import com.mikeoye.gitter.data.source.remote.GittersRemoteDataSource;
import com.mikeoye.gitter.gitters.GittersContract;
import com.mikeoye.gitter.gitters.GittersFragment;
import com.mikeoye.gitter.gitters.GittersPresenter;
import com.mikeoye.gitter.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class GitterDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gitter_detail);

        ButterKnife.bind(this);

        ActivityUtils.setUpToolBarWithBackEnabled(this, toolbar);
        getSupportActionBar().setTitle("");

        FragmentManager fragmentManager = getSupportFragmentManager();

        GitterDetailFragment gitterDetailFragment =
                (GitterDetailFragment) fragmentManager.findFragmentById(R.id.content_frame);

        if (gitterDetailFragment == null) {
            gitterDetailFragment = GitterDetailFragment.getInstance();
            ActivityUtils.addFragmentToActivity(fragmentManager, gitterDetailFragment, R.id.content_frame);
        }

        GitterDetailContract.Presenter presenter =
                new GitterDetailPresenter(GitterRepository.getInstance(GittersRemoteDataSource.getInstance()), gitterDetailFragment);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
