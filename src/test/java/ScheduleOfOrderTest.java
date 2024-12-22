import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static Model.Constants.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class ScheduleOfOrderTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URI;
    }

    @Test
    @DisplayName("Тест на получение списка заказов")
    public  void getOrdersTest(){
        given()
                .get(GET_ORDERS_URI)
                .then().statusCode(OK_CODE)
                .body(notNullValue());
    }
}
