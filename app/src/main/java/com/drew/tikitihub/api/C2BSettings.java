package com.drew.tikitihub.api;

public class C2BSettings {
    public static String shortCode;
    public static String commandId;
    public static String amount;
    public static String MSISDN;
    public static String billRef;
    public static String c2bUrl;

    public static String getShortCode() {
        return shortCode;
    }

    public static void setShortCode(String shortCode) {
        C2BSettings.shortCode = shortCode;
    }

    public static String getCommandId() {
        return commandId;
    }

    public static void setCommandId(String commandId) {
        C2BSettings.commandId = commandId;
    }

    public static String getAmount() {
        return amount;
    }

    public static void setAmount(String amount) {
        C2BSettings.amount = amount;
    }

    public static String getMSISDN() {
        return MSISDN;
    }

    public static void setMSISDN(String MSISDN) {
        C2BSettings.MSISDN = MSISDN;
    }

    public static String getBillRef() {
        return billRef;
    }

    public static void setBillRef(String billRef) {
        C2BSettings.billRef = billRef;
    }

    public static String getC2bUrl() {
        return c2bUrl;
    }

    public static void setC2bUrl(String c2bUrl) {
        C2BSettings.c2bUrl = c2bUrl;
    }
}
