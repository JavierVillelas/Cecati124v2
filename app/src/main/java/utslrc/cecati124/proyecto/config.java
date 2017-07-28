package utslrc.cecati124.proyecto;

/**
 * Created by jv8810230 on 7/4/2017.
 */

public class config {
    //Address of our scripts of the CRUD
    public static String IP ="http://201.171.140.107:8080/";
    //public static String IP ="http://10.10.10.73:8080/";


    public static final String URL_ADD= IP + "modeloAndroid/agregarEspecialidad.php";

    public static final String URL_GET_ALL = IP + "modeloAndroid/consultarEspecialidadTodo.php";
    public static final String URL_todos_cursos = IP + "modeloAndroid/consultarCursoTodo.php";

    public static final String URL_GET_EMP =  IP +"modeloAndroid/consultarEspecialidad.php?id=";

    public static final String URL_UPDATE_EMP = IP + "modeloAndroid/modificarEspecialidad.php";

    public static final String URL_DELETE_EMP = IP + "modeloAndroid/eliminarEspecialidad.php?id=";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NAME = "nombre";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";


    //employee id to pass with intent
    public static final String EMP_ID = "emp_id";
}
