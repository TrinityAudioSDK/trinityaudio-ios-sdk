package com.thetimmedia.runner;

import com.thetimmedia.driver.Driver;
import com.thetimmedia.utils.CustomCucumberWithSerenity;
import com.thetimmedia.utils.ProjectPropertiesUtils;
import cucumber.api.CucumberOptions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

//Uncomment following for local test
//@RunWith(CustomCucumberWithSerenity.class)
//@CucumberOptions(glue = { "com.thetimmedia.stepdefinitions" },
//                 features = { "src/test/resources/features" },
//                 plugin = { "io.qameta.allure.cucumber2jvm.AllureCucumber2Jvm" },
//                 tags = { "@MainSDKUsage" })
public class RunnerLocalTest {

    @BeforeClass
    public static void beforeClass() throws MalformedURLException {
        String serverURL = "http://10.10.205.250:4723/wd/hub";  // put IP address to Appium server
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        capabilities.setCapability("deviceName", "iPhone 8");
        capabilities.setCapability("platformVersion", "14.4");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("app", "/Users/proun/Desktop/SeleniumNode/TrinityPlayerDemoApp.app"); // App path on PC where Appium started

        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        capabilities.setCapability("useNewWDA", false);
        capabilities.setCapability("wdaStartupRetries", "4");
        capabilities.setCapability("waitForIdleTimeout", "600000");
        capabilities.setCapability("newCommandTimeout", "600000");

        AppiumDriver driver = new IOSDriver(new URL(serverURL), capabilities);
        driver.setSetting("elementResponseAttributes", "attribute/class");

        if (driver != null) {
            Driver.setDriver(driver);
            Driver.setDefaultImplicitlyWait();
        }
    }

    @After
    public void afterTest() {
        Driver.getDriver().closeApp();
    }

    @AfterClass
    public static void afterClass() {
        if (Driver.getDriver() != null) {
            Driver.getDriver().quit();
            ProjectPropertiesUtils.createEnvironmentProperties();
        }
    }
}
