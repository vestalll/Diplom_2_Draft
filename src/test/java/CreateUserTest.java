import client.UserClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.User;
import model.UserCredentials;
import model.UserToken;
import org.junit.Before;
import org.junit.Test;
import utils.UserGenerator;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest {
    UserClient userClient;
    User user;
    UserCredentials userCredentials;

    int statusCode;
    String accessToken;
    String messageText;


    @Before
    public void setUp() {
        userClient = new UserClient();
        user = UserGenerator.getRandom();
        userCredentials = new UserCredentials(user.getEmail(), user.getPassword());
    }

//    @After
//    @DisplayName("Логин и удаление пользователя")
//    public void tearDown() {
//        authorizationClient.loginUser(userCredentials);
//        ValidatableResponse createResponse = authorizationClient.deleteUser(userCredentials);
//        statusCode = createResponse.extract().statusCode();
//        assertThat("User isn't deleted", statusCode, equalTo(SC_OK));
//    }


    @Test
    @DisplayName("Создание уникального пользователя с заполнением всех полей верными значениями")
    public void userCreationWithValidCredentials() {
        ValidatableResponse createResponse = userClient.createUser(user);
        statusCode = createResponse.extract().statusCode();
        System.out.println("name: " + user.getName() + " email: " + user.getEmail() + " password: " + user.getPassword());
        assertThat("User isn't created", statusCode, equalTo(SC_OK));
    }

    @Test
    @DisplayName("Создание пользователя с существующими значениями")
    public void userCreationWithExistedData() {
        userClient.createUser(user);
        User existedUser = new User(user.getEmail(), user.getPassword(), user.getName());
        ValidatableResponse createResponse = userClient.createUser(existedUser);
        statusCode = createResponse.extract().statusCode();
        messageText = createResponse.extract().path("message");
        System.out.println("name: " + user.getName() + " email: " + user.getEmail() + " password: " + user.getPassword());
        System.out.println("name: " + existedUser.getName() + " email: " + existedUser.getEmail() + " password: " + user.getPassword());
        assertThat("User isn't created", messageText, equalTo("User already exists"));
        assertThat("User isn't created", statusCode, equalTo(SC_FORBIDDEN));

    }


    @Test
    @DisplayName("Создание пользователя с пустым значением поля \"email\"")
    public void userCreationWithEmptyEmailField() {
        user.setEmail("");
        ValidatableResponse createResponse = userClient.createUser(user);
        statusCode = createResponse.extract().statusCode();
        messageText = createResponse.extract().path("message");
        System.out.println("name: " + user.getName() + " email: " + user.getEmail());
        assertThat("User isn't created", messageText, equalTo("Email, password and name are required fields"));
        assertThat("User isn't created", statusCode, equalTo(SC_FORBIDDEN));
    }


    @Test
    @DisplayName("Создание пользователя с пустым значением поля \"password\"")
    public void userCreationWithEmptyPasswordField() {
        user.setPassword("");
        ValidatableResponse createResponse = userClient.createUser(user);
        statusCode = createResponse.extract().statusCode();
        messageText = createResponse.extract().path("message");
        System.out.println("name: " + user.getName() + " email: " + user.getEmail() + " password: " + user.getPassword());
        assertThat("User isn't created", messageText, equalTo("Email, password and name are required fields"));
        assertThat("User isn't created", statusCode, equalTo(SC_FORBIDDEN));
    }

    @Test
    @DisplayName("Удаление пользователя")
    public void userDeleting() {
        userClient.createUser(user);
        System.out.println(user.getEmail() + "   " + user.getPassword() + "   " + user.getName());
        ValidatableResponse loginResponse = userClient.loginUser(userCredentials);
        String token = loginResponse.extract().path("accessToken");
        System.out.println(token);
        UserToken userToken = new UserToken(token);
        ValidatableResponse deleteResponse = userClient.deleteUser(userToken);
        statusCode = deleteResponse.extract().statusCode();
        assertThat("User isn't deleted", statusCode, equalTo(SC_ACCEPTED));
    }
}
