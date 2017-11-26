package com.todo.view.addtaskactivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.todo.BaseActivity;
import com.todo.R;
import com.todo.di.components.ApplicationComponent;
import com.todo.model.Task;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class AddTaskActivity extends BaseActivity implements AddTaskView {
    @BindView(R.id.add_title)
    EditText mTitleEditText;
    @BindView(R.id.add_description)
    EditText mDescriptionEditText;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @Inject
    AddTaskPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onViewAttached(this);

        setContentView(R.layout.activity_add_task);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @OnClick(R.id.fab)
    void clickFab() {
        String title = mTitleEditText.getText().toString();
        String description = mDescriptionEditText.getText().toString();
        presenter.addTask(new Task(title, description));
    }

    @Override
    protected void injectDependencies(ApplicationComponent component) {
        component.plus(new AddTaskModule(this)).injectTo(this);

    }

    @Override
    public void onTaskAdded() {
        finish();
    }
}
