package com.example.gabrielonze.resla;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gabrielonze.resla.Adapters.AdapterBook;
import com.example.gabrielonze.resla.Adapters.AdapterCardapio;
import com.example.gabrielonze.resla.Adapters.AdapterRestaurante;
import com.example.gabrielonze.resla.RequestsObjects.BookResponse;
import com.example.gabrielonze.resla.RequestsObjects.RestauranteResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
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
                    /*listProducts.setVisibility(ListView.GONE);*/
                    return true;
                case R.id.navigation_reservas:
                    listRes.setVisibility(ListView.GONE);
                    listBook.setVisibility(ListView.VISIBLE);
                    return true;
                case R.id.navigation_read_qr:
                    openQRActivity();
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

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        String imgUrl = "https://i.ytimg.com/vi/mEBFswpYms4/maxresdefault.jpg";

        listRest = new ArrayList<>();
        listRest.add(new RestauranteResponse(1, "McDonalds", imgUrl, 2.55D));
        listRest.add(new RestauranteResponse(1, "Burger King", imgUrl, 5D));
        listRest.add(new RestauranteResponse(1, "Girafas", imgUrl, 4.8D));
        listRest.add(new RestauranteResponse(1, "Bobs", imgUrl, 3.33D));
        listRest.add(new RestauranteResponse(1, "Habibs", imgUrl, 0D));

        adapterRes = new AdapterRestaurante(listRest, this, true);
        listRes.setAdapter(adapterRes);


        listBok = new ArrayList<>();
        listBok.add(new BookResponse(1, "Bobs", imgUrl, false, 2));
        listBok.add(new BookResponse(1, "Habibs", imgUrl, true, 5));

        adapterBook = new AdapterBook(listBok, this);
        listBook.setAdapter(adapterBook);

    }

    public void openQRActivity() {
        Intent i = new Intent(MainActivity.this, QRCodeActivity.class);
        startActivity(i);
    }

    //public void showTop

}
