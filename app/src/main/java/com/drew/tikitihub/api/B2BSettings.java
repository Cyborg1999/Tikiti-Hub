package com.drew.tikitihub.api;

public class B2BSettings {
    public static String initiator;
    public static String securityCredential;
    public static String commandID;
    public static String senderIdentifierType;
    public static String receiverIdentifierType;
    public static String amount;
    public static String partyA;
    public static String partyB;
    public static String remarks;
    public static String accountReference;
    public static String queueTimeOutURL;
    public static String resultURL;
    public static String b2bUrl;

    public static String getInitiator() {
        return initiator;
    }

    public static void setInitiator(String initiator) {
        B2BSettings.initiator = initiator;
    }

    public static String getSecurityCredential() {
        return securityCredential;
    }

    public static void setSecurityCredential(String securityCredential) {
        B2BSettings.securityCredential = securityCredential;
    }

    public static String getCommandID() {
        return commandID;
    }

    public static void setCommandID(String commandID) {
        B2BSettings.commandID = commandID;
    }

    public static String getSenderIdentifierType() {
        return senderIdentifierType;
    }

    public static void setSenderIdentifierType(String senderIdentifierType) {
        B2BSettings.senderIdentifierType = senderIdentifierType;
    }

    public static String getReceiverIdentifierType() {
        return receiverIdentifierType;
    }

    public static void setReceiverIdentifierType(String receiverIdentifierType) {
        B2BSettings.receiverIdentifierType = receiverIdentifierType;
    }

    public static String getAmount() {
        return amount;
    }

    public static void setAmount(String amount) {
        B2BSettings.amount = amount;
    }

    public static String getPartyA() {
        return partyA;
    }

    public static void setPartyA(String partyA) {
        B2BSettings.partyA = partyA;
    }

    public static String getPartyB() {
        return partyB;
    }

    public static void setPartyB(String partyB) {
        B2BSettings.partyB = partyB;
    }

    public static String getRemarks() {
        return remarks;
    }

    public static void setRemarks(String remarks) {
        B2BSettings.remarks = remarks;
    }

    public static String getAccountReference() {
        return accountReference;
    }

    public static void setAccountReference(String accountReference) {
        B2BSettings.accountReference = accountReference;
    }

    public static String getQueueTimeOutURL() {
        return queueTimeOutURL;
    }

    public static void setQueueTimeOutURL(String queueTimeOutURL) {
        B2BSettings.queueTimeOutURL = queueTimeOutURL;
    }

    public static String getResultURL() {
        return resultURL;
    }

    public static void setResultURL(String resultURL) {
        B2BSettings.resultURL = resultURL;
    }

    public static String getB2bUrl() {
        return b2bUrl;
    }

    public static void setB2bUrl(String b2bUrl) {
        B2BSettings.b2bUrl = b2bUrl;
    }
}
