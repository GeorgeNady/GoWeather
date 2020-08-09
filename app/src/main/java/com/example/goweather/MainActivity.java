package com.example.goweather;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import org.json.*;
import java.text.MessageFormat;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    final String API_KEY = Constant.API_KEY;
    String latitude = "29.995680";
    String longitude = "31.204898";
    String currentWeatherAPI;
    EditText etCity;
    String city;
    DownloadJSON task;

    ImageView ivBackground;
    TextView tvLocation;
    TextView day;
    TextView date_day;
    TextView date_month;
    ImageView ivTempStatus;
    TextView temp;
    TextView temp_max;
    TextView temp_min;
    TextView temp_feels_like;
    Date d;
    Calendar c;
    TextView details_uv_index ;
    TextView details_sunrise_time ;
    TextView details_sunset_time ;
    TextView details_humidity ;

    ArrayList<TextView> hourly_UI_Temp_Time_items;
    ArrayList<TextView> hourly_UI_Humidity_items;
    ArrayList<TextView> hourly_UI_Temperature_items;
    String JSONData;

    ImageView ivRefresh;
    TextView tvOpenWeather;
    ImageView ivSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ---------------------------------- Hourly_Time_TextViews
        hourly_UI_Temp_Time_items = new ArrayList<>(24);
        hourly_UI_Temp_Time_items.clear();
        TextView hourly_temp_time_0 = findViewById(R.id.hourly_temp_time_0); hourly_UI_Temp_Time_items.add(hourly_temp_time_0);
        TextView hourly_temp_time_1 = findViewById(R.id.hourly_temp_time_1); hourly_UI_Temp_Time_items.add(hourly_temp_time_1);
        TextView hourly_temp_time_2 = findViewById(R.id.hourly_temp_time_2); hourly_UI_Temp_Time_items.add(hourly_temp_time_2);
        TextView hourly_temp_time_3 = findViewById(R.id.hourly_temp_time_3); hourly_UI_Temp_Time_items.add(hourly_temp_time_3);
        TextView hourly_temp_time_4 = findViewById(R.id.hourly_temp_time_4); hourly_UI_Temp_Time_items.add(hourly_temp_time_4);
        TextView hourly_temp_time_5 = findViewById(R.id.hourly_temp_time_5); hourly_UI_Temp_Time_items.add(hourly_temp_time_5);
        TextView hourly_temp_time_6 = findViewById(R.id.hourly_temp_time_6); hourly_UI_Temp_Time_items.add(hourly_temp_time_6);
        TextView hourly_temp_time_7 = findViewById(R.id.hourly_temp_time_7); hourly_UI_Temp_Time_items.add(hourly_temp_time_7);
        TextView hourly_temp_time_8 = findViewById(R.id.hourly_temp_time_8); hourly_UI_Temp_Time_items.add(hourly_temp_time_8);
        TextView hourly_temp_time_9 = findViewById(R.id.hourly_temp_time_9); hourly_UI_Temp_Time_items.add(hourly_temp_time_9);
        TextView hourly_temp_time_10 = findViewById(R.id.hourly_temp_time_10); hourly_UI_Temp_Time_items.add(hourly_temp_time_10);
        TextView hourly_temp_time_11 = findViewById(R.id.hourly_temp_time_11); hourly_UI_Temp_Time_items.add(hourly_temp_time_11);
        TextView hourly_temp_time_12 = findViewById(R.id.hourly_temp_time_12); hourly_UI_Temp_Time_items.add(hourly_temp_time_12);
        TextView hourly_temp_time_13 = findViewById(R.id.hourly_temp_time_13); hourly_UI_Temp_Time_items.add(hourly_temp_time_13);
        TextView hourly_temp_time_14 = findViewById(R.id.hourly_temp_time_14); hourly_UI_Temp_Time_items.add(hourly_temp_time_14);
        TextView hourly_temp_time_15 = findViewById(R.id.hourly_temp_time_15); hourly_UI_Temp_Time_items.add(hourly_temp_time_15);
        TextView hourly_temp_time_16 = findViewById(R.id.hourly_temp_time_16); hourly_UI_Temp_Time_items.add(hourly_temp_time_16);
        TextView hourly_temp_time_17 = findViewById(R.id.hourly_temp_time_17); hourly_UI_Temp_Time_items.add(hourly_temp_time_17);
        TextView hourly_temp_time_18 = findViewById(R.id.hourly_temp_time_18); hourly_UI_Temp_Time_items.add(hourly_temp_time_18);
        TextView hourly_temp_time_19 = findViewById(R.id.hourly_temp_time_19); hourly_UI_Temp_Time_items.add(hourly_temp_time_19);
        TextView hourly_temp_time_20 = findViewById(R.id.hourly_temp_time_20); hourly_UI_Temp_Time_items.add(hourly_temp_time_20);
        TextView hourly_temp_time_21 = findViewById(R.id.hourly_temp_time_21); hourly_UI_Temp_Time_items.add(hourly_temp_time_21);
        TextView hourly_temp_time_22 = findViewById(R.id.hourly_temp_time_22); hourly_UI_Temp_Time_items.add(hourly_temp_time_22);
        TextView hourly_temp_time_23 = findViewById(R.id.hourly_temp_time_23); hourly_UI_Temp_Time_items.add(hourly_temp_time_23);

        // ---------------------------------- Hourly_Humidity_Items
        hourly_UI_Humidity_items = new ArrayList<>(24);
        hourly_UI_Humidity_items.clear();
        TextView hourly_humidity_0 = findViewById(R.id.hourly_humidity_0); hourly_UI_Humidity_items.add(hourly_humidity_0);
        TextView hourly_humidity_1 = findViewById(R.id.hourly_humidity_1); hourly_UI_Humidity_items.add(hourly_humidity_1);
        TextView hourly_humidity_2 = findViewById(R.id.hourly_humidity_2); hourly_UI_Humidity_items.add(hourly_humidity_2);
        TextView hourly_humidity_3 = findViewById(R.id.hourly_humidity_3); hourly_UI_Humidity_items.add(hourly_humidity_3);
        TextView hourly_humidity_4 = findViewById(R.id.hourly_humidity_4); hourly_UI_Humidity_items.add(hourly_humidity_4);
        TextView hourly_humidity_5 = findViewById(R.id.hourly_humidity_5); hourly_UI_Humidity_items.add(hourly_humidity_5);
        TextView hourly_humidity_6 = findViewById(R.id.hourly_humidity_6); hourly_UI_Humidity_items.add(hourly_humidity_6);
        TextView hourly_humidity_7 = findViewById(R.id.hourly_humidity_7); hourly_UI_Humidity_items.add(hourly_humidity_7);
        TextView hourly_humidity_8 = findViewById(R.id.hourly_humidity_8); hourly_UI_Humidity_items.add(hourly_humidity_8);
        TextView hourly_humidity_9 = findViewById(R.id.hourly_humidity_9); hourly_UI_Humidity_items.add(hourly_humidity_9);
        TextView hourly_humidity_10 = findViewById(R.id.hourly_humidity_10); hourly_UI_Humidity_items.add(hourly_humidity_10);
        TextView hourly_humidity_11 = findViewById(R.id.hourly_humidity_11); hourly_UI_Humidity_items.add(hourly_humidity_11);
        TextView hourly_humidity_12 = findViewById(R.id.hourly_humidity_12); hourly_UI_Humidity_items.add(hourly_humidity_12);
        TextView hourly_humidity_13 = findViewById(R.id.hourly_humidity_13); hourly_UI_Humidity_items.add(hourly_humidity_13);
        TextView hourly_humidity_14 = findViewById(R.id.hourly_humidity_14); hourly_UI_Humidity_items.add(hourly_humidity_14);
        TextView hourly_humidity_15 = findViewById(R.id.hourly_humidity_15); hourly_UI_Humidity_items.add(hourly_humidity_15);
        TextView hourly_humidity_16 = findViewById(R.id.hourly_humidity_16); hourly_UI_Humidity_items.add(hourly_humidity_16);
        TextView hourly_humidity_17 = findViewById(R.id.hourly_humidity_17); hourly_UI_Humidity_items.add(hourly_humidity_17);
        TextView hourly_humidity_18 = findViewById(R.id.hourly_humidity_18); hourly_UI_Humidity_items.add(hourly_humidity_18);
        TextView hourly_humidity_19 = findViewById(R.id.hourly_humidity_19); hourly_UI_Humidity_items.add(hourly_humidity_19);
        TextView hourly_humidity_20 = findViewById(R.id.hourly_humidity_20); hourly_UI_Humidity_items.add(hourly_humidity_20);
        TextView hourly_humidity_21 = findViewById(R.id.hourly_humidity_21); hourly_UI_Humidity_items.add(hourly_humidity_21);
        TextView hourly_humidity_22 = findViewById(R.id.hourly_humidity_22); hourly_UI_Humidity_items.add(hourly_humidity_22);
        TextView hourly_humidity_23 = findViewById(R.id.hourly_humidity_23); hourly_UI_Humidity_items.add(hourly_humidity_23);

        // ---------------------------------- Hourly_Humidity_Items
        hourly_UI_Temperature_items = new ArrayList<>(24);
        hourly_UI_Temperature_items.clear();
        TextView hourly_temp_0 = findViewById(R.id.hourly_temp_0); hourly_UI_Temperature_items.add(hourly_temp_0);
        TextView hourly_temp_1 = findViewById(R.id.hourly_temp_1); hourly_UI_Temperature_items.add(hourly_temp_1);
        TextView hourly_temp_2 = findViewById(R.id.hourly_temp_2); hourly_UI_Temperature_items.add(hourly_temp_2);
        TextView hourly_temp_3 = findViewById(R.id.hourly_temp_3); hourly_UI_Temperature_items.add(hourly_temp_3);
        TextView hourly_temp_4 = findViewById(R.id.hourly_temp_4); hourly_UI_Temperature_items.add(hourly_temp_4);
        TextView hourly_temp_5 = findViewById(R.id.hourly_temp_5); hourly_UI_Temperature_items.add(hourly_temp_5);
        TextView hourly_temp_6 = findViewById(R.id.hourly_temp_6); hourly_UI_Temperature_items.add(hourly_temp_6);
        TextView hourly_temp_7 = findViewById(R.id.hourly_temp_7); hourly_UI_Temperature_items.add(hourly_temp_7);
        TextView hourly_temp_8 = findViewById(R.id.hourly_temp_8); hourly_UI_Temperature_items.add(hourly_temp_8);
        TextView hourly_temp_9 = findViewById(R.id.hourly_temp_9); hourly_UI_Temperature_items.add(hourly_temp_9);
        TextView hourly_temp_10 = findViewById(R.id.hourly_temp_10); hourly_UI_Temperature_items.add(hourly_temp_10);
        TextView hourly_temp_11 = findViewById(R.id.hourly_temp_11); hourly_UI_Temperature_items.add(hourly_temp_11);
        TextView hourly_temp_12 = findViewById(R.id.hourly_temp_12); hourly_UI_Temperature_items.add(hourly_temp_12);
        TextView hourly_temp_13 = findViewById(R.id.hourly_temp_13); hourly_UI_Temperature_items.add(hourly_temp_13);
        TextView hourly_temp_14 = findViewById(R.id.hourly_temp_14); hourly_UI_Temperature_items.add(hourly_temp_14);
        TextView hourly_temp_15 = findViewById(R.id.hourly_temp_15); hourly_UI_Temperature_items.add(hourly_temp_15);
        TextView hourly_temp_16 = findViewById(R.id.hourly_temp_16); hourly_UI_Temperature_items.add(hourly_temp_16);
        TextView hourly_temp_17 = findViewById(R.id.hourly_temp_17); hourly_UI_Temperature_items.add(hourly_temp_17);
        TextView hourly_temp_18 = findViewById(R.id.hourly_temp_18); hourly_UI_Temperature_items.add(hourly_temp_18);
        TextView hourly_temp_19 = findViewById(R.id.hourly_temp_19); hourly_UI_Temperature_items.add(hourly_temp_19);
        TextView hourly_temp_20 = findViewById(R.id.hourly_temp_20); hourly_UI_Temperature_items.add(hourly_temp_20);
        TextView hourly_temp_21 = findViewById(R.id.hourly_temp_21); hourly_UI_Temperature_items.add(hourly_temp_21);
        TextView hourly_temp_22 = findViewById(R.id.hourly_temp_22); hourly_UI_Temperature_items.add(hourly_temp_22);
        TextView hourly_temp_23 = findViewById(R.id.hourly_temp_23); hourly_UI_Temperature_items.add(hourly_temp_23);

        task = new DownloadJSON();

        etCity = findViewById(R.id.etCity);
        ivBackground =  findViewById(R.id.ivBackground);
        tvLocation = findViewById(R.id.tvLocation);
        day = findViewById(R.id.day);
        date_day = findViewById(R.id.date_day);
        date_month = findViewById(R.id.date_month);
        ivTempStatus =  findViewById(R.id.ivTempStatus);
        temp = findViewById(R.id.temp);
        temp_max = findViewById(R.id.temp_max);
        temp_min = findViewById(R.id.temp_min);
        temp_feels_like = findViewById(R.id.temp_feels_like);

        details_uv_index = findViewById(R.id.details_uv_index);
        details_sunrise_time = findViewById(R.id.details_sunrise_time);
        details_sunset_time = findViewById(R.id.details_sunset_time);
        details_humidity = findViewById(R.id.details_humidity);

        ivRefresh = findViewById(R.id.ivRefresh);
        tvOpenWeather = findViewById(R.id.tvOpenWeather);
        ivSearch = findViewById(R.id.ivSearch);

        tvOpenWeather.setVisibility(View.VISIBLE);

        tvOpenWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://openweathermap.org/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        ivRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshPage();
                Toast.makeText(getApplicationContext(), "data has been updated successfully", Toast.LENGTH_SHORT).show();
            }
        });

        refreshPage();

        String oneCallWeatherAPI = "https://api.openweathermap.org/data/2.5/onecall?lat=" + latitude + "&lon=" + longitude + "&%20exclude=hourly,current,daily&appid=" + API_KEY;
        JSONData = getJSONContent(oneCallWeatherAPI);

        extractDataFromAPI(hourly_UI_Temp_Time_items, hourly_UI_Humidity_items, hourly_UI_Temperature_items, JSONData);

        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                city = etCity.getText().toString(); Log.i("shrmt",city);
                currentWeatherAPI = "https://api.openweathermap.org/data/2.5//weather?q=" + city + "&appid=" + API_KEY;
                String currentWeartherJson = getJSONContent(currentWeatherAPI);
                String coord_lat = "";
                String coord_lon = "";
                String country = "";
                String city = "";

                try {

                    JSONObject currentWeartherJson_opject = new JSONObject(currentWeartherJson);

                    String coord = currentWeartherJson_opject.getString("coord");
                    JSONObject coord_parts = new JSONObject(coord);
                    coord_lat = coord_parts.getString("lat");
                    coord_lon = coord_parts.getString("lon");

                    String sys = currentWeartherJson_opject.getString("sys");
                    JSONObject sys_parts = new JSONObject(sys);
                    country = sys_parts.getString("country");

                    city = currentWeartherJson_opject.getString("name");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                latitude = coord_lat;
                longitude = coord_lon;

                tvLocation.setText(MessageFormat.format("{0}, {1}", city, country));

                String oneCallWeatherAPI = "https://api.openweathermap.org/data/2.5/onecall?lat=" + latitude + "&lon=" + longitude + "&%20exclude=hourly,current,daily&appid=" + API_KEY;

                JSONData = getJSONContent(oneCallWeatherAPI);

                extractDataFromAPI(hourly_UI_Temp_Time_items, hourly_UI_Humidity_items, hourly_UI_Temperature_items, JSONData);

            }
        });

    }

    private void extractDataFromAPI(
            ArrayList<TextView> hourly_UI_Temp_Time_items,
            ArrayList<TextView> hourly_UI_Humidity_items,
            ArrayList<TextView> hourly_UI_Temperature_items,
            String JSONData) {
        try {

            JSONObject jsonObject = new JSONObject(JSONData);

            String hourly = jsonObject.getString("hourly");
            JSONArray hourly_parts = new JSONArray(hourly);

            for (int i = 0 ; i < 24 ; i++ ) {

                JSONObject hourly_item = hourly_parts.getJSONObject(i);

                // Item ( Time _ date _ Hour )
                String hourly_dt = hourly_item.getString("dt");
                Date dt = new Date((long) Integer.parseInt(hourly_dt) * 1000);
                String newTimeFormat = getHoursFormat(dt);
                hourly_UI_Temp_Time_items.get(i).setText(newTimeFormat);

                // Item Hourly Humidity
                String humidity = hourly_item.getString("humidity");
                hourly_UI_Humidity_items.get(i).setText(String.format("%s%%", humidity));

                // Hourly Item Temperature
                String hourly_temp = hourly_item.getString("temp");
                int hourly_temp_V2 = (int) Double.parseDouble(hourly_temp) - (int) 273.15;
                hourly_UI_Temperature_items.get(i).setText(MessageFormat.format("{0}°", String.valueOf(hourly_temp_V2)));

            }

            String current = jsonObject.getString("current");

            JSONObject current_parts = new JSONObject(current);

            String currentTemp = current_parts.getString("temp");
            int currentTemp_2 = (int) Double.parseDouble(currentTemp) - (int) 273.15;
            temp.setText(String.valueOf(currentTemp_2));

            String currentFeelsLike = current_parts.getString("feels_like");
            int currentFeelsLike_2 = (int) Double.parseDouble(currentFeelsLike) - (int) 273.15;
            temp_feels_like.setText(MessageFormat.format("Feels Like {0}°", String.valueOf(currentFeelsLike_2)));

            details_humidity.setText(MessageFormat.format("{0}%", current_parts.getString("humidity")));

            String daily = jsonObject.getString("daily");

            JSONArray daily_parts = new JSONArray(daily);

            JSONObject daily_item = daily_parts.getJSONObject(0);

            // Double uv_index = daily_item.getDouble("uvi"); // TODO details_uv_index.setText(getUVIndex(uv_index));

            int sunrise = daily_item.getInt("sunrise");
            Date dt_sunrise = new Date((long) sunrise * 1000);
            details_sunrise_time.setText(getHoursAndMinutesFormatted(dt_sunrise));

            int sunset = daily_item.getInt("sunset");
            Date dt_sunset = new Date((long) sunset * 1000);
            details_sunset_time.setText(getHoursAndMinutesFormatted(dt_sunset));

            String daily_temp = daily_item.getString("temp");
            JSONObject daily_temp_parts = new JSONObject(daily_temp);

            double daily_temp_min = daily_temp_parts.getDouble("min");
            String daily_temp_min_in_celsius = String.valueOf((int) ( daily_temp_min - 273.15 ));
            temp_min.setText(MessageFormat.format("{0}°", daily_temp_min_in_celsius));

            double daily_temp_max = daily_temp_parts.getDouble("max");
            String daily_temp_max_in_celsius = String.valueOf((int) ( daily_temp_max - 273.15 ));
            temp_max.setText(MessageFormat.format("{0}°", daily_temp_max_in_celsius));

        } catch (Exception e) {

            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        refreshPage();
    }

    @SuppressWarnings("deprecation")
    private String getHoursAndMinutesFormatted(Date dt) {
        String st;
        if ( dt.getHours() == 0 ) {
            st = "12:" + dt.getMinutes() + " am";
        } else if (dt.getHours() > 0 && dt.getHours() < 12) {
            st = dt.getHours() + ":" + dt.getMinutes() + " am";
        } else if (dt.getHours() == 12) {
            st = "12:" + dt.getMinutes() + " pm";
        } else {
            st = dt.getHours() - 12 + ":" + dt.getMinutes() + " pm";
        }
        return  st;
    }

    @SuppressWarnings("deprecation")
    private String getHoursFormat(Date dt) {
        // get time in 00 am format

        String newTimeFormat;

        if ( dt.getHours() == 0 ) {

            newTimeFormat = "12" + " am";

        } else
            if (dt.getHours() > 0 && dt.getHours() < 12) {
            newTimeFormat = dt.getHours() + " am";
        } else if (dt.getHours() == 12) {
            newTimeFormat = "12" + " pm";
        } else {
            newTimeFormat = ((dt.getHours() - 12)) + " pm";
        }
        return newTimeFormat;
    }

    private void refreshPage() {

        if (getHours() >= 0 && getHours() < 6) {
            ivBackground.setImageResource(R.drawable.night);
        } else if (getHours() >= 6 && getHours() < 12) {
            ivBackground.setImageResource(R.drawable.morning);
        } else if (getHours() >= 12 && getHours() < 18) {
            ivBackground.setImageResource(R.drawable.day);
        } else {
            ivBackground.setImageResource(R.drawable.evening);
        }

        day.setText(getDayOfWeek());
        date_month.setText(getMonth());
        date_day.setText(String.valueOf(getDayOfMonth()));

        extractDataFromAPI(hourly_UI_Temp_Time_items, hourly_UI_Humidity_items, hourly_UI_Temperature_items, JSONData);

    }

    public String getJSONContent(String url) {

        task = new DownloadJSON();
        String result;

        try {

            result = task.execute(url).get();

            return result;

        } catch (Exception e) {

            e.printStackTrace();

            return null + "from function";

        }

    }

    private String getDayOfWeek() {
        d = new Date();
        c = Calendar.getInstance();
        c.setTime(d);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        String day = "";
        switch (dayOfWeek) {
            case 1:
                day = "Sun,";
                break;

            case 2:
                day = "Mon,";
                break;

            case 3:
                day = "Tue,";
                break;

            case 4:
                day = "Wed,";
                break;

            case 5:
                day = "Thu,";
                break;

            case 6:
                day = "Fri,";
                break;

            case 7:
                day = "Sat,";
                break;
        }
        return day;
    }

    private String getMonth() {
        d = new Date();
        c = Calendar.getInstance();
        c.setTime(d);
        int month = c.get(Calendar.MONTH);
        String day = "";
        switch (month) {
            case 0:
                day = "January";
                break;

            case 1:
                day = "February";
                break;

            case 2:
                day = "March";
                break;

            case 3:
                day = "April";
                break;

            case 4:
                day = "May";
                break;

            case 5:
                day = "June";
                break;

            case 6:
                day = "July";
                break;

            case 7:
                day = "August";
                break;

            case 8:
                day = "September";
                break;

            case 9:
                day = "October";
                break;

            case 10:
                day = "November";
                break;

            case 11:
                day = "December";
                break;
        }
        return day;
    }

    private int getDayOfMonth() {
        d = new Date();
        c = Calendar.getInstance();
        c.setTime(d);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    private int getHours() {
        d = new Date();
        c = Calendar.getInstance();
        c.setTime(d);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }














}