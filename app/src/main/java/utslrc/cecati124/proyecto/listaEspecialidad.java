package utslrc.cecati124.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.ArrayList;

public class listaEspecialidad extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listado;
    int r_codigo;
    String codigos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_especialidad);
        listado = (ListView) findViewById(R.id.lvDatos);
        listado.setOnItemClickListener(this);

        //instanciar variables
        //Intent intent = getIntent();
        //Bundle bundle = intent.getExtras();

        //if(bundle!=null){
        //     r_codigo = (int) bundle.get("codigo");
        //}
        //termina el codigo para instanciar lvariables

        ObtDatos();
    }

    public void ObtDatos(){

        AsyncHttpClient client = new AsyncHttpClient();
        String url="http://201.171.236.126:8080/modeloAndroid/consultarEspecialidad.php";
        // String url="http://201.171.137.34:8080/modeloAndroid/consultarInstructor.php";
        RequestParams parametros = new RequestParams();
        parametros.put("codigo",r_codigo);

        client.post(url, parametros, new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                if(statusCode==200){
                    CargarLista(obtDatosJSON(new String(responseBody)));
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void CargarLista(ArrayList<String> datos){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, datos);
        listado.setAdapter(adapter);
    }

    public ArrayList<String> obtDatosJSON(String response){
        ArrayList<String> listado = new ArrayList<String>();
        try{
            JSONArray jsonArray = new JSONArray(response);
            String texto;
            for(int i=0;i<jsonArray.length();i++){
                codigos = jsonArray.getJSONObject(i).getString("cod_especialidad");
                texto=jsonArray.getJSONObject(i).getString("cod_especialidad")+" - "
                        +jsonArray.getJSONObject(i).getString("especialidad");
                listado.add(texto);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return listado;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String selectedFromList = (listado.getItemAtPosition(position).toString());
        Toast.makeText(listaEspecialidad.this, selectedFromList, Toast.LENGTH_SHORT).show();
    }

    public void agregar(View view){
        Intent intent = new Intent(this,agregarInstructores.class);
        startActivity(intent);
    }

    public void perfil(View view){
        Intent intent = new Intent(this,perfil.class);
        startActivity(intent);
    }
    public void instructor(View view){
        Intent intent = new Intent(this,listaInstructores.class);
        startActivity(intent);
    }
}
