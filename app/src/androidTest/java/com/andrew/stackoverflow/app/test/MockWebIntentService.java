package com.andrew.stackoverflow.app.test;

import com.andrew.stackoverflow.app.WebDataStorage;
import com.andrew.stackoverflow.app.WebIntentService;

public class MockWebIntentService extends WebIntentService {

    @Override
    public void handleActionFetchQuestion() {
        String currentMethodName = "handleActionFetchQuestion";
        ClearableWebDataStorage storage = ClearableWebDataStorage.getInstance(getApplicationContext());
        storage.setIntentServiceMethodWasCalled(currentMethodName);

    }

}