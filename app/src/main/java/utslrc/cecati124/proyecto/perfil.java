package utslrc.cecati124.proyecto;

import android.content.Intent;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.ArrayList;

public class perfil extends AppCompatActivity {
    TextView nombre;
    ToggleButton toggleButton;
    ListView listado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        nombre = (TextView) findViewById(R.id.tvNombre);
        listado = (ListView) findViewById(R.id.lvDatos);
        //instanciar variables
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle!=null){
            String r_nombre = (String) bundle.get("nombre");
            nombre.setText(r_nombre);
        }
        ObtDatos();
    }

    public void ObtDatos(){
        AsyncHttpClient client = new AsyncHttpClient();
        String url="http://201.171.137.34:8080/modelo/claseInstructorJ.php";
        RequestParams parametros = new RequestParams();
        parametros.put("codigo",3);

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
                texto=jsonArray.getJSONObject(i).getString("nombres")+" "
                        + jsonArray.getJSONObject(i).getString("apellidos")+" | "
                        + jsonArray.getJSONObject(i).getString("tipo");
                listado.add(texto);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listado;
    }

    public void especialidad(View view){
        Intent intent = new Intent(this,listaEspecialidad.class);
        startActivity(intent);
    }

    public void instructor(View view){
        Intent intent = new Intent(this,listaInstructores.class);
        startActivity(intent);
    }

}
