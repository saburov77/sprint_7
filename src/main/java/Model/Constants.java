package Model;

public class Constants {
    //Адрес тестового стенда
    public static final String BASE_URI = "http://qa-scooter.praktikum-services.ru/";
    //Используемые в проекте URL запрсов к API
    public static final String COURIER_CREATE_URI = "/api/v1/courier";
    public static final String COURIER_LOGIN_URI = "/api/v1/courier/login";
    public static final String COURIER_DELETE_URI = "/api/v1/courier/%d";
    public static final String ORDER_CREATE_URI = "/api/v1/orders";
    public static final String GET_ORDERS_URI = "/api/v1/orders";
    // Используемые в проекте коды ответа сервера
    public static final int CREATED_CODE = 201;
    public static final int CONFLICT_CODE = 409;
    public static final int BAD_REQUEST_CODE = 400;
    public static final int OK_CODE = 200;
    public static final int NOT_FOUND_CODE = 404;
}
