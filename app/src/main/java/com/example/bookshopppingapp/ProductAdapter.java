package com.example.bookshopppingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    Context context;

    ArrayList<Product> products;
    ArrayList<Product> productsInCart =  new ArrayList<Product>();;

    public ProductAdapter(Context c, ArrayList<Product>p){
        context =c;
        products= p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_comicproductlist,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(products.get(position).getName());
        holder.price.setText(products.get(position).getPrice());

        Picasso.get().load(products.get(position).getImage()).into(holder.productPicture);
        holder.onClick(position);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,price,description;
        ImageView productPicture;
        Button addToCart,viewCart;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.productName);
            price = (TextView) itemView.findViewById(R.id.productPrice);
            productPicture = (ImageView) itemView.findViewById(R.id.productPicture);
            addToCart = (Button) itemView.findViewById(R.id.addToCart);
            viewCart = (Button) itemView.findViewById(R.id.viewCart);
        }
        public void onClick (final int position){
            addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    SharedPreferences sh=context.getSharedPreferences("cart",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sh.edit();
                    Gson gson= new Gson();
                    ArrayList<Product> prod =  new ArrayList<Product>();
                    ArrayList<Product> prod1 =  new ArrayList<Product>();
                    String jspn=sh.getString("fav",null);
                    Type type=new TypeToken<ArrayList<Product>>(){}.getType();
                    prod=gson.fromJson(jspn,type);
                    if(prod==null){
                    prod1.add(products.get(position));
                    Gson g=new Gson();
                    String json=   gson.toJson(prod1);
                    editor.putString("fav",json);
                    editor.apply();

                    }else{
                        prod.add(products.get(position));
                        Gson g=new Gson();
                        String json=   gson.toJson(prod);
                        editor.putString("fav",json);
                        editor.apply();
                    }
                    Toast.makeText(context," ' " + products.get(position).getName()+ " ' added to cart",Toast.LENGTH_SHORT).show();
                }
            });

            viewCart.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context.getApplicationContext(), CartView.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}
