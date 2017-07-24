package utslrc.cecati124.proyecto;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class verEspecialidadFragment extends Fragment implements View.OnClickListener{
    private TextView etId;
    private EditText etNombre;

    private Button btnModificar;
    private Button btnEliminar;

    private String id;
    private String nombre;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ver_especialidad, container, false);
        etId = (TextView) view.findViewById(R.id.etId);
        etNombre = (EditText) view.findViewById(R.id.etNombre1);

        btnModificar = (Button) view.findViewById(R.id.btnModificar);
        btnEliminar = (Button) view.findViewById(R.id.btnElimimar);

        btnModificar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
        id = getArguments().getString("cod_especialidad");
        nombre = getArguments().getString("nombre");

        etId.setText(id);
        etNombre.setText(nombre);


        getEspecialidad();
        return view;
    }

    private void getEspecialidad(){
        class GetEspecialidad extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(),"Cargando...","Espere...",false,false);
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
                loading = ProgressDialog.show(getActivity(),"Modificando...","Espere...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getActivity(),s, Toast.LENGTH_LONG).show();
               // startActivity(new Intent(getActivity(),verEspecialidadTodo.class));
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
                loading = ProgressDialog.show(getActivity(), "Actualizando...", "Espere...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
               // startActivity(new Intent(getActivity(),verEspecialidadTodo.class));
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
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
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
