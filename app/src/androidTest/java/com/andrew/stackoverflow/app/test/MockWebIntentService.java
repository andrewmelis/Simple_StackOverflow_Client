package com.andrew.stackoverflow.app.test;

import com.andrew.stackoverflow.app.WebIntentService;

public class MockWebIntentService extends WebIntentService {

    @Override
    protected void handleActionFetchQuestion() {
        String currentMethodName = "handleActionFetchQuestion";
        passMethodNameToStorage(currentMethodName);
        super.handleActionFetchQuestion();
    }

    @Override
    protected String retrieveQuestionFromWebAPI() {
        String currentMethodName = "retrieveQuestionFromWebAPI";
        passMethodNameToStorage(currentMethodName);
        return null;
    }

    @Override
    protected void passFetchedQuestionToStorage(String questionJSON) {
        String currentMethodName = "passFetchedQuestionToStorage";
        passMethodNameToStorage(currentMethodName);
        super.passFetchedQuestionToStorage(questionJSON);
    }

    private void passMethodNameToStorage(String currentMethodName) {
        TestingWebDataStorage storage = TestingWebDataStorage.getInstance(getApplicationContext());
        storage.setIntentServiceMethodWasCalled(currentMethodName);
    }
}