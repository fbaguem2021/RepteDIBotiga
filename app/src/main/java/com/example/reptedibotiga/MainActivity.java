package com.example.reptedibotiga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static String listaCompra = "Carrito vacio";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Producte[] productes = getProductes();

        final RecyclerView listProducts = findViewById(R.id.list);
        FloatingActionButton btnFlotante = findViewById(R.id.btnFlotante);
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

        btnFlotante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), listaCompra, Toast.LENGTH_LONG).show();
            }
        });

    }

    private Producte[] getProductes(){
        return new Producte[]{
                new Producte("p0", R.drawable.persona, 94.99, 11, 32, false),
                new Producte("p1", R.drawable.persona, 59.86, 13, 1 , true, 15),
                new Producte("p2", R.drawable.persona, 40.70, 18, 62, false),
                new Producte("p3", R.drawable.persona, 52.98, 6 , 90, false),
                new Producte("p4", R.drawable.persona, 71.09, 17, 16, true, 25),
                new Producte("p5", R.drawable.persona, 79.15, 5 , 53, true, 20),
                new Producte("p6", R.drawable.persona, 38.91, 8 , 51, false),
                new Producte("p7", R.drawable.persona, 36.48, 14, 27, false),
                new Producte("p8", R.drawable.persona, 73.35, 7 , 35, true, 15),
                new Producte("p9", R.drawable.persona, 40.49, 18, 58, true, 20)
        };
    }
/*
94.99 | 11 | 32
59.86 | 13 | 10
40.70 | 18 | 62
52.98 | 06 | 90
71.09 | 17 | 16
79.15 | 05 | 53
38.91 | 08 | 51
36.48 | 14 | 27
73.35 | 07 | 35
40.49 | 18 | 58










*/
}