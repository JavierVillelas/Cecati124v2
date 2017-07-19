package utslrc.cecati124.proyecto;

import android.content.Intent;
import android.preference.PreferenceActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.TintTypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        nombre = (TextView) findViewById(R.id.tvNombre);

        //instanciar variables
        //Intent intent = getIntent();
        //Bundle bundle = intent.getExtras();

      //  if(bundle!=null){
       //     String r_nombre = (String) bundle.get("nombre");
       //     nombre.setText(r_nombre);
        //}
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
