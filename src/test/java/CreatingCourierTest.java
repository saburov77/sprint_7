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
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.CoreMatchers.is;



public class CreatingCourierTest {
    protected int courierId;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URI;
    }
    @After
    public void cleanUp() {

    }

    @Test
           public void creatingCourierTest() {
                CourierData courierData = new CourierData("Leopold4", "Qwer127", "Вася6");
                CourierApi courierApi = new CourierApi();
                ValidatableResponse response = courierApi.createCourier(courierData);

                response.assertThat()
                    .statusCode(201)
                    .body("ok", is(true));

                //CourierData loginData = new CourierData("Leopold7", "Qwer1257");
                //ValidatableResponse login = courierApi.loginCourier(loginData);
                //CourierLogin courierLogin = new CourierLogin();
                //courierId = login.body().as(CourierLogin.class).getId;
                //System.out.println(courierId);

   }


}
