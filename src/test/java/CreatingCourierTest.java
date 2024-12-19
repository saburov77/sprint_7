import Model.CourierApi;
import Model.CourierData;

// импортируем Before
import Model.CourierLogin;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
// импортируем Test
import org.junit.Test;
// дополнительный статический импорт нужен, чтобы использовать given(), get() и then()
import static Model.Constants.BASE_URI;
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
        login = "Leo15";
        password = "Qwer3211";
        firstName = "Vasya7";
    }
    @After
    public void cleanUp() {
        if (courierId != null) {
            CourierApi courierApi = new CourierApi();
            courierApi.deleteCourier(courierId);
        } else { System.out.println("В базе данных нет курьера с логином " + login);}

    }

    @Test
    public void creatingCourierTest() {
        CourierData courierData = new CourierData(login, password, firstName);
        CourierApi courierApi = new CourierApi();
        ValidatableResponse response = courierApi.createCourier(courierData);

        response.assertThat()
                .statusCode(201)
                .body("ok", is(true));

        CourierData loginData = new CourierData(login, password);
        Response courierLogin = courierApi.courierLogin(loginData);
        courierId = courierLogin.body().as(CourierLogin.class).getId();
   }

   @Test
    public void createSameCourierTest () {
       CourierData courierData = new CourierData(login, password, firstName);
       CourierApi courierApi = new CourierApi();
       ValidatableResponse responseFirst = courierApi.createCourier(courierData);
       responseFirst.assertThat()
               .statusCode(201)
               .body("ok", is(true));

       CourierData loginData = new CourierData(login, password);
       Response courierLogin = courierApi.courierLogin(loginData);
       courierId = courierLogin.body().as(CourierLogin.class).getId();

       ValidatableResponse responseSecond = courierApi.createCourier(courierData);
       responseSecond.assertThat()
               .statusCode(409)
               .body("message", is("Этот логин уже используется. Попробуйте другой."));


   }


}
