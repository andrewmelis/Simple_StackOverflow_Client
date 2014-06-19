package com.andrew.stackoverflow.app.test.network;

import android.test.AndroidTestCase;

import com.andrew.stackoverflow.app.network.WebAPI;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

public class WebAPI_Test extends AndroidTestCase {

    private String sampleResponse =
    "{\"items\":[{\"tags\":[\"dynamics-crm\"],\"owner\":{\"reputation\":453,\"user_id\":382" +
    ",\"user_type\":\"registered\",\"accept_rate\":83,\"profile_image\"" +
    ":\"https://www.gravatar.com/avatar/456164d3e5618b412069fc727f0a8645?s=128&d=identicon&r=PG\"" +
    ",\"display_name\":\"skfd\",\"link\":\"http://stackoverflow.com/users/382/skfd\"}" +
    ",\"is_answered\":false,\"view_count\":3,\"answer_count\":1" +
    ",\"score\":0,\"last_activity_date\":1403165116,\"creation_date\":1403164784" +
    ",\"question_id\":24301567,\"link\"" +
    ":\"http://stackoverflow.com/questions/24301567/error-when-deleting-manged-solution\"" +
    ",\"title\":\"Error when deleting manged solution\"}]" +
    ",\"has_more\":true,\"quota_max\":300,\"quota_remaining\":258}";


    private WebAPI webAPI;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(sampleResponse));
        server.play();

        webAPI = new WebAPI(server.getUrl("/2.2/questions/"));
    }

    public void testPerformFetchQuestionSucceeds() {
        String actualString = webAPI.performFetchQuestion();

        assertEquals(sampleResponse, actualString);
    }

}