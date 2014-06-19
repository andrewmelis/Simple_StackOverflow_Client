package com.andrew.stackoverflow.app.test.service;

import android.content.Intent;
import android.test.ServiceTestCase;

import com.andrew.stackoverflow.app.test.data.TestingWebDataStorage;

public class WebIntentService_Test extends ServiceTestCase<MockWebIntentService> {

    private TestingWebDataStorage storage;

    public WebIntentService_Test() {
        super(MockWebIntentService.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        storage = TestingWebDataStorage.getInstance(getSystemContext());
    }

    @Override
    public void tearDown() throws Exception {
        storage.clearWebDataStorage();
    }

    public void testHandleActionMethodShouldBeCalledByOnHandleIntent() throws InterruptedException {
        buildAndStartMockWebIntentService();
        Thread.sleep(100l);

        assertTrue(storage.getIntentServiceMethodWasCalled("handleActionFetchQuestion"));
    }

    public void testHandleActionFetchQuestionCallsWebAPI() throws InterruptedException {
        buildAndStartMockWebIntentService();
        Thread.sleep(200l);

        assertTrue(storage.getIntentServiceMethodWasCalled("retrieveQuestionFromWebAPI"));
    }

    public void testFetchedQuestionPassedToStorage() throws InterruptedException {
        buildAndStartMockWebIntentService();
        Thread.sleep(200l);

        assertTrue(storage.getIntentServiceMethodWasCalled("passFetchedQuestionToStorage"));
        assertEquals("testing string", storage.getQuestion());
    }

    private void buildAndStartMockWebIntentService() {
        Intent intent = new Intent(getContext(), MockWebIntentService.class);
        intent.setAction(MockWebIntentService.ACTION_FETCH_QUESTION);
        startService(intent);
    }

}