package com.example.gabrielonze.resla;

import android.app.Activity;
import android.content.Intent;
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
import com.example.gabrielonze.resla.RequestsObjects.CardapioResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class InRestaurantActivity extends AppCompatActivity {

    List<CardapioResponse> listCardapio, listOrders;
    ListView listProducts, listPedidos;
    AdapterCardapio adapterCardapio, adapterPedidos;
    Button callWaiter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.cardapio:
                    listPedidos.setVisibility(ListView.GONE);
                    listProducts.setVisibility(ListView.VISIBLE);
                    callWaiter.setVisibility(Button.GONE);
                    return true;
                case R.id.pedidos:
                    callWaiter.setVisibility(Button.VISIBLE);
                    listProducts.setVisibility(ListView.GONE);
                    listPedidos.setVisibility(ListView.VISIBLE);
                    return true;
            }
            return false;
        }
    };

    public void addProduct(CardapioResponse cr) {
        listOrders.add(cr);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_restaurant);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        listProducts = findViewById(R.id.productList);
        listPedidos = findViewById(R.id.orderList);
        callWaiter = findViewById(R.id.call_waiter);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        String imgUrl = "https://i.ytimg.com/vi/mEBFswpYms4/maxresdefault.jpg";

        listCardapio = new ArrayList<>();
        listCardapio.add(new CardapioResponse(1, "HAHAHA", "Bla bla bla", imgUrl, 9.99, 0, "cat", 2.55D));
        listCardapio.add(new CardapioResponse(1, "HEHEHE", "Bla bla bla bla", imgUrl, 1.69, 0, "cat", 5D));
        listCardapio.add(new CardapioResponse(1, "HUEHUE", "Bla bla", imgUrl, 1.99, 0, "cat", 4.8D));
        listCardapio.add(new CardapioResponse(1, "KKKKKK", "Bla", imgUrl, 6.66, 0, "cat", 3.33D));
        listCardapio.add(new CardapioResponse(1, "RSRSRS", "Bla bla bla bla bla", imgUrl, 11.22, 0, "cat", 0D));

        listOrders = new ArrayList<>();

        adapterCardapio = new AdapterCardapio(listCardapio, this, false);
        adapterPedidos = new AdapterCardapio(listOrders, this, true);
        listProducts.setAdapter(adapterCardapio);
        listPedidos.setAdapter(adapterPedidos);

        callWaiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(InRestaurantActivity.this, "Chamando atendende..", Toast.LENGTH_LONG).show();
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

}
