package com.mulesoft.connectors.tutorial.api.proxy;

import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.dsl.xml.TypeDsl;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.http.api.client.proxy.ProxyConfig;

/**
 * A Proxy configuration for NTLM authentication proxies.
 *
 * @since 1.0
 */
@Alias("NTLM")
@TypeDsl(allowTopLevelDefinition = true)
public class NtlmProxyConfig extends DefaultHttpProxyConfig implements ProxyConfig.NtlmProxyConfig {

    /**
     * The domain to authenticate against the proxy.
     */
    @Parameter
    @DisplayName("NTLM Domain")
    private String ntlmDomain;

    public String getNtlmDomain() {
        return ntlmDomain;
    }

    public void setNtlmDomain(String ntlmDomain) {
        this.ntlmDomain = ntlmDomain;
    }

}