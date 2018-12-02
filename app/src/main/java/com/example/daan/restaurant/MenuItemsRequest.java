package com.example.daan.restaurant;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

public class MenuItemsRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    Context context;
    CategoriesRequest categoriesRequest;
    Callback callback_activity;

    public interface Callback {
        void gotMenuItems(ArrayList<MenuActivity.MenuItem> menuItems);
        void gotMenuItemsError(String message);
    }

    public void getMenuItems(Callback activity){
        callback_activity = activity;
        //https://resto.mprog.nl/menu?category=entrees
        String json_url = "https://resto.mprog.nl/menu";
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(json_url, null, this,this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

    }
}
