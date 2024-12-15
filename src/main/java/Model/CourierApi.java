package Model;

import io.restassured.response.ValidatableResponse;

import static Model.Constants.COURIER_CREATE_URI;
import static Model.Constants.COURIER_LOGIN_URI;
import static io.restassured.RestAssured.given;

public class CourierApi {



    public ValidatableResponse createCourier(CourierData courierData) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courierData)
                .when()
                .post(COURIER_CREATE_URI)
                .then();

    }
    public ValidatableResponse loginCourier(CourierData courierData) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courierData)
                .when()
                .post(COURIER_LOGIN_URI)
                .then();

    }
}
