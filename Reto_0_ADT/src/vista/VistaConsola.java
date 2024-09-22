package vista;

import controlador.IDao;
import controlador.IVista;
import modelo.UnidadDidactica;
import utilidades.Utilidades;

import java.util.List;

public class VistaConsola implements IVista {

    private IDao dao;

    public VistaConsola(IDao dao) {
        this.dao = dao;
    }

    public void mostrarMenu() {
        System.out.println("+------------------------------+");
        System.out.println("|      Menú Principal          |");
        System.out.println("+------------------------------+");
        System.out.println("| 1. Crear Unidad Didáctica    |");
        System.out.println("| 2. Consultar Unidades        |");
        System.out.println("| 0. Salir                     |");
        System.out.println("+------------------------------+");
    }

    public void mostrarOpciones() {
        int opcion;
        do {
            mostrarMenu();
            opcion = Utilidades.introducirInteger("Seleccione una opción:");

            switch (opcion) {
                case 1:
                    crearUnidadDidactica();
                    break;
                case 2:
                    consultarUnidadesDidacticas();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 0);
    }

    private void crearUnidadDidactica() {
        UnidadDidactica unidad = new UnidadDidactica();
        unidad.setidUnidad(Utilidades.introducirInteger("Ingrese ID de la unidad didáctica:"));
        unidad.setAcronimo(Utilidades.introducirCadena("Ingrese el acrónimo:"));
        unidad.setTitulo(Utilidades.introducirCadena("Ingrese el título:"));
        unidad.setEvaluacion(Utilidades.introducirCadena("Ingrese la evaluación:"));
        unidad.setDescripcion(Utilidades.introducirCadena("Ingrese la descripción:"));

        dao.crearUnidadDidactica(unidad);
        System.out.println("Unidad Didáctica creada exitosamente.");
    }

    private void consultarUnidadesDidacticas() {
        List<UnidadDidactica> unidades = dao.get();
        System.out.println("+------------------------------+");
        System.out.println("|     Unidades Didácticas      |");
        System.out.println("+------------------------------+");
        for (UnidadDidactica unidad : unidades) {
            System.out.println("| " + unidad);
        }
        System.out.println("+------------------------------+");
    }

    @Override
    public void visualizaMenu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
