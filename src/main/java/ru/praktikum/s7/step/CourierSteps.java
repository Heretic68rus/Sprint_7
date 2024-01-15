package ru.praktikum.s7.step;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.s7.client.CourierClient;
import ru.praktikum.s7.dto.CourierCreateRequest;
import ru.praktikum.s7.dto.CourierDeleteRequest;
import ru.praktikum.s7.dto.CourierLoginRequest;

public class CourierSteps {
    private final CourierClient courierClient;
    public CourierSteps(CourierClient courierClient) {
        this.courierClient = courierClient;
    }
    @Step("Send request body and get response")
    public ValidatableResponse createCourier(String login, String password, String firstName) {
        CourierCreateRequest courierCreateRequest = new CourierCreateRequest();
        courierCreateRequest.setLogin(login);
        courierCreateRequest.setPassword(password);
        courierCreateRequest.setFirstName(firstName);
        return courierClient.createCourier(courierCreateRequest).then();
    }
    @Step("Send request body and get response")
    public ValidatableResponse loginCourier(String login, String password) {
        CourierLoginRequest courierLoginRequest = new CourierLoginRequest();
        courierLoginRequest.setLogin(login);
        courierLoginRequest.setPassword(password);
        return courierClient.loginCourier(courierLoginRequest)
                .then();
    }
    public ValidatableResponse deleteCourier(Integer id) {
        CourierDeleteRequest courierDeleteRequest = new CourierDeleteRequest();
        courierDeleteRequest.setId(id);
        return courierClient.deleteCourier(courierDeleteRequest)
                .then();
    }
}
