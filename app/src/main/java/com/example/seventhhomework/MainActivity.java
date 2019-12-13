package com.example.seventhhomework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.AbstractList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView_name, textView_time, textView_content;
    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter = new RecyclerAdapter(MainActivity.this);
    private HttpURLConnection connection;
    private InputStream inputStream;
    private BufferedReader bufferedReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv_record);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(myAdapter);
        initUI();
    }

    private void initUI() {
        textView_name = findViewById(R.id.tv_name);
        textView_time = findViewById(R.id.tv_time);
        textView_content = findViewById(R.id.tv_content);
        findViewById(R.id.btn_refresh).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String data = getDataFromServer();
                Log.i("Main",data);
            }
        }).start();
    }

    private String getDataFromServer() {
        try {
            URL url = new URL("http://gank.io/api/data/Android/10/1");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == 200) {
                inputStream = connection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder stringBuilder = new StringBuilder();
                for (String line = ""; (line = bufferedReader.readLine()) != null; ) {
                    stringBuilder.append(line);
                }
                jsontojava(stringBuilder.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
                if (inputStream != null) inputStream.close();
                if (connection != null) connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return "";
    }

    public void jsontojava(String stringBuilder) {
        List list = null;
        try {
            JSONObject jsonObject = new JSONObject(stringBuilder);
            JSONArray resultsArray = jsonObject.getJSONArray("results");
            for (int i = 1; i < resultsArray.length(); i++) {
                JSONObject object = resultsArray.getJSONObject(1);
                String publishedAt = object.getString("publishedAt");
                String desc = object.getString("desc");
                String who = object.getString("who");
                list = new List(who,publishedAt,desc);
//                textView_name.setText(who);
//                textView_time.setText(publishedAt);
//                textView_content.setText(desc);
                list.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
