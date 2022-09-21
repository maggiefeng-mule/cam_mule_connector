package com.mulesoft.connectors.tutorial.internal.service;

import com.mulesoft.connectors.tutorial.internal.config.ChuckNorrisConfiguration;
import com.mulesoft.connectors.tutorial.internal.connection.ChuckNorrisConnection;
import com.mulesoft.extensions.request.builder.RequestBuilder;
import org.mule.connectors.commons.template.service.ConnectorService;
import org.mule.connectors.commons.template.service.DefaultConnectorService;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.mule.runtime.api.metadata.MediaType.APPLICATION_JSON;


public class ChuckNorrisService extends DefaultConnectorService<ChuckNorrisConfiguration, ChuckNorrisConnection> implements ConnectorService {

    public ChuckNorrisService(ChuckNorrisConfiguration config, ChuckNorrisConnection connection) {
        super(config, connection);
    }

    public String getRandomJoke(String category) {
        try {
            return RequestBuilder.get(
                    getConnection().getHttpClient(),
                    String.format("%s/random", getConnection().getServiceRootUrl()))
                    .queryParam("category", category)
                    .accept(APPLICATION_JSON)
                    .contentType(APPLICATION_JSON)
                    .execute();
        } catch (IOException | TimeoutException e) {
            return "Error: cannot connect to the API (" + e.getMessage() + ")";
        }

    }

}
