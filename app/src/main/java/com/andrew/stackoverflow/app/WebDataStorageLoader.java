package com.andrew.stackoverflow.app;

import android.content.AsyncTaskLoader;
import android.content.Context;

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