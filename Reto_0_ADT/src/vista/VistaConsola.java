package vista;

import controlador.IDao;
import controlador.IVista;
import modelo.UnidadDidactica;
import modelo.ConvocatoriaExamen; // Asegúrate de tener esta clase
import modelo.Enunciado; // Asegúrate de tener esta clase
import utilidades.Utilidades;

import java.util.List;
import modelo.Dificultad;

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
        System.out.println("| 3. Crear Convocatoria        |");
        System.out.println("| 4. Crear Enunciado           |");
        System.out.println("| 5. Consultar Enunciados      |");
        System.out.println("| 6. Visualizar Documento       |");
        System.out.println("| 7. Asignar Enunciado         |");
        System.out.println("| 0. Salir                     |");
        System.out.println("+------------------------------+");
    }

    @Override
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
                case 3:
                    opcionCrearConvocatoria();
                    break;
                case 4:
                    opcionCrearEnunciado();
                    break;
                case 5:
                    opcionConsultaEnunciadosDeUnidad();
                    break;
                case 6:
                    opcionVisualizaDocumento();
                    break;
                case 7:
                    opcionAsignarEnunciadoAConvocatoria();
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
        List<UnidadDidactica> unidades = dao.getUnidadesDidacticas();
        System.out.println("+------------------------------+");
        System.out.println("|     Unidades Didácticas      |");
        System.out.println("+------------------------------+");
        for (UnidadDidactica unidad : unidades) {
            System.out.println("| " + unidad);
        }
        System.out.println("+------------------------------+");
    }

    private void opcionCrearConvocatoria() {
        ConvocatoriaExamen convocatoria = new ConvocatoriaExamen();
        convocatoria.setIdConvocatoria(Utilidades.introducirInteger("Ingrese ID de la convocatoria:"));
        convocatoria.setConvocatoria(Utilidades.introducirCadena("Ingrese el nombre de la convocatoria:"));
        convocatoria.setDescripcion(Utilidades.introducirCadena("Ingrese la descripción:"));
        convocatoria.setFecha(Utilidades.introducirFecha("Ingrese la fecha (dd/MM/yyyy):"));
        convocatoria.setCurso(Utilidades.introducirCadena("Ingrese el curso:"));

        dao.crearConvocatoria(convocatoria);
        System.out.println("Convocatoria creada exitosamente.");
    }

   private void crearEnunciado() {
    Enunciado enunciado = new Enunciado();
    enunciado.setIdEnunciado(Utilidades.introducirInteger("Ingrese ID del enunciado:"));
    enunciado.setDescripcion(Utilidades.introducirCadena("Ingrese la descripción del enunciado:"));
    
    // Pedir dificultad y convertir a enum
    String dificultadStr = Utilidades.introducirCadena("Ingrese la dificultad (ALTA, MEDIA, BAJA):").toUpperCase();
    Dificultad dificultad;
    try {
        dificultad = Dificultad.valueOf(dificultadStr);
    } catch (IllegalArgumentException e) {
        System.out.println("Dificultad no válida. Se establecerá a BAJA por defecto.");
        dificultad = Dificultad.BAJA; // Asignar un valor por defecto si es necesario
    }
    enunciado.setDificultad(dificultad);

    enunciado.setDisponible(Utilidades.introducirRespuesta("¿Está disponible? (1: Sí, 0: No)"));
    enunciado.setRuta(Utilidades.introducirCadena("Ingrese la ruta del documento:"));

    dao.crearEnunciado(enunciado);
    System.out.println("Enunciado creado exitosamente.");
}

    public void opcionConsultaEnunciadosDeUnidad() {
        int idUnidad = Utilidades.introducirInteger("Ingrese ID de la unidad didáctica para consultar enunciados:");
        List<Enunciado> enunciados = dao.getEnunciadosDeUnidad(idUnidad);
        System.out.println("+------------------------------+");
        System.out.println("|   Enunciados de la Unidad    |");
        System.out.println("+------------------------------+");
        for (Enunciado enunciado : enunciados) {
            System.out.println("| " + enunciado);
        }
        System.out.println("+------------------------------+");
    }

    public void opcionVisualizaDocumento() {
        String rutaDocumento = Utilidades.introducirCadena("Ingrese la ruta del documento:");
        // Lógica para visualizar el documento (puede ser abrirlo, leerlo, etc.)
        System.out.println("Visualizando documento en: " + rutaDocumento);
        // Asegúrate de manejar excepciones y validar la ruta
    }

    public void opcionAsignarEnunciadoAConvocatoria() {
        int idEnunciado = Utilidades.introducirInteger("Ingrese ID del enunciado:");
        int idConvocatoria = Utilidades.introducirInteger("Ingrese ID de la convocatoria:");
        dao.asignarEnunciadoAConvocatoria(idEnunciado, idConvocatoria);
        System.out.println("Enunciado asignado a la convocatoria exitosamente.");
    }

    @Override
    public void opcionCrearUnidadYConvocatoria() {
        // Implementa si es necesario
    }

    @Override
    public void opcionConsultaConvocatoriasConEnunciado() {
        // Implementa si es necesario
    }

    /*@Override
    public void opcionVisualizaDocumento() {
        // Implementa si es necesario
    }

    @Override
    public void opcionAsignarEnunciadoAConvocatoria() {
        // Implementa si es necesario
    }
*/
    @Override
    public void visualizaMenu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void opcionCrearEnunciado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
