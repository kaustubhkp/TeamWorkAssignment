package com.teamwork.assignment.dagger;


import com.teamwork.assignment.net.FetchProjectListService;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    FetchProjectListService fetchProjectListService();
}
