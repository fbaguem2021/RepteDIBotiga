package com.example.reptedibotiga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Producte[] productes = getProductes();

        final RecyclerView listProducts = findViewById(R.id.list);
        ProductesAdapter adapter = new ProductesAdapter(productes);

        listProducts.setHasFixedSize(false);
        listProducts.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        listProducts.setAdapter(adapter);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FitxaActivity.class);

                int position = listProducts.getChildAdapterPosition(view);
                Producte producte =productes[position];
                intent.putExtra(FitxaActivity.PRODUCTE, producte);
                intent.putExtra(FitxaActivity.POSITION, position);

                startActivity(intent);
            }
        });
    }

    private Producte[] getProductes(){
        return new Producte[]{
                new Producte("p0",0, 0, 0, 0, false),
                new Producte("p1",0, 0, 0, 0, true, 0),
                new Producte("p2",0, 0, 0, 0, false),
                new Producte("p3",0, 0, 0, 0, false),
                new Producte("p4",0, 0, 0, 0, true, 0),
                new Producte("p5",0, 0, 0, 0, true, 0),
                new Producte("p6",0, 0, 0, 0, false),
                new Producte("p7",0, 0, 0, 0, false),
                new Producte("p8",0, 0, 0, 0, true, 0),
                new Producte("p9",0, 0, 0, 0, true, 0)
        };
    }
}