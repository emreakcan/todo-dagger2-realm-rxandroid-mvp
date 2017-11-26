package com.todo.view.adapter;

import com.todo.model.Task;

import io.realm.RealmResults;


public class RealmToDoAdapter extends RealmModelAdapter<Task> {


    public RealmToDoAdapter( RealmResults<Task> tasks) {
        super(tasks);
    }

}
