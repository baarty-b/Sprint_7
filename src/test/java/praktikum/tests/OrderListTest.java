package praktikum.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import praktikum.models.OrderResponse;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.notNullValue;
import static praktikum.Constants.BASE_URL;
import static praktikum.Constants.LIST_ORDER_URL;

public class OrderListTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @Feature("Orders")
    @Story("Orders")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка заказов")
    public void getOrdersTest() {

        given()
                .header("Content-type", "application/json")
                .queryParam("limit", 10)
                .log().all()
                .when()
                .get(LIST_ORDER_URL)
                .then()
                .log().all()
                .statusCode(SC_OK)
                .assertThat().body("orders.track", notNullValue())
                .extract().body().jsonPath().getList("orders", OrderResponse.class);


    }
}