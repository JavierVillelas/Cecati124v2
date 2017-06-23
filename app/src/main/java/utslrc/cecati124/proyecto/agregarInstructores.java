package utslrc.cecati124.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class agregarInstructores extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spTipo;
    Button boton;
    EditText nombres, apellidos, telefono, direccion, correo, contrasena1, contrasena2;

    ArrayAdapter<String> aaTipo;
    String [] opcTipo = new String []{"Externo", "Plantilla", "Capacitacion"};
    private RequestQueue requestQueue;
    private static final String URL ="http://201.171.236.126:8080/modeloAndroid/guardarInstructor.php";
    private StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_instructores);
        spTipo=(Spinner) findViewById(R.id.spTipo);
        nombres=(EditText) findViewById(R.id.etNombres);
        apellidos=(EditText) findViewById(R.id.etApellidos);
        telefono=(EditText) findViewById(R.id.etTelefono);
        direccion=(EditText) findViewById(R.id.etDireccion);
        correo=(EditText) findViewById(R.id.etCorreo);
        contrasena1=(EditText) findViewById(R.id.etContrasena1);
        contrasena2=(EditText) findViewById(R.id.etContrasena2);
        boton=(Button) findViewById(R.id.btnAgregar);

        spTipo.setOnItemSelectedListener(this);
        aaTipo=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opcTipo);
        spTipo.setAdapter(aaTipo);

        requestQueue = Volley.newRequestQueue(this);


        boton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    public void onResponse(String response) {
                        try {
                            JSONObject objeto = new JSONObject(response);
                            if(objeto.names().get(0).equals("si")){
                                Toast.makeText(getApplicationContext(), objeto.getString("si"), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),perfil.class));
                            }else if(objeto.names().get(0).equals("no")){
                                Toast.makeText(getApplicationContext(), objeto.getString("no"), Toast.LENGTH_SHORT).show();
                            }else if(objeto.names().get(0).equals("falta")){
                                Toast.makeText(getApplicationContext(), objeto.getString("falta"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {

                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                }) {
                    protected  Map<String,String> getParams() throws AuthFailureError{
                        HashMap<String, String> hashMap = new HashMap <String, String>();
                        hashMap.put("nombres", nombres.getText().toString());
                        hashMap.put("apellidos", apellidos.getText().toString());
                        hashMap.put("telefono", telefono.getText().toString());
                        hashMap.put("direccion", direccion.getText().toString());
                        hashMap.put("correo", correo.getText().toString());
                        hashMap.put("spTipo", spTipo.getOnItemSelectedListener().toString());
                        hashMap.put("contrasena1", contrasena1.getText().toString());
                        hashMap.put("contrasena2", contrasena2.getText().toString());

                        return hashMap;
                    }
                };
                requestQueue.add(request);
            }
        });
    }

    public void perfil(View view){
        Intent intent = new Intent(this,perfil.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
