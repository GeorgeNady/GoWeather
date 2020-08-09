package com.example.goweather;

import android.os.AsyncTask;
import java.io.*;
import java.net.*;

public class DownloadJSON extends AsyncTask<String,Void,String> {

    @Override
    protected String doInBackground(String... urls) {

        URL url;
        HttpURLConnection connection;
        StringBuilder result = new StringBuilder();

        try {

            url = new URL(urls[0]);
            connection = (HttpURLConnection) url.openConnection();
            InputStream in = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            int data = reader.read();

            while (data != -1) {

                char current = (char) data;

                result.append(current);

                data = reader.read();

            }

            return result.toString();

        } catch (Exception e) {

            e.printStackTrace();

            return null + String.valueOf(e.getMessage());

        }

    }

}

