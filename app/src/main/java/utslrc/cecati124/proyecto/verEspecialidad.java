package utslrc.cecati124.proyecto;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class verEspecialidad extends AppCompatActivity implements View.OnClickListener{
    private TextView etId;
    private EditText etNombre;

    private Button btnModificar;
    private Button btnEliminar;

    private String id;
    private String nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_especialidad);

        Intent intent = getIntent();

        id = intent.getStringExtra("cod_especialidad");
        nombre = intent.getStringExtra("nombre");

        etId = (TextView) findViewById(R.id.etId);
        etNombre = (EditText) findViewById(R.id.etNombre1);

        btnModificar = (Button) findViewById(R.id.btnModificar);
        btnEliminar = (Button) findViewById(R.id.btnElimimar);

        btnModificar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);

        etId.setText("Codigo: "+id);
        etNombre.setText(nombre);


        getEspecialidad();
    }

    private void getEspecialidad(){
        class GetEspecialidad extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(verEspecialidad.this,"Cargando...","Espere...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                cargarEspecialidad(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(config.URL_GET_EMP,id);
                return s;
            }
        }
        GetEspecialidad ge = new GetEspecialidad();
        ge.execute();
    }

    private void cargarEspecialidad(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String nombre = c.getString("nombre");

            etNombre.setText(nombre);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void modificarEspecialidad(){
        final String nombre = etNombre.getText().toString().trim();

        class modificarEspecialidad extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(verEspecialidad.this,"Modificando...","Espere...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(verEspecialidad.this,s, Toast.LENGTH_LONG).show();
                startActivity(new Intent(verEspecialidad.this,verEspecialidadTodo.class));
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("id",id);
                hashMap.put("nombre",nombre);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(config.URL_UPDATE_EMP,hashMap);

                return s;
            }
        }

        modificarEspecialidad ue = new modificarEspecialidad();
        ue.execute();
    }

    private void eliminarEspecialidad(){
        class eliminarEspecialidad extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(verEspecialidad.this, "Actualizando...", "Espere...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(verEspecialidad.this, s, Toast.LENGTH_LONG).show();
                startActivity(new Intent(verEspecialidad.this,verEspecialidadTodo.class));
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(config.URL_DELETE_EMP, id);
                return s;
            }
        }

        eliminarEspecialidad de = new eliminarEspecialidad();
        de.execute();
    }

    private void confirElimar(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Esta Seguro de eliminar Especialidad?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        eliminarEspecialidad();
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == btnModificar){
            modificarEspecialidad();
        }

        if(v == btnEliminar){
            confirElimar();
        }
    }
}
