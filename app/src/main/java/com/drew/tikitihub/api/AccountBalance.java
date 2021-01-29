package com.drew.tikitihub.api;

public class AccountBalance {
    public static String initiatorName;
    public static String securityCredential;
    public static String commandId;
    public static String amount;
    public static String partyA;
    public static String identifierType;
    public static String remarks;
    public static String timeOutUrl;
    public static String resultUrl;
    public static String accountBalanceUrl;

    public static String getInitiatorName() {
        return initiatorName;
    }

    public static void setInitiatorName(String initiatorName) {
        AccountBalance.initiatorName = initiatorName;
    }

    public static String getSecurityCredential() {
        return securityCredential;
    }

    public static void setSecurityCredential(String securityCredential) {
        AccountBalance.securityCredential = securityCredential;
    }

    public static String getCommandId() {
        return commandId;
    }

    public static void setCommandId(String commandId) {
        AccountBalance.commandId = commandId;
    }

    public static String getAmount() {
        return amount;
    }

    public static void setAmount(String amount) {
        AccountBalance.amount = amount;
    }

    public static String getPartyA() {
        return partyA;
    }

    public static void setPartyA(String partyA) {
        AccountBalance.partyA = partyA;
    }

    public static String getIdentifierType() {
        return identifierType;
    }

    public static void setIdentifierType(String identifierType) {
        AccountBalance.identifierType = identifierType;
    }

    public static String getRemarks() {
        return remarks;
    }

    public static void setRemarks(String remarks) {
        AccountBalance.remarks = remarks;
    }

    public static String getTimeOutUrl() {
        return timeOutUrl;
    }

    public static void setTimeOutUrl(String timeOutUrl) {
        AccountBalance.timeOutUrl = timeOutUrl;
    }

    public static String getResultUrl() {
        return resultUrl;
    }

    public static void setResultUrl(String resultUrl) {
        AccountBalance.resultUrl = resultUrl;
    }

    public static String getAccountBalanceUrl() {
        return accountBalanceUrl;
    }

    public static void setAccountBalanceUrl(String accountBalanceUrl) {
        AccountBalance.accountBalanceUrl = accountBalanceUrl;
    }
}
