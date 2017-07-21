package utslrc.cecati124.proyecto;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;


public class agregarEspecialidadFragment extends Fragment implements View.OnClickListener{
    private EditText editTextName;

    private Button buttonAdd;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_agregar_especialidad, container, false);

        editTextName = (EditText) view.findViewById(R.id.etNombre);

        buttonAdd = (Button) view.findViewById(R.id.buttonAdd);

        //Setting listeners to button
        buttonAdd.setOnClickListener((View.OnClickListener)this);

        return view;
    }

    private void addEmployee(){

        final String nombre = editTextName.getText().toString().trim();

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(),"Agregando..","Espere...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
               // startActivity(new Intent(getActivity(),agregarEspecialidad.class));
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put("nombre",nombre);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(config.URL_ADD, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }


    public void onClick(View v) {
        if(v == buttonAdd){
            addEmployee();
        }

    }
}
