package com.drew.tikitihub.api;

public class B2CSettings {
    public static String initiatorName;
    public static String securityCredential;
    public static String commandId;
    public static String amount;
    public static String partyA;
    public static String partyB;
    public static String remarks;
    public static String timeOutUrl;
    public static String resultUrl;
    public static String b2cUrl;

    public static String getInitiatorName() {
        return initiatorName;
    }

    public static void setInitiatorName(String initiatorName) {
        B2CSettings.initiatorName = initiatorName;
    }

    public static String getSecurityCredential() {
        return securityCredential;
    }

    public static void setSecurityCredential(String securityCredential) {
        B2CSettings.securityCredential = securityCredential;
    }

    public static String getCommandId() {
        return commandId;
    }

    public static void setCommandId(String commandId) {
        B2CSettings.commandId = commandId;
    }

    public static String getAmount() {
        return amount;
    }

    public static void setAmount(String amount) {
        B2CSettings.amount = amount;
    }

    public static String getPartyA() {
        return partyA;
    }

    public static void setPartyA(String partyA) {
        B2CSettings.partyA = partyA;
    }

    public static String getPartyB() {
        return partyB;
    }

    public static void setPartyB(String partyB) {
        B2CSettings.partyB = partyB;
    }

    public static String getRemarks() {
        return remarks;
    }

    public static void setRemarks(String remarks) {
        B2CSettings.remarks = remarks;
    }

    public static String getTimeOutUrl() {
        return timeOutUrl;
    }

    public static void setTimeOutUrl(String timeOutUrl) {
        B2CSettings.timeOutUrl = timeOutUrl;
    }

    public static String getResultUrl() {
        return resultUrl;
    }

    public static void setResultUrl(String resultUrl) {
        B2CSettings.resultUrl = resultUrl;
    }

    public static String getB2cUrl() {
        return b2cUrl;
    }

    public static void setB2cUrl(String b2cUrl) {
        B2CSettings.b2cUrl = b2cUrl;
    }
}
