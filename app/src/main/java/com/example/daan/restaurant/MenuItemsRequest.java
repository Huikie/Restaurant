package com.example.daan.restaurant;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuItemsRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    Context context;
    Callback callback_activity;
    ArrayList<MenuItem> menuItems = new ArrayList<>();

    public interface Callback {
        void gotMenuItems(ArrayList<MenuItem> menuItems);
        void gotMenuItemsError(String message);
    }

    public MenuItemsRequest(Context context){
        this.context = context;
    }

    public void getMenuItems(Callback activity, String category){
        callback_activity = activity;
        String json_url = "https://resto.mprog.nl/menu?category=" + category;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(json_url, null, this,this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        callback_activity.gotMenuItemsError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try{

            JSONArray jsonArray = response.getJSONArray("items");
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject category = jsonArray.getJSONObject(i);
                String description = category.getString("description");
                String price = category.getString("price");
                String image_url = category.getString("image_url");
                String name = category.getString("name");
                menuItems.add(new MenuItem(name, description, image_url, category.toString(), price));

            }
            callback_activity.gotMenuItems(menuItems);
        }
        catch(JSONException e){
            System.out.println(e.toString());
        }
    }
}
