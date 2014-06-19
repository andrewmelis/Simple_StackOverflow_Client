package com.andrew.stackoverflow.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.preference.PreferenceManager;

public class WebDataStorage {

    private static WebDataStorage uniqueInstance;

    protected final SharedPreferences settings;
    protected final SharedPreferences.Editor editor;
    protected final DataSetObservable observable;

    public static synchronized WebDataStorage getInstance(Context context) {
        if (uniqueInstance == null) {
            uniqueInstance = new WebDataStorage(context);
        }
        return uniqueInstance;
    }

    protected WebDataStorage(Context context) {
        settings = PreferenceManager.getDefaultSharedPreferences(context);
        editor = settings.edit();
        observable = new DataSetObservable();
    }

    public void setQuestion(String questionToSet) {
        editor.putString("question", questionToSet);
        editor.commit();
        observable.notifyChanged();
    }

    public String getQuestion() {
        return settings.getString("question", "nothing retrieved yet!");
    }

    public void registerObserver(DataSetObserver observer) {
        observable.registerObserver(observer);
    }

    public void unregisterObserver(DataSetObserver observer) {
        observable.unregisterObserver(observer);
    }

    public void unregisterAllObservers() {
        observable.unregisterAll();
    }
}