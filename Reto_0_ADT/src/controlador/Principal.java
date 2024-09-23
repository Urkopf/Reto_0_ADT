package controlador;

import java.sql.SQLException;
import modelo.ImplementacionDB;
import vista.VistaConsola;



/**
 *
 * @author 2dam
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        IDao dao = new ImplementacionDB();
        IVista vista = new VistaConsola(dao);
        
        vista.mostrarOpciones();
    }

}
