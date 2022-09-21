package com.mulesoft.connectors.tutorial.internal.operation;

import com.mulesoft.connectors.tutorial.internal.config.ChuckNorrisConfiguration;
import com.mulesoft.connectors.tutorial.internal.connection.ChuckNorrisConnection;
import com.mulesoft.connectors.tutorial.internal.metadata.JokeCategoryValueProvider;
import com.mulesoft.connectors.tutorial.internal.metadata.JokeMetadataResolver;
import com.mulesoft.connectors.tutorial.internal.service.ChuckNorrisService;
import org.mule.connectors.commons.template.operation.ConnectorOperations;
import org.mule.runtime.extension.api.annotation.metadata.OutputResolver;
import org.mule.runtime.extension.api.annotation.metadata.TypeResolver;
import org.mule.runtime.extension.api.annotation.param.*;
import org.mule.runtime.extension.api.annotation.values.OfValues;

public class GetRandomJokeOperation extends ConnectorOperations<ChuckNorrisConfiguration, ChuckNorrisConnection, ChuckNorrisService> {

    public GetRandomJokeOperation() {
        super(ChuckNorrisService::new);
    }

    @MediaType(MediaType.APPLICATION_JSON)
    @OutputResolver(output = JokeMetadataResolver.class)
    public String getJoke(@Config ChuckNorrisConfiguration config,
                          @Connection ChuckNorrisConnection connection,
                          @Optional @OfValues(JokeCategoryValueProvider.class) String category) {
        return newExecutionBuilder(config, connection)
                .execute(ChuckNorrisService::getRandomJoke)
                .withParam(category);
    }


    @MediaType(MediaType.APPLICATION_JSON)
    public void addJoke(@Config ChuckNorrisConfiguration config,
                        @Connection ChuckNorrisConnection connection,
                        @Content @TypeResolver(JokeMetadataResolver.class) String joke) {
        System.out.println(joke);
    }

}
