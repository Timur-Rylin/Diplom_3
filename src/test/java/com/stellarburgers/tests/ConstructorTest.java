package com.stellarburgers.tests;

import com.stellarburgers.helpers.BrowserDriver;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class ConstructorTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        driver = BrowserDriver.getDriver("chrome");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    @DisplayName("Переход к разделу «Булки»")
    public void testNavigateToBunsSection() throws InterruptedException {
        driver.get("https://stellarburgers.education-services.ru/");

        WebElement saucesTab = driver.findElement(By.xpath("//span[text()='Соусы']/parent::div"));
        saucesTab.click();
        Thread.sleep(500);

        WebElement bunsTab = driver.findElement(By.xpath("//span[text()='Булки']/parent::div"));
        bunsTab.click();
        Thread.sleep(500);

        WebElement activeTab = driver.findElement(By.xpath("//div[contains(@class, 'current')]"));
        assertEquals("Булки", activeTab.getText());
    }

    @Test
    @DisplayName("Переход к разделу «Соусы»")
    public void testNavigateToSaucesSection() throws InterruptedException {
        driver.get("https://stellarburgers.education-services.ru/");

        WebElement saucesTab = driver.findElement(By.xpath("//span[text()='Соусы']/parent::div"));
        saucesTab.click();
        Thread.sleep(500);

        WebElement activeTab = driver.findElement(By.xpath("//div[contains(@class, 'current')]"));
        assertEquals("Соусы", activeTab.getText());
    }

    @Test
    @DisplayName("Переход к разделу «Начинки»")
    public void testNavigateToFillingsSection() throws InterruptedException {
        driver.get("https://stellarburgers.education-services.ru/");

        WebElement fillingsTab = driver.findElement(By.xpath("//span[text()='Начинки']/parent::div"));
        fillingsTab.click();
        Thread.sleep(500);

        WebElement activeTab = driver.findElement(By.xpath("//div[contains(@class, 'current')]"));
        assertEquals("Начинки", activeTab.getText());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}