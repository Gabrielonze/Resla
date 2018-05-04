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
import com.example.gabrielonze.resla.RequestsObjects.CardapioResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class InRestaurantActivity extends AppCompatActivity {

    List<CardapioResponse> listCardapio, listOrders;
    ListView listProducts, listPedidos;
    AdapterCardapio adapterCardapio, adapterPedidos;
    Button callWaiter, sortButton;
    String sort;

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

        String imgUrl = "https://i.ytimg.com/vi/mEBFswpYms4/maxresdefault.jpg";

        listCardapio = new ArrayList<>();
        listCardapio.add(new CardapioResponse(1, "HAHAHA", "Bla bla bla", imgUrl, 9.99, 0, "Pizza", 2.55D));
        listCardapio.add(new CardapioResponse(1, "HEHEHE", "Bla bla bla bla", imgUrl, 1.69, 0, "Doce", 5D));
        listCardapio.add(new CardapioResponse(1, "HUEHUE", "Bla bla", imgUrl, 1.99, 0, "Pizza", 4.8D));
        listCardapio.add(new CardapioResponse(1, "KKKKKK", "Bla", imgUrl, 6.66, 0, "Drinks", 3.33D));
        listCardapio.add(new CardapioResponse(1, "RSRSRS", "Bla bla bla bla bla", imgUrl, 11.22, 0, "Doce", 0D));

        listOrders = new ArrayList<>();

        adapterCardapio = new AdapterCardapio(listCardapio, this, false, false);
        adapterPedidos = new AdapterCardapio(listOrders, this, true, false);
        listProducts.setAdapter(adapterCardapio);
        listPedidos.setAdapter(adapterPedidos);

        changeSort(false);

        callWaiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(InRestaurantActivity.this, "Chamando atendende..", Toast.LENGTH_LONG).show();
            }
        });

        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSort(true);
            }
        });

    }

    private void changeSort(Boolean firstTime) {

        String sort = getIntent().getStringExtra("sort");
        if (sort == null)
            sort = "Nota";

        switch (sort) {
            case "Categoria":

                Collections.sort(listCardapio, new Comparator<CardapioResponse>() {
                    @Override public int compare(CardapioResponse p1, CardapioResponse p2) {
                        return (int) (p1.getPrice() - p2.getPrice());
                    }

                });
                Log.d("aa", listCardapio.toString());
                sort = "Preço";
                break;


            case "Preço":

                Collections.sort(listCardapio, new Comparator<CardapioResponse>() {
                    @Override public int compare(CardapioResponse p1, CardapioResponse p2) {
                        return (int) (p2.getRating() - p1.getRating());
                    }
                });
                Log.d("aa", listCardapio.toString());
                sort = "Nota";
                break;


            default:

                Collections.sort(listCardapio, new Comparator<CardapioResponse>() {
                    @Override public int compare(CardapioResponse p1, CardapioResponse p2) {
                        return p1.getCategory().hashCode() - p2.getCategory().hashCode();
                    }

                });
                Log.d("aa", listCardapio.toString());
                sort = "Categoria";
                break;
        }

        /*adapterCardapio = new AdapterCardapio(listCardapio, this, false);
        listProducts.setAdapter(adapterCardapio);*/
        if (firstTime){
            finish();
            overridePendingTransition(0, 0);
            Intent i = getIntent();
            i.putExtra("sort", sort);
            startActivity(i);
            overridePendingTransition(0, 0);
        }
        sortButton.setText(sort);
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
