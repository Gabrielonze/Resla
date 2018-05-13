package com.example.gabrielonze.resla.RequestsObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FirstScreenResponse {

    @SerializedName("restaurants")
    @Expose
    private List<RestauranteResponse> restaurants;
    @SerializedName("books")
    @Expose
    private List<BookResponse> books;

    public FirstScreenResponse(List<RestauranteResponse> restaurants, List<BookResponse> books) {
        this.restaurants = restaurants;
        this.books = books;
    }

    public List<BookResponse> getBooks() {
        return books;
    }

    public void setBooks(List<BookResponse> books) {
        this.books = books;
    }

    public List<RestauranteResponse> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<RestauranteResponse> restaurants) {
        this.restaurants = restaurants;
    }
}
