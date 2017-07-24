package utslrc.cecati124.proyecto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class verCursoTodoFragment extends Fragment implements ListView.OnItemClickListener{
    private ListView listView;
    private String JSON_STRING;
    FloatingActionButton fab;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ver_curso_todo, container, false);
        listView = (ListView) view.findViewById(R.id.listView);
        listView.setOnItemClickListener(this);

        fab = (FloatingActionButton) view.findViewById(R.id.fabBotonAgregar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent e = new Intent(getActivity(), agregarCurso.class);
                startActivity(e);
            }
        });
        getJSON();
        // Inflate the layout for this fragment

        return view;
    }


    private void showEmployee(){
        JSONObject jsonObject = null;

        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString("id");
                String name = jo.getString("nombre");
                String instructor = jo.getString("instNombres")+ " " +jo.getString("instApellidos");
                String fechaini = jo.getString("fechaini");
                String fechafin = jo.getString("fechafin");
                String horario = jo.getString("horario");

                HashMap<String,String> curso = new HashMap<>();
                curso.put("id",id);
                curso.put("nombre",name);
                curso.put("instructor", instructor);
                curso.put("fechaini", fechaini);
                curso.put("fechafin", fechafin);
                curso.put("horario", horario);

                list.add(curso);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        ListAdapter adapter = new SimpleAdapter(
                getActivity(), list, R.layout.fragment_list_view_curso,
                new String[]{"id","nombre", "instructor", "fechaini", "fechafin", "horario"},
                new int[]{R.id.txtId, R.id.txtNombre, R.id.txtInstructor, R.id.txtFechaIni, R.id.txtFechaFin, R.id.txtHorario1});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(),"Cargando Datos","Espere...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(config.URL_todos_cursos);
                return s;
            }


        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Intent intent = new Intent(getActivity(), verEspecialidad.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        //String cod = map.get("id").toString();
        //String nombre = map.get("nombre").toString();
        //intent.putExtra("cod_especialidad",cod);
        //intent.putExtra("nombre",nombre);
        //startActivity(intent);

        verEspecialidadFragment agregar = new verEspecialidadFragment();
        Bundle caja = new Bundle();
        String cod = ("id").toString();
        String nombre = ("nombre").toString();
        caja.putString("cod_especialidad",cod);
        caja.putString("nombre",nombre);
        agregar.setArguments(caja);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentos, agregar);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
