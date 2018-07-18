package com.teamwork.assignment.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.teamwork.assignment.R;
import com.teamwork.assignment.app.App;
import com.teamwork.assignment.dagger.DaggerHomeComponent;
import com.teamwork.assignment.dagger.HomeModule;
import com.teamwork.assignment.model.ProjectInfo;
import com.teamwork.assignment.presenters.ILoadProjects;
import com.teamwork.assignment.ui.adapter.ProjectListAdapter;
import java.util.List;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity implements ILoadProjects.IView {
    @BindView(R.id.rv_project_list)
    RecyclerView recyclerView;
    @BindView(R.id.pb_home_progress)
    ProgressBar progressBar;
    @BindView(R.id.tv_try_again)
    TextView tryAgain;

    @Inject
    ILoadProjects.IPresenter presenter;

    private final ProjectListAdapter adapter = new ProjectListAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DaggerHomeComponent.builder()
                .appComponent(((App)getApplication()).getAppComponent())
                .homeModule(new HomeModule(this, this))
                .build().inject(this);

        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void showProjectList(@NonNull List<ProjectInfo> projectInfoList) {
        tryAgain.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        adapter.setProjectInfoList(projectInfoList);
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress(boolean shouldShow) {
        if (shouldShow) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showTryAgain(String message) {
        showMessage(message);
    }

    @Override
    public void showInternetConnectionError(String message) {
        recyclerView.setVisibility(View.GONE);
        showMessage(message);
    }

    @OnClick(R.id.tv_try_again)
    public void onTryAgainClicked() {
        tryAgain.setVisibility(View.GONE);
        presenter.start(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.refresh) {
            presenter.start(this);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showMessage(String message) {
        tryAgain.setVisibility(View.VISIBLE);
        tryAgain.setText(message);
    }
}
