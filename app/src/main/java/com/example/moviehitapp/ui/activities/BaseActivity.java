package com.example.moviehitapp.ui.activities;

import android.os.Bundle;

import com.example.moviehitapp.annotations.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

@View
public abstract class BaseActivity extends DaggerAppCompatActivity {

    @LayoutRes
    protected abstract int layoutRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutRes());
        ButterKnife.bind(this);
    }
}