package com.example.gabrielonze.resla.RequestsObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OkResponse {

    @SerializedName("ok")
    @Expose
    private boolean ok;

    public OkResponse() {
    }

    public OkResponse(boolean ok) {
        this.ok = ok;
    }

}
