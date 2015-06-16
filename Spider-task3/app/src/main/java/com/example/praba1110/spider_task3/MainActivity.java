package com.example.praba1110.spider_task3;

import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Handler;


public class MainActivity extends ActionBarActivity {
    TextView timer;
    TextView value;
    long n, t, v;
    String url = "http://spider.nitt.edu/~vishnu/time.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = (TextView) findViewById(R.id.timer);
        value = (TextView) findViewById(R.id.value);
        t=10;
        Main();

    }

    public void Main() {

        new GetData().execute(url);
        looper();

    }

    public void exit(View v) {

        finish();
        System.exit(0);

    }

    public void looper()  {
        new CountDownTimer((1000 * t+100), 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished / 1000));

            }

            @Override
            public void onFinish() {
                value.setText(String.valueOf(v));

                t=n;
                Main();
            }
        }.start();
    }

    public class GetData extends AsyncTask<String, Integer, Long> {
        @Override
        protected Long doInBackground(String... params) {
            long no = 0;
            try {
                URL url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                try {
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setRequestProperty("Accept-Encoding", "identity");
                    InputStreamReader input = new InputStreamReader(conn.getInputStream());
                    BufferedReader in = new BufferedReader(input);
                    String i = in.readLine();
                    no = Long.parseLong(i);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return no;

        }

        @Override
        protected void onPostExecute(Long aLong) {
            v=aLong;
            n = aLong % 10;
            if(n==0)
            {
                n=aLong%100;
            }
        }
    }
}