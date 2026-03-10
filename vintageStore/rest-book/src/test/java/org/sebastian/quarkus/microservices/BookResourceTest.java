package org.sebastian.quarkus.microservices;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.Matchers.is;

@QuarkusTest
class BookResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
        .formParam("title", "Understanding Quarkus")
        .formParam("author", "Sebastian Daschner")
        .formParam("genre", "TI")
          .when()
            .post("/api/books")
          .then()
             .statusCode(201)
             .body("isbn_13", startsWith("13-1"))
             .body("title", startsWith("Understanding Quarkus"))
             .body("author", startsWith("Sebastian Daschner"))
             .body("genre", is("TI"));
    }

}