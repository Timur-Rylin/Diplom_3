package com.stellarburgers.tests;

import com.stellarburgers.helpers.AllureListener;
import com.stellarburgers.helpers.BrowserDriver;
import com.stellarburgers.pages.MainPage;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertEquals;

@RunWith(AllureListener.class)
public class ConstructorTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = BrowserDriver.getDriver();
    }

    @Test
    @DisplayName("Переход к разделу «Соусы»")
    @Description("Проверка перехода к разделу 'Соусы' в конструкторе")
    public void testNavigateToSaucesSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickSaucesTab();
        assertEquals("Соусы", mainPage.getActiveTabText());
    }

    @Test
    @DisplayName("Переход к разделу «Начинки»")
    @Description("Проверка перехода к разделу 'Начинки' в конструкторе")
    public void testNavigateToFillingsSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickFillingsTab();
        assertEquals("Начинки", mainPage.getActiveTabText());
    }

    @Test
    @DisplayName("Переход к разделу «Булки»")
    @Description("Проверка перехода к разделу 'Булки' в конструкторе")
    public void testNavigateToBunsSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();
        assertEquals("Булки", mainPage.getActiveTabText());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}