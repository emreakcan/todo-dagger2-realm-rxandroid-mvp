package com.todo.presenter;

/**
 * Created by emre on 26.11.2017.
 */


public abstract class Presenter<V> {

    protected V view;

    public void onViewAttached(V view) {
        this.view = view;
    }

    public void onViewDetached() {
        this.view = null;
    }

    protected boolean isViewAttached() {
        return view != null;
    }

}
