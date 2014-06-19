package com.andrew.stackoverflow.app.test;

import com.andrew.stackoverflow.app.WebIntentService;

public class MockWebIntentService extends WebIntentService {

    public boolean handleActionFetchQuestionWasCalled = false;

    @Override
    protected void handleActionFetchQuestion() {
        handleActionFetchQuestionWasCalled = true;
    }
}