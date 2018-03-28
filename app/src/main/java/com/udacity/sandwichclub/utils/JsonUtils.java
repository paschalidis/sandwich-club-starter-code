package com.udacity.sandwichclub.utils;

import android.support.annotation.Nullable;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    // Hardcoded values for json indexes
    private static final String JSON_NAME_INDEX = "name";
    private static final String JSON_NAME_MAIN_INDEX = "mainName";
    private static final String JSON_NAME_ALSO_KNOWN_INDEX = "alsoKnownAs";
    private static final String JSON_PLACE_OF_ORIGIN_INDEX = "placeOfOrigin";
    private static final String JSON_DESCRIPTION_INDEX = "description";
    private static final String JSON_IMAGE_INDEX = "image";
    private static final String JSON_INGREDIENTS_INDEX = "ingredients";

    @Nullable
    public static Sandwich parseSandwichJson(String json) {
        Log.d(JsonUtils.class.getName(), json);

        if (json.isEmpty()) {
            return null;
        }

        Sandwich sandwich = new Sandwich();

        try {
            JSONObject sandwichJsonObject = new JSONObject(json);
            JSONObject nameJsonObject = sandwichJsonObject.getJSONObject(JSON_NAME_INDEX);

            sandwich.setMainName(nameJsonObject.getString(JSON_NAME_MAIN_INDEX));
            sandwich.setPlaceOfOrigin(sandwichJsonObject.getString(JSON_PLACE_OF_ORIGIN_INDEX));
            sandwich.setDescription(sandwichJsonObject.getString(JSON_DESCRIPTION_INDEX));
            sandwich.setImage(sandwichJsonObject.getString(JSON_IMAGE_INDEX));

            JSONArray ingredientJsonArray = sandwichJsonObject.getJSONArray(JSON_INGREDIENTS_INDEX);
            sandwich.setIngredients(convertJsonArray(ingredientJsonArray));

            JSONArray alsoKnownAsJsonArray = nameJsonObject.getJSONArray(JSON_NAME_ALSO_KNOWN_INDEX);
            sandwich.setAlsoKnownAs(convertJsonArray(alsoKnownAsJsonArray));

            return sandwich;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Convert JSONArray type to ArrayList<String>
     *
     * @param JSONArray jsonArray
     * @return ArrayList<String>
     * @throws JSONException exception parsing array
     */
    private static ArrayList<String> convertJsonArray(JSONArray jsonArray) throws JSONException {
        ArrayList<String> data = new ArrayList<>();

        if (jsonArray == null) {
            return data;
        }

        for (int i = 0; i < jsonArray.length(); i++) {
            data.add(jsonArray.getString(i));
        }

        return data;
    }
}