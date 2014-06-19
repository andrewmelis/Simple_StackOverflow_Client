package com.andrew.stackoverflow.app.test;

import android.content.Loader;
import android.content.pm.ActivityInfo;
import android.preference.PreferenceManager;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.TextView;

import com.andrew.stackoverflow.app.MainActivity;
import com.andrew.stackoverflow.app.R;
import com.andrew.stackoverflow.app.WebDataStorage;
import com.andrew.stackoverflow.app.WebDataStorageLoader;

public class MainActivity_Test extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity activity;
    private TextView fetchedQuestionText;
    private Button fetchQuestionButton;
    private TestingWebDataStorage storage;

    public MainActivity_Test() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        activity = getActivity();
        storage = TestingWebDataStorage.getInstance(activity);

        fetchedQuestionText = (TextView) activity.findViewById(R.id.fetched_question_text);
        fetchQuestionButton = (Button) activity.findViewById(R.id.fetch_question_button);
    }

    @Override
    public void tearDown() throws Exception {
        storage.clearWebDataStorage();
        super.tearDown();
    }

    public void testFetchTextInitNotNull() {
        assertNotNull(fetchedQuestionText);
    }

    public void testFetchTextInitToPreviouslyRetrievedQuestionThroughObserver() {
        WebDataStorage.getInstance(activity).setQuestion("previously fetched question");
        killAndRestartActivity();
        assertEquals("previously fetched question", fetchedQuestionText.getText().toString());

        clearRealWebDataStorage();
    }

    private void killAndRestartActivity() {
        activity.finish();
        activity = this.getActivity();
    }

    public void testRotateToLandscapeOrientationMaintainsPreviousQuestion() {
        WebDataStorage.getInstance(activity).setQuestion("other fetched question");
        rotateScreen();
        assertEquals("other fetched question", fetchedQuestionText.getText().toString());

        clearRealWebDataStorage();
    }

    private void rotateScreen() {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getInstrumentation().waitForIdleSync();
    }

    public void testFetchTextUpdatesWhenDBUpdatesThroughObserver() throws InterruptedException {
        WebDataStorage.getInstance(activity).setQuestion("updated test question");
        storage.setQuestion("updated test question");
        Thread.sleep(100l);    //allow observer to be notified of changes -- should move to LoaderTestCase?
        assertEquals("updated test question", fetchedQuestionText.getText().toString());

        clearRealWebDataStorage();
    }

    /*TODO find way to inject ClearableWebDataStorage into
        testFetchTextInitToPreviouslyRetrievedQuestionThroughObserver,
        testRotateToLandscapeOrientationMaintainsPreviousQuestion, and
        testFetchTextUpdatesWhenDBUpdatesThroughObserver */
    private void clearRealWebDataStorage() {
        PreferenceManager.getDefaultSharedPreferences(activity).edit().clear().commit();
    }

    public void testFetchButtonInitNotNull() {
        assertNotNull(fetchQuestionButton);
    }

    public void testPressingFetchButtonCallsWebIntentService() throws InterruptedException {
        InspectableFetchQuestionButtonListener mockListener = new InspectableFetchQuestionButtonListener();
        fetchQuestionButton.setOnClickListener(mockListener);

        fetchQuestionButton.callOnClick();
        getInstrumentation().waitForIdleSync();

        assertTrue(mockListener.startActionFetchQuestionWasCalled);
    }

    public void testOnCreateLoaderReturnsWebDataStorageLoader() {
        Loader<String> testLoader = activity.onCreateLoader(0, null);
        assertNotNull(testLoader);
        assertTrue(testLoader instanceof WebDataStorageLoader);
    }

    @UiThreadTest
    public void testOnLoadFinishedUpdatesFetchQuestionText() {
        WebDataStorageLoader testLoader = new WebDataStorageLoader(activity, storage);
        String expectedText = "the loader should change the textview";

        activity.onLoadFinished(testLoader, expectedText);

        assertEquals(expectedText, fetchedQuestionText.getText().toString());
    }
}