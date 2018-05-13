package com.example.gabrielonze.resla;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gabrielonze.resla.RequestsObjects.ApiManager;
import com.example.gabrielonze.resla.RequestsObjects.CardapioResponse;
import com.example.gabrielonze.resla.RequestsObjects.OkResponse;
import com.example.gabrielonze.resla.RequestsObjects.RestauranteResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookActivity extends AppCompatActivity {

    private Button addButton;
    private ImageView logo;
    private TextView name_txt;
    private EditText qtd, day, obs;
    private int restaurantId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book);
        restaurantId = this.getIntent().getIntExtra("restaurantId", -1);

        addButton = findViewById(R.id.add_button);
        logo = findViewById(R.id.food_img);
        name_txt = findViewById(R.id.food_name);
        qtd = findViewById(R.id.qtd);
        day = findViewById(R.id.dayInput);
        obs = findViewById(R.id.obs);

        getRestaurantInfo();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInputs();
            }
        });
    }

    private void checkInputs() {
        if(qtd.getText().toString().equals("") || qtd.getText().toString().equals("") || day.getText().toString().equals("")){
            Toast.makeText(this, "Informações incompletas", Toast.LENGTH_SHORT).show();
        } else {
            saveBook();
        }
    }

    private void saveBook() {

        Call<OkResponse> req = ApiManager.getInstance().saveBook(restaurantId, Integer.parseInt(qtd.getText().toString()), day.getText().toString(), obs.getText().toString());
        req.enqueue(new Callback<OkResponse>() {
            @Override
            public void onResponse(Call<OkResponse> call, Response<OkResponse> response) {
                finish();
            }

            @Override
            public void onFailure(Call<OkResponse> call, Throwable t) {
                Toast.makeText(BookActivity.this, "Erro :(", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void getRestaurantInfo() {
        Call<RestauranteResponse> req = ApiManager.getInstance().restaurantInfo(restaurantId);
        req.enqueue(new Callback<RestauranteResponse>() {
            @Override
            public void onResponse(Call<RestauranteResponse> call, Response<RestauranteResponse> response) {
                RestauranteResponse r = response.body();

                name_txt.setText(r.getName());

                Picasso.with(getApplicationContext()).load(r.getImageUrl()).
                        placeholder(android.R.drawable.progress_indeterminate_horizontal).
                        into(logo);
            }

            @Override
            public void onFailure(Call<RestauranteResponse> call, Throwable t) {
                Toast.makeText(BookActivity.this, "Erro :(", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
