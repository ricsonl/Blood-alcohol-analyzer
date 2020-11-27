package android.teste.bloodalcohol2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calcAndReturn(View v){

        Intent it = getIntent();
        if(it.getAction() == "CALCULAR_ALCOOLEMIA"){
            Bundle params = it.getExtras();

            Float peso = params.getFloat("peso");
            String sexo = params.getString("sexo");
            Float copos = params.getFloat("copos");
            String jejum = params.getString("jejum");

            float coef = (jejum.equals("n")) ? 1.1f : ((sexo.equals("m")) ? 0.7f : 0.6f);

            System.out.println(coef);

            double alc =  (4.8 * copos)/(peso * coef);
            String classif = (alc > 0.2) ? "Pessoa Alcoolizada" : "Pessoa NÃO Alcoolizada";

            System.out.println(alc);
            System.out.println(classif);

            it.putExtra("alc", alc);
            it.putExtra("classif", classif);

            setResult(1, it);
            finish();
        } else {
            Toast.makeText(this, "Erro", Toast.LENGTH_LONG).show();
        }
    }
}