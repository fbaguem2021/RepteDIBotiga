package com.example.reptedibotiga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class FitxaActivity extends AppCompatActivity {

    public static final String PRODUCTE = "producte";
    public static final String POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitxa);

        Intent intent = getIntent();
        final Producte producteSelected = (Producte) intent.getSerializableExtra(PRODUCTE);

        ImageView imgSelected = findViewById(R.id.imgSelected);
        TextView nameSelected = findViewById(R.id.nameSelected);

        TextView priceSelected = findViewById(R.id.priceSelected);
        TextView discountSelected = findViewById(R.id.discountSelected);
        Spinner cuantitySpinner = findViewById(R.id.cuantitySpinner);

        imgSelected.setImageResource(producteSelected.getImage());
        nameSelected.setText(producteSelected.getName());
        priceSelected.setText(producteSelected.getPrice()+" €");
        if (producteSelected.isInDiscount())
            discountSelected.setText(producteSelected.getDiscountedPrice()+" €");

    }
    private void startSpinner(Spinner spinner, Producte producte){
        String [] totalNumSpinner = new String[producte.getInStock()];
        for(int i = 0; i < producte.getInStock(); i++)
        {
            totalNumSpinner[i] = i + 1 + "";
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, totalNumSpinner);
        spinner.setAdapter(adapter);
    }
}