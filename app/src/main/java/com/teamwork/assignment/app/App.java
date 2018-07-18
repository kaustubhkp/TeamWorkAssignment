package com.teamwork.assignment.app;

import android.app.Application;

import com.teamwork.assignment.dagger.AppComponent;
import com.teamwork.assignment.dagger.AppModule;
import com.teamwork.assignment.dagger.DaggerAppComponent;

import net.danlew.android.joda.JodaTimeAndroid;


public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                            .appModule(new AppModule(this))
                            .build();
        JodaTimeAndroid.init(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
