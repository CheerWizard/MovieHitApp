package com.example.moviehitapp.viewmodels;

import com.example.moviehitapp.annotations.View_Model;
import com.example.moviehitapp.business_logic.repositories.IMovieRepository;
import com.example.moviehitapp.business_logic.data.MoviesResponse;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

@View_Model
public class MovieResponseViewModel extends ViewModel implements IViewModel {
    //repository
    private final IMovieRepository repository;
    //disposable
    private CompositeDisposable disposable;
    //live data
    private MutableLiveData<MoviesResponse> moviesResponseLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> errorLiveData = new MutableLiveData<>();

    @Inject
    public MovieResponseViewModel(IMovieRepository repository , Disposable disposable) {
        this.disposable = (CompositeDisposable) disposable;
        this.repository = repository;
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

    public synchronized void fetchMostPopularMovies() {
        final Single<MoviesResponse> moviesResponseSingle = repository.findPopularMovies();
        dispose(moviesResponseSingle);
    }

    public synchronized void fetchHighestRatedMovies() {
        final Single<MoviesResponse> moviesResponseSingle = repository.findHighestRatedMovies();
        dispose(moviesResponseSingle);
    }

    private synchronized void dispose(@NonNull Single<MoviesResponse> moviesResponseSingle) {
        loadingLiveData.setValue(true);
        disposable.add(moviesResponseSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<MoviesResponse>() {
                    @Override
                    public void onSuccess(MoviesResponse value) {
                        repository.saveData(value);
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
