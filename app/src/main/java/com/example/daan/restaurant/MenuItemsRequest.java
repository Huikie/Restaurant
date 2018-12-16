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

public class MenuItemsRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    Context context;
    Callback callback_activity;
    ArrayList<MenuItem> menuItems = new ArrayList<>();

    /**Method that makes it possible to do a callback from a different activity.*/
    public interface Callback {
        void gotMenuItems(ArrayList<MenuItem> menuItems);
        void gotMenuItemsError(String message);
    }

    // Constructor
    public MenuItemsRequest(Context context){
        this.context = context;
    }

    /**This method will attempt to retrieve the menuitem that is clicked on (category parameter)
     *  from the API, and if succesful, will notify the activity that instantiated the request
     *  that it is done through the callback. */
    public void getMenuItems(Callback activity, String category){
        callback_activity = activity;
        String json_url = "https://resto.mprog.nl/menu?category=" + category;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(json_url, null, this,this);
        queue.add(jsonObjectRequest);
    }

    @Override
    /**When something goes wrong with getting the menuitem this method display an error message.*/
    public void onErrorResponse(VolleyError error) {
        callback_activity.gotMenuItemsError(error.getMessage());
    }

    @Override
    /**When everything goes as expected the response parameter will contain a JSONObject.*/
    public void onResponse(JSONObject response) {
        try{

            // Get array with menuitems out of JSONObject.
            JSONArray jsonArray = response.getJSONArray("items");

            // Add every item in that array to the menuItems ArrayList<MenuItem>.
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject category = jsonArray.getJSONObject(i);
                String description = category.getString("description");
                String price = category.getString("price");
                String image_url = category.getString("image_url");
                String name = category.getString("name");
                menuItems.add(new MenuItem(name, description, image_url, category.toString(), "€" + price));

            }

            // Pass the ArrayList back to the activity that wanted to have it.
            callback_activity.gotMenuItems(menuItems);

        }
        catch(JSONException e){
            System.out.println(e.toString());
        }
    }

}
