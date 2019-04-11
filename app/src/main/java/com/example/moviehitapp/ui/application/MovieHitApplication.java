package com.example.moviehitapp.ui.application;

import com.example.moviehitapp.annotations.Application;
import com.example.moviehitapp.dependency_injection.components.DaggerApplicationComponent;
import com.example.moviehitapp.utils.managers.DatabaseManager;
import com.example.moviehitapp.dependency_injection.components.ApplicationComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

@Application
public class MovieHitApplication extends DaggerApplication {
    @Override
    public void onTerminate() {
        super.onTerminate();
        if (DatabaseManager.isOpen()) DatabaseManager.close();
    }
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        ApplicationComponent component = DaggerApplicationComponent.builder().application(this).build();
        component.inject(this);
        return component;
    }
}
