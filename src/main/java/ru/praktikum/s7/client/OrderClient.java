package ru.praktikum.s7.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.praktikum.s7.dto.GetAListOfOrdersRequest;
import ru.praktikum.s7.dto.OrderCreateRequest;
public class OrderClient extends RestClient {
    @Step("Send POST request to /api/v1/orders")
    public Response createOrder(OrderCreateRequest orderCreateRequest) {
        return getDefaultRequestSpecification()
                .body(orderCreateRequest)
                .when()
                .post("/orders");
    }
    @Step("Send GET request to /api/v1/orders")
    public Response getAListOfOrders(GetAListOfOrdersRequest getAListOfOrdersRequest) {
        return getDefaultRequestSpecification()
                .body(getAListOfOrdersRequest)
                .when()
                .get("/orders");
    }

}
