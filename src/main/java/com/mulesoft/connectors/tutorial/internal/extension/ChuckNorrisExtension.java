package com.mulesoft.connectors.tutorial.internal.extension;

import com.mulesoft.connectors.tutorial.api.proxy.DefaultHttpProxyConfig;
import com.mulesoft.connectors.tutorial.api.proxy.HttpProxyConfig;
import com.mulesoft.connectors.tutorial.api.proxy.NtlmProxyConfig;
import com.mulesoft.connectors.tutorial.internal.config.ChuckNorrisConfiguration;
import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.Import;
import org.mule.runtime.extension.api.annotation.SubTypeMapping;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;
import org.mule.runtime.http.api.tcp.TcpClientSocketProperties;

@Extension(name = "Chuck Norris Jokes")
@Configurations(ChuckNorrisConfiguration.class)
@Xml(prefix = "chuck-norris")
@SubTypeMapping(baseType = HttpProxyConfig.class, subTypes = {DefaultHttpProxyConfig.class, NtlmProxyConfig.class})
@Import(type = TcpClientSocketProperties.class)
public class ChuckNorrisExtension {

}
