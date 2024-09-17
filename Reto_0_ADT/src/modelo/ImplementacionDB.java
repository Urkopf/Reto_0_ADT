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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
                unidad.setId(resultado.getInt("ID"));
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


}
