package com.example.restapi_2018096;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ListView albumListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        albumListView = findViewById(R.id.albumListView);

        getAlbum();
    }

    private void getAlbum() {
        Call<AlbumPojo> call = RetrofitClient.getInstance().getMyApi().getAlbum();
        call.enqueue(new Callback<AlbumPojo>() {
            @Override
            public void onResponse(Call<AlbumPojo> call, Response<AlbumPojo> response) {
                AlbumPojo albumData  = response.body();
                ArrayList<ResultsItem> data = albumData.getResults();
                String[] oneAlbum = new String[data.size()];

                for (int i = 0; i < data.size(); i++) {
                    oneAlbum[i] = data.get(i).getName();
                }

                albumListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, oneAlbum));
            }

            @Override
            public void onFailure(Call<AlbumPojo> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }

}