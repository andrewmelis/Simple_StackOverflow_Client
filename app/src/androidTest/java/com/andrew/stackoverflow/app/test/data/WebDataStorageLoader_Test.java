package com.andrew.stackoverflow.app.test.data;

import android.test.InstrumentationTestCase;

import com.andrew.stackoverflow.app.data.WebDataStorage;
import com.andrew.stackoverflow.app.data.WebDataStorageLoader;

public class WebDataStorageLoader_Test extends InstrumentationTestCase {

    public void testLoadInBackground() {
        WebDataStorage wds = TestingWebDataStorage.getInstance(getInstrumentation().getTargetContext());

        String expected = "test question";
        wds.setQuestion(expected);

        WebDataStorageLoader loader = new WebDataStorageLoader(getInstrumentation().getTargetContext());
        String actual = loader.loadInBackground();

        assertEquals(expected, actual);
    }
}