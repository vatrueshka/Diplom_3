package api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserClientSteps extends RestAssuredClient {
    private static final String USER_PATH = "/api/auth";

    @Step("Создание пользователя.")
    public ValidatableResponse create(User user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .when()
                .post(USER_PATH + "/register")
                .then();
    }

    @Step("Авторизация пользователя.")
    public ValidatableResponse login(UserCredentials credentials) {
        return given()
                .spec(getBaseSpec())
                .body(credentials)
                .when()
                .post(USER_PATH + "/login")
                .then();
    }

    @Step("Удалить пользователя.")
    public void delete(String bearerToken) {
        if (bearerToken == null || bearerToken == "") {
            return;
        }
        given()
                .spec(getBaseSpec())
                .auth().oauth2(bearerToken.replace("Bearer ", ""))
                .when()
                .delete(USER_PATH + "/user")
                .then()
                .statusCode(202);

    }

}