package Model;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static Model.Constants.*;
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
    public Response courierLogin(CourierData courierData) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courierData)
                .when()
                .post(COURIER_LOGIN_URI);

    }
    public ValidatableResponse deleteCourier(Integer courierId) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .delete(String.format(COURIER_DELETE_URI, courierId))
                .then();

    }
}
