package com.teamwork.assignment.dagger;

import android.content.Context;

import com.teamwork.assignment.net.FetchProjectListService;
import com.teamwork.assignment.presenters.HomePresenter;
import com.teamwork.assignment.presenters.ILoadProjects;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {

    private final ILoadProjects.IView homeView;
    private final Context context;

    public HomeModule(Context context, ILoadProjects.IView homeView) {
        this.context = context;
        this.homeView = homeView;
    }

    @Provides
    @HomeScope
    public ILoadProjects.IPresenter provideHomePresenter(FetchProjectListService fetchProjectListService) {
        return new HomePresenter(context, fetchProjectListService, homeView);
    }
}
