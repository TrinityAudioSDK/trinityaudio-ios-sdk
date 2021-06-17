package com.thetimmedia.utils;

import com.thetimmedia.driver.Driver;
import com.thetimmedia.fielddecorator.CustomAbstractElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Waiters for different element conditions
 */
public class Waiters {
    public static void waitAppearanceOf(int limitInSeconds, CustomAbstractElement element) {
        By by = element.getBy();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), limitInSeconds);
        wait.ignoring(StaleElementReferenceException.class, InvalidElementStateException.class)
                .ignoring(NoSuchElementException.class, NotFoundException.class).pollingEvery(Duration.ofSeconds(1))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitForTextToBe(int limitInSeconds, CustomAbstractElement element, String text) {
        By by = element.getBy();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), (long) limitInSeconds);
        wait.pollingEvery(Duration.ofSeconds(1))
                .ignoring(StaleElementReferenceException.class, IllegalMonitorStateException.class)
                .until(ExpectedConditions.textToBePresentInElementLocated(by, text));
    }

    public static void waitDisappearanceOf(int limitInSeconds, CustomAbstractElement element) {
        Driver.setImplicitlyWait(0);
        By by = element.getBy();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), (long) limitInSeconds);
        wait.pollingEvery(Duration.ofSeconds(1))
                .ignoring(StaleElementReferenceException.class, IllegalMonitorStateException.class)
                .until(ExpectedConditions.invisibilityOfElementLocated(by));
        Driver.setDefaultImplicitlyWait();
    }

    public static void waitForJStoLoad() {
        //for web view only
        if (!"NATIVE_APP".equals(Driver.getDriver().getContext())) {
            // wait for Javascript to load
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Integer.parseInt(ProjectPropertiesUtils.getInstance().getDefaultTimeout()));
            wait.ignoring(StaleElementReferenceException.class, IllegalMonitorStateException.class)
                    .until(readyStateCondition());
        }
    }

    private static ExpectedCondition<Boolean> readyStateCondition() {
        return driver -> {
            Boolean result = false;
            try {
                result = (Boolean) Driver.getDriver().executeScript("return document.readyState==\"complete\";");
            } catch (Exception e) {}
            return result;
        };
    }
}
