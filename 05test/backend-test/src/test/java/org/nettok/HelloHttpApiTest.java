package org.nettok;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HelloHttpApiTest {

    private final static Logger logger = LoggerFactory.getLogger(HelloHttpApiTest.class);

    @BeforeAll
    static void configureRestAssured() {
        baseURI = "https://cfaudbgdzycyghb2rv6uxlctdu0liqsj.lambda-url.us-east-1.on.aws";
        basePath = "/api/v1";
    }

    @ParameterizedTest
    @ValueSource(strings = {"Frantz Fanon", "Michael Parenti", ""})
    void testGetHello(String name) {
        final String expectedName = StringUtils.defaultIfEmpty(name, "mundo");

        final var response = given()
                .log().all()
                .queryParam("name", name)
            .when()
                .get("/hello")
            .then()
                .statusCode(200)
                .body("value", equalTo("Hola %s!".formatted(expectedName)))
                .body("timestamp", isA(String.class))
                .log().all()
            .extract().response();

        final String timestampString = response.path("timestamp");
        final var timestamp = ZonedDateTime.parse(timestampString);
        logger.info("Timestamp: {}", timestamp);
    }

}
