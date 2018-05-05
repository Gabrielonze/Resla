package com.example.gabrielonze.resla.RequestsObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookResponse {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("used")
    @Expose
    private boolean used;
    @SerializedName("people")
    @Expose
    private int people;


    public BookResponse() {
    }

    public BookResponse(int id, String name, String imageUrl, boolean used, int people) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.used = used;
        this.people = people;
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

    public int getPeople() {
        return people;
    }

    public boolean isUsed() {
        return used;
    }

    @Override
    public String toString() {
        return "CardapioResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", people=" + people +
                '}';
    }
}
