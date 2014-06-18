package com.andrew.stackoverflow.app;

import android.view.View;

public class FetchQuestionButtonListener implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        WebIntentService.startActionFetchQuestion(view.getContext());
    }
}