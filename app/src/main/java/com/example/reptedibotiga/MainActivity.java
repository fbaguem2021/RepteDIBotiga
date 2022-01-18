package com.example.reptedibotiga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Producte> carrito = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Producte[] productes = getProductes();

        ListView list = findViewById(R.id.list);
        ProductesAdapter adapter = new ProductesAdapter(this, productes);
        list.setAdapter(adapter);

    }

    private Producte[] getProductes(){
        ArrayList<Producte> llista = new ArrayList<>();


        return new Producte[]{
                new Producte("p0", 0, 0, 0, false),
                new Producte("p1", 0, 0, 0, true, 0),
                new Producte("p2", 0, 0, 0, false),
                new Producte("p3", 0, 0, 0, false),
                new Producte("p4", 0, 0, 0, true, 0),
                new Producte("p5", 0, 0, 0, true, 0),
                new Producte("p6", 0, 0, 0, false),
                new Producte("p7", 0, 0, 0, false),
                new Producte("p8", 0, 0, 0, true, 0),
                new Producte("p9", 0, 0, 0, true, 0)
        };
    }
}