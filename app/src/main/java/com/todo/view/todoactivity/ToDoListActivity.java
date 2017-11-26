package com.todo.view.todoactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.todo.BaseActivity;
import com.todo.R;
import com.todo.di.components.ApplicationComponent;
import com.todo.model.Task;
import com.todo.model.realm.RealmController;
import com.todo.view.adapter.RealmToDoAdapter;
import com.todo.view.adapter.ToDoAdapter;
import com.todo.view.addtaskactivity.AddTaskActivity;
import com.todo.view.utils.ItemTouchHelperCallback;
import com.todo.view.utils.ToDoItemDecoration;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class ToDoListActivity extends BaseActivity implements ToDoAdapter.OnItemActionListener {
    @BindView(R.id.main_container)
    CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.main_recycler)
    RecyclerView mRecyclerView;
    private ToDoAdapter mAdapter;

    @Inject
    TodoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onViewAttached(this);

        setContentView(R.layout.activity_main);
        setupRecyclerView();

    }

    public void setupRecyclerView(){
        mAdapter = new ToDoAdapter(this);
        creatingObserables();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(mAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        RecyclerView.ItemDecoration dividerItemDecoration = new ToDoItemDecoration(ResourcesCompat.getDrawable(getResources(), R.drawable.divider, null));
        mRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    protected void injectDependencies(ApplicationComponent component) {
        component.plus(new TodoModule(this)).injectTo(this);

    }


    @OnClick(R.id.main_add)
    void setAddClick() {

        Intent addTaskIntent = new Intent(ToDoListActivity.this, AddTaskActivity.class);
        startActivity(addTaskIntent);
    }

    private void creatingObserables() {

        final Observable<RealmResults<Task>> listObserable = Observable.just(RealmController.with(this).getTasks());
        listObserable.subscribe(new Observer<RealmResults<Task>>() {

            @Override
            public void onError(Throwable e) {
                Log.d("TODOACTV", "onError()", e);
            }

            @Override
            public void onComplete() {
            }

            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(RealmResults<Task> tasks) {
                mAdapter.setRealmAdapter(new RealmToDoAdapter(tasks));
            }

        });

    }

    @Override
    public void onItemClick(RealmObject item) {

    }

    @Override
    public void onItemDismiss(RealmObject item, int position) {

    }


}
