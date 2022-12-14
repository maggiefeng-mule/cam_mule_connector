package com.mulesoft.connectors.tutorial.internal.connection.provider;


import com.mulesoft.connectors.tutorial.api.proxy.HttpProxyConfig;
import com.mulesoft.connectors.tutorial.internal.connection.ChuckNorrisConnection;
import com.mulesoft.connectors.tutorial.internal.connection.provider.param.ConnectionParameterGroup;
import org.mule.connectors.commons.template.connection.ConnectorConnectionProvider;
import org.mule.runtime.api.connection.CachedConnectionProvider;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.lifecycle.Initialisable;
import org.mule.runtime.api.lifecycle.InitialisationException;
import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.RefName;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.http.api.HttpService;
import org.mule.runtime.http.api.client.HttpClient;
import org.mule.runtime.http.api.client.HttpClientConfiguration;
import org.mule.runtime.http.api.tcp.TcpClientSocketProperties;

import javax.inject.Inject;

import static org.mule.runtime.api.meta.ExpressionSupport.NOT_SUPPORTED;
import static org.mule.runtime.core.api.lifecycle.LifecycleUtils.initialiseIfNeeded;
import static org.mule.runtime.extension.api.annotation.param.ParameterGroup.CONNECTION;

@Alias("basic")
@DisplayName("Basic Authentication")
public class HttpConnectionProvider extends ConnectorConnectionProvider<ChuckNorrisConnection> implements CachedConnectionProvider<ChuckNorrisConnection>, Initialisable {

    @RefName
    private String configName;

    @ParameterGroup(name = CONNECTION)
    @Placement(order = 1)
    private ConnectionParameterGroup connectionParams;

    @Parameter
    @Optional
    @Placement(tab = "Proxy", order = 3)
    @Expression(NOT_SUPPORTED)
    @DisplayName("Proxy Configuration")
    private HttpProxyConfig proxyConfig;

    @Inject
    private HttpService httpService;

    @Override
    public void initialise() throws InitialisationException {
        initialiseIfNeeded(connectionParams.getTlsContext());
    }

    @Override
    public ChuckNorrisConnection connect() throws ConnectionException {
        HttpClient httpClient = httpService.getClientFactory().create(new HttpClientConfiguration.Builder()
                .setTlsContextFactory(connectionParams.getTlsContext())
                .setProxyConfig(proxyConfig)
                .setClientSocketProperties(TcpClientSocketProperties.builder()
                        .connectionTimeout(connectionParams.getConnectionTimeout())
                        .build())
                .setMaxConnections(connectionParams.getMaxConnections())
                .setUsePersistentConnections(connectionParams.getUsePersistentConnections())
                .setConnectionIdleTimeout(connectionParams.getConnectionIdleTimeout())
                .setStreaming(connectionParams.isStreamResponse())
                .setResponseBufferSize(connectionParams.getResponseBufferSize())
                .setName(configName)
                .build());
        httpClient.start();
        return new ChuckNorrisConnection(httpClient);
    }
}
