import client.UserClient;
import client.OrderClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.*;
import org.junit.Before;
import org.junit.Test;
import utils.UserGenerator;

import java.util.List;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateOrderTest {
    OrderClient orderClient;
    UserClient userClient;
    User user;
    UserCredentials userCredentials;
    int statusCode;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = UserGenerator.getRandom();
        userCredentials = new UserCredentials(user.getEmail(), user.getPassword());
        orderClient = new OrderClient();
    }


    @Test
    @DisplayName("Создание заказа с авторизацией")
    public void orderCreationWithUserAuthorization() {
        userClient.createUser(user);
        ValidatableResponse loginResponse = userClient.loginUser(userCredentials);
        String token = loginResponse.extract().path("accessToken");
        UserToken userToken = new UserToken(token);
        List<String> ingredientHashes = List.of( "61c0c5a71d1f82001bdaaa6d",
                "61c0c5a71d1f82001bdaaa70");
        Order order = new Order(ingredientHashes);
        ValidatableResponse createResponse = orderClient.createOrder(userToken, order);
        statusCode = createResponse.extract().statusCode();
        assertThat(statusCode, equalTo(SC_OK));

    }

//    @Test
//    @DisplayName("Создание заказа без авторизации")
//    public void orderCreationWithoutUserAuthorization() {
//        ValidatableResponse createUserResponse = userClient.createUser(user);
//        //   ValidatableResponse loginResponse = authorizationClient.loginUser(userCredentials);
//        String token = createUserResponse.extract().path("accessToken");
//        UserToken userToken = new UserToken(token);
//        String hash = "61c0c5a71d1f82001bdaaa6d";
//        Ingredient ingredient = new Ingredient(hash);
//        List<Ingredient> ingredients = List.of(ingredient);
//        Order order = new Order(ingredients);
//        ValidatableResponse createResponse = orderClient.createOrder(userToken, order);
//        statusCode = createResponse.extract().statusCode();
//        assertThat(statusCode, equalTo(SC_OK));
//
//    }

    @Test
    @DisplayName("Создание заказа без ингредиентов")
    public void orderCreationWithoutIngredients() {
    }

    @Test
    @DisplayName("Создание заказа с неверным хэшем ингрединетов")
    public void orderCreationWithInvalidIngredientsHash() {
    }
}











