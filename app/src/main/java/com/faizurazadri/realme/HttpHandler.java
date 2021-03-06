package com.faizurazadri.realme;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpHandler {

    public static final String TAG = HttpHandler.class.getSimpleName();

    public HttpHandler(){}

    public String makeServiceCall(String reqUrl){
        String response = null;

        try {
            URL url = new URL(reqUrl);
            HttpURLConnection  connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            response = converStreamToString(inputStream);
        }catch (MalformedURLException e){
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        }catch (ProtocolException e){
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        }catch (IOException e){
            Log.e(TAG, "IOException: " + e.getMessage());
        }catch (Exception e){
            Log.e(TAG, "Exception: " + e.getMessage());
        }

        return response;
    }

    private String converStreamToString(InputStream inputStream){
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();

        String line;

        try {
            while ((line = reader.readLine())!=null){
                stringBuilder.append(line).append('\n');
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}
