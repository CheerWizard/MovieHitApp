package com.example.moviehitapp.dependency_injection.modules;

import com.example.moviehitapp.annotations.ViewModelKey;
import com.example.moviehitapp.utils.factories.ViewModelFactory;
import com.example.moviehitapp.viewmodels.MovieResponseViewModel;

import javax.inject.Singleton;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Singleton
@Module(includes = {RepositoryModule.class , DisposableModule.class})
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MovieResponseViewModel.class)
    abstract ViewModel bindMovieViewModel(MovieResponseViewModel movieResponseViewModel);
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}