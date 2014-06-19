package com.andrew.stackoverflow.app.test;

import android.view.View;

import com.andrew.stackoverflow.app.FetchQuestionButtonListener;
import com.andrew.stackoverflow.app.WebIntentService;

public class InspectableFetchQuestionButtonListener extends FetchQuestionButtonListener {

    public boolean startActionFetchQuestionWasCalled = false;

    @Override
    protected void callStartActionFetchQuestion(View view) {
        startActionFetchQuestionWasCalled = true;
        WebIntentService.startActionFetchQuestion(view.getContext());
    }
}