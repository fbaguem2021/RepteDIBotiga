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

import java.text.ParseException;

public class FitxaActivity extends AppCompatActivity {

    public static final String PRODUCTE = "producte";
    public static final String POSITION = "position";
    public static Producte producteActual;

    public class Holder {
        final ImageView imgSelected;
        final TextView nameSelected;
        final TextView lblStock;
        final TextView lblStored;
        final TextView inDiscountSelected;
        final TextView priceSelected;
        final Spinner cuantitySpinner;
        final TextView lblPrecioTotal;
        final Button btnAniadirCarrito;
        final Button btnComprar;
        final View view;

        public Holder() {
            this.view = FitxaActivity.this.findViewById(R.id.layoutFitxa);

            this.imgSelected        = view.findViewById(R.id.imgSelected);
            this.nameSelected       = view.findViewById(R.id.nameSelected);
            this.lblStock           = view.findViewById(R.id.lblStock);
            this.lblStored          = view.findViewById(R.id.lblStored);
            this.inDiscountSelected = view.findViewById(R.id.inDiscountSelected);
            this.priceSelected      = view.findViewById(R.id.priceSelected);
            this.cuantitySpinner    = view.findViewById(R.id.cuantitySpinner);
            this.lblPrecioTotal     = view.findViewById(R.id.lblPrecioTotal);
            this.btnAniadirCarrito   = view.findViewById(R.id.btnAniadirCarrito);
            this.btnComprar         = view.findViewById(R.id.btnComprar);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitxa);

        Intent intent = getIntent();
        final Producte producteSelected = (Producte) intent.getSerializableExtra(PRODUCTE);

        Holder holder = cargarProducto(producteSelected);
        holder.cuantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int totalNum = Integer.parseInt(holder.cuantitySpinner.getSelectedItem().toString());
                producteActual.setCantidadProductos(totalNum);

                java.text.DecimalFormat formatter = new java.text.DecimalFormat("##.##");
                double totalPrice = producteActual.getTotPrice() * producteActual.getCantidadProductos();

                try {
                    // guarde el valor de totalPrice, despres de aplicar-li el format previament creat
                    totalPrice = formatter.parse(formatter.format(totalPrice)).doubleValue();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                producteActual.setTotPrice(totalPrice);
                holder.lblPrecioTotal.setText( formatter.format(totalPrice) + " €" );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        holder.btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), producteActual.toString(), Toast.LENGTH_LONG).show();
            }
        });

        holder.btnAniadirCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.listaCompra.equals("Carrito vacio"))
                    MainActivity.listaCompra = "Articulos en carrito:";

                MainActivity.listaCompra = MainActivity.listaCompra.
                        concat("\n    "+producteActual.getName()+"(x"+producteActual.getCantidadProductos()+") -> "+producteActual.getTotPrice()+"€");
                Toast.makeText(view.getContext(), "Articulo/s añadido al carrito. Volviendo a la lista de productos", Toast.LENGTH_SHORT).show();

                try {
                    Thread.sleep(3000);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
    private Holder cargarProducto(Producte producteSelected){
        Holder holder = new Holder();

        holder.imgSelected.setImageResource(producteSelected.getImage());
        holder.nameSelected.setText(producteSelected.getName());
        holder.lblStock.setText( "En  stock:  "+producteSelected.getInStock());
        holder.lblStored.setText("Almacenado: "+producteSelected.getInStorage());

        if (producteSelected.isInDiscount()) {
            holder.inDiscountSelected.setText("En oferta: SI");
            holder.priceSelected.setText(""+producteSelected.getDiscountedPrice() + " €");
            producteActual = new Producte(producteSelected.getName(), 1, producteSelected.getDiscountedPrice());
        } else {
            holder.inDiscountSelected.setText("En oferta: NO (-"+producteSelected.getDiscount()+"%)");
            holder.priceSelected.setText(producteSelected.getPrice()+" €");
            producteActual = new Producte(producteSelected.getName(), 1, producteSelected.getPrice());
        }
        holder.cuantitySpinner.setAdapter(getCuantityAdapter(producteSelected));

        holder.lblPrecioTotal.setText( (producteActual.getTotPrice() * producteActual.getCantidadProductos()) + " €" );

        return holder;
    }

}