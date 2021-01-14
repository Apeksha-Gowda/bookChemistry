package com.example.bookshopppingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class CartView extends AppCompatActivity {
    Button checkout;
    ArrayList<Product> productList;
    float ki = 0;
    int p = 0;
    Set<String> keySet;
    TextView total;
    Collection<Integer> values;
    ArrayList<Integer> listOfValues;
    ListView list;
    ArrayList<String> listOfKeys;
    LinkedHashMap<String, Integer> hm;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        list = findViewById(R.id.cartList);
        total = findViewById(R.id.cost);
        checkout = (Button) findViewById(R.id.checkout);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplication(),"Thanks for shopping",Toast.LENGTH_SHORT).show();
                clear();
                Intent intent = new Intent(getApplicationContext(), ThankYouSplash.class);
                startActivity(intent);
            }
        });
        total.setText("Calculating");
        SharedPreferences sh = this.getSharedPreferences("cart", Context.MODE_PRIVATE);
        String jspn = sh.getString("fav", null);
        //Log.d("Apeksha", jspn);
        Type type = new TypeToken<ArrayList<Product>>() {
        }.getType();
        Gson gson = new Gson();
        productList = gson.fromJson(jspn, type);
        this.countFrequencies(productList);
        listadapter li = new listadapter();
        list.setAdapter(li);
        keySet = hm.keySet();

        // Creating an ArrayList of keys
        // by passing the keySet

        listOfKeys = new ArrayList<String>(keySet);

        // Getting Collection of values from HashMap
        values = hm.values();

        // Creating an ArrayList of values

        listOfValues = new ArrayList<>(values);
        for (int i = 0; i < listOfKeys.size(); i++) {
            for (int a = 0; a < productList.size(); a++) {
                if (listOfKeys.get(i).equals(productList.get(a).getName())) {
                    ki = ki + (listOfValues.get(i)) * (Float.parseFloat(productList.get(a).getPrice().substring(2)));
                    total.setText(Float.toString(ki));
                    break;
                }
            }
        }
        total.setText(Float.toString(ki));
    }

    public void clear() {
        SharedPreferences prefs = this.getSharedPreferences("cart", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }

    public void countFrequencies(ArrayList<Product> list) {
        // hashmap to store the frequency of element
        hm = new LinkedHashMap<String, Integer>();

        for (Product i : list) {
            Integer j = hm.get(i.getName());
            hm.put(i.getName(), (j == null) ? 1 : j + 1);
        }

        // displaying the occurrence of elements in the arraylist
        for (Map.Entry<String, Integer> val : hm.entrySet()) {
            Log.d("Apeksha", "Element " + val.getKey() + " "
                    + "occurs"
                    + ": " + val.getValue() + " times");
        }
    }

    class listadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return hm.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = getLayoutInflater().inflate(R.layout.activity_cartitems, viewGroup, false);
            ImageView img = v.findViewById(R.id.img);
            TextView quantity = v.findViewById(R.id.quantity);
            TextView bookname = v.findViewById(R.id.bookname);

            String im = "";
            for (int a = 0; a < productList.size(); a++) {
                if (listOfKeys.get(i).equals(productList.get(a).getName())) {
                    im = productList.get(a).getImage();
                    break;
                }
            }
            Glide.with(CartView.this).load(im).into(img);
            quantity.setText(listOfValues.get(i).toString());
            bookname.setText(listOfKeys.get(i));
            return v;
        }
    }
}
