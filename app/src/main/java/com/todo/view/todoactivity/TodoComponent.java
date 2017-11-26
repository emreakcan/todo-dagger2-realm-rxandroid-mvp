package com.todo.view.todoactivity;

import com.todo.di.scopes.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by emre on 26.11.2017.
 */


@ActivityScope
@Subcomponent(
        modules = {
                TodoModule.class
        }
)
public interface TodoComponent {
    void injectTo(ToDoListActivity activity);
}
