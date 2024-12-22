import Model.CourierApi;
import Model.CourierData;

import Model.CourierResponseData;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
// импортируем Test
import org.junit.Test;
// дополнительный статический импорт нужен, чтобы использовать given(), get() и then()
import static Model.Constants.*;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.CoreMatchers.is;



public class CreatingCourierTest {

    protected Integer courierId;
    protected String login;
    protected String password;
    protected String firstName;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URI;
        login = "Leo111";
        password = "3211";
        firstName = "Vasya19";
    }

    @After
    public void cleanUp() {
        CourierApi courierApi = new CourierApi();
        CourierData loginData = new CourierData(login, password);
        Response courierLogin = courierApi.courierLogin(loginData);
        courierId = courierLogin.body().as(CourierResponseData.class).getId();

        if (courierId != null) {
            courierApi.deleteCourier(courierId);
        } else { System.out.println("В базе данных нет курьера с логином " + login);}

    }

    @Test
    @DisplayName("Тест на создание курьера")
    public void creatingCourierTest() {
        CourierData courierData = new CourierData(login, password, firstName);
        CourierApi courierApi = new CourierApi();

        ValidatableResponse response = courierApi.createCourier(courierData);
        response.assertThat().log().all()
                .statusCode(CREATED_CODE)
                .body("ok", is(true));
    }

   @Test
   @DisplayName("Тест, что нельзя создать двух одинаковых курьеров")
    public void createSameCourierTest () {

       CourierData courierData = new CourierData(login, password, firstName);
       CourierApi courierApi = new CourierApi();

       ValidatableResponse responseFirst = courierApi.createCourier(courierData);
       responseFirst.assertThat().log().all()
               .statusCode(CREATED_CODE)
               .body("ok", is(true));

       ValidatableResponse responseSecond = courierApi.createCourier(courierData);
       responseSecond.assertThat()
               .statusCode(CONFLICT_CODE).log().all()
               .body("message", is("Этот логин уже используется. Попробуйте другой."));
   }

    @Test
    @DisplayName("Тест, что нельзя создать двух курьеров c одинаковым Login")
    public void createSameLoginTest () {
        CourierData courierData = new CourierData(login, password, firstName);
        CourierApi courierApi = new CourierApi();
        CourierData sameLoginData = new CourierData(login, password+"1", firstName+"1");

        ValidatableResponse responseFirst = courierApi.createCourier(courierData);
        responseFirst.assertThat().log().all()
                .statusCode(CREATED_CODE)
                .body("ok", is(true));

        ValidatableResponse responseSecond = courierApi.createCourier(sameLoginData);
        responseSecond.assertThat()
                .statusCode(CONFLICT_CODE).log().all()
                .body("message", is("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Тест на создание курьера без поля FirstName")
    public void createWithoutFirstNameField() {
        CourierData courierData = new CourierData(login, password);
        CourierApi courierApi = new CourierApi();
        ValidatableResponse response = courierApi.createCourier(courierData);

        response.assertThat().log().all()
                .statusCode(CREATED_CODE)
                .body("ok", is(true));
    }

    @Test
    @DisplayName("Тест на создание курьера без поля Login")
    public void createWithoutLoginField() {
        CourierData courierData = new CourierData("", password, firstName);
        CourierApi courierApi = new CourierApi();
        ValidatableResponse response = courierApi.createCourier(courierData);

        response.assertThat().log().all()
                .statusCode(BAD_REQUEST_CODE)
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Тест на создание курьера без поля Password")
    public void createWithoutPasswordField() {
        CourierData courierData = new CourierData(login, "", firstName);
        CourierApi courierApi = new CourierApi();
        ValidatableResponse response = courierApi.createCourier(courierData);

        response.assertThat().log().all()
                .statusCode(BAD_REQUEST_CODE)
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

}
