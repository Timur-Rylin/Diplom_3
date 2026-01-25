package com.stellarburgers.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//button[text()='Войти в аккаунт']")
    private WebElement loginAccountButton;

    @FindBy(xpath = "//a[@href='/account']")
    private WebElement personalAccountLink;

    @FindBy(xpath = "//button[text()='Оформить заказ']")
    private WebElement placeOrderButton;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("https://stellarburgers.education-services.ru/");
        wait.until(ExpectedConditions.visibilityOf(loginAccountButton));
    }

    public void clickLoginAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginAccountButton));
        loginAccountButton.click();
    }

    public void clickPersonalAccountLink() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountLink));
        personalAccountLink.click();
    }

    public boolean isPlaceOrderButtonDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(placeOrderButton));
            return placeOrderButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginButtonDisplayed() {
        try {
            return loginAccountButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}