package com.thetimmedia.platformAPIs;

import com.thetimmedia.driver.Driver;
import com.thetimmedia.utils.CommonSteps;
import com.thetimmedia.utils.ProjectPropertiesUtils;

import java.io.*;
import java.net.*;
import java.util.Base64;

public class KobitonAPIs {

    static String username = ProjectPropertiesUtils.getInstance().getKobitonUserName();
    static String apiKey = ProjectPropertiesUtils.getInstance().getKobitonToken();

    static String generateBasicAuth() {
        String authString = username + ":" + apiKey;
        byte[] authEncBytes = Base64.getEncoder().encode(authString.getBytes());
        String authStringEnc = new String(authEncBytes);
        return "Basic " + authStringEnc;
    }

    private static String getApps() {
        try {
            URL obj = new URL("https://api.kobiton.com/v1/apps");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", generateBasicAuth());
            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    public static String getLatestAppVersionId() {
        String apps = getApps();
        return CommonSteps.getDataFromResponse(apps,
                "$.apps[?(@.name=='"+ProjectPropertiesUtils.getInstance().getAppName()+"')].versions[0].id");
    }

    public static String getAppBundleVersion() {
        String apps = getApps();
        return CommonSteps.getDataFromResponse(apps,
                "$.apps[?(@.name=='"+ProjectPropertiesUtils.getInstance().getAppName()+"')].versions[0].version");
    }

    public static String getAppBundleIdentifier() {
        String apps = getApps();
        return CommonSteps.getDataFromResponse(apps,
                "$.apps[?(@.name=='"+ProjectPropertiesUtils.getInstance().getAppName()+"')].versions[0].nativeProperties.CFBundleIdentifier");
    }

    public static String getDateUploaded() {
        String apps = getApps();
        return CommonSteps.getDataFromResponse(apps,
                "$.apps[?(@.name=='"+ProjectPropertiesUtils.getInstance().getAppName()+"')].versions[0].createdAt");
    }
    public static String getLogURL() throws IOException {
        String toReturn = "";
        // 30 sec wait for link appearance (ping every 1 second)
        for (int i = 0; i < 30; i++) {
            String sessionDetails = getSessionDetails();
            if (sessionDetails == null || sessionDetails.isEmpty()) {
                return "";
            }
            toReturn = CommonSteps.getDataFromResponse(sessionDetails, "$.log.previewUrl");
            if (toReturn!=null) {
                return toReturn;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "log URL not available";
    }

    public static String getVideoURL() throws IOException {
        String toReturn = "";
        // 30 sec wait for link appearance (ping every 1 second)
        for (int i = 0; i < 30; i++) {
            String sessionDetails = getSessionDetails();
            if (sessionDetails == null || sessionDetails.isEmpty()) {
                return "";
            }
            toReturn = CommonSteps.getDataFromResponse(sessionDetails, "$.video.path");
            if (toReturn!=null) {
                return toReturn;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "video URL not available";
    }

    private static String getSessionDetails() throws IOException {
        Object session = Driver.getDriver().getCapabilities().getCapability("kobitonSessionId");
        if (session == null) {
            return "";
        }
        String sessionId = session.toString();
        URL obj = new URL("https://api.kobiton.com/v1/sessions/" + sessionId);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", generateBasicAuth());
        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
