package com.mikeoye.gitter.gitters;

import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.mikeoye.gitter.R;
import com.mikeoye.gitter.data.source.GitterRepository;
import com.mikeoye.gitter.data.source.remote.GittersRemoteDataSource;
import com.mikeoye.gitter.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class GittersActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ActivityUtils.setUpToolbar(this, toolbar);
        getSupportActionBar().setTitle("");

        FragmentManager fragmentManager = getSupportFragmentManager();
        GittersFragment gittersFragment =
                (GittersFragment) fragmentManager.findFragmentById(R.id.content_frame);

        if (gittersFragment == null) {
            gittersFragment = GittersFragment.getInstance();
            ActivityUtils.addFragmentToActivity(fragmentManager, gittersFragment, R.id.content_frame);
        }

        GittersContract.Presenter presenter =
                new GittersPresenter(GitterRepository.getInstance(GittersRemoteDataSource.getInstance()), gittersFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
