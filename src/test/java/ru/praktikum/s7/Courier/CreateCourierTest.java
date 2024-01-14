package ru.praktikum.s7.Courier;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.s7.client.CourierClient;
import ru.praktikum.s7.step.CourierSteps;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.is;

public class CreateCourierTest {
    private CourierSteps courierSteps;

    @Before
    public void setUp() {
        courierSteps = new CourierSteps(new CourierClient());
    }

    @Test
    public void createCourierSuccessfully() {
        String login = RandomStringUtils.random(10);
        String password = RandomStringUtils.random(10);
        String firstName = RandomStringUtils.random(10);

        courierSteps
                .createCourier(login, password, firstName)
                .statusCode(SC_CREATED)
                .body("ok", is(true));
        Integer photoId = courierSteps
                .loginCourier(login, password)
                .extract().body().path("id");
        courierSteps
                .deleteCourier(photoId);
    }
    @Test
    public void cantCreateTwoIdenticalCouriers() {
        String login = "TestCourier";
        String password = RandomStringUtils.random(10);
        String firstName = RandomStringUtils.random(10);

        courierSteps
                .createCourier(login, password, firstName);
        courierSteps
                .createCourier(login, password, firstName)
                .statusCode(SC_CONFLICT)
                .body("message", is("Этот логин уже используется"));
    }
    @Test
    public void createCourierWithoutLoginReturnsAnErrorBadRequest() {
        String login = "";
        String password = RandomStringUtils.random(10);
        String firstName = RandomStringUtils.random(10);

        courierSteps
                .createCourier(login, password, firstName)
                .statusCode(SC_BAD_REQUEST)
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }
    @Test
    public void createCourierWithoutPasswordReturnsAnErrorBadRequest() {
        String login = RandomStringUtils.random(10);
        String password = "";
        String firstName = RandomStringUtils.random(10);

        courierSteps
                .createCourier(login, password, firstName)
                .statusCode(SC_BAD_REQUEST)
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }
}
