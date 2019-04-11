package com.example.moviehitapp.dependency_injection.modules;

import javax.inject.Singleton;

import dagger.Module;

@Singleton
@Module(includes = {DaoModule.class})
public class StorageModule {
}
