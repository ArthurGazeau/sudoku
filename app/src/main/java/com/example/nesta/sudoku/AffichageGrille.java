package com.example.nesta.sudoku;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ViewModele.Grille;

/**
 * Created by Nesta on 03/02/2017.
 */


public class AffichageGrille extends AppCompatActivity {
    private GrilleJeu  gDessin;
    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.grille_jeu);
        Bundle bundle1 = getIntent().getExtras();
        Grille g = (Grille) bundle1.get("grille");

        final GrilleJeu gDessin = (GrilleJeu) findViewById(R.id.dessin);
        this.gDessin = gDessin;
        gDessin.setGrille(g);

    }

}
