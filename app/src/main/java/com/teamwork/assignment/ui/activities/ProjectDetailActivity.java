package com.teamwork.assignment.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.teamwork.assignment.R;
import com.teamwork.assignment.model.ProjectInfo;
import com.teamwork.assignment.utils.Utils;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectDetailActivity extends AppCompatActivity {

    public static final String PROJECT_INFO = "project_detail_info";
    @BindView(R.id.iv_project_icon)
    ImageView projectIcon;
    @BindView(R.id.tv_project_name)
    TextView projectName;
    @BindView(R.id.tv_project_start_date)
    TextView projectStartDate;
    @BindView(R.id.tv_project_end_date)
    TextView projectEndDate;
    @BindView(R.id.tv_project_status)
    TextView projectStatus;
    @BindView(R.id.tv_project_description)
    TextView projectDescription;

    private ProjectInfo projectDetailInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        ButterKnife.bind(this);

        android.support.v7.app.ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        projectDetailInfo = Parcels.unwrap(getIntent().getParcelableExtra(PROJECT_INFO));
        initViews();
    }

    private void initViews() {
        Picasso.get().load(projectDetailInfo.logo).into(projectIcon);

        projectName.setText(String.format(getResources().getString(R.string.project_name),
                projectDetailInfo.name));

        projectStartDate.setText(Html.fromHtml(
                String.format(getResources().getString(R.string.project_start_date),
                Utils.getDateForLocaleFromUtc(projectDetailInfo.startDate, Utils.DD_MM_YYY))));

        projectEndDate.setText(Html.fromHtml(
                String.format(getResources().getString(R.string.project_end_date),
                Utils.getDateForLocaleFromUtc(projectDetailInfo.endDate, Utils.DD_MM_YYY))));

        projectStatus.setText(Html.fromHtml(
                String.format(getResources().getString(R.string.project_status),
                projectDetailInfo.status)));

        projectDescription.setText(Html.fromHtml(
                String.format(getResources().getString(R.string.project_description),
                projectDetailInfo.description)));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
