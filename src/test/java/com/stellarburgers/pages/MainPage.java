package com.stellarburgers.pages;

import io.qameta.allure.Step;
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

    @FindBy(xpath = "//span[text()='Булки']/parent::div")
    private WebElement bunsTab;

    @FindBy(xpath = "//span[text()='Соусы']/parent::div")
    private WebElement saucesTab;

    @FindBy(xpath = "//span[text()='Начинки']/parent::div")
    private WebElement fillingsTab;

    @FindBy(xpath = "//div[contains(@class, 'tab_tab_type_current__')]")
    private WebElement activeTab;

    @FindBy(xpath = "//h1[contains(text(), 'Соберите бургер')]")
    private WebElement pageHeader;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    @Step("Открыть главную страницу")
    public void open() {
        driver.get("https://stellarburgers.education-services.ru/");
        wait.until(ExpectedConditions.visibilityOf(pageHeader));
    }

    @Step("Кликнуть по кнопке 'Войти в аккаунт'")
    public void clickLoginAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginAccountButton)).click();
    }

    @Step("Кликнуть по ссылке 'Личный кабинет'")
    public void clickPersonalAccountLink() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountLink)).click();
    }

    @Step("Кликнуть по вкладке 'Булки'")
    public void clickBunsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsTab)).click();
    }

    @Step("Кликнуть по вкладке 'Соусы'")
    public void clickSaucesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesTab)).click();
    }

    @Step("Кликнуть по вкладке 'Начинки'")
    public void clickFillingsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsTab)).click();
    }

    @Step("Получить текст активной вкладки")
    public String getActiveTabText() {
        return wait.until(ExpectedConditions.visibilityOf(activeTab)).getText();
    }

    @Step("Проверить отображение кнопки 'Оформить заказ'")
    public boolean isPlaceOrderButtonDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(placeOrderButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}