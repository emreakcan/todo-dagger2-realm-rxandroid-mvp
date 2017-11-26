package com.todo.di.components;

import com.todo.MyApplication;
import com.todo.di.modules.ApplicationModule;
import com.todo.view.addtaskactivity.AddTaskComponent;
import com.todo.view.addtaskactivity.AddTaskModule;
import com.todo.view.todoactivity.TodoComponent;
import com.todo.view.todoactivity.TodoModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Morgoth on 14.10.2017.
 */
@Singleton
@Component(modules =
        {
                ApplicationModule.class
        }
)


public interface ApplicationComponent {
    void injectTo(MyApplication injectTo);


    AddTaskComponent plus(AddTaskModule module);
    TodoComponent plus(TodoModule module);
}
