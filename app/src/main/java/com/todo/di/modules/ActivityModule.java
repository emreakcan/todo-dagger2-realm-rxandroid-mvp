package com.todo.di.modules;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.todo.di.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by emre on 26.11.2017.
 */

@Module
public abstract class ActivityModule {

    private final AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public AppCompatActivity provideActivity() {
        return activity;
    }

    @Provides
    @ActivityScope
    public Context provideContext() {
        return activity.getBaseContext();
    }
}