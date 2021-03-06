package utslrc.cecati124.proyecto;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity{
    Button boton;
    EditText nombre, contrasena;
    TextInputLayout impnombre, impcontrasena;

    private RequestQueue requestQueue;
    //public static String IP ="http://10.10.10.73:8080/";
    private static String IP ="http://201.171.140.107:8080/";
    private static final String URL = IP + "modeloAndroid/login.php";
    private StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        boton=(Button) findViewById(R.id.btnIngresar);
        nombre=(EditText) findViewById(R.id.etCorreo);
        contrasena=(EditText) findViewById(R.id.etPassword);
        impnombre = (TextInputLayout) findViewById(R.id.impCorreo);
        impcontrasena = (TextInputLayout) findViewById(R.id.impPassword);

        requestQueue = Volley.newRequestQueue(this);

        TextView recupera = (TextView) findViewById(R.id.link);
        recupera.setMovementMethod(LinkMovementMethod.getInstance());

       // anadirVistas();
        boton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {

                if(Patterns.EMAIL_ADDRESS.matcher(nombre.getText().toString()).matches()==false){
                    impnombre.setError("El correo es invalido");
                }else{
                    impnombre.setError(null);
                    request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        public void onResponse(String response) {
                            try {
                                JSONObject objeto = new JSONObject(response);
                                if(objeto.names().get(0).equals("si")){
                                    Toast.makeText(getApplicationContext(), objeto.getString("si"), Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),dashboard.class));
                                }else if(objeto.names().get(0).equals("no")){
                                    Toast.makeText(getApplicationContext(), objeto.getString("no"), Toast.LENGTH_SHORT).show();
                                }else if(objeto.names().get(0).equals("falta")){
                                    Toast.makeText(getApplicationContext(), objeto.getString("falta"), Toast.LENGTH_SHORT).show();
                                }else if(objeto.names().get(0).equals("desactivado")){
                                    Toast.makeText(getApplicationContext(), objeto.getString("desactivado"), Toast.LENGTH_SHORT).show();
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
                            hashMap.put("correo", nombre.getText().toString());
                            hashMap.put("contrasena", contrasena.getText().toString());

                            return hashMap;
                        }
                    };
                    requestQueue.add(request);
                }

            }
        });
    }

    public void principal(View view){
        Intent intent = new Intent(this,listaInstructores.class);
        //String dato = nombre.getText().toString();
        //int codigo1 = new Integer(dato).intValue();
        //intent.putExtra("codigo",codigo1);
        startActivity(intent);
    }

}
