package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String jsonString) {
        Sandwich sandwich = new Sandwich();
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(jsonString);
            JSONObject name = jsonObject.getJSONObject("name");
            String mainName = name.getString("mainName");
            sandwich.setMainName(mainName);
            JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");
            ArrayList<String> listOfAlsoKnownAs = new ArrayList<>();
            for (int index = 0; index < alsoKnownAs.length(); index++) {
                listOfAlsoKnownAs.add(alsoKnownAs.getString(index));
            }
            sandwich.setAlsoKnownAs(listOfAlsoKnownAs);
            String placeOfOrigin = jsonObject.getString("placeOfOrigin");
            sandwich.setPlaceOfOrigin(placeOfOrigin);
            String description = jsonObject.getString("description");
            sandwich.setDescription(description);
            String image = jsonObject.getString("image");
            sandwich.setImage(image);
            JSONArray ingredients = jsonObject.getJSONArray("ingredients");
            ArrayList<String> listOfIngredients = new ArrayList<>();
            for (int index = 0; index < ingredients.length(); index++){
                listOfIngredients.add(ingredients.getString(index));
            }
            sandwich.setIngredients(listOfIngredients);

        } catch (JSONException e) {
            return null;
        }
        return sandwich;
    }
}
