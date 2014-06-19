package com.andrew.stackoverflow.app.activity;

import android.view.View;

import com.andrew.stackoverflow.app.service.WebIntentService;

public class FetchQuestionButtonListener implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        callStartActionFetchQuestion(view);
    }

    protected void callStartActionFetchQuestion(View view) {
        WebIntentService.startActionFetchQuestion(view.getContext());
    }


}