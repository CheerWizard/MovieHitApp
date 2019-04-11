package com.example.moviehitapp.viewmodels;

import com.example.moviehitapp.annotations.View_Model;
import com.example.moviehitapp.business_logic.repositories.IMovieRepository;
import com.example.moviehitapp.model.MoviesResponse;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

@View_Model
public class MovieResponseViewModel extends ViewModel implements IViewModel {
    //disposable
    private CompositeDisposable disposable;
    //live data
    private final MutableLiveData<MoviesResponse> moviesResponseLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> errorLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();
    //repository
    private final IMovieRepository repository;

    @Inject
    public MovieResponseViewModel(IMovieRepository repository , Disposable disposable) {
        this.repository = repository;
        this.disposable = (CompositeDisposable) disposable;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }

    public MutableLiveData<MoviesResponse> getMoviesResponseLiveData() {
        return moviesResponseLiveData;
    }
    public LiveData<Boolean> getErrorLiveData() {
        return errorLiveData;
    }
    public LiveData<Boolean> getLoadingLiveData() {
        return loadingLiveData;
    }

    public void fetchPopularMovies() {
        loadingLiveData.setValue(true);
        disposable.add(repository.findData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<MoviesResponse>() {
                    @Override
                    public void onSuccess(MoviesResponse value) {
                        errorLiveData.setValue(false);
                        moviesResponseLiveData.setValue(value);
                        loadingLiveData.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        errorLiveData.setValue(true);
                        loadingLiveData.setValue(false);
                    }
                }));
    }

    public void fetchRatedMovies() {
        loadingLiveData.setValue(true);
        disposable.add(repository.findHighestRatedMovies().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<MoviesResponse>() {
                    @Override
                    public void onSuccess(MoviesResponse value) {
                        errorLiveData.setValue(false);
                        moviesResponseLiveData.setValue(value);
                        loadingLiveData.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        errorLiveData.setValue(true);
                        loadingLiveData.setValue(false);
                    }
                }));
    }
}
