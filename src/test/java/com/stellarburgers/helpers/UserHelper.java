package com.stellarburgers.helpers;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class UserHelper {

    public static class UserData {
        private String email;
        private String password;
        private String name;

        public UserData(String email, String password, String name) {
            this.email = email;
            this.password = password;
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public String getName() {
            return name;
        }
    }

    public static class Credentials {
        private String email;
        private String password;

        public Credentials(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }

    @Step("Создание пользователя через API: {email}")
    public static Response registerUser(String email, String password, String name) {
        RestAssured.baseURI = TestData.API_BASE_URL;

        UserData userData = new UserData(email, password, name);

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

        Credentials credentials = new Credentials(email, password);

        return given()
                .header("Content-type", "application/json")
                .body(credentials)
                .when()
                .post("/auth/login");
    }
}