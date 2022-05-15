package client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.Order;
import model.UserCredentials;
import model.UserToken;

import static io.restassured.RestAssured.given;

public class OrderClient extends StellarBurgersRestClient {
    private static final String ORDER_PATH = "api/orders";

    @Step("Создание заказа")
    public ValidatableResponse createOrder(UserToken userToken, Order order) {
        return given()
                .spec(getBaseSpec())
                .header("Authorization", userToken.getToken())
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then().log().body();
    }

    @Step("Получение заказов пользователя")
    public ValidatableResponse getUserOrder(UserToken userToken) {
        return given()
                .spec(getBaseSpec())
                .header("Authorization", userToken.getToken())
                .when()
                .get(ORDER_PATH)
                .then().log().body();
    }
}
