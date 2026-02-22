package com.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//h2[text()='Вход']")
    private WebElement loginHeader;

    @FindBy(xpath = "//input[@name='name']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@name='Пароль']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[text()='Войти']")
    private WebElement loginButton;

    @FindBy(xpath = "//a[text()='Зарегистрироваться']")
    private WebElement registerLink;

    @FindBy(xpath = "//a[text()='Восстановить пароль']")
    private WebElement recoverPasswordLink;

    @FindBy(xpath = "//p[contains(@class, 'input__error')]")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @Step("Ввести email: {email}")
    public void setEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailField.clear();
        emailField.sendKeys(email);
    }

    @Step("Ввести пароль")
    public void setPassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    @Step("Кликнуть по кнопке 'Войти'")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Кликнуть по ссылке 'Зарегистрироваться'")
    public void clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    @Step("Кликнуть по ссылке 'Восстановить пароль'")
    public void clickRecoverPasswordLink() {
        wait.until(ExpectedConditions.elementToBeClickable(recoverPasswordLink)).click();
    }

    @Step("Выполнить вход с email: {email}")
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }

    @Step("Проверить отображение поля ввода email")
    public boolean isEmailInputDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(emailInput)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверить отображение заголовка страницы входа")
    public boolean isLoginHeaderDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(loginHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Получить текст сообщения об ошибке")
    public String getErrorMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
        } catch (Exception e) {
            return "";
        }
    }
}