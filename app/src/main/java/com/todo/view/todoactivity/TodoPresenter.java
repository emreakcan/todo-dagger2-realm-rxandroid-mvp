package com.todo.view.todoactivity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.todo.presenter.Presenter;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by emre on 26.11.2017.
 */

public class TodoPresenter extends Presenter {
    private Activity activity;
    @Inject
    Realm realm;

    public TodoPresenter(Realm realm, AppCompatActivity appCompatActivity) {
        this.realm = realm;
        this.activity = appCompatActivity;
    }


}
