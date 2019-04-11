package com.example.moviehitapp.ui.application;

import com.example.moviehitapp.utils.managers.DatabaseManager;
import com.example.moviehitapp.dependency_injection.components.ApplicationComponent;
import com.example.moviehitapp.dependency_injection.components.DaggerApplicationComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class MovieHitApplication extends DaggerApplication {
    @Override
    public void onTerminate() {
        super.onTerminate();
        DatabaseManager.close();
    }
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        ApplicationComponent component = DaggerApplicationComponent.builder().application(this).build();
        component.inject(this);
        return component;
    }
}
