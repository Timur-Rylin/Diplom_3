package com.stellarburgers.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

public class AllureSimpleTest {

    @Test
    @DisplayName("Проверка работы Allure - тест 1")
    public void testAllure1() {
        System.out.println("Тест 1 выполняется");
        assert 1 == 1;
    }

    @Test
    @DisplayName("Проверка работы Allure - тест 2")
    public void testAllure2() {
        System.out.println("Тест 2 выполняется");
        assert "test".equals("test");
    }
}