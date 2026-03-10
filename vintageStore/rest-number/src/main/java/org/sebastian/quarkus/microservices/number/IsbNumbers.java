package org.sebastian.quarkus.microservices.number;

import java.time.Instant;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTransient;

@Schema(description = "Represents the generated ISBN numbers for books")
public class IsbNumbers {
    
    @Schema(required = true, description = "The generated ISBN-10 number")
    @JsonbProperty("isbn_10")
    public String isbn10;
    @Schema(required = true, description = "The generated ISBN-13 number")
    @JsonbProperty("isbn_13")
    public String isbn13;
    @JsonbTransient
    public Instant generationDate;    

    // toString() method for debugging purposes
    @Override
    public String toString() {
        return "IsbNumbers{" +
                "isbn10='" + isbn10 + '\'' +
                ", isbn13='" + isbn13 + '\'' +
                ", generationDate=" + generationDate +
                '}';
    }
}
