package com.iiit.quarkus.sample.integrate.spring.web;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class SpringGreetingControllerTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello-spring")
          .then()
             .statusCode(200)
             .body(is("Hello Spring"));
    }

}