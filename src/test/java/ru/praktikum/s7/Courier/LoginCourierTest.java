package ru.praktikum.s7.Courier;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.s7.client.CourierClient;
import ru.praktikum.s7.step.CourierSteps;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class LoginCourierTest {
    private CourierSteps courierSteps;

    @Before
    public void setUp() {
        CourierClient courierClient = new CourierClient();
        courierSteps = new CourierSteps(courierClient);
    }
    @Test
    public void loginCourierSuccessfully() {
        String login = RandomStringUtils.random(10);
        String password = RandomStringUtils.random(10);
        String firstName = RandomStringUtils.random(10);

        courierSteps
                .createCourier(login, password, firstName);

        courierSteps
                .loginCourier(login, password)
                .statusCode(SC_OK)
                .body("id", notNullValue());
    }
    @Test
    public void loginCourierWithoutLoginReturnsAnErrorBadRequest() {
        String login = "";
        String password = RandomStringUtils.random(10);
        String firstName = RandomStringUtils.random(10);

        courierSteps
                .createCourier(login, password, firstName);

        courierSteps
                .loginCourier("", password)
                .statusCode(SC_BAD_REQUEST)
                .body("message", is("Недостаточно данных для входа"));
    }
    @Test
    public void loginCourierWithoutPasswordReturnsAnErrorBadRequest() {
        String login = RandomStringUtils.random(10);
        String password = "";
        String firstName = RandomStringUtils.random(10);

        courierSteps
                .createCourier(login, password, firstName);

        courierSteps
                .loginCourier(login, "")
                .statusCode(SC_BAD_REQUEST)
                .body("message", is("Недостаточно данных для входа"));
    }
    @Test
    public void loginCourierWithWrongLoginReturnsAnErrorNotFound() {
        String login = "Test_login";
        String password = RandomStringUtils.random(10);
        String firstName = RandomStringUtils.random(10);

        courierSteps
                .createCourier(login, password, firstName);

        courierSteps
                .loginCourier("OtherLogin", password)
                .statusCode(SC_NOT_FOUND)
                .body("message", is("Учетная запись не найдена")).and().extract().path("track");
    }
    @Test
    public void loginCourierWithWrongPasswordReturnsAnErrorNotFound() {
        String login = RandomStringUtils.random(10);
        String password = "Test_password";
        String firstName = RandomStringUtils.random(10);

        courierSteps
                .createCourier(login, password, firstName);

        courierSteps
                .loginCourier(login, "OtherPassword")
                .statusCode(SC_NOT_FOUND)
                .body("message", is("Учетная запись не найдена"));
    }
}

