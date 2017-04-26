package com.mikeoye.gitter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mikeoye.gitter.R;
import com.mikeoye.gitter.data.model.Gitter;
import com.mikeoye.gitter.data.model.ServerResponse;
import com.mikeoye.gitter.data.source.GitterDataSource;
import com.mikeoye.gitter.data.source.PreferenceHelper;
import com.mikeoye.gitter.data.source.remote.GittersRemoteDataSource;
import com.mikeoye.gitter.gitters.GittersActivity;
import com.mikeoye.gitter.utils.ActivityUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ButterKnife.bind(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick(R.id.btn_get_started)
    public void launchGittersActivity() {
        ActivityUtils.switchToActivityAndDestroyCurrent(this, GittersActivity.class);
    }

}
