package com.mulesoft.connectors.tutorial.api.proxy;

import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.dsl.xml.TypeDsl;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.Password;


/**
 * Basic HTTP Proxy configuration based on host and port, and optionally a username and password for proxy authentication.
 *
 * @since 1.0
 */
@Alias("Default")
@TypeDsl(allowTopLevelDefinition = true)
public class DefaultHttpProxyConfig implements HttpProxyConfig {

    /**
     * Host where the proxy requests will be sent.
     */
    @Parameter
    private String host;

    /**
     * Port where the proxy requests will be sent.
     */
    @Parameter
    private int port = Integer.MAX_VALUE;

    /**
     * The username to authenticate against the proxy.
     */
    @Parameter
    @Optional
    private String username;

    /**
     * The password to authenticate against the proxy.
     */
    @Parameter
    @Optional
    @Password
    private String password;

    /**
     * A list of comma separated hosts against which the proxy should not be used
     */
    @Parameter
    @Optional
    private String nonProxyHosts;

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNonProxyHosts() {
        return nonProxyHosts;
    }

}