import client.AuthorizationClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.User;
import model.UserCredentials;
import model.UserToken;
import org.junit.Before;
import org.junit.Test;
import utils.UserGenerator;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UpdateUserTest {
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
    @DisplayName("Изменение данных пользователя с авторизацией")
    public void userDataUpdatingWithAuthorization() {
        authorizationClient.createUser(user);
        ValidatableResponse loginResponse = authorizationClient.loginUser(userCredentials);
        String token = loginResponse.extract().path("accessToken");
        UserToken userToken = new UserToken(token);
        User updatedUser = UserGenerator.getRandom();
        ValidatableResponse updateResponse = authorizationClient.updateUser(userToken, updatedUser);
        statusCode = updateResponse.extract().statusCode();
        assertThat(statusCode, equalTo(SC_OK));
    }

    @Test
    @DisplayName("Изменение данных пользователя без авторизации")
    public void userDataUpdatingWithoutAuthorization() {
        ValidatableResponse createResponse = authorizationClient.createUser(user);
        String token = createResponse.extract().path("accessToken");
        UserToken userToken = new UserToken(token);
        User updatedUser = UserGenerator.getRandom();
        ValidatableResponse updateResponse = authorizationClient.updateUser(userToken, updatedUser);
        statusCode = updateResponse.extract().statusCode();
        assertThat(statusCode, equalTo(SC_OK));
    }
}
