package com.stellarburgers.tests;

import com.stellarburgers.helpers.BrowserDriver;
import com.stellarburgers.helpers.TestData;
import com.stellarburgers.helpers.UserHelper;
import com.stellarburgers.pages.LoginPage;
import com.stellarburgers.pages.MainPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

import static org.junit.Assert.assertTrue;

public class LoginTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = BrowserDriver.getDriver("chrome");
        UserHelper.registerUser(driver);
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void testLoginViaMainPageLoginButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(TestData.EXISTING_USER_EMAIL, TestData.EXISTING_USER_PASSWORD);

        MainPage mainPageAfterLogin = new MainPage(driver);
        assertTrue(mainPageAfterLogin.isPlaceOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void testLoginViaPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickPersonalAccountLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(TestData.EXISTING_USER_EMAIL, TestData.EXISTING_USER_PASSWORD);

        MainPage mainPageAfterLogin = new MainPage(driver);
        assertTrue(mainPageAfterLogin.isPlaceOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void testLoginViaRegistrationForm() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        driver.findElement(By.xpath("//a[text()='Войти']")).click();

        LoginPage loginPageFromRegistration = new LoginPage(driver);
        loginPageFromRegistration.login(TestData.EXISTING_USER_EMAIL, TestData.EXISTING_USER_PASSWORD);

        MainPage mainPageAfterLogin = new MainPage(driver);
        assertTrue(mainPageAfterLogin.isPlaceOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void testLoginViaPasswordRecoveryForm() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRecoverPasswordLink();

        driver.findElement(By.xpath("//a[text()='Войти']")).click();

        LoginPage loginPageFromRecovery = new LoginPage(driver);
        loginPageFromRecovery.login(TestData.EXISTING_USER_EMAIL, TestData.EXISTING_USER_PASSWORD);

        MainPage mainPageAfterLogin = new MainPage(driver);
        assertTrue(mainPageAfterLogin.isPlaceOrderButtonDisplayed());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}