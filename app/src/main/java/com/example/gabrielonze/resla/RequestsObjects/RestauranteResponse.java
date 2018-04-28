package com.example.gabrielonze.resla.RequestsObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestauranteResponse {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("rating")
    @Expose
    private double rating;


    public RestauranteResponse() {
    }

    public RestauranteResponse(int id, String name, String imageUrl, double rating) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "CardapioResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", rating=" + rating +
                '}';
    }
}
