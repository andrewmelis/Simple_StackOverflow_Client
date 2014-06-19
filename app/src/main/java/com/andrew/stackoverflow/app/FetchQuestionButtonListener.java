package com.andrew.stackoverflow.app;

import android.view.View;

public class FetchQuestionButtonListener implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        callStartActionFetchQuestion(view);
    }

    protected void callStartActionFetchQuestion(View view) {
        WebIntentService.startActionFetchQuestion(view.getContext(), WebIntentService.class);
    }


}