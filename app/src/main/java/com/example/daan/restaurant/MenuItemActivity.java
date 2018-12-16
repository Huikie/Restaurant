package com.example.daan.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class MenuItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        // Get clicked MenuItem
        Intent intent = getIntent();
        final MenuItem retrievedMenuItem = (MenuItem) intent.getSerializableExtra("clicked_MenuItem");

        TextView title = findViewById(R.id.detail_Name);
        ImageView food_image = findViewById(R.id.detail_Image);
        TextView description = findViewById(R.id.detail_Description);
        TextView price = findViewById(R.id.detail_Price);

        // Fill the right View with right elements from the MenuItem.
        title.setText(retrievedMenuItem.getName());
        Picasso.get().load(retrievedMenuItem.getImageUrl()).into(food_image);
        description.setText(retrievedMenuItem.getDescription());
        price.setText(retrievedMenuItem.getPrice());

    }
}
