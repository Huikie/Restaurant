package com.example.daan.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        CategoriesRequest x = new CategoriesRequest(this);
        x.getCategories(this);
        //Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show();
        ListView categories_list = findViewById(R.id.categories_List);


        categories_list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int i = position;
                Object category = parent.getItemAtPosition(i);
                Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
                intent.putExtra("category", category.toString());
                startActivity(intent);
            }
        });
    }
    @Override
    public void gotCategories(ArrayList<String> categories) {
        //Toast.makeText(this, categories.get(0), Toast.LENGTH_LONG).show();
        //I used this instructions to implement the adapter: https://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);
        ListView categories_list = findViewById(R.id.categories_List);
        categories_list.setAdapter(itemsAdapter);
    }

    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}