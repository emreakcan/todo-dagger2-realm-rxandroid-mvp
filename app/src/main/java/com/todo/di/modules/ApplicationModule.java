package com.todo.di.modules;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;


import com.todo.MyApplication;
import com.todo.di.ForApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by emre on 26.11.2017.
 */
@Module
public class ApplicationModule {

    @NonNull
    private final MyApplication app;

    public ApplicationModule(@NonNull MyApplication app) {
        this.app = app;
    }

    @NonNull
    @Provides
    @Singleton
    public Application provideApplication() {
        return app;
    }

    @NonNull
    @Provides
    @Singleton
    @ForApplication
    public Context provideApplicationContext() {
        return app.getApplicationContext();
    }


    @NonNull
    @Provides
    @Singleton
    public Realm provideRealm() {
        return Realm.getDefaultInstance();
    }



}
