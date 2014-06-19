package com.andrew.stackoverflow.app.test;

import android.test.ServiceTestCase;
import android.test.mock.MockContext;

public class WebIntentService_Test extends ServiceTestCase<MockWebIntentService> {

    private ClearableWebDataStorage storage;

    public WebIntentService_Test() {
        super(MockWebIntentService.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        storage = ClearableWebDataStorage.getInstance(getContext());
    }

    @Override
    public void tearDown() throws Exception {
        storage.clearWebDataStorage();
    }

//    public void testStartActionCommandShouldBeCalledByOnHandleIntent() throws InterruptedException {
//        getService().startActionFetchQuestion(getContext(), MockWebIntentService.class);
//        Thread.sleep(10000l);
//        assertTrue(storage.getIntentServiceMethodWasCalled("handleActionFetchQuestion"));
//    }

    public void testHandleActionFetchQuestionWritesToDB() {
        getService().handleActionFetchQuestion();
        assertTrue(storage.getIntentServiceMethodWasCalled("handleActionFetchQuestion"));
    }

}