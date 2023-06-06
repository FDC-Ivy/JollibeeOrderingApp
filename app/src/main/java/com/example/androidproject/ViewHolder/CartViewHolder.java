package com.example.androidproject.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.R;

public class CartViewHolder extends RecyclerView.ViewHolder{
    public ImageView cartImage;
    public TextView cartProductName, cartProductPrice, cartProductQty, deleteProduct;
    public RelativeLayout cartClick;
    public CartViewHolder(@NonNull View itemView) {
        super(itemView);

        cartImage = itemView.findViewById(R.id.cart_product_image);
        cartProductName = itemView.findViewById(R.id.cart_product_name);
        cartProductPrice = itemView.findViewById(R.id.cart_product_price);
        cartProductQty = itemView.findViewById(R.id.cart_product_qty);
        cartClick = itemView.findViewById(R.id.cart_view_click);
        deleteProduct = itemView.findViewById(R.id.cart_product_delete_txt);
    }
}
