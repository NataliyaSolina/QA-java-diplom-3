package org.example;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.example.Property.*;

public class ApiMethods {

    Map<String, String> user = Map.of(
            "name", NAME,
            "email", EMAIL,
            "password", PASSWORD
    );
    public Map<String, String> cred = Map.of(
            "email", EMAIL,
            "password", PASSWORD
    );

    @Step("Регистрация по АПИ")
    public void registerUserApi() {
        given()
                .header("Content-type", JSON)
                .and()
                .body(user)
                .when()
                .post(BASE_URL + BASE_PATH_API + PATH_REGISTER);
    }

    @Step("Авторизация по АПИ")
    public Response authUserApi() {
        return given()
                .header("Content-type", JSON)
                .and()
                .body(cred)
                .when()
                .post(BASE_URL + BASE_PATH_API + PATH_LOGIN);

    }

    @Step("Авторизация по АПИ")
    public Response authUserApi(String login, String pass) {
        Map<String, String> cred = new HashMap<>();
        cred.put("email", login);
        cred.put("password", pass);
        return given()
                .header("Content-type", JSON)
                .and()
                .body(cred)
                .when()
                .post(BASE_URL + BASE_PATH_API + PATH_LOGIN);

    }

    @Step("Удаление по АПИ")
    public void deleteUserApi(Response responseAuth) {
        given()
                .header("Content-type", JSON)
                .and()
                .header("Authorization", responseAuth.path("accessToken"))
                .when()
                .delete(BASE_URL + BASE_PATH_API + PATH_USER);
    }
}
