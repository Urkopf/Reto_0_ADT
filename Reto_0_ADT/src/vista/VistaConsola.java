/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.IDao;
import controlador.IVista;
import java.sql.SQLException;
import utilidades.Utilidades;

/**
 *
 * @author 2dam
 */
public class VistaConsola implements IVista {

    private IDao dao;

    public VistaConsola(IDao dao) throws SQLException {
        this.dao = dao;
        visualizaMenu();
    }

    private void menu() {

        System.out.println("--------------------------------------");
        System.out.println("             MENU OPCION              ");
        System.out.println("--------------------------------------");
        System.out.println("1) Consulta Ejemplo                   ");
        System.out.println("2) Insercion UnidadDidactica          ");
        System.out.println("3) Salir de al App                    ");
        System.out.println("--------------------------------------");
        System.out.println("                 Opcion               ");
    }
    /*
    private void consulta() {
        List<UnidadDidactica> mis_unidades;
        mis_unidades = dao.get();
        for (UnidadDidactica unidad : mis_unidades) {
            System.out.println(unidad);
        }
    }

    private void insercionUnidadDidactica() {
        String acronimo, titulo, evaluacion, descripcion;
        System.out.println("Introduce los campos: ");
        UnidadDidactica unidad = new UnidadDidactica();
        //Cuidado ejemplo para id de manera manual
        unidad.setidUnidad(2);
        acronimo = Utilidades.introducirCadena("Introduce el acronimo:");
        unidad.setAcronimo(acronimo);
        titulo = Utilidades.introducirCadena("Introduce el titulo:");
        unidad.setTitulo(titulo);
        evaluacion = Utilidades.introducirCadena("Introduce el evaluacion:");
        unidad.setEvaluacion(evaluacion);
        descripcion = Utilidades.introducirCadena("Introduce el descripcion:");
        unidad.setDescripcion(descripcion);

        dao.crearUnidadDidactica(unidad);
        System.out.println("Todo Bien!!!");
    }
    */
    @Override
    public void visualizaMenu() {
        Integer opc;
        do {
            menu();
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

    @Override
    public void opcionCrearUnidadYConvocatoria() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void opcionCrearEnunciado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void opcionConsultaEnunciadosDeUnidad() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void opcionConsultaConvocatoriasConEnunciado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void opcionVisualizaDocumento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void opcionAsignarEnunciadoAConvocatoria() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
