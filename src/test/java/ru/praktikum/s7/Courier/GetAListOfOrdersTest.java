package ru.praktikum.s7.Courier;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.s7.client.OrderClient;
import ru.praktikum.s7.step.OrderSteps;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.notNullValue;

public class GetAListOfOrdersTest {
    private OrderSteps orderSteps;

    @Before
    public void setUp() {
        orderSteps = new OrderSteps(new OrderClient());
    }

    @Test
    public void getAListOfOrdersSuccessfully() {
        Integer courierId = RandomUtils.nextInt();
        String nearestStation = RandomStringUtils.randomAlphabetic(8);
        Integer limit = RandomUtils.nextInt();
        Integer page = RandomUtils.nextInt();

        orderSteps
                .getAListOfOrders(courierId, nearestStation, limit, page)
                .statusCode(SC_OK)
                .body("orders", notNullValue());
    }
}
