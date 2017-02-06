package com.example.nesta.sudoku;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

import ViewModele.Grille;

/**
 * Created by Nesta on 03/02/2017.
 */





public class AffichageListe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affichage_liste);

        final ListView listView = (ListView) findViewById(R.id.listView);
        Bundle objetbundle = this.getIntent().getExtras();
        int level = objetbundle.getInt("niveau");
        final List<VGrille> listeGrille = new ArrayList<>();
        for(int i=0;i<20;i++){
            listeGrille.add(new VGrille(level, i, (int) (Math.random()*100)));
        }
        Adaptateur adapter = new Adaptateur(this, (ArrayList<VGrille>) listeGrille);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VGrille vGrille = listeGrille.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(AffichageListe.this);
                builder.setTitle("Info");
                builder.setMessage("Numéro : "+vGrille.getNum() + "                    "+ "Finis à : "+ vGrille.getDone()+"%");
                builder.setCancelable(false);
                builder.setPositiveButton("Continuer", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(AffichageListe.this,AffichageGrille.class);
                        Grille g = new Grille("008203500009670408346050702430010059967005001000496203280034067703500904004107020");
                        intent.putExtra("grille",g);

                        AffichageListe.this.startActivity(intent);
                        dialog.cancel();
                    }
                });
                builder.setNegativeButton("Retour", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();


            }

        });
    }
}
