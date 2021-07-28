package com.thetimmedia.utils;

import com.thetimmedia.driver.Driver;
import com.thetimmedia.platformAPIs.KobitonAPIs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.util.Optional.ofNullable;

/**
 * Init all project properties
 */
public class ProjectPropertiesUtils {
    private static final String KOBITON_USERNAME = "kobitonUserName";
    private static final String KOBITON_TOKEN = "kobitonToken";
    private static final String OS = "os";
    private static final String DEVICE = "device";
    private static final String PLATFORM = "platformVersion";
    private static final String DEFAULT_TIMEOUT = "defaultTimeout";
    private static final String APP_NAME = "appName";


    private static ProjectPropertiesUtils instance;

    private String kobitonUserName;
    private String kobitonToken;
    private String os;
    private String device;
    private String platform;
    private String defaultTimeout;
    private String appName;

    private static Properties properties;

    private static void initialization() {
        InputStream is = null;
        instance = new ProjectPropertiesUtils();
        properties = new Properties();
        try {
            is = ProjectPropertiesUtils.class.getClassLoader().getResourceAsStream("project.properties");
            properties.load(is);
            instance = new ProjectPropertiesUtils();
            instance.setKobitonUserName(System.getProperty(KOBITON_USERNAME, getProperties().getProperty(KOBITON_USERNAME)));
            instance.setKobitonToken(System.getProperty(KOBITON_TOKEN, getProperties().getProperty(KOBITON_TOKEN)));
            instance.setOs(System.getProperty(OS, getProperties().getProperty(OS)));
            instance.setDevice(System.getProperty(DEVICE, getProperties().getProperty(DEVICE)));
            instance.setPlatform(System.getProperty(PLATFORM, getProperties().getProperty(PLATFORM)));
            instance.setDefaultTimeout(System.getProperty(DEFAULT_TIMEOUT, getProperties().getProperty(DEFAULT_TIMEOUT)));
            instance.setAppName(System.getProperty(APP_NAME, getProperties().getProperty(APP_NAME)));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public static synchronized ProjectPropertiesUtils getInstance() {
        if (instance == null) {
            initialization();
        }
        return instance;
    }

    /**
     * generate environment.properties file to represent it in Allure environment section
     */
    public static void createEnvironmentProperties() {
        FileOutputStream fos = null;
        try {
            Properties props = new Properties();
            File directory = new File("target/allure-results");
            if(!directory.exists())
                directory.mkdir();
            fos = new FileOutputStream("target/allure-results/environment.properties");
            ofNullable(props.setProperty("Device", Driver.getDriver().getCapabilities().getCapability("deviceName").toString()));
            ofNullable(props.setProperty("Platform Version", Driver.getDriver().getCapabilities().getCapability("platformVersion").toString()));
            ofNullable(props.setProperty("Kobiton App version", String.valueOf(Driver.getDriver().getCapabilities().getCapability("appVersionId"))));
            ofNullable(props.setProperty("App URL", String.valueOf(Driver.getDriver().getCapabilities().getCapability("appUrl"))));

            ofNullable(props.setProperty("App Name", ProjectPropertiesUtils.getInstance().getAppName()));
            ofNullable(props.setProperty("App version (Bundle Version)", KobitonAPIs.getAppBundleVersion()));
            ofNullable(props.setProperty("Bundle Identifier", KobitonAPIs.getAppBundleIdentifier()));
            ofNullable(props.setProperty("Date Uploaded", KobitonAPIs.getDateUploaded()));

//            ofNullable(props.setProperty("Device Logs", KobitonAPIs.getLogURL())); enable if you want to see logs link in Allure environment section
//            ofNullable(props.setProperty("Video URL", KobitonAPIs.getVideoURL())); enable if you want to see Video link in Allure environment section
            props.store(fos, "See https://docs.qameta.io/allure/#_environment");
        } catch (IOException e) {
            //logger.error("IO problem when writing allure properties file", e);
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getKobitonToken() {
        return kobitonToken;
    }

    public void setKobitonToken(String kobitonToken) {
        this.kobitonToken = kobitonToken;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public static Properties getProperties() {
        return properties;
    }

    public static void setProperties(Properties properties) {
        ProjectPropertiesUtils.properties = properties;
    }

    public static void setInstance(ProjectPropertiesUtils instance) {
        ProjectPropertiesUtils.instance = instance;
    }

    public String getKobitonUserName() {
        return kobitonUserName;
    }

    public void setKobitonUserName(String kobitonUserName) {
        this.kobitonUserName = kobitonUserName;
    }

    public String getDefaultTimeout() {
        return defaultTimeout;
    }

    public void setDefaultTimeout(String defaultTimeout) {
        this.defaultTimeout = defaultTimeout;
    }

  public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
