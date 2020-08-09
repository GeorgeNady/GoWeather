package com.example.goweather;

import android.os.AsyncTask;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadJSON2 extends AsyncTask<String,Void,String> {

    @Override
    protected String doInBackground(String... urls) {

        URL url;
        HttpURLConnection connection;
        StringBuilder result = new StringBuilder();

        try {

            url = new URL(urls[1]);
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
