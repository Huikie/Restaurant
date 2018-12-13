package com.example.daan.restaurant;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;

public class MenuItemAdapter extends ArrayAdapter<MenuItem> {
    ArrayList<MenuItem> menuItem;
    //Constructor
    public MenuItemAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        menuItem = objects;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, parent, false);
        }
        int index = position;
        ImageView image = convertView.findViewById(R.id.food_Image);
        TextView food_title = convertView.findViewById(R.id.food);
        TextView food_price = convertView.findViewById(R.id.price);
        String price = menuItem.get(index).getPrice();
        String name = menuItem.get(index).getName();

        // I used Picasso to get the images from the web and put them in an ImageView (http://square.github.io/picasso/)
        String image_link = menuItem.get(index).getImageUrl();
        Picasso.get().load(image_link).into(image);

        food_title.setText(name);
        food_price.setText(price);
        return convertView;
    }
}
