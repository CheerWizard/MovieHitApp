package com.example.moviehitapp.business_logic.repositories;

import com.example.moviehitapp.annotations.Repository;

import androidx.lifecycle.LiveData;
import io.reactivex.Single;

@Repository
public interface IRepository<Data , ID> {
//    LiveData<Data> find();
//    MutableLiveData<Data> findDataById(ID id);
    void saveData(Data data);
    void updateData(Data data);
//    void deleteDataById(ID id);
    void deleteData(Data data);
//    void deleteAllData();
}
