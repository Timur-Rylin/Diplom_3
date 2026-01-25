package com.stellarburgers.tests;

import com.stellarburgers.helpers.BrowserDriver;
import com.stellarburgers.helpers.TestData;
import com.stellarburgers.pages.LoginPage;
import com.stellarburgers.pages.MainPage;
import com.stellarburgers.pages.RegistrationPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class RegistrationTest {
    private WebDriver driver;
    private String testEmail;

    @Before
    public void setUp() {
        driver = BrowserDriver.getDriver("chrome");
        testEmail = TestData.getUniqueEmail();
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void testSuccessfulRegistration() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.setName(TestData.VALID_NAME);
        registrationPage.setEmail(testEmail);
        registrationPage.setPassword(TestData.VALID_PASSWORD);
        registrationPage.clickRegisterButton();

        // Ждем перехода на страницу входа
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LoginPage loginPageAfterRegistration = new LoginPage(driver);
        assertTrue(loginPageAfterRegistration.isEmailInputDisplayed());
    }

    @Test
    @DisplayName("Ошибка для некорректного пароля (меньше 6 символов)")
    public void testRegistrationWithShortPasswordFails() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.setName(TestData.VALID_NAME);
        registrationPage.setEmail(testEmail);
        registrationPage.setPassword(TestData.SHORT_PASSWORD);
        registrationPage.clickRegisterButton();

        String errorMessage = registrationPage.getErrorMessage();
        assertTrue(errorMessage.length() > 0);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}