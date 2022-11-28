package com.example.restapi_2018096;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "http://localhost/db_album/api/album/list.php";
    @GET("album?limit=1")
    Call<AlbumPojo> getAlbum();
}
