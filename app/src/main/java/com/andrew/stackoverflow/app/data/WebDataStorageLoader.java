package com.andrew.stackoverflow.app.data;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.andrew.stackoverflow.app.data.WebDataStorage;

public class WebDataStorageLoader extends AsyncTaskLoader<String> {

    private WebDataStorage storage = WebDataStorage.getInstance(getContext());

    public WebDataStorageLoader(Context context) {
        super(context);
        storage = WebDataStorage.getInstance(getContext());
    }

    public WebDataStorageLoader(Context context, WebDataStorage storage) {
        super(context);
        this.storage = storage;
    }

    @Override
    public String loadInBackground() {
        return storage.getQuestion();
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}