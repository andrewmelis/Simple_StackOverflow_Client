package com.andrew.stackoverflow.app;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* static methods serve as web API facade
    results sent directly to WebDataStorage
    activities then updated through DataSetObservers
 */
public class WebIntentService extends IntentService {
    protected static final String ACTION_FETCH_QUESTION = "com.andrew.stackoverflow.app.action.FETCH_QUESTION";

    public static void startActionFetchQuestion(Context context, Class<? extends WebIntentService> intentService) {
        Intent intent = new Intent(context, intentService);
        intent.setAction(WebIntentService.ACTION_FETCH_QUESTION);
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

    protected void handleActionFetchQuestion() {
        String questionJSON = "temp";

//        WebDataStorage.getInstance(getApplicationContext()).setQuestion(questionJSON);

        throw new UnsupportedOperationException("Not yet implemented");
    }
}
