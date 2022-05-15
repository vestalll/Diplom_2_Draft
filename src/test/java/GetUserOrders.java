import client.UserClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.User;
import model.UserCredentials;
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

    int statusCode;
    String accessToken;
    String messageText;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = UserGenerator.getRandom();
        userCredentials = new UserCredentials(user.getEmail(), user.getPassword());
    }

    @Test
    @DisplayName("Получение заказов пользователя")
    public void userCreationWithValidCredentials() {
        userClient.createUser(user);
        ValidatableResponse loginResponse = userClient.loginUser(userCredentials);
        String token = loginResponse.extract().path("accessToken");
        statusCode = loginResponse.extract().statusCode();
     //   ValidatableResponse getResponse = orderClient.getUserOrder(token);
        assertThat("User isn't created", statusCode, equalTo(SC_OK));
    }


}
