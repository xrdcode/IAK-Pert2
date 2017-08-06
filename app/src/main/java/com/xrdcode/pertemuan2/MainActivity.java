package com.xrdcode.pertemuan2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.xrdcode.pertemuan2.adapter.MovieAdapter;
import com.xrdcode.pertemuan2.helper.MovieHelper;
import com.xrdcode.pertemuan2.model.Movies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public Movies movie;
    public LinearLayoutManager linearLayoutManager;
    public MovieAdapter movieAdapter;
    public String apiUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        apiUrl = MovieHelper.MOVIE_URL + "popular" + "?api_key=" + MovieHelper.API_KEY;
        JsonParser jsonParser =  new JsonParser();
        jsonParser.execute(apiUrl);
    }

    class JsonParser extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            String movieJsonStr = null;

            try {
                URL url = new URL(params[0]);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }
                movieJsonStr = buffer.toString();
            } catch (IOException e) {
                Log.e("MainActivity", "Error ", e);
                return null;
            }
            return movieJsonStr;
        }

        @Override
        protected void onPostExecute(String s) {
            movie = new Gson().fromJson(s, Movies.class);
            movieAdapter = new MovieAdapter(getApplicationContext(), movie.results);
            recyclerView.setAdapter(movieAdapter);
        }
    }

}
