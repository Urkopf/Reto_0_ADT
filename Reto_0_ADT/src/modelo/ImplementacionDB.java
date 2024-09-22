/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.IDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @author 2dam
 */
public class ImplementacionDB implements IDao {

    private ResourceBundle fichConf;
    private String URL, DBROOT, DBROOTPASS;
    private Connection conexion;
    private PreparedStatement declaracion;

    private final String CONSULTA_TODO = "SELECT * FROM UNIDADDIDACTICA";
    private final String INSERCION_EJEMPLO = "INSERT INTO unidaddidactica VALUES (?,?,?,?,?)";

    public ImplementacionDB() {
        fichConf = ResourceBundle.getBundle("modelo.dbConfig");
        URL = fichConf.getString("URL");
        DBROOT = fichConf.getString("DBROOT");
        DBROOTPASS = fichConf.getString("DBROOTPASS");
    }

    private void openConnection() throws SQLException {

        conexion = DriverManager.getConnection(URL, DBROOT, DBROOTPASS);

    }

    private void closeConnection() {
        try {
            if (declaracion != null) {
                declaracion.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException evento) {
            evento.printStackTrace();
        }
    }
    /*
    //Ejemplo para que todo funcionaba correctamente la conexion
    @Override
    public List<UnidadDidactica> get() {
        UnidadDidactica unidad;
        List<UnidadDidactica> lista = new ArrayList<>();
        ResultSet resultado;

        try {
            openConnection();
            declaracion = conexion.prepareStatement(CONSULTA_TODO);
            resultado = declaracion.executeQuery();
            while (resultado.next()) {
                unidad = new UnidadDidactica();
                unidad.setidUnidad(resultado.getInt("ID"));
                unidad.setAcronimo(resultado.getString("ACRONIMO"));
                unidad.setTitulo(resultado.getString("TITULO"));
                unidad.setEvaluacion(resultado.getString("EVALUACION"));
                unidad.setDescripcion(resultado.getString("DESCRIPCION"));
                lista.add(unidad);
            }

        } catch (SQLException evento) {
            evento.printStackTrace();
        } finally {
            closeConnection();
        }

        return lista;
    }

    @Override
    public void crearUnidadDidactica(UnidadDidactica unidad) {
        ResultSet resultado;
        try {
            openConnection();
            //Preparamos la insercion con la sql de arriba
            declaracion = conexion.prepareStatement(INSERCION_EJEMPLO);
            declaracion.setInt(1, unidad.getidUnidad());
            declaracion.setString(2, unidad.getAcronimo());
            declaracion.setString(3, unidad.getTitulo());
            declaracion.setString(4, unidad.getEvaluacion());
            declaracion.setString(5, unidad.getDescripcion());
            declaracion.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
    */
    @Override
    public void insertarUnidadDidactica(UnidadDidactica unidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertarConvocatoria(ConvocatoriaExamen convocatoria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertarEnunciado(Enunciado enunciado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<Integer, UnidadDidactica> cargarUnidadesDidacticas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<Integer, Enunciado> cargarEnunciados() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<Integer, ConvocatoriaExamen> cargarConvocatoriasExamen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<Integer, ConvocatoriaExamen> consultaEnunciadoConvocatoria(Integer idEnunciado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void agregarConvocatoriaEnunciado(Integer idEnunciado, Integer idConvocatoria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void crearUnidadDidactica(UnidadDidactica unidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UnidadDidactica> get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UnidadDidactica> getUnidadesDidacticas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void crearConvocatoria(ConvocatoriaExamen convocatoria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void crearEnunciado(Enunciado enunciado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Enunciado> getEnunciadosDeUnidad(int idUnidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void asignarEnunciadoAConvocatoria(int idEnunciado, int idConvocatoria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
