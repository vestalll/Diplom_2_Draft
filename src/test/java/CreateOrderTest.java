import client.AuthorizationClient;
import client.OrderClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.*;
import org.junit.Before;
import org.junit.Test;
import utils.UserGenerator;
import client.AuthorizationClient;

import java.util.List;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateOrderTest {
    OrderClient orderClient;
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
    @DisplayName("Создание заказа с авторизацией")
    public void orderCreationWithUserAuthorization() {
        authorizationClient.createUser(user);
        ValidatableResponse loginResponse = authorizationClient.loginUser(userCredentials);
        String token = loginResponse.extract().path("accessToken");
        UserToken userToken = new UserToken(token);
        String hash = "61c0c5a71d1f82001bdaaa6d";
        Ingredient ingredient = new Ingredient(hash);
        List<Ingredient> ingredients = List.of(ingredient);
        Order order = new Order(ingredients);
        ValidatableResponse createResponse = orderClient.createOrder(userToken, order);
        statusCode = createResponse.extract().statusCode();
        assertThat(statusCode, equalTo(SC_OK));

    }










}
