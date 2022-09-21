package com.mulesoft.connectors.tutorial.internal.config;


import com.mulesoft.connectors.tutorial.internal.connection.provider.HttpConnectionProvider;
import com.mulesoft.connectors.tutorial.internal.operation.GetRandomJokeOperation;
import org.mule.connectors.commons.template.config.ConnectorConfig;
import org.mule.runtime.extension.api.annotation.Configuration;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

@Operations({
        GetRandomJokeOperation.class
})
@ConnectionProviders({HttpConnectionProvider.class})
@Configuration(name = "config")
@DisplayName("Configuration")
public class ChuckNorrisConfiguration implements ConnectorConfig {

}
