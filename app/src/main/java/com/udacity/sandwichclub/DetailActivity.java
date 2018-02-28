package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.*;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private ImageView ingredientsIv;
    private TextView originTv;
    private TextView alsoKnownTv;
    private TextView ingredientsTv;
    private TextView descriptionTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ingredientsIv = findViewById(R.id.image_iv);
        originTv = findViewById(R.id.origin_tv);
        alsoKnownTv = findViewById(R.id.also_known_tv);
        ingredientsTv = findViewById(R.id.ingredients_tv);
        descriptionTv = findViewById(R.id.description_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Log.v("JSON", json);
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }
        populateUI(sandwich);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        Picasso.with(this)
                .load(sandwich.getImage())
                .placeholder(R.drawable.placeholder)
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
        originTv.setText(sandwich.getPlaceOfOrigin());
        alsoKnownTv.setText(listToString(sandwich.getAlsoKnownAs()));
        ingredientsTv.setText(listToString(sandwich.getIngredients()));
        descriptionTv.setText(sandwich.getDescription());
    }

    private String listToString(List<String> listOfStrings) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : listOfStrings) {
            stringBuilder.append(string);
            stringBuilder.append(", ");
        }
        if(!stringBuilder.toString().isEmpty())
            stringBuilder.delete(stringBuilder.length()-2, stringBuilder.length());
        else
            stringBuilder.append("none");
        return stringBuilder.toString();
    }
}
