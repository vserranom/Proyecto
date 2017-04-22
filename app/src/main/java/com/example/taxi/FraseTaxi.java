package com.example.taxi;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;

public class FraseTaxi {
    String TAG ="LTAXI ";
    String frase, fraseInicio, fraseFin, palabra;
    EditText editText;
    int maxProgress = 100;
    int progress = 0;

    int score = 0;

    LinearLayout layout;


    FraseTaxi(Context context, String frase){
        this.frase = frase;

        cortarFrase();

        createView(context);

    }

    void cortarFrase(){
        /*
        String[] sss = frase.split("my", 2);

        for(int i=0; i<sss.length; i++){
            System.out.println(TAG + "ssss[" + i + "]=" + sss[i]);
        }
        */

        String [] fraseSplit = frase.split(" "); //separamos las palabras
        int randomNum = (int)(Math.random() * fraseSplit.length); //Num random para ocultar palabra
        System.out.println(TAG +"randomNUM=" + randomNum);

        String [] fraseSplit1 = Arrays.copyOfRange(fraseSplit, 0, randomNum); //creamos array de palabras de la parte anterior a la palabra oculta
        String [] fraseSplit2 = Arrays.copyOfRange(fraseSplit, randomNum+1, fraseSplit.length); //creamos array de palabras de la parte posterior a la palabra oculta
        fraseInicio = TextUtils.join(" ", fraseSplit1); //unimos parte anterior de palabra oculta
        fraseFin = TextUtils.join(" ", fraseSplit2); //unimos parte posterior de palabra oculta
        palabra = fraseSplit[randomNum].replaceAll("[^\\dA-Za-z ]", "").trim(); //guardamos palabra oculta y quitamos puntos y ?!, trim() quita los espacios
        //palabra= palabra.replaceAll("[^\\dA-Za-z ]", "").trim();
        System.out.println(TAG+"palabra nueva:{"+palabra+"}");
        System.out.println(TAG + "parte anterior: {" + fraseInicio+"}");
        System.out.println(TAG + "parte posterior: {" + fraseFin+"}");
        System.out.println(TAG + "palabra oculta:{" + palabra+"}");
    }


    void createView(Context context){
        layout = new LinearLayout(context); //creamos LinearLayout

        TextView textViewFrase1 = new TextView(context);
        textViewFrase1.setText(fraseInicio); //creamos textView con la parte anterior a la palabra oculta

        TextView textViewFrase2 = new TextView(context);
        textViewFrase2.setText(fraseFin); //creamos textView con la parte posterior a la palabra oculta

        editText = new EditText(context); //Creamos edit text para que el user introduzca la palabra
        editText.setImeOptions(5);
        editText.setWidth(55*palabra.length());// TODO

        System.out.println(TAG+"Tamaño palabra:"+53*palabra.length());

        layout.addView(textViewFrase1); //añadimos al linearLayout la parte 1
        layout.addView(editText); //añadimos al linearLayout el editText (escribe usuario)
        layout.addView(textViewFrase2); //añadimos al linearLayout la parte 2

    }

    View getView(){
        return layout;
    }

    String getPalabra(){
       return editText.getText().toString();
    } //recuperamos lo que escriba el user

    boolean corregir(String palabra){//Comparamos lo que escribe el user con la palabra oculta
        System.out.println(TAG + "this.palabra={" + this.palabra +"}");
        System.out.println(TAG + "palabra={" + palabra + "}");
        if (palabra.equals(this.palabra)){
            score= score+1;
        }
        return palabra.equals(this.palabra); //si son iguales, return true
    }
}
