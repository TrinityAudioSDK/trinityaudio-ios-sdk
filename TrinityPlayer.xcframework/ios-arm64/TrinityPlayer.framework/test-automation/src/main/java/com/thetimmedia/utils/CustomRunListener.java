package com.thetimmedia.utils;

import net.thucydides.core.steps.StepEventBus;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;


public class CustomRunListener extends RunListener {

    /**
     * register custom Steps listener
     * @param description
     */
    @Override
    public void testStarted(Description description) {
        StepEventBus.getEventBus().dropAllListeners();
        StepEventBus.getEventBus().registerListener(new StepMonitor());
    }

    @Override
    public void testRunStarted(Description description) {
    }

    @Override
    public void testFailure(Failure failure) {

    }
}
