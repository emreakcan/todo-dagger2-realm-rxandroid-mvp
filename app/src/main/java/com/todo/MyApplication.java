package com.todo;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;


import com.todo.di.components.ApplicationComponent;
import com.todo.di.components.DaggerApplicationComponent;
import com.todo.di.modules.ApplicationModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by emre on 26.11.2017.
 */


public class MyApplication extends Application {

    private ApplicationComponent component;

    @NonNull
    public static ApplicationComponent getComponent(Context context) {
        return ((MyApplication) context.getApplicationContext()).getComponent();
    }

    @NonNull
    public ApplicationComponent getComponent() {
        return component;
    }


    @Override
    public void onCreate() {
        super.onCreate();


        this.component = prepareApplicationComponent().build();
        this.component.injectTo(this);

        Realm.init(getApplicationContext());

        // create your Realm configuration
        RealmConfiguration config = new RealmConfiguration.
                Builder().
                deleteRealmIfMigrationNeeded().
                build();
        Realm.setDefaultConfiguration(config);
    }

    @NonNull
    protected DaggerApplicationComponent.Builder prepareApplicationComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this));
    }

}
