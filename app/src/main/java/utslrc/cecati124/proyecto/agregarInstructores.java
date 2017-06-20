package utslrc.cecati124.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class agregarInstructores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_instructores);
    }

    public void perfil(View view){
        Intent intent = new Intent(this,perfil.class);
        startActivity(intent);
    }
}
