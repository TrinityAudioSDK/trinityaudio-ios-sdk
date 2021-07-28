package com.thetimmedia.driver;

import com.thetimmedia.utils.ProjectPropertiesUtils;
import io.appium.java_client.AppiumDriver;

import java.util.concurrent.TimeUnit;

public class Driver {
    private static AppiumDriver driver;

    public static AppiumDriver getDriver() {
        return driver;
    }

    public static void setImplicitlyWait (int seconds) {
        Driver.getDriver().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public static void setDefaultImplicitlyWait () {
        Driver.getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(ProjectPropertiesUtils.getInstance().getDefaultTimeout()), TimeUnit.SECONDS);
    }

    public static void setDriver(AppiumDriver driver) {
        Driver.driver = driver;
    }
}
