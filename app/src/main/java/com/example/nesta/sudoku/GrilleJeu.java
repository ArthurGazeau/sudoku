package com.example.nesta.sudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

import ViewModele.Case;
import ViewModele.Grille;

/**
 * Created by Nesta on 03/02/2017.
 */

public class GrilleJeu extends View implements View.OnTouchListener {


    private int num = 0;
    private int width;
    private Context context;
    private Grille grille;

    private boolean firstTouch;
    private long tempsClick;


    public GrilleJeu(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.setOnTouchListener(this);
    }

    @Override
    public void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        width = getWidth();
        List<List> listGrille = grille.getGrille();

        for (int i = 1; i < 10; i++) {
            if (i % 3 == 0) {
                paint.setStrokeWidth(6);
            } else {
                paint.setStrokeWidth(2);
            }

            canvas.drawLine((width / 9) * i, 0, (width / 9) * i, width, paint);
            canvas.drawLine(0, (width / 9) * i, width, (width / 9) * i, paint);

        }
        Paint paintRect = new Paint();
        paintRect.setStyle(Paint.Style.STROKE);
        Paint paintText = new Paint();
        paintText.setColor(Color.BLACK);
        paintText.setTextSize(50);
        paint.setStrokeWidth(2);

        for (int j = 1; j < 10; j++) {
            canvas.drawRect(width / 10 * j - 50, width + 50, width / 10 * j + 50, width + 150, paintRect);
            canvas.drawText(String.valueOf(j), width / 10 * j - 15, width + 120, paintText);

        }

        for (int i = 0; i < 9; i++) {
            List<Case> listCase = listGrille.get(i);
            for (int j = 0; j < 9; j++) {
                Case caseActuelle = listCase.get(j);
                if (caseActuelle.getValeur() != 0) {
                    canvas.drawText(String.valueOf(caseActuelle.getValeur()), width / 9 * j + ((width / 9) / 2 - 10), width / 9 * i + ((width / 9) / 2 + 10), paintText);
                }
            }
        }
    }


    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x =(int)event.getX();
        int y =(int)event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                for(int i=1;i<=9;i++){
                    if(x >= width/10*i-25 && x <= width/10*i+25 && y >= width+50 && y <= width+100){
                        num = i;
                    }
                }
                Log.d("num", String.valueOf(num));
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                int xCase = -1;
                int yCase = -1;

                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (x >= width / 9 * i && x < width / 9 * (i + 1) && y >= width / 9 * j && y < width / 9 * (j + 1)) {
                            xCase = j;
                            yCase = i;
                        }
                    }
                }
                if(num != 0 && xCase != -1 && yCase != -1) {
                    if(grille.ajoutPossible(xCase, yCase, num)) {
                        grille.modiferCase(xCase, yCase, num);
                    }
                }
                num = 0;

                if(firstTouch && System.currentTimeMillis() - tempsClick < 300 && xCase != -1 && yCase != -1){
                    grille.modiferCase(xCase, yCase, 0);
                    firstTouch = false;
                    Log.d("DT", "doubleTap ");
                }else{
                    firstTouch = true;
                    tempsClick = System.currentTimeMillis();
                }
                break;
        }
        this.invalidate();
        return true;

    }

}

