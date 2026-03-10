package org.sebastian.quarkus.microservices;

import java.time.Instant;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.json.bind.annotation.JsonbProperty;

@Schema(description = "Represents a book in the vintage store")
public class Book {

    @JsonbProperty("isbn_13")
    @Schema(required = true, description = "The ISBN-13 identifier for the book")
    public String isbn13;
    @Schema(required = true, description = "The title of the book")
    public String title;
    @Schema(required = true, description = "The author of the book")
    public String author;
    @JsonbProperty("year_of_publication")
    @Schema(required = true, description = "The year the book was published")
    public int yearOfPublication;
    @Schema(required = true, description = "The genre of the book")
    public String genre;
    @JsonbDateFormat("yyyy-MM-dd")
    @JsonbProperty("creation_date")
    @Schema(description = "The date when the book was created in the system")
    public Instant creationDate;

    @Override
    public String toString() {
        return "Book{" +
                "isbn13='" + isbn13 + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", genre='" + genre + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
    
}
