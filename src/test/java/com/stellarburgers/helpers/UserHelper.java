package com.stellarburgers.helpers;

import org.openqa.selenium.WebDriver;
import com.stellarburgers.pages.*;

public class UserHelper {

    public static void registerUser(WebDriver driver) {
        // Генерируем уникальный email
        String email = TestData.getUniqueEmail();
        System.out.println("Регистрируем пользователя с email: " + email);

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.setName(TestData.VALID_NAME);
        registrationPage.setEmail(email);
        registrationPage.setPassword(TestData.VALID_PASSWORD);
        registrationPage.clickRegisterButton();

        // Ждем перехода на страницу входа
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Сохраняем email для использования в тестах входа
        TestData.EXISTING_USER_EMAIL = email;
        System.out.println("Зарегистрирован пользователь: " + email);
    }
}