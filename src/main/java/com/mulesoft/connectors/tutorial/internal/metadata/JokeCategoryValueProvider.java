package com.mulesoft.connectors.tutorial.internal.metadata;

import com.mulesoft.connectors.tutorial.internal.config.ChuckNorrisConfiguration;
import com.mulesoft.connectors.tutorial.internal.connection.ChuckNorrisConnection;
import com.mulesoft.extensions.request.builder.RequestBuilder;
import org.mule.runtime.api.metadata.MediaType;
import org.mule.runtime.api.value.Value;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.values.ValueBuilder;
import org.mule.runtime.extension.api.values.ValueProvider;
import org.mule.runtime.extension.api.values.ValueResolvingException;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public class JokeCategoryValueProvider implements ValueProvider {

    @Connection
    private ChuckNorrisConnection connection;

    @Config
    private ChuckNorrisConfiguration config;

    @Override
    public Set<Value> resolve() throws ValueResolvingException {

        try {
            return Stream.of(RequestBuilder.get(connection.getHttpClient(),
                    String.format("%s/categories", connection.getServiceRootUrl()))
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .execute()
                    .replace("\"", "")
                    .replace("[", "")
                    .replace("]", "")
                    .split(","))
                    .map(category -> ValueBuilder.newValue(category).withDisplayName(category).build())
                    .collect(toSet());
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
