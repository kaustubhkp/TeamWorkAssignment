package com.teamwork.assignment.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.teamwork.assignment.R;
import com.teamwork.assignment.model.ProjectInfo;
import com.teamwork.assignment.ui.activities.ProjectDetailActivity;
import com.teamwork.assignment.utils.Utils;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.teamwork.assignment.ui.activities.ProjectDetailActivity.PROJECT_INFO;
import static com.teamwork.assignment.utils.Constants.ACTIVE;


public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ProjectInfoViewHolder> {

    private List<ProjectInfo> projectInfoList;

    public ProjectListAdapter() {
        projectInfoList = new ArrayList<>();
    }

    public void setProjectInfoList(@NonNull List<ProjectInfo> projects) {
        Objects.requireNonNull(projects);
        projectInfoList.clear();
        projectInfoList.addAll(projects);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProjectInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_info_item,
                parent, false);
        return new ProjectInfoViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectInfoViewHolder holder, int position) {
        holder.bind(projectInfoList.get(position));
    }

    @Override
    public int getItemCount() {
        return projectInfoList.size();
    }

    static class ProjectInfoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_project_icon)
        ImageView projectIcon;
        @BindView(R.id.tv_project_name)
        TextView projectName;
        @BindView(R.id.tv_project_start_date)
        TextView projectStartDate;
        @BindView(R.id.tv_project_end_date)
        TextView projectEndDate;
        @BindView(R.id.iv_project_status)
        ImageView projectStatusIcon;
        @BindView(R.id.relative_layout_item)
        RelativeLayout relativeLayoutItem;

        private final Resources resources;

        ProjectInfoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            resources = itemView.getContext().getResources();
        }

        void bind(ProjectInfo project) {
            Picasso.get().load(project.logo).into(projectIcon);

            projectName.setText(String.format(resources.getString(R.string.project_name),
                    project.name));

            projectStartDate.setText(Html.fromHtml(String.format(resources.getString(R.string.project_start_date),
                    Utils.getDateForLocaleFromUtc(project.startDate, Utils.DD_MM_YYY))));

            projectEndDate.setText(Html.fromHtml(String.format(resources.getString(R.string.project_end_date),
                    Utils.getDateForLocaleFromUtc(project.endDate, Utils.DD_MM_YYY))));

            if (project.status.endsWith(ACTIVE)) {
                Picasso.get().load(R.drawable.project_active_icon)
                        .into(projectStatusIcon);
            } else {
                Picasso.get().load(R.drawable.project_done_icon)
                        .into(projectStatusIcon);
            }

            relativeLayoutItem.setOnClickListener(v -> {
                Context context = v.getContext();
                Intent result = new Intent();
                result.setClass(context, ProjectDetailActivity.class);
                result.putExtra(PROJECT_INFO, Parcels.wrap(project));
                context.startActivity(result);
            });
        }
    }
}
