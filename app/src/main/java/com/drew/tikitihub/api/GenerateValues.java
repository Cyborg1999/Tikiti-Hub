package com.drew.tikitihub.api;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;

import static com.drew.tikitihub.api.LNMPSettings.getBusiness_short_code;
import static com.drew.tikitihub.api.LNMPSettings.getDate;
import static com.drew.tikitihub.api.LNMPSettings.getPasskey;

public class GenerateValues {

    public static String generateDate() {
        String date = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
        Log.d("DATE: ", date);
        return date;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String generatePassword() throws UnsupportedEncodingException {
        //String passKey="bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919";
        String passKey = getPasskey();
        String payBill = getBusiness_short_code();
        // String secretKey = getConsumer_secret();
        String time = getDate();
        String psd = payBill + passKey + time;

        byte[] bytes = psd.getBytes("ISO-8859-1");

        String password = Base64.getEncoder().encodeToString(bytes);
        Log.d("PASSWORD: ", password);
        return password;
    }
}
