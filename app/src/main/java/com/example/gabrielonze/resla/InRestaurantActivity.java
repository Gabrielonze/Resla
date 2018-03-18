package com.example.gabrielonze.resla;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gabrielonze.resla.Adapters.AdapterCardapio;
import com.example.gabrielonze.resla.RequestsObjects.CardapioResponse;

import java.util.ArrayList;
import java.util.List;

public class InRestaurantActivity extends AppCompatActivity {

    List<CardapioResponse> listCardapio, listOrders;
    ListView listProducts, listPedidos;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.cardapio:
                    listPedidos.setVisibility(ListView.GONE);
                    listProducts.setVisibility(ListView.VISIBLE);
                    return true;
                case R.id.pedidos:
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
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        String imgUrl = "https://i.ytimg.com/vi/mEBFswpYms4/maxresdefault.jpg";

        listCardapio = new ArrayList<>();
        listCardapio.add(new CardapioResponse(1, "HAHAHA", "Bla bla bla", imgUrl, 5.99));
        listCardapio.add(new CardapioResponse(1, "HEHEHE", "Bla bla bla bla", imgUrl, 7.83));
        listCardapio.add(new CardapioResponse(1, "HUEHUE", "Bla bla", imgUrl, 1.99));
        listCardapio.add(new CardapioResponse(1, "KKKKKK", "Bla", imgUrl, 6.66));
        listCardapio.add(new CardapioResponse(1, "RSRSRS", "Bla bla bla bla bla", imgUrl, 8.86));

        listOrders = new ArrayList<>();

        ListAdapter adapterCardapio = new AdapterCardapio(listCardapio, this, false);
        ListAdapter adapterPedidos = new AdapterCardapio(listOrders, this, true);
        listProducts.setAdapter(adapterCardapio);
        listPedidos.setAdapter(adapterPedidos);

    }

}
