package com.example.gabrielonze.resla;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gabrielonze.resla.Adapters.AdapterBook;
import com.example.gabrielonze.resla.Adapters.AdapterCardapio;
import com.example.gabrielonze.resla.Adapters.AdapterRestaurante;
import com.example.gabrielonze.resla.RequestsObjects.ApiManager;
import com.example.gabrielonze.resla.RequestsObjects.CardapioResponse;
import com.example.gabrielonze.resla.RequestsObjects.FirstScreenResponse;
import com.example.gabrielonze.resla.RequestsObjects.RestauranteResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantActivity extends AppCompatActivity {

    Button book;
    ListView listRes;
    AdapterCardapio adapterRes;
    List<CardapioResponse> listCardapio;
    int restaurantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_dishes);
        listRes = findViewById(R.id.top_res);
        book = findViewById(R.id.bookButton);
        restaurantId = this.getIntent().getIntExtra("restaurantId", -1);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reservar();
            }
        });

        getData();

    }

    public void reservar() {
        Intent i = new Intent(RestaurantActivity.this, BookActivity.class);
        i.putExtra("restaurantId", restaurantId);
        startActivity(i);
    }

    public void getData() {
        Call<List<CardapioResponse>> req = ApiManager.getInstance().cardapio(restaurantId);

        req.enqueue(new Callback<List<CardapioResponse>>() {
            @Override
            public void onResponse(Call<List<CardapioResponse>> call, Response<List<CardapioResponse>> response) {
                List<CardapioResponse> r = response.body();

                adapterRes = new AdapterCardapio(r, RestaurantActivity.this, true, true);
                listRes.setAdapter(adapterRes);
            }

            @Override
            public void onFailure(Call<List<CardapioResponse>> call, Throwable t) {
                Toast.makeText(RestaurantActivity.this, "Erro :(", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
