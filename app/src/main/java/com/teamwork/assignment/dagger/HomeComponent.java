package com.teamwork.assignment.dagger;


import com.teamwork.assignment.ui.activities.HomeActivity;

import dagger.Component;

@HomeScope
@Component(modules = HomeModule.class, dependencies = AppComponent.class)
public interface HomeComponent {
    void inject(HomeActivity homeActivity);
}
