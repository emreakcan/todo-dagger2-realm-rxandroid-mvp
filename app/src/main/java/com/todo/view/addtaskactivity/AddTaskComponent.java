package com.todo.view.addtaskactivity;

import com.todo.di.scopes.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by emre on 26.11.2017.
 */

@ActivityScope
@Subcomponent(
        modules = {
                AddTaskModule.class
        }
)
public interface AddTaskComponent {
    void injectTo(AddTaskActivity activity);
}
