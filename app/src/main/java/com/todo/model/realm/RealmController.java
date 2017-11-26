package com.todo.model.realm;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.todo.model.Task;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Marius-Andrei Rosu on 02/09/16.
 */

public class RealmController {
    private static RealmController mInstance;
    private final Realm mRealm;

    public RealmController(Application application) {
        mRealm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {
        if (mInstance == null) {
            mInstance = new RealmController(fragment.getActivity().getApplication());
        }
        return mInstance;
    }

    public static RealmController with(Activity activity) {
        if (mInstance == null) {
            mInstance = new RealmController(activity.getApplication());
        }
        return mInstance;
    }

    public static RealmController with(Application application) {
        if (mInstance == null) {
            mInstance = new RealmController(application);
        }
        return mInstance;
    }

    public static RealmController getInstance() {
        return mInstance;
    }

    public Realm getRealm() {
        return mRealm;
    }

    //Find all objects in the {@link Task.class}.
    public RealmResults<Task> getTasks() {
        return mRealm.where(Task.class).findAll();
    }

    //Query a single item with the given id.
    public Task getTask(String id) {
        return mRealm.where(Task.class).equalTo(RealmUtils.TASK_ID, id).findFirst();
    }

    public void addTask(final Task task) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mRealm.copyToRealm(task);
            }
        });
    }

    public void removeTask(final long id) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Task> result = realm.where(Task.class).equalTo(RealmUtils.TASK_ID, id).findAll();
                result.deleteAllFromRealm();
            }
        });
    }
}
