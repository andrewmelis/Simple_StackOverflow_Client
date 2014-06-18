package com.andrew.stackoverflow.app.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.TextView;

import com.andrew.stackoverflow.app.MainActivity;
import com.andrew.stackoverflow.app.R;
import com.andrew.stackoverflow.app.WebDataStorage;

public class MainActivity_Test extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity activity;
    private TextView questionFetchedText;
    private Button fetchQuestionButton;

    public MainActivity_Test() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        activity = getActivity();

        questionFetchedText = (TextView) activity.findViewById(R.id.question_fetched_text);
        fetchQuestionButton = (Button) activity.findViewById(R.id.fetch_question_button);
    }

    public void testFetchTextInitNotNull() {
        assertNotNull(questionFetchedText);
    }

    public void testFetchTextInitToPreviouslyRetrievedQuestionFromDB() {
        WebDataStorage.getInstance(activity).setQuestion("test question");

        killAndRestartActivity();

        assertEquals("test question", questionFetchedText.getText().toString());
    }

    public void testRotate() {
        assertFalse(true);
    }

    public void testFetchButtonInitNotNull() {
        assertNotNull(fetchQuestionButton);
    }

    public void testPressingFetchButtonCallsStaticWebIntentService() {
//        fetchQuestionButton.callOnClick();

        getInstrumentation().waitForIdleSync();

        //how do I test this?
//        Mockito.verify(activity).fetchQuestion()          //requires fetchQuestion public...
        assertFalse(true);
    }

    private void killAndRestartActivity() {
        activity.finish();
        activity = this.getActivity();
    }
}