package org.sebastian.quarkus.microservices;

import java.time.Instant;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tags;
import org.sebastian.quarkus.microservices.number.IsbNumbers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/numbers")
@Tags(value = { @org.eclipse.microprofile.openapi.annotations.tags.Tag(name = "Number Rest Endpoint") })
public class NumberResource {

    private final Random random = new Random();
    private final Logger logger;

    public NumberResource() {
        this.logger = Logger.getLogger(NumberResource.class.getName());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Generates book numbers",
        description = "Generates random ISBN-10 and ISBN-13 numbers for books"
    )
    public IsbNumbers generateIsbNumbers() {
        IsbNumbers isbNumbers = new IsbNumbers();
        isbNumbers.isbn10 = "10-" + random.nextInt(100_000_000);
        isbNumbers.isbn13 = "13-" + random.nextInt(100_000_000);
        isbNumbers.generationDate = Instant.now();
        logger.log(Level.INFO, "Numbers generated {0}", isbNumbers);
        return isbNumbers;
    }
}
