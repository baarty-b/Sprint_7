package praktikum.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.models.CreateCourierRequest;

import static praktikum.Constants.BASE_URL;
import static praktikum.checks.CheckCourier.*;
import static praktikum.DataGenerator.getCourier;
import static praktikum.DataGenerator.getCourierWithoutLogin;
import static praktikum.API.CourierAPI.*;

public class CreateCourierTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    public Integer id;
    @After
    public void delCourier() {
        Response responseDelete = deleteCourier(id);
        checkDeleteCourier(responseDelete);
    }

    @Test
    @Feature("Courier")
    @Story("Courier")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание курьера - без логина")
    public void createCourierWithoutLoginTest() {
        CreateCourierRequest data = getCourierWithoutLogin();
        Response response = sendPostRequestCreateCourierWithoutLogin(data);
        checkCreateCourierWithoutLogin(response);
    }

    @Test
    @Feature("Courier")
    @Story("Courier")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание курьера - уже существует")
    public void createCourierDoubleTest() {
        CreateCourierRequest data = getCourier();
        // Создать курьера
        Response response = sendPostRequestCreateCourier(data);
        checkCreateCourier(response);
        // Создать курьера с предыдущем логином
        Response responseDouble = sendPostRequestCreateCourier(data);
        checkCreateDoubleCourier(responseDouble);
        // Постусловие - Получить id
        id = getIdCourier(data);

    }


    @Test
    @Feature("Courier")
    @Story("Courier")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Удаление курьера - несуществующий ID")
    public void deleteUnrealCourierTest() {
        Response responseDelete = deleteCourier(595959);
        checkDeleteUnrealCourier(responseDelete);
    }


    @Test
    @Feature("Courier")
    @Story("Courier")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание и удаление курьера")
    public void deleteCourier1Test() {
        CreateCourierRequest data = getCourier();
        // Создать курьера
        Response response = sendPostRequestCreateCourier(data);
        checkCreateCourier(response);
        // Получить id
        id = getIdCourier(data);

    }

}