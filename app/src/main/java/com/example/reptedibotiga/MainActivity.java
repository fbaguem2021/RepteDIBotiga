package com.example.reptedibotiga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static LinearLayout preLayout = null;
    public static String listaCompra = "Carrito vacio";
    public static Producte producteSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar =  findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        final Producte[] productes = getProductes();

        final RecyclerView listProducts = findViewById(R.id.list);
        FloatingActionButton btnFlotante = findViewById(R.id.btnFlotante);
        ProductesAdapter adapter = new ProductesAdapter(productes);

        listProducts.setHasFixedSize(false);
        listProducts.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        listProducts.setAdapter(adapter);

        // Marca de forma visual quin producte s'ha sel·leccionat
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = listProducts.getChildAdapterPosition(view);

                LinearLayout curLayout = view.findViewById(R.id.itemLayout);
                if (curLayout != preLayout) {

                    producteSelected = productes[position];
                    curLayout.setBackgroundResource(R.drawable.border_selected);

                    if (preLayout != null)
                        preLayout.setBackgroundResource(R.drawable.border);

                    preLayout = curLayout;
                }



            }
        });

        // Mostra un toast amb tots els elements afegits a la llista de la compra
        btnFlotante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), listaCompra, Toast.LENGTH_LONG).show();
            }
        });

    }
    // Inicia el botó de la AppBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }
    // Event que s'activa al clicar en el botó de la AppBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.myActionButton:
                // Comprova si l'usuari ha sel·leccionat o no un producte
                if (producteSelected != null) {
                    Toast.makeText(this, "Pasando a la compra del producto", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, FitxaActivity.class);
                    intent.putExtra(FitxaActivity.PRODUCTE, producteSelected);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Primero debes seleccionar un producto ", Toast.LENGTH_SHORT).show();
                }

                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
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
}