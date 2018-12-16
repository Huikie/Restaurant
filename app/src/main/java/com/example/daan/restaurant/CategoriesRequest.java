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

    /**Method that makes it possible to do a callback from a different activity.*/
    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    // Constructor
    public CategoriesRequest(Context context){
        this.context = context;
    }

    /**This method will attempt to retrieve the categories from the API, and if succesful, will notify the
     * activity that instantiated the request that it is done through the callback. */
    public void getCategories(Callback activity){
        callback_activity = activity;
        String json_url = "https://resto.mprog.nl/categories";
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(json_url, null, this,this);
        queue.add(jsonObjectRequest);
    }

    @Override
    /**When something goes wrong with getting the categories this method display an error message.*/
    public void onErrorResponse(VolleyError error) {
        callback_activity.gotCategoriesError(error.getMessage());
    }

    @Override
    /**When everything goes as expected the response parameter will contain a JSONObject.*/
    public void onResponse(JSONObject response) {
            try{
                // Get array with menu categories out of JSONObject.
                JSONArray jsonArray = response.getJSONArray("categories");

                // Add every item in that array to the categories ArrayList<String>.
                for (int i = 0; i < jsonArray.length(); i++){
                    categories.add(jsonArray.getString(i));
                }

                // Pass the ArrayList back to the activity that wanted to have it.
                callback_activity.gotCategories(categories);

            }
            catch(JSONException e){
                System.out.println(e.toString());
            }
    }

}
