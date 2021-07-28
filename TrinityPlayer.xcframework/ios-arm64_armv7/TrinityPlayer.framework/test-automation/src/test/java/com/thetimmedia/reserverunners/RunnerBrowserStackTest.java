package com.thetimmedia.reserverunners;

public class RunnerBrowserStackTest {

//    public static IOSDriver<IOSElement> driver;
//
//    @BeforeClass
//    public static void beforeClass () throws MalformedURLException {
//
////
////        curl -u "alexandrp1:U6cnxNJgTuCKpTefZzXn" \
////        -X POST "https://api-cloud.browserstack.com/app-automate/upload" \
////        -F "file=@/path/to/app/file/application-debug.ipa"
////
////        Response Example:
////        {
////            "app_url":"bs://f7c874f21852ba57957a3fdc33f47514288c4ba4"
////        }
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        // Set your access credentials
//        capabilities.setCapability("browserstack.user", "alexandrp1");
//        capabilities.setCapability("browserstack.key", "U6cnxNJgTuCKpTefZzXn");
//
//        // Set URL of the application under test
//        capabilities.setCapability("app", "bs://482976a6a01b8f3bea9ee6a7cd86df399f9659c9");
//
//        // Specify device and os_version for testing
//        capabilities.setCapability("device", "iPhone 12 Pro Max");
//        capabilities.setCapability("os_version", "14");
//
//        // Set other BrowserStack capabilities
//        capabilities.setCapability("project", "First Java Project");
//        capabilities.setCapability("build", "Java iOS");
//        capabilities.setCapability("name", "first_test");
//        capabilities.setCapability("browserstack.networkLogs", true);
//
//        capabilities.setCapability("waitForIdleTimeout", "10000");
//        capabilities.setCapability("wdaEventloopIdleDelay", "2");
////        capabilities.setCapability("iosInstallPause", "5000");
//        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
//        capabilities.setCapability("browserstack.appium_version", "1.19.1");
//
//        capabilities.setCapability("launchTimeout", "20000");
//        capabilities.setCapability("idleTimeout", "10");
//
//        // Initialise the remote Webdriver using BrowserStack remote URL
//        // and desired capabilities defined above
//        driver = new IOSDriver<>(
//                new URL("http://hub-cloud.browserstack.com/wd/hub"), capabilities);
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//
//    }
//
//    @AfterClass
//    public static void afterClass () {
//        driver.quit();
//    }
//
//    @Test
//    public void firstTest(){
//        driver.findElementByIosNsPredicate("type == 'XCUIElementTypeButton' AND label == 'Main SDK Usage' ").click();
//        JavascriptExecutor js = driver;
//        Map<String, Object> params = new HashMap<>();
//        params.put("direction", "down");
//        params.put("velocity", 2500);
//        params.put("element", driver.findElementByIosNsPredicate("type == 'XCUIElementTypeButton' AND label == 'Apply' ").getId());
//        js.executeScript("mobile: swipe" , params);
//        driver.findElementByIosNsPredicate("type == 'XCUIElementTypeButton' AND label == 'Back' ").click();
//    }
}
