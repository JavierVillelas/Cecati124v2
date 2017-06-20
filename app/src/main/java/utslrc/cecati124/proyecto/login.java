package utslrc.cecati124.proyecto;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class login extends AppCompatActivity implements View.OnClickListener{
    Button boton;
    EditText nombre;
    ProgressBar barra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        boton=(Button) findViewById(R.id.btnIngresar);
        nombre=(EditText) findViewById(R.id.etCorreo);

        TextView recupera = (TextView) findViewById(R.id.link);
        recupera.setMovementMethod(LinkMovementMethod.getInstance());

        anadirVistas();
    }

    private void anadirVistas(){
        barra = (ProgressBar) findViewById(R.id.progressBar);
        barra.setProgress(0);
    }

    public void principal(View view){
        Intent intent = new Intent(this,listaInstructores.class);
        //String dato = nombre.getText().toString();
        //int codigo1 = new Integer(dato).intValue();
        //intent.putExtra("codigo",codigo1);
        startActivity(intent);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnIngresar:
             //   Toast.makeText(getApplicationContext(),"El usuario no puede ingresar al sistema",Toast.LENGTH_LONG).show();
                new AsyncTask_load().execute();
                break;
            default:
                break;
        }
    }

    public class AsyncTask_load extends AsyncTask<Void, Integer, Void>{
        int progreso;

        protected void onPreExecute(){
         //   Toast.makeText(login.this, "onPreExecute", Toast.LENGTH_SHORT).show();
            progreso = 0;
        }

        protected Void doInBackground(Void... params){
            while(progreso<100){
                progreso++;
                publishProgress(progreso);
                SystemClock.sleep(20);
            }
            return null;
        }

        protected void onProgressUpdate(Integer... values){
            barra.setProgress(values[0]);
        }
    }
}
