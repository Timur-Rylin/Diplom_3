package com.stellarburgers.helpers;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class UserHelper {

    @Step("Создание пользователя через API: {email}")
    public static Response registerUser(String email, String password, String name) {
        RestAssured.baseURI = TestData.API_BASE_URL;

        Map<String, String> userData = new HashMap<>();
        userData.put("email", email);
        userData.put("password", password);
        userData.put("name", name);

        return given()
                .header("Content-type", "application/json")
                .body(userData)
                .when()
                .post("/auth/register");
    }

    @Step("Удаление пользователя через API")
    public static Response deleteUser(String accessToken) {
        RestAssured.baseURI = TestData.API_BASE_URL;

        return given()
                .header("Authorization", accessToken)
                .when()
                .delete("/auth/user");
    }

    @Step("Логин пользователя через API")
    public static Response loginUser(String email, String password) {
        RestAssured.baseURI = TestData.API_BASE_URL;

        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", email);
        credentials.put("password", password);

        return given()
                .header("Content-type", "application/json")
                .body(credentials)
                .when()
                .post("/auth/login");
    }
}