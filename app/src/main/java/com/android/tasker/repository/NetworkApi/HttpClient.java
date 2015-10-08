package com.android.tasker.repository.NetworkApi;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Sibi on 30/09/15.
 */
public class HttpClient {

    private static final String METHOD_POST = "POST";
    private static final String METHOD_GET = "GET";
    private static final String METHOD_PATCH = "PATCH";
    private static final String METHOD_DELETE = "DELETE";
    private static final String CONTENT_TYPE = "application/json";

    private String log = "";

    public String get(String url) throws IOException {
        HttpURLConnection con = openConnection(url, METHOD_GET);
        String response = readResponsAsString(con);
        con.disconnect();

        logGet(url, response);

        return response;
    }

    public String patch(String url, String data) throws IOException {
        HttpURLConnection con = openConnection(url, METHOD_PATCH);

        con.getOutputStream().write(data.getBytes());
        String response = readResponsAsString(con);
        con.disconnect();

        logPatch(url, data, response);

        return response;
    }

    public String delete(String url) throws IOException {
        HttpURLConnection con = openConnection(url, METHOD_DELETE);
        String response = readResponsAsString(con);
        con.disconnect();

        logDelete(url, response);

        return response;
    }

    public String post(String url, String data) throws IOException {
        HttpURLConnection con = openConnection(url, METHOD_POST);
        Log.d("step", "2.1");

        try {
            con.getOutputStream().write(data.getBytes());
            String response = readResponsAsString(con);
            con.disconnect();

            logPost(url, data, response);
            Log.d("step", "2.2");
            return response;
        } catch (IOException e) {
            throw new RuntimeException("error while writing data to : " + url, e);
        }
    }

    private HttpURLConnection openConnection(String url, String method) throws IOException {
        HttpURLConnection httpCon = null;
            httpCon = (HttpURLConnection) (new URL(url)).openConnection();
            httpCon.setRequestProperty("Content-Type", CONTENT_TYPE);
            httpCon.setRequestMethod(method);
            httpCon.setDoInput(true);
            if (method.equals(METHOD_POST))
                httpCon.setDoOutput(true);
            else if(method.equals(METHOD_PATCH))
                httpCon.setDoOutput(true);
            httpCon.connect();
            return httpCon;
    }

    private String readResponsAsString(HttpURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine + "\n");
        }

        in.close();

        return response.toString();
    }

    private void logGet(String url, String response) {
        log += "\n\n\nGET : " + url + "\n\n" + response;
    }

    private void logDelete(String url, String response) {
        log += "\n\n\nDelete : " + url + "\n\n" + response;
    }

    private void logPost(String url, String data, String response) {
        log += "\n\n\nPOST : " + url + "\n\nData : " + data + "\n\nResponse : " + response;
    }

    private void logPatch(String url, String data, String response) {
        log += "\n\n\nPATCH : " + url + "\n\nData : " + data + "\n\nResponse : " + response;
    }

    public String getLog() {
        return log;
    }

}
