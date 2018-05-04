package com.example.gabrielonze.resla;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gabrielonze.resla.Adapters.AdapterCardapio;
import com.example.gabrielonze.resla.Adapters.AdapterRestaurante;
import com.example.gabrielonze.resla.RequestsObjects.CardapioResponse;
import com.example.gabrielonze.resla.RequestsObjects.RestauranteResponse;

import java.util.ArrayList;
import java.util.List;

public class RestaurantActivity extends AppCompatActivity {

    ListView listRes;
    AdapterCardapio adapterRes;
    List<CardapioResponse> listCardapio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_dishes);
        listRes = findViewById(R.id.top_res);

        String imgUrl = "https://i.ytimg.com/vi/mEBFswpYms4/maxresdefault.jpg";

        listCardapio = new ArrayList<>();
        listCardapio.add(new CardapioResponse(1, "HAHAHA", "Bla bla bla", imgUrl, 9.99, 0, "Pizza", 2.55D));
        listCardapio.add(new CardapioResponse(1, "HEHEHE", "Bla bla bla bla", imgUrl, 1.69, 0, "Doce", 5D));
        listCardapio.add(new CardapioResponse(1, "HUEHUE", "Bla bla", imgUrl, 1.99, 0, "Pizza", 4.8D));
        listCardapio.add(new CardapioResponse(1, "KKKKKK", "Bla", imgUrl, 6.66, 0, "Drinks", 3.33D));
        listCardapio.add(new CardapioResponse(1, "RSRSRS", "Bla bla bla bla bla", imgUrl, 11.22, 0, "Doce", 0D));

        adapterRes = new AdapterCardapio(listCardapio, this, true, true);
        listRes.setAdapter(adapterRes);

    }

    public void openQRActivity() {
        Intent i = new Intent(RestaurantActivity.this, QRCodeActivity.class);
        startActivity(i);
    }

    //public void showTop

}
