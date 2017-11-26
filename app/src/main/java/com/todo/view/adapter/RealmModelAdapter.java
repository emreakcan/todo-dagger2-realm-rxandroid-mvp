package com.todo.view.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;
import io.realm.RealmObject;

public class RealmModelAdapter<T extends RealmObject> extends RealmBaseAdapter<T> {

    public RealmModelAdapter(@Nullable OrderedRealmCollection<T> data) {
        super(data);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}