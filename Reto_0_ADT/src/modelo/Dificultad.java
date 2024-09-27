/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author 2dam
 */
public enum Dificultad {
    ALTA, MEDIA, BAJA;

    //Convertir String en el Enum Correspondiente
    public static Dificultad convertirStringEnum(String texto) {
        return Dificultad.valueOf(texto);
    }
}
