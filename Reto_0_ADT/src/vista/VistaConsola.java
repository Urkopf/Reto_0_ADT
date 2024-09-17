/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.IDao;
import java.util.List;
import modelo.UnidadDidactica;
import utilidades.Utilidades;

/**
 *
 * @author 2dam
 */
public class VistaConsola {

    private IDao dao;

    public VistaConsola(IDao dao) {
        this.dao = dao;
        Integer opc;
        do {
            interfaz();
            opc = Utilidades.introducirInteger("Introduzca una opcion valida: ");
            switch (opc) {
                case 1:
                    consulta();
                    break;
                case 2:
                    System.out.println("HastaLuego");
                    break;
                case 3:
                    System.out.println("Agur");
                    break;
                default:
                    System.out.println("Opcion incorrecta");
            }
        } while (opc != 3);
    }

    private void interfaz() {

        System.out.println("--------------------------------------");
        System.out.println("             MENU OPCION              ");
        System.out.println("--------------------------------------");
        System.out.println("1) Base de Datos                      ");
        System.out.println("2) Archivos                           ");
        System.out.println("3) Salir de al App                    ");
        System.out.println("--------------------------------------");
        System.out.println("                 Opcion               ");
    }

    private void consulta() {
        List<UnidadDidactica> mis_unidades;
        mis_unidades = dao.get();
        for (UnidadDidactica unidad : mis_unidades) {
            System.out.println(unidad);
        }
    }

}
