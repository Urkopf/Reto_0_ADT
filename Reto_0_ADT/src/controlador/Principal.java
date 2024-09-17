package controlador;

import java.sql.SQLException;
import modelo.ImplementacionDB;
import vista.VistaConsola;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
        VistaConsola vista = new VistaConsola(dao);
    }

}
