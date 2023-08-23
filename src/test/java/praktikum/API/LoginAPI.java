package praktikum.API;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import praktikum.models.LoginCourierRequest;

import static io.restassured.RestAssured.given;
import static praktikum.Constants.LOGIN_COURIER_URL;

public class LoginAPI {
    @Step("Отправить запрос на авторизацию с валидными значениями")
    public static Response sendPostRequestLogin(LoginCourierRequest data) {
        return given()
                .header("Content-type", "application/json")
                .body(data)
                .log().all()
                .when()
                .post(LOGIN_COURIER_URL);
    }




}