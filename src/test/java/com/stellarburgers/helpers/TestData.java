package com.stellarburgers.helpers;

import java.util.concurrent.ThreadLocalRandom;

public class TestData {
    public static final String BASE_URL = "https://stellarburgers.education-services.ru/";

    // Генерируем новые данные при каждом обращении
    private static int getRandomNumber() {
        return ThreadLocalRandom.current().nextInt(10000, 99999);
    }

    public static final String VALID_NAME = "ТестовыйПользователь";
    public static final String VALID_PASSWORD = "Secret123";
    public static final String SHORT_PASSWORD = "12345";

    // Для статического использования
    public static String getUniqueEmail() {
        return "testuser_" + getRandomNumber() + "_" + System.currentTimeMillis() + "@example.com";
    }

    // Для динамического использования (будет установлено после регистрации)
    public static String EXISTING_USER_EMAIL = "";
    public static final String EXISTING_USER_PASSWORD = VALID_PASSWORD;
}