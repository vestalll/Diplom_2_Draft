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

public class EditUserTest {
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
        System.out.println(user.getEmail() + "   " + user.getPassword() + "   " + user.getName());
        ValidatableResponse loginResponse = authorizationClient.loginUser(userCredentials);
        String token = loginResponse.extract().path("accessToken");
        System.out.println(token);
        UserToken userToken = new UserToken(token);
        String updatedEmail = "email@mail.com";
        String updatedPassword = "qwerty";
        String updatedName = "lucky";
        User user = new User(updatedEmail, updatedPassword, updatedName);
        ValidatableResponse updateResponse = authorizationClient.updateUser(userToken, user);
        statusCode = updateResponse.extract().statusCode();
        System.out.println("name: " + user.getName() + " email: " + user.getEmail() + " password: " + user.getPassword());
        assertThat(statusCode, equalTo(SC_OK));
    }
}
