package com.andrew.stackoverflow.app.test.data;

import android.content.Context;

import com.andrew.stackoverflow.app.data.WebDataStorage;

public class TestingWebDataStorage extends WebDataStorage {

    private static TestingWebDataStorage uniqueInstance;

    public static synchronized TestingWebDataStorage getInstance(Context context) {
        if (uniqueInstance == null) {
            uniqueInstance = new TestingWebDataStorage(context);
        }
        return uniqueInstance;
    }

    private TestingWebDataStorage(Context context) {
        super(context);
    }

    public void clearWebDataStorage() {
        editor.clear();
        editor.commit();

        unregisterAllObservers();
    }

    public void setIntentServiceMethodWasCalled(String methodName) {
        editor.putBoolean(methodName, true);
        editor.commit();
    }

    public boolean getIntentServiceMethodWasCalled(String methodName) {
        return settings.getBoolean(methodName, false);
    }
}