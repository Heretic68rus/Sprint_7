package ru.praktikum.s7.client;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import static ru.praktikum.s7.config.RestConfig.BASE_URL;

public abstract class RestClient {
    @Step("Send default URL https://qa-scooter.praktikum-services.ru/api/v1/")
    protected RequestSpecification getDefaultRequestSpecification() {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON);
    }
}
