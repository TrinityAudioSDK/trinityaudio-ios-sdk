package com.thetimmedia.utils;

import com.google.common.collect.ImmutableMap;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.thetimmedia.driver.Driver;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import net.minidev.json.JSONArray;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.nio.file.InvalidPathException;
import java.time.Duration;
import java.util.*;

/**
 * Class with common actions, used during testing
 */
public class CommonSteps {

    public static void switchToWebView() {
        Set<String> contextNames = null;
        while (true) {
            contextNames = Driver.getDriver().getContextHandles();
            if (contextNames.size() > 1) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Driver.getDriver().context(contextNames.toArray()[contextNames.size()-1].toString());
    }

    public static void switchToMainApp() {
        for (int i = 0; i < 5; i++) {
            Driver.getDriver().context("NATIVE_APP");
            if ("NATIVE_APP".equals(Driver.getDriver().getContext())) {
                return;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private static byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

    @Attachment(value = "Page source", type = "text/html", fileExtension = "txt")
    private static byte[] savePageSource(byte[] pageSource) {
        return pageSource;
    }

    public static void screenshot() {
        saveScreenshot(((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES));
    }

    public static void savePageSource() {
        savePageSource(Driver.getDriver().getPageSource().getBytes());
    }


    @Step ("Swipe up")
    public static void swipeUp () {
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "up");
        params.put("velocity", 2500);
        Driver.getDriver().executeScript("mobile: swipe", params);
    }

    @Step ("Swipe down")
    public static void swipeDown () {
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "down");
        params.put("velocity", 2500);
        Driver.getDriver().executeScript("mobile: swipe", params);
    }

    public static void captureAudio(Duration duration) throws InterruptedException {
        Driver.getDriver().executeScript("mobile: startAudioRecording", ImmutableMap.of("audioInput", ":1"));
        Thread.sleep(duration.toMillis());
        byte[] mp4Data = Base64.getMimeDecoder()
                .decode((String) Driver.getDriver().executeScript("mobile: stopAudioRecording"));
        saveAudio(mp4Data);
    }

    @Attachment(value = "Audio", type = "audio")
    private static byte[] saveAudio(byte[] audio) {
        return audio;
    }

    public static String getDataFromResponse(String response, String path) {
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(response);
        String result = null;
        try {
            Object dataResult = JsonPath.read(document, path);
            if (dataResult instanceof JSONArray) {
                JSONArray jSONArray = (JSONArray) dataResult;
                dataResult = jSONArray.size() > 0 ? jSONArray.get(0) : "";
            }
            result = dataResult.toString();
        } catch (InvalidPathException ie) {
            // do nothing just return null if path not found
        } catch (PathNotFoundException e) {
            // do nothing just return null if path not found
        }
        return result;
    }

    public static void switchToFrameIfNeeded() {
        if ("NATIVE_APP".equals(Driver.getDriver().getContext())) {
            Waiters.waitForJStoLoad();
            CommonSteps.switchToWebView();
        }
        String selfName = "";
        for (int i = 0; i < Integer.parseInt(ProjectPropertiesUtils.getInstance().getDefaultTimeout()); i++) {
            try {
                selfName = Driver.getDriver().executeScript("return self.name").toString().toLowerCase();
                break;
            } catch (Exception e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                // retry switch to webview
                switchToMainApp();
                switchToWebView();
                Driver.getDriver().switchTo().defaultContent();
            }
        }
//        if (!selfName.equals("Trinity Player")) { // document.title
        if (!selfName.equals("frame")) {
            for (int i = 0; i < Integer.parseInt(ProjectPropertiesUtils.getInstance().getDefaultTimeout()); i++) {
                try {
                    for (int j = 0; j < 5 && selfName.isEmpty(); j++) {
                        Waiters.waitForJStoLoad();
                        Driver.getDriver().switchTo().frame(0);
                        selfName = Driver.getDriver().executeScript("return self.name").toString().toLowerCase();
                    }
                    return;
                } catch (Exception e) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

}
