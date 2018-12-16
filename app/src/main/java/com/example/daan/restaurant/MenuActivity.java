package com.example.daan.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

// Implements MenuItemsRequest.Callback to be able to retrieve menuitems from the Restaurant server.
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

        // Put a menuitem that is clicked to the activity where a single menuitem will be shown.
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
    /**Put the received menuitems in a ListView using the custom adapter.*/
    public void gotMenuItems(ArrayList<MenuItem> menuItems) {
        MenuItemAdapter itemsAdapter = new MenuItemAdapter(this, R.layout.menu_item, menuItems);
        ListView food_list = findViewById(R.id.menu_List);
        food_list.setAdapter(itemsAdapter);
    }

    @Override
    /**If something goes wrong with receiving the menuitems an error message will be displayed.*/
    public void gotMenuItemsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
