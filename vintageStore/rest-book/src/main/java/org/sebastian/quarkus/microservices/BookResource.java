package org.sebastian.quarkus.microservices;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/books")
@Tag(name = "Books", description = "Operations related to books in the vintage store")
public class BookResource {

    private final Logger logger;

    @RestClient
    NumberProxy numberProxy;

    public BookResource() {
        logger = Logger.getLogger(BookResource.class.getName());        
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Operation(
        summary = "Create a new book",
        description = "Creates a new book with the provided details and returns the created book."
    )
    @Retry(maxRetries = 3, delay = 3000)
    @Fallback(fallbackMethod = "fallingBackOncreatingABook")
    public Response createABook(@FormParam("title") String title, @FormParam("author") String author, @FormParam("yearOfPublication") int yearOfPublication, @FormParam("genre") String genre) {
        
        Book book = new Book();
        book.isbn13 = numberProxy.generateIsbNumbers().isbn13;
        book.title = title;
        book.author = author;
        book.yearOfPublication = yearOfPublication;
        book.genre = genre;
        book.creationDate = Instant.now();        
        logger.log(Level.INFO, "Book created: {0}", book);

        return Response
            .status(Response.Status.CREATED)
            .entity(book)
            .build();
    }

    public Response fallingBackOncreatingABook(String title, String author, int yearOfPublication, String genre) throws FileNotFoundException {
        
        Book book = new Book();
        book.isbn13 = "Will be generated when the service is back up";
        book.title = title;
        book.author = author;
        book.yearOfPublication = yearOfPublication;
        book.genre = genre;
        book.creationDate = Instant.now();   
        saveBookOnDisk(book);  
           
        logger.log(Level.WARNING, "Book created with fallback: {0}", book);

        return Response
            .status(Response.Status.PARTIAL_CONTENT)
            .entity(book)
            .build();
    }

    private void saveBookOnDisk(Book book) throws FileNotFoundException {
        logger.log(Level.INFO, "Saving book to disk: {0}", book);
        String bookJson = JsonbBuilder.create().toJson(book);
        try (PrintWriter out = new PrintWriter("book-" + Instant.now().toEpochMilli() + ".json")) {
            out.println(bookJson);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error saving book to disk", e);
        }
    }
}
