package com.todo.view.todoactivity;

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
public class TodoModule extends ActivityModule {

    public TodoModule(AppCompatActivity activity) {
        super(activity);
    }

    @Provides
    @ActivityScope
    public TodoPresenter provideAddContactActivityPresenter(Realm realm, AppCompatActivity appCompatActivity) {
        return new TodoPresenter(realm, appCompatActivity);
    }

}