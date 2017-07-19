package utslrc.cecati124.proyecto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        verEspecialidadTodoFragment ver = new verEspecialidadTodoFragment();
        setContentView(R.layout.activity_dashboard);

        
    }

}
