package com.stellarburgers.tests;

import com.stellarburgers.helpers.AllureListener;
import com.stellarburgers.helpers.BrowserDriver;
import com.stellarburgers.helpers.TestData;
import com.stellarburgers.helpers.UserHelper;
import com.stellarburgers.pages.LoginPage;
import com.stellarburgers.pages.MainPage;
import com.stellarburgers.pages.PasswordRecoveryPage;
import com.stellarburgers.pages.RegistrationPage;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertTrue;

@RunWith(AllureListener.class)
public class LoginTest {
    private WebDriver driver;
    private String userEmail;
    private String userPassword;
    private String accessToken;

    @Before
    public void setUp() {
        driver = BrowserDriver.getDriver();
        userEmail = "login_test_" + System.currentTimeMillis() + "@example.com";
        userPassword = "Password123";

        Response response = UserHelper.registerUser(userEmail, userPassword, "LoginUser");
        if (response.statusCode() == 200) {
            accessToken = response.jsonPath().getString("accessToken");
        }
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Description("Проверка входа через кнопку 'Войти в аккаунт' на главной странице")
    public void testLoginViaMainPageLoginButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userEmail, userPassword);

        MainPage mainPageAfterLogin = new MainPage(driver);
        assertTrue(mainPageAfterLogin.isPlaceOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Проверка входа через ссылку 'Личный кабинет' на главной странице")
    public void testLoginViaPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickPersonalAccountLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userEmail, userPassword);

        MainPage mainPageAfterLogin = new MainPage(driver);
        assertTrue(mainPageAfterLogin.isPlaceOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка входа через ссылку 'Войти' на странице регистрации")
    public void testLoginViaRegistrationForm() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickLoginLink();

        LoginPage loginPageFromRegistration = new LoginPage(driver);
        loginPageFromRegistration.login(userEmail, userPassword);

        MainPage mainPageAfterLogin = new MainPage(driver);
        assertTrue(mainPageAfterLogin.isPlaceOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка входа через ссылку 'Войти' на странице восстановления пароля")
    public void testLoginViaPasswordRecoveryForm() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRecoverPasswordLink();
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
        passwordRecoveryPage.clickLoginLink();

        LoginPage loginPageFromRecovery = new LoginPage(driver);
        loginPageFromRecovery.login(userEmail, userPassword);

        MainPage mainPageAfterLogin = new MainPage(driver);
        assertTrue(mainPageAfterLogin.isPlaceOrderButtonDisplayed());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (accessToken != null) {
            UserHelper.deleteUser(accessToken);
        }
    }
}