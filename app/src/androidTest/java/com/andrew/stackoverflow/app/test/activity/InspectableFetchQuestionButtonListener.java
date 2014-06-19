package com.andrew.stackoverflow.app.test.activity;

import android.view.View;

import com.andrew.stackoverflow.app.activity.FetchQuestionButtonListener;
import com.andrew.stackoverflow.app.service.WebIntentService;

public class InspectableFetchQuestionButtonListener extends FetchQuestionButtonListener {

    public boolean startActionFetchQuestionWasCalled = false;

    @Override
    protected void callStartActionFetchQuestion(View view) {
        startActionFetchQuestionWasCalled = true;
        WebIntentService.startActionFetchQuestion(view.getContext());
    }
}