package com.drew.tikitihub.api;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static com.drew.tikitihub.api.Network.sendRequest;

public class Mpesa {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String sktPush() throws JSONException, IOException {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("BusinessShortCode", LNMPSettings.getBusiness_short_code());
        jsonObject.put("Password", LNMPSettings.getPassword());
        jsonObject.put("Timestamp", LNMPSettings.getDate());
        jsonObject.put("TransactionType", LNMPSettings.getTransaction_type());
        jsonObject.put("Amount", LNMPSettings.getAmount());
        jsonObject.put("PhoneNumber", LNMPSettings.getPhone());
        jsonObject.put("PartyA", LNMPSettings.getPhone());
        jsonObject.put("PartyB", LNMPSettings.getBusiness_short_code());
        jsonObject.put("CallBackURL", LNMPSettings.getCallback_url());
        jsonObject.put("AccountReference", LNMPSettings.getAccount_reference());
        jsonObject.put("QueueTimeOutURL", LNMPSettings.getTimeout_url());
        jsonObject.put("TransactionDesc", LNMPSettings.getTransaction_desc());

        jsonArray.put(jsonObject);

        String requestJson = jsonArray.toString().replaceAll("[\\[\\]]", "");
        return sendRequest(requestJson, LNMPSettings.getStk_push_url());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String c2bSimulation() throws JSONException, IOException {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ShortCode", C2BSettings.getShortCode());
        jsonObject.put("CommandID", C2BSettings.getCommandId());
        jsonObject.put("Amount", C2BSettings.getAmount());
        jsonObject.put("Msisdn", C2BSettings.getMSISDN());
        jsonObject.put("BillRefNumber", C2BSettings.getBillRef());

        jsonArray.put(jsonObject);

        String requestJson = jsonArray.toString().replaceAll("[\\[\\]]", "");
        return sendRequest(requestJson, C2BSettings.getC2bUrl());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String b2bSimulation() throws JSONException, IOException {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Initiator", B2BSettings.getInitiator());
        jsonObject.put("SecurityCredential", B2BSettings.getSecurityCredential());
        jsonObject.put("CommandID", B2BSettings.getCommandID());
        jsonObject.put("SenderIdentifierType", B2BSettings.getSenderIdentifierType());
        jsonObject.put("ReceiverIdentifierType", B2BSettings.getReceiverIdentifierType());
        jsonObject.put("Amount", B2BSettings.getAmount());
        jsonObject.put("PartyA", B2BSettings.getPartyA());
        jsonObject.put("PartyB", B2BSettings.getPartyB());
        jsonObject.put("Remarks", B2BSettings.getRemarks());
        jsonObject.put("AccountReference", B2BSettings.getAccountReference());
        jsonObject.put("QueueTimeOutURL", B2BSettings.getQueueTimeOutURL());
        jsonObject.put("ResultURL", B2BSettings.getResultURL());

        jsonArray.put(jsonObject);

        String requestJson = jsonArray.toString().replaceAll("[\\[\\]]", "");
        return sendRequest(requestJson, B2BSettings.getB2bUrl());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String b2cSimulation() throws JSONException, IOException {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("InitiatorName", B2CSettings.getInitiatorName());
        jsonObject.put("SecurityCredential", B2CSettings.getSecurityCredential());
        jsonObject.put("CommandID", B2CSettings.getCommandId());
        jsonObject.put("Amount", B2CSettings.getAmount());
        jsonObject.put("PartyA", B2CSettings.getPartyA());
        jsonObject.put("PartyB", B2CSettings.getPartyB());
        jsonObject.put("Remarks", B2CSettings.getRemarks());
        jsonObject.put("QueueTimeOutURL", B2CSettings.getTimeOutUrl());
        jsonObject.put("ResultURL", B2CSettings.getResultUrl());
        //jsonObject.put("Occassion", occassion);

        jsonArray.put(jsonObject);

        String requestJson = jsonArray.toString().replaceAll("[\\[\\]]", "");
        return sendRequest(requestJson, B2CSettings.getB2cUrl());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String accountBalance() throws JSONException, IOException {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Initiator", AccountBalance.getInitiatorName());
        jsonObject.put("SecurityCredential", AccountBalance.getSecurityCredential());
        jsonObject.put("CommandID", AccountBalance.getCommandId());
        jsonObject.put("PartyA", AccountBalance.getPartyA());
        jsonObject.put("IdentifierType", AccountBalance.getIdentifierType());
        jsonObject.put("Remarks", AccountBalance.getRemarks());
        jsonObject.put("QueueTimeOutURL", AccountBalance.getTimeOutUrl());
        jsonObject.put("ResultURL", AccountBalance.getResultUrl());

        jsonArray.put(jsonObject);

        String requestJson = jsonArray.toString().replaceAll("[\\[\\]]", "");
        return sendRequest(requestJson, AccountBalance.getAccountBalanceUrl());
    }


}
