package com.thetimmedia.runner;

import com.thetimmedia.driver.Driver;
import com.thetimmedia.platformAPIs.KobitonAPIs;
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

//@RunWith(CustomCucumberWithSerenity.class)
//@CucumberOptions(glue = { "com.thetimmedia.stepdefinitions" },
//                 features = { "src/test/resources/features" },
//                 plugin = { "io.qameta.allure.cucumber2jvm.AllureCucumber2Jvm" },
//                 tags = { "@MainSDKUsage" })
public class RunnerKobitonTest {

    @BeforeClass
    public static void beforeClass() throws MalformedURLException {

        String username = ProjectPropertiesUtils.getInstance().getKobitonUserName();
        String token = ProjectPropertiesUtils.getInstance().getKobitonToken();
        String kobitonServerUrl = "https://" + username + ":" + token + "@api.kobiton.com/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        // The generated session will be visible to you only. In case you want this session available for other users, please assign this device to specific group.
        capabilities.setCapability("sessionName", "Automation test session");
        capabilities.setCapability("sessionDescription", "");
        capabilities.setCapability("deviceOrientation", "portrait");
        capabilities.setCapability("captureScreenshots", true);
        //        capabilities.setCapability("browserName", "safari");
        capabilities.setCapability("deviceGroup", "KOBITON");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        // For deviceName, platformVersion Kobiton supports wildcard
        // character *, with 3 formats: *text, text* and *text*
        // If there is no *, Kobiton will match the exact text provided
        capabilities.setCapability("deviceName", ProjectPropertiesUtils.getInstance().getDevice());
        capabilities.setCapability("platformVersion", ProjectPropertiesUtils.getInstance().getPlatform());
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("app", "kobiton-store:v" + KobitonAPIs.getLatestAppVersionId());
        capabilities.setCapability("webviewConnectTimeout", "3000");

        // setting this to avoid iOS notifications on account verification as per Kobiton suuport
        capabilities.setCapability("autoAcceptAlerts", true);
        capabilities.setCapability("autoDismissAlerts", true);

        // Initialise the remote Webdriver using Kobiton remote URL
        // and desired capabilities defined above
        AppiumDriver driver = new IOSDriver(new URL(kobitonServerUrl), capabilities);
        if (driver != null) {
            driver.setSetting("elementResponseAttributes", "attribute/class");
            Driver.setDriver(driver);
            Driver.setDefaultImplicitlyWait();
        }
    }

    @AfterClass
    public static void afterClass() {
        if (Driver.getDriver() != null) {
            ProjectPropertiesUtils.createEnvironmentProperties();
            Driver.getDriver().quit();
        }
    }

    @After
    public void afterTest() {
        Driver.getDriver().closeApp();
    }
}
