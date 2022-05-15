package client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.User;
import model.UserCredentials;
import model.UserToken;
import org.apache.commons.lang3.builder.ToStringExclude;

import static io.restassured.RestAssured.given;

public class UserClient extends StellarBurgersRestClient {
    private static final String REGISTRATION_PATH = "api/auth/register";
    private static final String LOGIN_PATH = "api/auth/login";
    private static final String LOGOUT_PATH = "api/auth/logout";
    private static final String USER_PATH = "api/auth/user";



    @Step ("Создание пользователя")
    public ValidatableResponse createUser(User user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .when()
                .post(REGISTRATION_PATH)
                .then().log().body();
    }

    @Step ("Логин пользователя")
    public ValidatableResponse loginUser(UserCredentials userCredentials) {
        return given()
                .spec(getBaseSpec())
                .body(userCredentials)
                .when()
                .post(LOGIN_PATH)
                .then().log().body();

    }

    @Step ("Изменение данных пользователя")
    public ValidatableResponse updateUser(UserToken userToken, User user) {
        return given()
                .spec(getBaseSpec())
                .header("Authorization", userToken.getToken())
                .body(user)
                .when()
                .patch(USER_PATH)
                .then().log().body();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(UserToken userToken) {
        return given()
                .spec(getBaseSpec())
                .header("Authorization", userToken.getToken())
                .when()
                .delete(USER_PATH)
                .then().log().body();
    }

}



