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

public class MenuActivity extends AppCompatActivity implements MenuItemsRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intent = getIntent();
        String category = intent.getStringExtra("category");
        MenuItemsRequest x = new MenuItemsRequest(this);
        x.getMenuItems(this, category);

        ListView menu_item_List = findViewById(R.id.menu_List);

        menu_item_List.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int i = position;
                MenuItem clickedMenuItem = (MenuItem) parent.getItemAtPosition(i);
                Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
                intent.putExtra("clicked_MenuItem", clickedMenuItem);
                startActivity(intent);
            }
        });

    }

    @Override
    public void gotMenuItems(ArrayList<MenuItem> menuItems) {
        //Toast.makeText(this, menuItems.get(0), Toast.LENGTH_LONG).show();
        MenuItemAdapter itemsAdapter = new MenuItemAdapter(this, R.layout.menu_item, menuItems);
        ListView food_list = findViewById(R.id.menu_List);
        food_list.setAdapter(itemsAdapter);
    }

    @Override
    public void gotMenuItemsError(String message) {

    }

}
