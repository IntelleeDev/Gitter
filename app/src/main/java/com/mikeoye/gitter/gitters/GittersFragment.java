package com.mikeoye.gitter.gitters;


import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mikeoye.gitter.R;
import io.codetail.animation.ViewAnimationUtils;
import com.mikeoye.gitter.data.model.Gitter;
import com.mikeoye.gitter.gitterdetail.GitterDetailActivity;
import com.mikeoye.gitter.utils.ActivityUtils;
import com.mikeoye.gitter.utils.Constants;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GittersFragment extends Fragment implements GittersContract.View {

    @BindView(R.id.root_layout)
    FrameLayout rootLayout;

    @BindView(R.id.progress_bar)
    ProgressBar loadingIndicator;

    @BindView(R.id.feat_gitter_iv)
    ImageView featuredGitterImageView;

    @BindView(R.id.feat_gitter_profile_link)
    TextView featuredGitterProfileLink;

    @BindView(R.id.feat_gitter_username)
    TextView featuredGitterUsernameTextView;

    private Activity parentActivity;

    private RecyclerView recyclerView;

    private GittersAdapter gittersAdapter;

    private GittersContract.Presenter presenter;

    public GittersFragment() {
        // Required empty public constructor
    }

    public static GittersFragment getInstance() {
        GittersFragment gittersFragment = new GittersFragment();
        return gittersFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gitters, container, false);

        ButterKnife.bind(this, view);
        parentActivity = getActivity();

        recyclerView = ButterKnife.findById(view, R.id.gitters_recycler_view);

        initializeWidgets();

        return view;
    }

    @Override
    public void setPresenter(GittersContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showGitterDetailsUi(@NonNull Bundle bundle) {
        Intent intent = new Intent(parentActivity, GitterDetailActivity.class);
        intent.putExtra(Constants.PROFILE_BUNDLE, bundle);
        parentActivity.startActivity(intent);
    }

    @Override
    public void showListOfGitters(@NonNull final List<Gitter> gitters) {
        parentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List dataSet = new ArrayList<>(gitters);
                Gitter featuredDeveloper = (Gitter) dataSet.remove(0);
                bindFeaturedDeveloperData(featuredDeveloper);
                gittersAdapter.replaceData(dataSet);
            }
        });
    }

    @Override
    public void showTotalCount(int totalCount) {
        // Stub
    }

    @Override
    public void showLoadingIndicator() {
        parentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadingIndicator.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void hideLoadingIndicator() {
        parentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadingIndicator.setVisibility(View.GONE);
            }
        });
    }

    private void initializeWidgets() {
        gittersAdapter = new GittersAdapter(parentActivity, new ArrayList<Gitter>());
        gittersAdapter.setOnItemClickListener(new GittersAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Gitter gitter = gittersAdapter.getGitters().get(position);
                presenter.openGitterDetails(gitter);
            }
        });
        recyclerView.setAdapter(gittersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(parentActivity));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void bindFeaturedDeveloperData(Gitter gitter) {
        Glide.with(parentActivity).load(gitter.getAvatarUrl()).centerCrop().into(featuredGitterImageView);
        featuredGitterUsernameTextView.setText(gitter.getUsername());

        setUpFeaturedDeveloperClickListener(gitter);
    }

    private void setUpFeaturedDeveloperClickListener(final Gitter gitter) {
        featuredGitterProfileLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.openGitterDetails(gitter);
            }
        });
    }
}
