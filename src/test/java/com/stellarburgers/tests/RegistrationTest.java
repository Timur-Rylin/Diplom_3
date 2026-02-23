package com.stellarburgers.tests;

import com.stellarburgers.helpers.AllureListener;
import com.stellarburgers.helpers.BrowserDriver;
import com.stellarburgers.helpers.TestData;
import com.stellarburgers.helpers.UserHelper;
import com.stellarburgers.pages.LoginPage;
import com.stellarburgers.pages.MainPage;
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
public class RegistrationTest {
    private WebDriver driver;
    private String userEmail;
    private String userPassword;
    private String accessToken;

    @Before
    public void setUp() {
        driver = BrowserDriver.getDriver();
        userEmail = TestData.VALID_EMAIL;
        userPassword = TestData.VALID_PASSWORD;
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка успешной регистрации нового пользователя с валидными данными")
    public void testSuccessfulRegistration() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.setName(TestData.VALID_NAME);
        registrationPage.setEmail(userEmail);
        registrationPage.setPassword(userPassword);
        registrationPage.clickRegisterButton();

        LoginPage loginPageAfterRegistration = new LoginPage(driver);
        assertTrue(loginPageAfterRegistration.isEmailInputDisplayed());

        Response response = UserHelper.loginUser(userEmail, userPassword);
        if (response.statusCode() == 200) {
            accessToken = response.jsonPath().getString("accessToken");
        }
    }

    @Test
    @DisplayName("Ошибка для некорректного пароля (меньше 6 символов)")
    @Description("Проверка появления ошибки при регистрации с паролем меньше 6 символов")
    public void testRegistrationWithShortPasswordFails() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.setName(TestData.VALID_NAME);
        registrationPage.setEmail(userEmail);
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
        if (accessToken != null) {
            UserHelper.deleteUser(accessToken);
        }
    }
}