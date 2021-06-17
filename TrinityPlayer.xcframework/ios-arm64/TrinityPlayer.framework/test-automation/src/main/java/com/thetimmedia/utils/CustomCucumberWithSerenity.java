package com.thetimmedia.utils;

import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import java.io.IOException;

/**
 * Custom runner for tests
 */
public class CustomCucumberWithSerenity extends CucumberWithSerenity {
    public CustomCucumberWithSerenity(Class clazz) throws InitializationError, IOException {
        super(clazz);
    }

    /**
     * add Custom listener
     * @param notifier
     */
    @Override
    public void run (RunNotifier notifier){
        notifier.addListener(new CustomRunListener());
        super.run(notifier);
    }
}
