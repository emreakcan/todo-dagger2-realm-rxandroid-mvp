package com.todo.view.addtaskactivity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.todo.model.Task;
import com.todo.presenter.Presenter;

import io.realm.Realm;

/**
 * Created by emre on 26.11.2017.
 */

public class AddTaskPresenter extends Presenter<AddTaskView> {
    private Activity activity;
    private Realm realm;


    public AddTaskPresenter(Realm realm, AppCompatActivity appCompatActivity) {
        this.activity = appCompatActivity;
        this.realm = realm;
    }


    public void addTask(final Task task) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(task);
                view.onTaskAdded();
            }
        });
    }
}
