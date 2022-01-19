package com.example.reptedibotiga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FitxaActivity extends AppCompatActivity {

    public static final String PRODUCTE = "producte";
    public static final String POSITION = "position";
    public static Producte producteActual;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitxa);
        context = this;

        Intent intent = getIntent();
        final Producte producteSelected = (Producte) intent.getSerializableExtra(PRODUCTE);

        TextView priceSelected = findViewById(R.id.priceSelected);
        Spinner cuantitySpinner = findViewById(R.id.cuantitySpinner);
        Button btnAñadirCarrito = findViewById(R.id.btnAñadirCarrito);
        Button btnComprar = findViewById(R.id.btnComprar);

        cargarProducto(producteSelected);

        cuantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int totalNum = Integer.parseInt(cuantitySpinner.getSelectedItem().toString());
                if (producteSelected.isInDiscount()) {
                    double nuevoTotal = producteSelected.getDiscountedPrice() * totalNum;
                    producteActual.setTotPrice(nuevoTotal);
                } else {
                    double nuevoTotal = producteSelected.getPrice() * totalNum;
                    producteSelected.setTotPrice(nuevoTotal);
                }
                producteActual.setCantidadProductos(totalNum);

                priceSelected.setText(producteActual.getTotPrice()+"€");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), ""+cuantitySpinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private ArrayAdapter getCuantityAdapter( Producte producte) {
        String [] totalNumSpinner = new String[producte.getInStock()];
        for(int i = 0; i < producte.getInStock(); i++)
        {
            totalNumSpinner[i] = i + 1 + "";
        }
        return new ArrayAdapter(this, android.R.layout.simple_list_item_1, totalNumSpinner);
    }
    private final void cargarProducto(Producte producteSelected){
        ImageView imgSelected = findViewById(R.id.imgSelected);
        TextView nameSelected = findViewById(R.id.nameSelected);
        TextView lblStock = findViewById(R.id.lblStock);
        TextView lblStored = findViewById(R.id.lblStored);
        TextView inDiscountSelected = findViewById(R.id.inDiscountSelected);
        TextView priceSelected = findViewById(R.id.priceSelected);
        Spinner cuantitySpinner = findViewById(R.id.cuantitySpinner);

        imgSelected.setImageResource(producteSelected.getImage());
        nameSelected.setText(producteSelected.getName());
        lblStock.setText(""+producteSelected.getInStock());
        lblStored.setText(""+producteSelected.getInStorage());

        if (producteSelected.isInDiscount()) {
            inDiscountSelected.setText("En oferta: SI");
            priceSelected.setText(""+producteSelected.getDiscountedPrice() + " €");
            producteActual = new Producte((int) 1, producteSelected.getDiscountedPrice());
        } else {
            inDiscountSelected.setText("En oferta: NO");
            priceSelected.setText(producteSelected.getPrice()+"€");
            Toast.makeText(context, producteSelected.getPrice()+"€", Toast.LENGTH_SHORT).show();
            producteActual = new Producte(1, producteSelected.getDiscountedPrice());
        }
        cuantitySpinner.setAdapter(getCuantityAdapter(producteSelected));
    }
}