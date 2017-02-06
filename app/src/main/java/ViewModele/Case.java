package ViewModele;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nesta on 06/02/2017.
 */

public class Case  implements Parcelable {


        private int valeur;

        public Case(int valeur) {
            this.valeur = valeur;
        }

        public int getValeur() {
            return valeur;
        }

        public void setValeur(int valeur) {
            this.valeur = valeur;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(valeur);
        }

        public static final Parcelable.Creator<Case> CREATOR = new Parcelable.Creator<Case>() {

            @Override
            public Case createFromParcel(Parcel source) {
                return new Case(source);
            }


            @Override
            public Case[] newArray(int size) {
                return new Case[size];
            }

        };

        public Case(Parcel in) {
            valeur = in.readInt();
        }
    }
