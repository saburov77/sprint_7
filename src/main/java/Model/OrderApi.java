package Model;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static Model.Constants.ORDER_CREATE_URI;
import static io.restassured.RestAssured.given;

public class OrderApi {

    @Step ("Отправка запроса POST на создание курьера")
    public Response createOrder(OrderData orderData) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(orderData)
                .when()
                .post(ORDER_CREATE_URI);
    }
}
