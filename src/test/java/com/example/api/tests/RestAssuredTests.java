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
        // Базовый URL для всех запросов
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    // Тест GET запроса
    @Test
    public void testGetRequest() {
        given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("userId", equalTo(1))
                .body("id", equalTo(1));
    }

    // Тест POST запроса (исправлен для Java 11)
    @Test
    public void testPostRequest() {
        // Подготовка тела запроса в формате, совместимом с Java 11
        String requestBody = "{\"title\":\"foo\", \"body\":\"bar\", \"userId\":1}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/posts");

        response.then()
                .statusCode(201)
                .body("title", equalTo("foo"))
                .body("id", notNullValue());
    }
}