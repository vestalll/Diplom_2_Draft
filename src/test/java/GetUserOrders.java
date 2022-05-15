import client.OrderClient;
import client.UserClient;
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

public class GetUserOrders {
    UserClient userClient;
    User user;
    UserCredentials userCredentials;
    OrderClient orderClient;

    int statusCode;

    @Before
    public void setUp() {
        userClient = new UserClient();
        orderClient = new OrderClient();
        user = UserGenerator.getRandom();
        userCredentials = new UserCredentials(user.getEmail(), user.getPassword());
    }

    @Test
    @DisplayName("Получение заказов пользователя")
    public void userCreationWithValidCredentials() {
        userClient.createUser(user);
        ValidatableResponse loginResponse = userClient.loginUser(userCredentials);
        String token = loginResponse.extract().path("accessToken");
        UserToken userToken = new UserToken(token);
        ValidatableResponse getResponse = orderClient.getUserOrder(userToken);
        statusCode = getResponse.extract().statusCode();
        assertThat("User isn't created", statusCode, equalTo(SC_OK));
    }


}
