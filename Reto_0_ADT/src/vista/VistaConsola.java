/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import utilidades.Utilidades;

/**
 *
 * @author 2dam
 */
public class VistaConsola {

    public VistaConsola() {
        opcion1();
    }

    private void opcion1() {
        int opc;
        do {
            interfaz();
            opc = Utilidades.introducirInteger("Introduzca una opcion valida: ");
            switch (opc) {
                case 1:
                    break;
                case 2:
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


}
