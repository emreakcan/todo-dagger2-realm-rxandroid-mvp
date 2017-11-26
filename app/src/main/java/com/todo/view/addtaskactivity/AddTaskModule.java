package com.todo.view.addtaskactivity;

import android.support.v7.app.AppCompatActivity;

import com.todo.di.modules.ActivityModule;
import com.todo.di.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by emre on 26.11.2017.
 */


@Module
public class AddTaskModule extends ActivityModule {

    public AddTaskModule(AppCompatActivity activity) {
        super(activity);
    }

    @Provides
    @ActivityScope
    public AddTaskPresenter provideAddContactActivityPresenter(Realm realm, AppCompatActivity appCompatActivity) {
        return new AddTaskPresenter(realm, appCompatActivity);
    }

}