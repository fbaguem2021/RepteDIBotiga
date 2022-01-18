package com.example.reptedibotiga;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class ProductesAdapter extends RecyclerView.Adapter<ProductesAdapter.ViewHolder> implements View.OnClickListener {
    private Producte[] productes;
    private View.OnClickListener listener;

    public ProductesAdapter(Producte[] productes){
        this.productes = productes;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView lblName;
        TextView lblPrice;

        public ViewHolder(@NonNull View item){
            super(item);
            imgProduct = item.findViewById(R.id.imgProduct);
            lblName = item.findViewById(R.id.lblName);
            lblPrice = item.findViewById(R.id.lblPrice);
        }

        void bindGame(Producte product){
            imgProduct.setImageResource(product.getImage());
            lblName.setText(product.getName());
            lblPrice.setText(product.getPrice()+"€");
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.products_item, parent, false);
        item.setOnClickListener(this);
        return new ViewHolder(item);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindGame(productes[position]);
    }

    public int getItemCount() {
        return productes.length;
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view){
        if(listener != null){
            listener.onClick(view);
        }
    }
}
