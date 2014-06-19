package com.andrew.stackoverflow.app.test;

import android.test.InstrumentationTestCase;

import com.andrew.stackoverflow.app.WebDataStorage;
import com.andrew.stackoverflow.app.WebDataStorageLoader;

public class WebDataStorageLoader_Test extends InstrumentationTestCase {

    public void testLoadInBackground() {
        WebDataStorage wds = ClearableWebDataStorage.getInstance(getInstrumentation().getTargetContext());

        String expected = "test question";
        wds.setQuestion(expected);

        WebDataStorageLoader loader = new WebDataStorageLoader(getInstrumentation().getTargetContext());
        String actual = loader.loadInBackground();

        assertEquals(expected, actual);
    }
}