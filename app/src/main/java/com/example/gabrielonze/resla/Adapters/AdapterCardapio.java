package com.example.gabrielonze.resla.Adapters;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gabrielonze.resla.FoodDetailsActivity;
import com.example.gabrielonze.resla.InRestaurantActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.example.gabrielonze.resla.R;
import com.example.gabrielonze.resla.RequestsObjects.CardapioResponse;

import java.lang.reflect.Type;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterCardapio extends BaseAdapter {

    public List<CardapioResponse> quotes;
    private final InRestaurantActivity act;
    private final Boolean pedido;


    public AdapterCardapio(List<CardapioResponse> quotes, InRestaurantActivity act, Boolean pedido) {
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

        if (pedido) {
            addButton.setVisibility(Button.GONE);
        } else {
            addButton.setVisibility(Button.VISIBLE);
        }

        if (convertView == null) {
            final CardapioResponse product = quotes.get(position);

            if (product != null) {

                name_txt.setText(product.getName());

                if (pedido) {
                    desc_txt.setText("Quantidade: " + product.getQuantity());
                } else {
                    desc_txt.setText("Nota: " + product.getRating() + " - Categoria: " + product.getCategory());
                }

                String valorString = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(product.getPrice());
                addButton.setText("Detalhes - " + valorString);

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


    private void productDetails(CardapioResponse ar) {
        Intent i = new Intent(act, FoodDetailsActivity.class);
        Gson gson = new Gson();
        Type type = new TypeToken<CardapioResponse>() {}.getType();
        String json = gson.toJson(ar, type);
        i.putExtra("product", json);
        act.startActivityForResult(i, 1);
    }
}