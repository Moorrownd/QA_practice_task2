package com.example.api.tests;

import com.example.api.dto.PostDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PostDtoTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    static Stream<PostDto> postDtoProvider() {
        return Stream.of(
                new PostDto("foo", "bar", 1),
                new PostDto("hello", "world", 2),
                new PostDto("test", "data", 3)

        );
    }

    @ParameterizedTest
    @MethodSource("postDtoProvider")
    public void testPostWithDto(PostDto post) {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + System.getProperty("API_TOKEN"))
                .body(post)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo(post.title))
                .body("body", equalTo(post.body))
                .body("userId", equalTo(post.userId))
                .body("id", notNullValue());
    }
}
