package com.example.gabrielonze.resla;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gabrielonze.resla.Adapters.AdapterBook;
import com.example.gabrielonze.resla.Adapters.AdapterRestaurante;
import com.example.gabrielonze.resla.RequestsObjects.ApiManager;
import com.example.gabrielonze.resla.RequestsObjects.BookResponse;
import com.example.gabrielonze.resla.RequestsObjects.FirstScreenResponse;
import com.example.gabrielonze.resla.RequestsObjects.RestauranteResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ListView listRes, listBook;
    AdapterRestaurante adapterRes;
    AdapterBook adapterBook;
    List<RestauranteResponse> listRest;
    List<BookResponse> listBok;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_top_res:
                    listRes.setVisibility(ListView.VISIBLE);
                    listBook.setVisibility(ListView.GONE);
                    return true;
                case R.id.navigation_read_qr:
                    openQRActivity();
                    return true;
                case R.id.navigation_reservas:
                    listRes.setVisibility(ListView.GONE);
                    listBook.setVisibility(ListView.VISIBLE);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listRes = findViewById(R.id.top_res);
        listBook = findViewById(R.id.books);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getData();

    }

    public void openQRActivity() {
        Intent i = new Intent(MainActivity.this, QRCodeActivity.class);
        startActivity(i);
    }

    public void getData() {
        Call<FirstScreenResponse> req = ApiManager.getInstance().firstScreen();

        req.enqueue(new Callback<FirstScreenResponse>() {
            @Override
            public void onResponse(Call<FirstScreenResponse> call, Response<FirstScreenResponse> response) {
                FirstScreenResponse r = response.body();

                adapterRes = new AdapterRestaurante(r.getRestaurants(), MainActivity.this, true);
                listRes.setAdapter(adapterRes);

                adapterBook = new AdapterBook(r.getBooks(), MainActivity.this);
                listBook.setAdapter(adapterBook);
            }

            @Override
            public void onFailure(Call<FirstScreenResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erro :(", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
