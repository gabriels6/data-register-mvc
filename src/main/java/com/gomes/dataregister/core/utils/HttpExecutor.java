package com.gomes.dataregister.core.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpExecutor {

    public HttpExecutor(){

    }

    public String get(String url) {
        try {
            URL conUrl = new URL(url);
            HttpURLConnection con = (HttpURLConnection) conUrl.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream())
            );
            String inputLine;
            StringBuilder content = new StringBuilder();
            while((inputLine = in.readLine()) != null) {
                content.append(inputLine + "\n");
            }
            in.close();
            con.disconnect();
            return content.toString();
        } catch (IOException e) {
            return "Error";
        }
    }

    public BufferedReader getFile(String url) {
        try {
            URL conUrl = new URL(url);
            HttpURLConnection con = (HttpURLConnection) conUrl.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream())
            );
            in.close();
            con.disconnect();
            return in;
        } catch (IOException e) {
            return null;
        }
    }

}
