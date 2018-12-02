package com.example.daan.restaurant;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener{
    Context context;
    Callback callback_activity;
    ArrayList<String> categories = new ArrayList<>();

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    public CategoriesRequest(Context context){
        this.context = context;
    }

    public void getCategories(Callback activity){
        callback_activity = activity;
        String json_url = "https://resto.mprog.nl/categories";
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(json_url, null, this,this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        callback_activity.gotCategoriesError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
            try{
                JSONArray jsonArray = response.getJSONArray("categories");

                for (int i = 0; i < jsonArray.length(); i++){
                    categories.add(jsonArray.getString(i));
                }
                callback_activity.gotCategories(categories);
            }
            catch(JSONException e){
                System.out.println(e.toString());
            }
    }
}
