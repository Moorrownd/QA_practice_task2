package com.example.api.tests;

import com.example.api.dto.PostDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PostDtoTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void testPostWithDto() {
        // Создаём объект DTO с данными
        PostDto post = new PostDto("foo", "bar", 1);

        // Отправляем POST-запрос с этим объектом
        given()
                .contentType(ContentType.JSON)
                .body(post)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("foo"))
                .body("body", equalTo("bar"))
                .body("userId", equalTo(1))
                .body("id", notNullValue()); // JSONPlaceholder возвращает фиктивный id
    }
}
