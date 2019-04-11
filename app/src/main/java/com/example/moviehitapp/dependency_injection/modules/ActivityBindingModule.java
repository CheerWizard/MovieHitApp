package com.example.moviehitapp.dependency_injection.modules;

import com.example.moviehitapp.ui.activities.DetailActivity;
import com.example.moviehitapp.ui.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
@Singleton
@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract DetailActivity bindDetailActivity();
}
