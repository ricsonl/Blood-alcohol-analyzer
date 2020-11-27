package android.teste.bloodalcohol1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void send(View v){
        Intent it = new Intent(getBaseContext(), CalcActivity.class);

        EditText pesoEdit = findViewById(R.id.pesoIn);
        EditText sexoEdit = findViewById(R.id.sexoIn);
        EditText coposEdit = findViewById(R.id.coposIn);
        EditText jejumEdit = findViewById(R.id.jejumIn);

        if(!pesoEdit.getText().toString().equals("")
                && !sexoEdit.getText().toString().equals("")
                && !coposEdit.getText().toString().equals("")
                && !jejumEdit.getText().toString().equals("")){

            Bundle params = new Bundle();

            params.putFloat("peso", Float.parseFloat(pesoEdit.getText().toString()));
            params.putString("sexo", sexoEdit.getText().toString());
            params.putFloat("copos", Float.parseFloat(coposEdit.getText().toString()));
            params.putString("jejum", jejumEdit.getText().toString());

            it.putExtras(params);
            startActivityForResult(it, 1);

            return;
        }
        Toast toast = Toast.makeText(v.getContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent it) {
        super.onActivityResult(requestCode, resultCode, it);

        if(resultCode == 1) {
            double alc = it.getDoubleExtra("alc", Double.parseDouble("-1"));
            String classif = it.getStringExtra("classif");

            Toast.makeText(this, "Taxa de Alcoolemia: " + Double.toString(alc) + "\nClassificação: " + classif, Toast.LENGTH_LONG).show();
        }
    }
}