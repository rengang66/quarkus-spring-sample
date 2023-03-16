package com.iiit.quarkus.sample.vertx;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ProjectResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/projects")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }

}