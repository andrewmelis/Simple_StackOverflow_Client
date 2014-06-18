package com.andrew.stackoverflow.app;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

public class WebIntentService extends IntentService {
    protected static final String ACTION_FETCH_QUESTION = "com.andrew.stackoverflow.app.action.FETCH_QUESTION";

    public static void startActionFetchQuestion(Context context) {
        Intent intent = new Intent(context, WebIntentService.class);
        intent.setAction(ACTION_FETCH_QUESTION);
        context.startService(intent);
    }

    public WebIntentService() {
        super("WebIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FETCH_QUESTION.equals(action)) {
                handleActionFetchQuestion();
            }
        }
    }

    private void handleActionFetchQuestion() {
        String questionJSON = "";

//        questionJSON =

        throw new UnsupportedOperationException("Not yet implemented");
    }
}
