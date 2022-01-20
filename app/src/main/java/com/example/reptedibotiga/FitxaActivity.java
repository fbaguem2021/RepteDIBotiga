package com.example.reptedibotiga;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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

    public class Holder {
        final ImageView imgSelected;
        final TextView nameSelected;
        final TextView lblStock;
        final TextView lblStored;
        final TextView inDiscountSelected;
        final TextView priceSelected;
        final Spinner cuantitySpinner;
        final View view;

        public Holder(View view){
            this.view = view;

            this.imgSelected        = view.findViewById(R.id.imgSelected);
            this.nameSelected       = view.findViewById(R.id.nameSelected);
            this.lblStock           = view.findViewById(R.id.lblStock);
            this.lblStored          = view.findViewById(R.id.lblStored);
            this.inDiscountSelected = view.findViewById(R.id.inDiscountSelected);
            this.priceSelected      = view.findViewById(R.id.priceSelected);
            this.cuantitySpinner    = view.findViewById(R.id.cuantitySpinner);
        }
    }

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

        cargarProducto(producteSelected, );

        cuantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int totalNum = Integer.parseInt(cuantitySpinner.getSelectedItem().toString());
                if (producteSelected.isInDiscount()) {
                    double nuevoTotal = producteSelected.getDiscountedPrice() * totalNum;
                    producteActual.setTotPrice(nuevoTotal);
                } else {
                    double nuevoTotal = producteSelected.getPrice() * totalNum;
                    producteActual.setTotPrice(nuevoTotal);
                }
                producteActual.setCantidadProductos(totalNum);

                priceSelected.setText(producteActual.getTotPrice()+" €");
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
    private void cargarProducto(Producte producteSelected, View view){
        Holder holder = new Holder(view);

        holder.imgSelected.setImageResource(producteSelected.getImage());
        holder.nameSelected.setText(producteSelected.getName());
        holder.lblStock.setText( "En  stock:  "+producteSelected.getInStock());
        holder.lblStored.setText("Almacenado: "+producteSelected.getInStorage());

        if (producteSelected.isInDiscount()) {
            holder.inDiscountSelected.setText("En oferta: SI");
            holder.priceSelected.setText(""+producteSelected.getDiscountedPrice() + " €");
            producteActual = new Producte((int) 1, producteSelected.getDiscountedPrice());
        } else {
            holder.inDiscountSelected.setText("En oferta: NO");
            holder.priceSelected.setText(producteSelected.getPrice()+" €");
            producteActual = new Producte(1, producteSelected.getDiscountedPrice());
        }
        holder.cuantitySpinner.setAdapter(getCuantityAdapter(producteSelected));
    }
}