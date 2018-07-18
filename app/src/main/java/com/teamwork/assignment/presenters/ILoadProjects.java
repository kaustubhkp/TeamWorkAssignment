package com.teamwork.assignment.presenters;

import android.support.annotation.NonNull;

import com.teamwork.assignment.model.ProjectInfo;
import com.teamwork.assignment.mvp.BasePresenter;
import com.teamwork.assignment.mvp.BaseView;
import java.util.List;

public interface ILoadProjects {

     interface IPresenter extends BasePresenter {
         void getProjectList();
     }

     interface IView extends BaseView {
         void showProjectList(@NonNull List<ProjectInfo> projectsList);
         void showProgress(boolean shouldShow);
         void showTryAgain(String message);
    }
}
