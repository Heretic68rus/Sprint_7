package ru.praktikum.s7.Courier;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.s7.client.OrderClient;
import ru.praktikum.s7.step.OrderSteps;


import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private OrderSteps orderSteps;
    private final String[] color;

    public CreateOrderTest(String[] color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][]data() {
        return new Object[][] {
                new String[][]{{"BLACK"}},
                new String[][]{{"GREY"}},
                new String[][]{{"BLACK", "GREY"}},
                new String[][]{{}},
        };
    }
    @Before
    public void setUp() {
        orderSteps = new OrderSteps(new OrderClient());
    }
    @Test
    public void createOrderWithBothColorsSuccessfully() {
        String firstName = RandomStringUtils.randomAlphabetic(8);
        String lastName = RandomStringUtils.randomAlphabetic(8);
        String address = RandomStringUtils.randomAlphabetic(15);
        String metroStation = RandomStringUtils.randomAlphabetic(8);
        String phone = RandomStringUtils.randomNumeric(11);
        int rentTime = 1 + (int) (Math.random() * 168);
        String deliveryDate = "2020-06-06";
        String comment = RandomStringUtils.randomAlphabetic(20);

        orderSteps
                .createOrder(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color)
                .statusCode(SC_CREATED)
                .body("track", notNullValue());
    }
}
