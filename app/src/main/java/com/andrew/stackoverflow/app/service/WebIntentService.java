package com.andrew.stackoverflow.app.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.andrew.stackoverflow.app.network.WebAPI;
import com.andrew.stackoverflow.app.data.WebDataStorage;

import java.net.MalformedURLException;

import java.net.MalformedURLException;

/* static methods serve as web API facade
    results sent directly to WebDataStorage
    activities then updated through DataSetObservers
 */
public class WebIntentService extends IntentService {
    public static final String ACTION_FETCH_QUESTION = "com.andrew.stackoverflow.app.action.FETCH_QUESTION";

    public static void startActionFetchQuestion(Context context) {
        Intent intent = new Intent(context, WebIntentService.class);
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
        String questionJSON = "";
        questionJSON = retrieveQuestionFromWebAPI();
        passFetchedQuestionToStorage(questionJSON);
    }

    protected String retrieveQuestionFromWebAPI() {
        WebAPI webAPI = null;   //ideally, inject this dependency. tough with IntentService
        String retrieved = null;
        try {
            webAPI = new WebAPI();
            retrieved = webAPI.performFetchQuestion();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return retrieved;
    }

    protected void passFetchedQuestionToStorage(String questionJSON) {
        if (questionJSON != null) {
            WebDataStorage.getInstance(getApplicationContext()).setQuestion(questionJSON);
        }
    }
}
