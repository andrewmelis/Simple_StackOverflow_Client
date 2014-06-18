package com.andrew.stackoverflow.app.test;

import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.preference.PreferenceManager;
import android.test.AndroidTestCase;

import com.andrew.stackoverflow.app.WebDataStorage;

public class WebDataStorage_Test extends AndroidTestCase {

    private WebDataStorage webDataStorage;

    @Override
    public void tearDown() throws Exception {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
        editor.clear();
        editor.commit();

        webDataStorage.unregisterAllObservers();
    }

    public void setUp() throws Exception {
        super.setUp();
        webDataStorage = WebDataStorage.getInstance(getContext());
    }

    public void testGetInstanceDoesNotReturnNull() {
        WebDataStorage wds = webDataStorage;
        assertNotNull(wds);
    }

    public void testGetInstanceAlwaysReturnsSameUniqueInstance() {
        WebDataStorage wds1 = WebDataStorage.getInstance(getContext());
        WebDataStorage wds2 = WebDataStorage.getInstance(getContext());

        assertEquals(wds1, wds2);
    }

    public void testQuestionDefault() {
        assertEquals("nothing retrieved yet!", webDataStorage.getQuestion());
    }

    public void testSetQuestionActuallySets() {
        webDataStorage.setQuestion("test question");
        assertEquals("test question", webDataStorage.getQuestion());
    }

    public void testSetQuestionSetsForAllGetInstanceCalls() {
        WebDataStorage wds_pre = webDataStorage;
        wds_pre.setQuestion("test");

        assertEquals(wds_pre.getQuestion(), webDataStorage.getQuestion());
    }

    public void testSetQuestionIsObservable() {
        WebDataStorageTestObserver observer = new WebDataStorageTestObserver();
        webDataStorage.registerObserver(observer);

        webDataStorage.setQuestion("should observe change");

        assertTrue(observer.wasNotifiedOfNewSavedQuestion);
    }

    public void testObserverCanUnregister() {
        WebDataStorageTestObserver observer = new WebDataStorageTestObserver();

        webDataStorage.setQuestion("not yet registered");
        assertFalse(observer.wasNotifiedOfNewSavedQuestion);

        webDataStorage.registerObserver(observer);
        webDataStorage.setQuestion("should now be registered");
        assertTrue(observer.wasNotifiedOfNewSavedQuestion);

        webDataStorage.unregisterObserver(observer);
        observer.resetWasNotifiedOfNewSavedQuestion();
        webDataStorage.setQuestion("should no longer be registered");
        assertFalse(observer.wasNotifiedOfNewSavedQuestion);
    }

    public void testCanRegisterMultipleObservers() {
        WebDataStorageTestObserver observer1 = new WebDataStorageTestObserver();
        WebDataStorageTestObserver observer2 = new WebDataStorageTestObserver();

        webDataStorage.registerObserver(observer1);
        webDataStorage.registerObserver(observer2);

        webDataStorage.setQuestion("should now be registered");
        assertTrue(observer1.wasNotifiedOfNewSavedQuestion);
        assertTrue(observer2.wasNotifiedOfNewSavedQuestion);
    }

    public void testCanUnregisterAllObservers() {
        WebDataStorageTestObserver observer1 = new WebDataStorageTestObserver();
        WebDataStorageTestObserver observer2 = new WebDataStorageTestObserver();

        webDataStorage.registerObserver(observer1);
        webDataStorage.registerObserver(observer2);

        webDataStorage.unregisterAllObservers();

        webDataStorage.setQuestion("should not be registered");
        assertFalse(observer1.wasNotifiedOfNewSavedQuestion);
        assertFalse(observer2.wasNotifiedOfNewSavedQuestion);
    }

    private class WebDataStorageTestObserver extends DataSetObserver {
        public boolean wasNotifiedOfNewSavedQuestion = false;

        @Override
        public void onChanged() {
            super.onChanged();
            wasNotifiedOfNewSavedQuestion = true;
        }

        public void resetWasNotifiedOfNewSavedQuestion() {
            wasNotifiedOfNewSavedQuestion = false;
        }
    }

}