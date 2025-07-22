package com.example.api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestAssuredTests {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void testGetRequest() {
        assert System.getProperty("apiToken").equals("new123456");
        String apiName = System.getProperty("token");
        System.out.println("API Name: " + apiName);

        given()
                .header("Authorization", "Bearer " + System.getenv("API_TOKEN"))
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("userId", equalTo(1))
                .body("id", equalTo(1));
    }

    @Test
    public void testPostRequest() {
        String requestBody = "{\"title\":\"foo\", \"body\":\"bar\", \"userId\":1}";

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + System.getenv("API_TOKEN"))
                .body(requestBody)
                .when()
                .post("/posts");

        response.then()
                .statusCode(201)
                .body("title", equalTo("foo"))
                .body("id", notNullValue());
    }
}