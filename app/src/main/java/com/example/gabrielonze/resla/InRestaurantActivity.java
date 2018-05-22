package com.example.gabrielonze.resla;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gabrielonze.resla.Adapters.AdapterCardapio;
import com.example.gabrielonze.resla.RequestsObjects.ApiManager;
import com.example.gabrielonze.resla.RequestsObjects.CardapioResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InRestaurantActivity extends AppCompatActivity {

    List<CardapioResponse> listOrders;
    ListView listProducts, listPedidos;
    AdapterCardapio adapterCardapio, adapterPedidos;
    Button callWaiter, sortButton;
    String sort;
    int restaurantId, table;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.cardapio:
                    listPedidos.setVisibility(ListView.GONE);
                    listProducts.setVisibility(ListView.VISIBLE);
                    callWaiter.setVisibility(Button.GONE);
                    sortButton.setVisibility(Button.VISIBLE);
                    return true;
                case R.id.pedidos:
                    callWaiter.setVisibility(Button.VISIBLE);
                    sortButton.setVisibility(Button.GONE);
                    listProducts.setVisibility(ListView.GONE);
                    listPedidos.setVisibility(ListView.VISIBLE);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_restaurant);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        listProducts = findViewById(R.id.productList);
        listPedidos = findViewById(R.id.orderList);
        callWaiter = findViewById(R.id.call_waiter);
        sortButton = findViewById(R.id.sort_button);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        restaurantId = this.getIntent().getIntExtra("restaurantId", -1);
        table = this.getIntent().getIntExtra("table", -1);

        getData();

        listOrders = new ArrayList<>();
        adapterPedidos = new AdapterCardapio(listOrders, this, true, false);
        listPedidos.setAdapter(adapterPedidos);

        callWaiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callWaiter();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Gson gson = new Gson();
            Type type = new TypeToken<CardapioResponse>() {}.getType();
            String stringLocation = data.getStringExtra("product");

            if(stringLocation != null) {
                CardapioResponse product = gson.fromJson(stringLocation, type);
                listOrders.add(product);
                adapterPedidos.notifyDataSetChanged();
            }
        }
    }

    public void getData() {
        Call<List<CardapioResponse>> req = ApiManager.getInstance().cardapio(restaurantId);

        req.enqueue(new Callback<List<CardapioResponse>>() {
            @Override
            public void onResponse(Call<List<CardapioResponse>> call, Response<List<CardapioResponse>> response) {
                List<CardapioResponse> r = response.body();

                adapterCardapio = new AdapterCardapio(r, InRestaurantActivity.this, false, true);
                listProducts.setAdapter(adapterCardapio);
            }

            @Override
            public void onFailure(Call<List<CardapioResponse>> call, Throwable t) {
                Toast.makeText(InRestaurantActivity.this, "Erro :(", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void callWaiter() {

        Call<Boolean> req = ApiManager.getInstance().callWaiter(restaurantId, table);

        req.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean r = response.body();

                if(r)
                    Toast.makeText(InRestaurantActivity.this, "Atendente a caminho", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(InRestaurantActivity.this, "Erro :(", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
