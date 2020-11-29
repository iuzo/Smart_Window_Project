package com.example.smart_window_project;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("start", "start");
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService/getUltraSrtNcst"); /*URL*/
        try {
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=H1%2BKVL4VVRqLerRMBwOFwiFjimeJLM%2BoeR8CXL4lKXN0Hq8%2Btr7j0crO25qe%2BMP%2BiTejjZsZkcovfc6bcmlePQ%3D%3D"); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("XML", "UTF-8")); /*요청자료형식(XML/JSON)Default: XML*/
            urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode("20151201", "UTF-8")); /*15년 12월 1일 발표*/
            urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0600", "UTF-8")); /*06시 발표(정시단위)*/
            urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("18", "UTF-8")); /*예보지점의 X 좌표값*/
            urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*예보지점 Y 좌표*/
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Log.d("2th", String.valueOf(urlBuilder));
        URL url = null;
        try {
            url = new URL(urlBuilder.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.d("3th", "3th");
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("4th", "4th");
        try {
            conn.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        Log.d("5th", "5th");
        conn.setRequestProperty("Content-type", "application/json");
        try {
            conn.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Log.d("6th", "6th");
        BufferedReader rd = null;
        try {
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        Log.d("7th", "7th");
        StringBuilder sb = new StringBuilder();
        String line = null;
        while (true) {
            try {
                if (!((line = rd.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            sb.append(line);
        }

        Log.d("8th", "8th");
        try {
            rd.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        conn.disconnect();
        System.out.println(sb.toString());
    }
}