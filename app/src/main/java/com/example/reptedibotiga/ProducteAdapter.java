package com.example.reptedibotiga;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class ProductesAdapter extends ArrayAdapter /*extends RecyclerView.Adapter<ProductesAdapter.ViewHolder> implements View.OnClickListener*/ {
    private Producte[] productes;
    private View.OnClickListener listener;

    public ProductesAdapter(Context context, Producte[] productes){
        super(context, R.layout.products_item, productes);
        this.productes = productes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View item = LayoutInflater.from(getContext()).inflate(R.layout.products_item, parent, false);

        TextView lblName = item.findViewById(R.id.lblName);

        lblName.setText(productes[position].getName());

        return item;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View item){
            super(item);

        }
    }
}
