package com.andrew.stackoverflow.app.test.service;

import com.andrew.stackoverflow.app.service.WebIntentService;
import com.andrew.stackoverflow.app.test.data.TestingWebDataStorage;

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
        return "testing string";
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