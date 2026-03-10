package org.sebastian.quarkus.microservices;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/")
@OpenAPIDefinition(
    info = @Info(
        title = "This microservice generates book numbers",
        version = "1.0",
        description = "API for generating random ISBN numbers for books",
        contact = @Contact(
            name = "Sebastian",
            email = "sebascarman@gmail.com",
            url = "https://github.com/sebastiansz1992"
        )
    ),
    tags = {
        @Tag(name = "Number Generation", description = "Endpoints for generating book numbers"),
        @Tag(name = "ISBN", description = "Endpoints related to ISBN number generation")
     }
)
public class NumberMicroservice extends Application {
    
}
