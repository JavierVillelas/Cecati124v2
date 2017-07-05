package utslrc.cecati124.proyecto;

/**
 * Created by jv8810230 on 7/4/2017.
 */

public class config {
    public static final String URL_ADD="http://201.143.195.251:8080/modeloAndroid/agregarEspecialidad.php";
    public static final String URL_GET_ALL = "http://201.143.195.251:8080/modeloAndroid/consultarEspecialidad.php";
  //  public static final String URL_GET_EMP = "http://192.168.94.1/Android/CRUD/getEmp.php?id=";
  //  public static final String URL_UPDATE_EMP = "http://192.168.94.1/Android/CRUD/updateEmp.php";
  //  public static final String URL_DELETE_EMP = "http://192.168.94.1/Android/CRUD/deleteEmp.php?id=";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NAME = "name";
    public static final String KEY_EMP_DESG = "desg";
    public static final String KEY_EMP_SAL = "salary";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_DESG = "desg";
    public static final String TAG_SAL = "salary";

    //employee id to pass with intent
    public static final String EMP_ID = "emp_id";
}
