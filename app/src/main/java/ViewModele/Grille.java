package ViewModele;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nesta on 06/02/2017.
 */

public class Grille implements Parcelable {

    private List<List> grille;
    private List<Case> ligneGrille;

    public Grille(String grille1){
        grille = new ArrayList<>();
        char[] grille2 = grille1.toCharArray();

        for(int i=0;i<9;i++){
            ligneGrille = new ArrayList<>();
            for(int j=0;j<9;j++){
                ligneGrille.add(new Case(Integer.valueOf(String.valueOf(grille2[(i*9)+j]))));
            }
            grille.add(ligneGrille);
        }

    }

    public List<List> getGrille() {
        return grille;
    }

    public void modiferCase(int x, int y, int valeur){
        List<Case> ligne = grille.get(x);
        Case caseS = ligne.get(y);
        caseS.setValeur(valeur);
        ligne.set(y, caseS);
    }

    public boolean ajoutPossible(int x,int y, int valeur){
        boolean retour = true;

        //Test ligne
        List<Case> ligne = grille.get(x);
        for(Case caseSudoku : ligne){
            if(caseSudoku.getValeur() == valeur){
                retour = false;
                break;
            }
        }
        if(retour) {
            List<Case> colonne = new ArrayList<>();

            for (List<Case> listeLigne : grille) {
                colonne.add(listeLigne.get(y));
            }

            for(Case maCase : colonne){
                if(maCase.getValeur() == valeur){
                    retour = false;
                    break;
                }
            }
        }

        if(retour) {
            int xCarre = x - x % 3;
            int yCarre = y - y % 3;
            List<Case> carre = new ArrayList<>();
            for (int i = xCarre; i < xCarre + 3; i++) {
                List<Case> ligneGrille = grille.get(i);
                for (int j = yCarre; j < yCarre + 3; j++) {
                    carre.add(ligneGrille.get(j));
                }
            }

            for (Case caseSudoku : carre) {
                if(caseSudoku.getValeur() == valeur){
                    retour = false;
                    break;
                }
            }
        }
        return retour;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(grille);

    }

    public static final Parcelable.Creator<Grille> CREATOR = new Parcelable.Creator<Grille>() {

        @Override
        public Grille createFromParcel(Parcel source) {
            return new Grille(source);
        }


        @Override
        public Grille[] newArray(int size) {
            return new Grille[size];
        }

    };

    public Grille(Parcel in) {
        grille = new ArrayList<>();
        in.readList(grille,getClass().getClassLoader());
    }
}
