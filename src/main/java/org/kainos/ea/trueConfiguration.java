package org.kainos.ea;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import io.swagger.models.Swagger;
import org.hibernate.validator.constraints.*;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class trueConfiguration extends Configuration {
    // TODO: implement service configuration
    @Valid
    @NotNull
    private final SwaggerBundleConfiguration swagger = new SwaggerBundleConfiguration();

    @JsonProperty("swagger")
    public SwaggerBundleConfiguration getSwagger(){
        swagger.setResourcePackage("org.kainos.ea.resources");
        String[] schemes = {"http", "https"};
        swagger.setSchemes(schemes);
        return swagger;
    }

}
