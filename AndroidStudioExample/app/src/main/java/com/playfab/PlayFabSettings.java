package com.playfab;

import java.lang.StringBuilder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import com.playfab.PlayFabErrors.ErrorCallback;

public class PlayFabSettings {
    public static String SdkVersion = "0.76.180917";
    public static String BuildIdentifier = "jbuild_javasdk__sdk-slave2016-1_1";
    public static String SdkVersionString = "JavaSDK-0.76.180917";

    public static Map<String, String> RequestGetParams;
    static {
        Map<String, String> getParams = new HashMap<String, String>();
        getParams.put("sdk", SdkVersionString);
        RequestGetParams = Collections.unmodifiableMap(getParams);
    }

    public static String ProductionEnvironmentUrl = ".playfabapi.com"; // This is only for customers running a private cluster.  Generally you shouldn't touch this
    public static String VerticalName = null; // The name of a customer vertical. This is only for customers running a private cluster. Generally you shouldn't touch this
    public static String TitleId = null; // You must set this value for PlayFabSdk to work properly (Found in the Game Manager for your title, at the PlayFab Website)
    public static ErrorCallback GlobalErrorHandler;
    public static String EntityToken = null; // Set by GetEntityToken
    public static String DeveloperSecretKey = null; // You must set this value for PlayFabSdk to work properly (Found in the Game Manager for your title, at the PlayFab Website)
    public static String ClientSessionTicket = null; // Set by any Client Login Call
    public static String AdvertisingIdType = null; // Set this to the appropriate AD_TYPE_X constant below
    public static String AdvertisingIdValue = null; // Set this to corresponding device value
    public static Context androidContext = null; // Context object for android devices

    // DisableAdvertising is provided for completeness, but changing it is not suggested
    // Disabling this may prevent your advertising-related PlayFab marketplace partners from working correctly
    public static Boolean DisableAdvertising = false;
    public static final String AD_TYPE_IDFA = "Idfa";
    public static final String AD_TYPE_ANDROID_ID = "Adid";

    public static String GetURL(String apiCall) {
        StringBuilder sb = new StringBuilder(1000);

        String baseUrl = ProductionEnvironmentUrl;
        if (!baseUrl.startsWith("http")) {
            if (VerticalName != null) {
                sb.append("https://").append(VerticalName);
            } else {
                sb.append("https://").append(TitleId);
            }
        }

        sb.append(baseUrl).append(apiCall);

        boolean firstParam = true;
        for (Map.Entry paramPair : RequestGetParams.entrySet()) {
            if (firstParam) {
                sb.append("?");
                firstParam = false;
            } else {
                sb.append("&");
            }
            sb.append(paramPair.getKey()).append("=").append(paramPair.getValue());
        }

        return sb.toString();
    }
}
