package com.mikeoye.gitter.gitterdetail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikeoye.gitter.R;
import com.mikeoye.gitter.data.model.GitterProfile;
import com.mikeoye.gitter.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GitterDetailFragment extends Fragment implements GitterDetailContract.View {

    private static String LOG_TAG = GitterDetailFragment.class.getSimpleName();

    @BindView(R.id.gitter_bio_tv)
    TextView bioTextView;

    @BindView(R.id.email_tv)
    TextView emailTextView;

    @BindView(R.id.username_tv)
    TextView usernameTextView;

    @BindView(R.id.location_tv)
    TextView locationTextView;

    @BindView(R.id.fullname_tv)
    TextView fullnameTextView;

    @BindView(R.id.avatar_image_iv)
    ImageView avatarImageView;

    @BindView(R.id.num_of_followers)
    TextView numOffollowersTextView;

    @BindView(R.id.num_of_following)
    TextView numOffollowingTextView;

    @BindView(R.id.num_of_repositories)
    TextView numOfRepositoriesTextView;

    @BindView(R.id.progress_bar)
    ProgressBar loadingIndicator;

    @BindView(R.id.browser_link)
    TextView browerLinkTextView;

    private Activity parentActivity;

    private String profileLinkUrl;

    private GitterDetailContract.Presenter presenter;

    public GitterDetailFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }

    public static GitterDetailFragment getInstance() {
        return new GitterDetailFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gitter_detail, container, false);
        ButterKnife.bind(this, view);
        parentActivity = getActivity();
        retrieveIntentExtra();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.detail_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.share) {
            presenter.shareUserProfile();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void openGitterProfileLinkInBrowser(final Intent browserIntent) {
        parentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                startActivity(browserIntent);
            }
        });
    }

    @Override
    public void bindProfileDataToFields(final GitterProfile gitterProfile) {
        parentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                bindWidgetsWithData(gitterProfile);
            }
        });
    }

    @Override
    public void displayProfileImage(final String url) {
        parentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Glide.with(parentActivity).load(url).centerCrop().into(avatarImageView);
            }
        });
    }

    @Override
    public void showShareDialog(final Intent shareIntent, final String title) {
        parentActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                startActivity(Intent.createChooser(shareIntent, title));
            }
        });
    }

    @Override
    public Bundle getBundleExtra() {
        return retrieveIntentExtra();
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

    @Override
    public void setPresenter(GitterDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    private void bindWidgetsWithData(GitterProfile gitterProfile) {
        bioTextView.setText(gitterProfile.getBio());
        emailTextView.setText(gitterProfile.getEmail());
        locationTextView.setText(gitterProfile.getLocation());
        usernameTextView.setText(gitterProfile.getUsername());
        fullnameTextView.setText(gitterProfile.getFullname());
        numOffollowersTextView.setText(gitterProfile.getFollowers());
        numOffollowingTextView.setText(gitterProfile.getFollowing());
        numOfRepositoriesTextView.setText(gitterProfile.getRepositories());

        profileLinkUrl = gitterProfile.getProfileUrl();
        browerLinkTextView.setText(getString(R.string.view_profile_on_github));
        browerLinkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.createProfileLinkIntent();
            }
        });
    }

    private Bundle retrieveIntentExtra() {
        Bundle bundle = new Bundle();
        Intent intent = parentActivity.getIntent();
        if (intent != null && intent.hasExtra(Constants.PROFILE_BUNDLE)) {
            bundle = intent.getBundleExtra(Constants.PROFILE_BUNDLE);
        }
        return bundle;
    }

}
