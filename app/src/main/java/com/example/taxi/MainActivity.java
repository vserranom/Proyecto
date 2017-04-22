package com.example.taxi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String TAG ="LTAXI ";
    String[] frases = { "It's. my. 8. o'clock. my. I. need. my. Taxi?. \n",
            "Oh no, I’m in a hurry! I’m going to be late! Taxi! Taxi! \n",
            "It's 8 o'clock. Taxi! Taxi!\n"};
    /*String frase4 = "Do you need a taxi? \n";
    String frase5 = "Yes, I need a taxi.. \n";
    String frase6 = "Taxiiii! \n";
    String frase7 = "Uff. Thank you very much! \n";
    String frase8 = "You’re welcome \n";
    */
    List<FraseTaxi> frasesTaxi =  new ArrayList<>();


    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        for(String frase: frases) {
            frasesTaxi.add(new FraseTaxi(this, frase));
        }

        for(FraseTaxi fraseTaxi: frasesTaxi ){
            //System.out.println(TAG + "JAJAJAJAJAJ");
            ((LinearLayout) findViewById(R.id.frases)).addView(fraseTaxi.getView());
            //System.out.println(TAG + "UAUAUAUA");
            Button b = (Button) findViewById(R.id.button);
            b.setOnClickListener(this);
        }

    }

    public void onClick(View v) {
        for(FraseTaxi fraseTaxi : frasesTaxi) {
            System.out.println(TAG + "getPalabra=" + fraseTaxi.getPalabra());
            boolean correcta = fraseTaxi.corregir(fraseTaxi.getPalabra().trim());//.trim() elimina espacios y saltos de linea
            System.out.println(TAG + "correcta= " + correcta);
            if (correcta) {
                System.out.println(TAG + "ES CORRECTA!");
                System.out.println("Puntuación: " + fraseTaxi.score);
            } else {
                System.out.println(TAG + "INCORRECTA!");
            }
        }
    }

    public void onChange(){
        //progressbar.setProgress(25);
    }
}
