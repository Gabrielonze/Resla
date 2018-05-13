package com.example.gabrielonze.resla.RequestsObjects;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("restaurantInfo/{restautantId}")
    Call<RestauranteResponse> restaurantInfo(@Path("restautantId") int restautantId);

    @GET("cardapio/{restautantId}")
    Call<List<CardapioResponse>> cardapio(@Path("restautantId") int restautantId);

    @GET("firstScreen")
    Call<FirstScreenResponse> firstScreen();

    @GET("callWaiter/{restautantId}/{table}")
    Call<Boolean> callWaiter(@Path("restautantId") int restautantId, @Path("table") int table);

    @POST("saveBook")
    Call<String> saveBook(@Field("restautantId") int restautantId,
                          @Field("peopleAmount") int peopleAmount,
                          @Field("day") String day);

}
