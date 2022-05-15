import client.UserClient;
import client.OrderClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.UserGenerator;

import java.util.List;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateOrderTest {
    OrderClient orderClient;
    UserClient userClient;
    User user;
    UserCredentials userCredentials;
    int statusCode;
    int deleteStatusCode;
    int logoutStatusCode;
    String messageText;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = UserGenerator.getRandom();
        userCredentials = new UserCredentials(user.getEmail(), user.getPassword());
        orderClient = new OrderClient();
    }

    @After
    @DisplayName("Выход пользователя из системы")
    public void tearDown() {
        ValidatableResponse loginResponse = userClient.loginUser(userCredentials);
        String accessToken = loginResponse.extract().path("accessToken");
        String token = loginResponse.extract().path("refreshToken");
        UserToken userToken = new UserToken(accessToken);
        RefreshUserToken refreshUserToken = new RefreshUserToken(token);
        ValidatableResponse logoutResponse = userClient.logoutUser(userToken,refreshUserToken);
        logoutStatusCode = logoutResponse.extract().statusCode();
        assertThat("User isn't logout", logoutStatusCode, equalTo(SC_OK));
        //     String token = loginResponse.extract().path("accessToken");
        //    UserToken userToken = new UserToken(token);
        //    ValidatableResponse deleteResponse = userClient.deleteUser(userToken);
        //    deleteStatusCode = deleteResponse.extract().statusCode();
   //     assertThat("User isn't deleted", deleteStatusCode, equalTo(SC_ACCEPTED));
    }


    @Test
    @DisplayName("Создание заказа с авторизацией")
    public void orderCreationWithUserAuthorization() {
        userClient.createUser(user);
        ValidatableResponse loginResponse = userClient.loginUser(userCredentials);
        String token = loginResponse.extract().path("accessToken");
        UserToken userToken = new UserToken(token);
        List<String> ingredients = List.of("61c0c5a71d1f82001bdaaa6d", "61c0c5a71d1f82001bdaaa70");
        Order order = new Order(ingredients);
        ValidatableResponse createResponse = orderClient.createOrder(userToken, order);
        statusCode = createResponse.extract().statusCode();
        assertThat(statusCode, equalTo(SC_OK));

    }

    @Test
    @DisplayName("Создание заказа без авторизации")
    public void orderCreationWithoutUserAuthorization() {
        List<String> ingredients = List.of("61c0c5a71d1f82001bdaaa6d", "61c0c5a71d1f82001bdaaa70");
        Order order = new Order(ingredients);
        ValidatableResponse createResponse = orderClient.createOrder(order);
//         statusCode = createResponse.extract().statusCode();
        messageText = createResponse.extract().path("message");
        //  assertThat(statusCode, equalTo(SC_UNAUTHORIZED));
        System.out.println(messageText);
        assertThat(messageText, equalTo("email or password are incorrect"));
    }

    @Test
    @DisplayName("Создание заказа без ингредиентов")
    public void orderCreationWithoutIngredients() {
        userClient.createUser(user);
        ValidatableResponse loginResponse = userClient.loginUser(userCredentials);
        String token = loginResponse.extract().path("accessToken");
        UserToken userToken = new UserToken(token);
        ValidatableResponse createResponse = orderClient.createOrder(userToken);
        statusCode = createResponse.extract().statusCode();
        messageText = createResponse.extract().path("message");
        assertThat(statusCode, equalTo(SC_BAD_REQUEST));
        assertThat(messageText, equalTo("Ingredient ids must be provided"));
    }

    @Test
    @DisplayName("Создание заказа с неверным хэшем ингрединетов")
    public void orderCreationWithInvalidIngredientsHash() {
        userClient.createUser(user);
        ValidatableResponse loginResponse = userClient.loginUser(userCredentials);
        String token = loginResponse.extract().path("accessToken");
        UserToken userToken = new UserToken(token);
        List<String> ingredientHashes = List.of("4321", "1234");
        Order order = new Order(ingredientHashes);
        ValidatableResponse createResponse = orderClient.createOrder(userToken, order);
        statusCode = createResponse.extract().statusCode();
        assertThat(statusCode, equalTo(SC_INTERNAL_SERVER_ERROR));
    }
}











