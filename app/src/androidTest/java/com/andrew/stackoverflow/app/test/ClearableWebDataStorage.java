package com.andrew.stackoverflow.app.test;

import android.content.Context;

import com.andrew.stackoverflow.app.WebDataStorage;

public class ClearableWebDataStorage extends WebDataStorage {

    private static ClearableWebDataStorage uniqueInstance;

    public static synchronized ClearableWebDataStorage getInstance(Context context) {
        if (uniqueInstance == null) {
            uniqueInstance = new ClearableWebDataStorage(context);
        }
        return uniqueInstance;
    }

    private ClearableWebDataStorage(Context context) {
        super(context);
    }

    public void clearWebDataStorage() {
        editor.clear();
        editor.commit();

        unregisterAllObservers();
    }

    //used for testing WebIntentService
    public void setIntentServiceMethodWasCalled(String methodName) {
        editor.putBoolean(methodName, true);
        editor.commit();
    }

    public boolean getIntentServiceMethodWasCalled(String methodName) {
        return settings.getBoolean(methodName, false);
    }
}