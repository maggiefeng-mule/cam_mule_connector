package com.mulesoft.connectors.tutorial.internal.metadata;

import org.mule.metadata.api.builder.ObjectTypeBuilder;
import org.mule.metadata.api.model.MetadataType;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.metadata.MetadataContext;
import org.mule.runtime.api.metadata.MetadataResolvingException;
import org.mule.runtime.api.metadata.resolving.InputTypeResolver;
import org.mule.runtime.api.metadata.resolving.OutputTypeResolver;

public class JokeMetadataResolver implements OutputTypeResolver<String>, InputTypeResolver<String> {
    @Override
    public String getResolverName() {
        return "JokeMetadataResolver";
    }

    @Override
    public MetadataType getOutputType(MetadataContext metadataContext, String s) throws MetadataResolvingException, ConnectionException {
        ObjectTypeBuilder objectTypeBuilder = metadataContext.getTypeBuilder().objectType();
        objectTypeBuilder.addField()
                .key("category")
                .description("The category")
                .required(false)
                .value().stringType();
        objectTypeBuilder.addField()
                .key("icon_url")
                .description("The lindo icon")
                .required()
                .value().stringType();
        objectTypeBuilder.addField()
                .key("id")
                .description("The id")
                .required()
                .value().stringType();
        objectTypeBuilder.addField()
                .key("url")
                .description("The url")
                .required()
                .value().stringType();
        objectTypeBuilder.addField()
                .key("value")
                .description("The joke")
                .required()
                .value().stringType();

        return objectTypeBuilder.build();
    }

    @Override
    public String getCategoryName() {
        return "JokeMetadataResolver";
    }

    @Override
    public MetadataType getInputMetadata(MetadataContext metadataContext, String s) throws MetadataResolvingException, ConnectionException {
        ObjectTypeBuilder objectTypeBuilder = metadataContext.getTypeBuilder().objectType();
        objectTypeBuilder.addField()
                .key("category")
                .description("The category")
                .required(false)
                .value().stringType();
        objectTypeBuilder.addField()
                .key("icon_url")
                .description("The lindo icon")
                .required()
                .value().stringType();
        objectTypeBuilder.addField()
                .key("id")
                .description("The id")
                .required()
                .value().stringType();
        objectTypeBuilder.addField()
                .key("url")
                .description("The url")
                .required()
                .value().stringType();
        objectTypeBuilder.addField()
                .key("value")
                .description("The joke")
                .required()
                .value().stringType();

        return objectTypeBuilder.build();
    }
}
