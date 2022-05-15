import client.AuthorizationClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.User;
import model.UserCredentials;
import org.junit.Before;
import org.junit.Test;
import utils.UserGenerator;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LoginUserTest {
    AuthorizationClient authorizationClient;
    User user;
    UserCredentials userCredentials;

    int statusCode;

    @Before
    public void setUp() {
        authorizationClient = new AuthorizationClient();
        user = UserGenerator.getRandom();
        userCredentials = new UserCredentials(user.getEmail(), user.getPassword());
    }

    @Test
    @DisplayName("Авторизация пользователя")
    public void userAuthorization() {
        authorizationClient.createUser(user);
        ValidatableResponse createResponse = authorizationClient.loginUser(userCredentials);
        statusCode = createResponse.extract().statusCode();
        assertThat(statusCode, equalTo(SC_OK));
    }

    @Test
    @DisplayName("Авторизация пользователя с несуществующими данными")
    public void userAuthorizationWithInvalidData() {
        String nonExistedEmail = "qwe@lk.ru";
        String nonExistedPassword = "12345";
        UserCredentials userCredentials = new UserCredentials(nonExistedEmail, nonExistedPassword);
        ValidatableResponse loginResponse = authorizationClient.loginUser(userCredentials);
        statusCode = loginResponse.extract().statusCode();
        assertThat(statusCode, equalTo(SC_UNAUTHORIZED));
    }
}
