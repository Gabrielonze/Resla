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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gabrielonze.resla.Adapters.AdapterCardapio;
import com.example.gabrielonze.resla.RequestsObjects.CardapioResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FoodDetailsActivity extends AppCompatActivity {

    private CardapioResponse product;

    private Button addButton;
    private ImageView logo;
    private TextView name_txt, desc_txt;
    private EditText qtd;
    private Gson gson = new Gson();
    private Type type = new TypeToken<CardapioResponse>() {}.getType();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_details);

        String stringLocation = this.getIntent().getStringExtra("product");
        if(stringLocation != null) {
            product = gson.fromJson(stringLocation, type);
        }

        addButton = findViewById(R.id.add_button);
        logo = findViewById(R.id.food_img);
        name_txt = findViewById(R.id.food_name);
        desc_txt = findViewById(R.id.food_description);
        qtd = findViewById(R.id.qtd);

        name_txt.setText(product.getName());
        desc_txt.setText(product.getDescription());

        Picasso.with(getApplicationContext()).load(product.getImageUrl()).
                placeholder(android.R.drawable.progress_indeterminate_horizontal).
                into(logo);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnInfo();
            }
        });
    }

    private void returnInfo() {
        Intent returnIntent = new Intent();

        try{
            product.setQuantity(Integer.parseInt(qtd.getText().toString()));
            String json = gson.toJson(product, type);
            returnIntent.putExtra("product", json);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        } catch (Exception e) {
            Toast.makeText(this, "Digite uma quantidade", Toast.LENGTH_LONG).show();
        }

    }

}
