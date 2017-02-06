package com.example.nesta.sudoku;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Nesta on 03/02/2017.
 */



    class MyViewHolder{
        public TextView Numero;
        public TextView Pourcentage;
    }
    public class Adaptateur extends BaseAdapter {

        ArrayList<VGrille> myList = new ArrayList<VGrille>();
        Context context;

        public Adaptateur(Context context, ArrayList<VGrille> myList) {
            this.myList = myList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return myList.size();
        }

        @Override
        public VGrille getItem(int position) {
            return myList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return myList.indexOf(getItem(position));
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyViewHolder mViewHolder = null;



            if (convertView == null) {
                LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

                convertView = mInflater.inflate(R.layout.adaptateur_liste, parent, false);


                mViewHolder = new MyViewHolder();
                mViewHolder.Numero  = (TextView) convertView.findViewById(R.id.Numero);
                mViewHolder.Pourcentage = (TextView) convertView.findViewById(R.id.Pourcentage);

                convertView.setTag(mViewHolder);
            } else {

                mViewHolder = (MyViewHolder) convertView.getTag();
            }


            VGrille vGrille = (VGrille) getItem(position);


            mViewHolder.Numero.setText(String.valueOf(vGrille.getNum()) +"      niveau: "+vGrille.getLevel());
            mViewHolder.Pourcentage.setText(String.valueOf(vGrille.getDone()+"%"));
            mViewHolder.Numero.setTextSize(22);
            mViewHolder.Pourcentage.setTextSize(22);
            if (vGrille.getDone() < 40)

                mViewHolder.Pourcentage.setTextColor(Color.RED);

            else

                mViewHolder.Pourcentage.setTextColor(Color.GREEN);

                  return convertView;
        }

    /*@Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Toast toast = Toast.makeText(context, "Item " + (position + 1) + ": "
                + this.myList.get(position), Toast.LENGTH_SHORT);
        toast.show();

    }*/
    }


