package com.stellarburgers.helpers;

import io.qameta.allure.junit4.AllureJunit4;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class AllureListener extends BlockJUnit4ClassRunner {

    public AllureListener(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    public void run(RunNotifier notifier) {
        notifier.addListener(new AllureJunit4());
        super.run(notifier);
    }
}