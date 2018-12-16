package com.example.daan.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

// Implements CategoriesRequest.Callback to be able to retrieve categories from the Restaurant server.
public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        CategoriesRequest x = new CategoriesRequest(this);
        x.getCategories(this);
        ListView categories_list = findViewById(R.id.categories_List);

        // Put a category that is clicked, to the activity where menuitems that belong to that category will be shown.
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
    /**Put the received categories in a ListView a standard adapter.*/
    public void gotCategories(ArrayList<String> categories) {

        //I used this instructions to implement the adapter: https://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);

        ListView categories_list = findViewById(R.id.categories_List);
        categories_list.setAdapter(itemsAdapter);
    }

    @Override
    /**If something goes wrong with receiving the categories an error message will be displayed.*/
    public void gotCategoriesError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}