package com.example.gabrielonze.resla.Adapters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gabrielonze.resla.FoodDetailsActivity;
import com.example.gabrielonze.resla.R;
import com.example.gabrielonze.resla.RequestsObjects.CardapioResponse;
import com.example.gabrielonze.resla.RequestsObjects.RestauranteResponse;
import com.example.gabrielonze.resla.RestaurantActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterRestaurante extends BaseAdapter {

    public List<RestauranteResponse> quotes;
    private final AppCompatActivity act;
    private final Boolean pedido;


    public AdapterRestaurante(List<RestauranteResponse> quotes, AppCompatActivity act, Boolean pedido) {
        this.quotes = quotes;
        this.act = act;
        this.pedido = pedido;
    }

    @Override
    public int getCount() {
        return quotes.size();
    }

    @Override
    public Object getItem(int position) {
        return quotes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {

        final ImageView logo;
        final TextView name_txt, desc_txt;
        final Button addButton;

        View view = act.getLayoutInflater().inflate(R.layout.adapter_product, parent, false);

        logo = view.findViewById(R.id.food_img);
        name_txt = view.findViewById(R.id.food_name);
        desc_txt = view.findViewById(R.id.food_description);
        addButton = view.findViewById(R.id.add_button);

        if (convertView == null) {
            final RestauranteResponse product = quotes.get(position);

            if (product != null) {

                name_txt.setText(product.getName());

                desc_txt.setText("Nota: " + product.getRating());
                addButton.setText("Ver pratos");

                Picasso.with(act.getApplicationContext()).load(product.getImageUrl()).
                        placeholder(android.R.drawable.progress_indeterminate_horizontal).
                        resize(190,54).
                        into(logo);

                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        productDetails(product);
                    }
                });

            } else {
                System.out.println("Linha não encontrada");
            }

        } else {
            return convertView;
        }

        return view;
    }


    private void productDetails(RestauranteResponse ar) {
        Intent i = new Intent(act, RestaurantActivity.class);
        // i.putExtra("product", json);
        act.startActivityForResult(i, 1);
    }
}