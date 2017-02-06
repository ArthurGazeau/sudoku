package com.example.nesta.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        Button btFacile = (Button) findViewById(R.id.button);
        Button btMoyen = (Button) findViewById(R.id.button2);
        Button btDifficile = (Button) findViewById(R.id.button3);

        btFacile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AffichageListe.class);
                intent.putExtra("niveau", 1);
                context.startActivity(intent);
            }
        });

        btMoyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AffichageListe.class);
                intent.putExtra("niveau", 2);
                context.startActivity(intent);
            }
        });
        btDifficile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AffichageListe.class);
                intent.putExtra("niveau", 3);
                context.startActivity(intent);
            }
        });
    }
}

