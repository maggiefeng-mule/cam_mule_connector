package com.mulesoft.connectors.tutorial.internal.connection;

import org.mule.connectors.commons.template.connection.ConnectorConnection;
import org.mule.runtime.http.api.client.HttpClient;

public class ChuckNorrisConnection implements ConnectorConnection {
    private final HttpClient httpClient;

    public ChuckNorrisConnection(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public String getServiceRootUrl() {
        return "https://api.chucknorris.io/jokes";
    }

    @Override
    public void disconnect() {
        httpClient.stop();
    }

    @Override
    public void validate() {
        try {
            // TODO: ...
        } catch (Exception e) {
            // TODO: Ver que ondo
        }
    }


}
