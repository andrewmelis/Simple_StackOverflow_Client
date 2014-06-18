package com.andrew.stackoverflow.app.test;

import android.content.Intent;

import com.andrew.stackoverflow.app.WebIntentService;

public class MockWebIntentService extends WebIntentService {

    public static boolean startActionFetchQuestionWasCalled = false;

    @Override
    public void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FETCH_QUESTION.equals(action)) {
                startActionFetchQuestionWasCalled = true;
            }
        }
    }
}