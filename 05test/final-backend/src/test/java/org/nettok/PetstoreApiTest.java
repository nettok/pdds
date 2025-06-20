package org.nettok;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.nettok.model.User;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PetstoreApiTest {

    @BeforeAll
    static void configureRestAssured() {
        baseURI = "https://petstore.swagger.io";
        basePath = "/v2";
    }

    @Test
    void testCreateUserAndLogin() {
        final String username = "examen777";
        final String password = "secreto";

        final User user = getUser(username, password);

        // Create user
        given()
                .log().body()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/user")
                .then()
                .statusCode(200)
                .body("code", equalTo(200))
                .log().body()
                .extract().response();

        // Logs user into the system
        given()
                .log().body()
                .queryParam("username", username)
                .queryParam("password", password)
                .when()
                .get("/user/login")
                .then()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("message", startsWith("logged in user session"))
                .log().body()
                .extract().response();
    }

    private static User getUser(final String username,  final String password) {
        final User user = new User();
        user.setUsername(username);
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("email");
        user.setPassword(password);
        user.setPhone("1234567890");
        return user;
    }
}
