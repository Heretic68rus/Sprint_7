package ru.praktikum.s7.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.praktikum.s7.dto.CourierCreateRequest;
import ru.praktikum.s7.dto.CourierDeleteRequest;
import ru.praktikum.s7.dto.CourierLoginRequest;
public class CourierClient extends RestClient {
    @Step("Send POST request to /api/v1/courier")
    public Response createCourier(CourierCreateRequest courierCreateRequest) {
        return getDefaultRequestSpecification()
                .body(courierCreateRequest)
                .when()
                .post("/courier");
    }
    @Step("Send POST request to /api/v1/courier/login")
    public Response loginCourier(CourierLoginRequest courierLoginRequest) {
        return getDefaultRequestSpecification()
                .body(courierLoginRequest)
                .when()
                .post("/courier/login");
    }
    @Step("Send POST request to /api/v1/courier/:id")
    public Response deleteCourier(CourierDeleteRequest courierLoginRequest) {
        return getDefaultRequestSpecification()
                .body(courierLoginRequest)
                .when()
                .post("/courier/:id");
    }
}
