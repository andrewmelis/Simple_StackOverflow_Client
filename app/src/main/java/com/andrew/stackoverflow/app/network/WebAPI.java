package com.andrew.stackoverflow.app.network;

import com.andrew.stackoverflow.app.utilities.Utilities;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WebAPI {

    private String defaultURL = "https://api.stackexchange.com" +
                            "/2.2/questions?pagesize=1&order=desc&sort=activity&site=stackoverflow";

    private URL baseURL;

    public WebAPI() throws MalformedURLException {
        setDefaultURL();
    }

    public WebAPI(URL baseURL) throws MalformedURLException {        //enable URL injection for test
        if (baseURL == null) {
            setDefaultURL();
        } else {
            this.baseURL = baseURL;
        }
    }

    private void setDefaultURL() throws MalformedURLException {
        this.baseURL = new URL(defaultURL);
    }

    public String performFetchQuestion() {

        HttpURLConnection connection = null;
        String body = "";

        try {
            connection = (HttpURLConnection) baseURL.openConnection();
            body = Utilities.convertInputStreamToString(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            body = "connection error";
        } finally {
            disconnectConnection(connection);
        }
        return body;
    }

    private void disconnectConnection(HttpURLConnection connection) {
        if (connection != null) {
            connection.disconnect();
        }
    }

}