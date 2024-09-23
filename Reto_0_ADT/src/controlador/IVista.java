/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

/**
 *
 * @author 2dam
 */
public interface IVista {

    public void visualizaMenu();

    public void crearUnidadDidactica();

    public void opcionCrearConvocatoria();

    public void opcionCrearEnunciado();

    public void opcionConsultaEnunciadosDeUnidad();

    public void opcionConsultaConvocatoriasConEnunciado();

    public void opcionVisualizaDocumento();

    public void opcionAsignarEnunciadoAConvocatoria();

    public void mostrarOpciones();
}
