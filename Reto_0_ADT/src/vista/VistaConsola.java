package vista;

import controlador.IDao;
import controlador.IVista;
import java.util.Map;
import modelo.ConvocatoriaExamen;
import modelo.Dificultad;
import modelo.Enunciado;
import modelo.UnidadDidactica;
import utilidades.Utilidades;

public class VistaConsola implements IVista {

    private IDao dao;

    public VistaConsola(IDao dao) {
        this.dao = dao;
    }

    @Override
    public void visualizaMenu() {
        System.out.println("+------------------------------+");
        System.out.println("|      Menú Principal          |");
        System.out.println("+------------------------------+");
        System.out.println("| 1. Crear Unidad Didáctica    ");
        System.out.println("| 2. Consultar Unidades        ");
        System.out.println("| 3. Crear Convocatoria        ");
        System.out.println("| 4. Crear Enunciado           ");
        System.out.println("| 5. Consultar Enunciados de Unidad");
        System.out.println("| 6. Consultar Enunciados de Convocatoria");
        System.out.println("| 7. Visualizar Documento      ");
        System.out.println("| 8. Asignar Enunciado         ");
        System.out.println("| 0. Salir                     ");
        System.out.println("+------------------------------+");
    }

    @Override
    public void mostrarOpciones() {
        int opcion;
        do {
            visualizaMenu();
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
                    opcionConsultaConvocatoriasConEnunciado();
                    break;
                case 7:
                    opcionVisualizaDocumento();
                    break;
                case 8:
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

    @Override
    public void crearUnidadDidactica() {
        UnidadDidactica unidad = new UnidadDidactica();
        unidad.setidUnidad(Utilidades.introducirInteger("Ingrese ID de la unidad didáctica:"));
        unidad.setAcronimo(Utilidades.introducirCadena("Ingrese el acrónimo:"));
        unidad.setTitulo(Utilidades.introducirCadena("Ingrese el título:"));
        unidad.setEvaluacion(Utilidades.introducirCadena("Ingrese la evaluación:"));
        unidad.setDescripcion(Utilidades.introducirCadena("Ingrese la descripción:"));

        dao.insertarUnidadDidactica(unidad);
        System.out.println("Unidad Didáctica creada exitosamente.");
    }

    private void consultarUnidadesDidacticas() {
        Map<Integer, UnidadDidactica> unidades = dao.cargarUnidadesDidacticas();
        System.out.println("+------------------------------+");
        System.out.println("|     Unidades Didácticas      |");
        System.out.println("+------------------------------+");
        unidades.values().forEach((unidad) -> {
            System.out.println("| " + unidad);
        });
        System.out.println("+------------------------------+");
    }

    @Override
    public void opcionCrearConvocatoria() {
        ConvocatoriaExamen convocatoria = new ConvocatoriaExamen();
        convocatoria.setIdConvocatoria(Utilidades.introducirInteger("Ingrese ID de la convocatoria:"));
        convocatoria.setConvocatoria(Utilidades.introducirCadena("Ingrese el nombre de la convocatoria:"));
        convocatoria.setDescripcion(Utilidades.introducirCadena("Ingrese la descripción:"));
        convocatoria.setFecha(Utilidades.introducirFecha("Ingrese la fecha (dd/MM/yyyy):"));
        convocatoria.setCurso(Utilidades.introducirCadena("Ingrese el curso:"));

        dao.insertarConvocatoria(convocatoria);
        System.out.println("Convocatoria creada exitosamente.");
    }

    @Override
    public void opcionCrearEnunciado() {
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

        dao.insertarEnunciado(enunciado);
        System.out.println("Enunciado creado exitosamente.");
    }

    @Override
    public void opcionConsultaEnunciadosDeUnidad() {
        Map<Integer, UnidadDidactica> unidades = dao.cargarUnidadesDidacticas();
        System.out.println("+---------------------------------+");
        System.out.println("| Unidades didacticas Disponibles |");
        System.out.println("+---------------------------------+");
        unidades.values().forEach((unidad) -> {
            System.out.println("| " + unidad);
        });
        System.out.println("+------------------------------+");
        int idUnidad = Utilidades.introducirInteger("Ingrese ID de la unidad didáctica para consultar enunciados:");
        Map<Integer, Enunciado> enunciados = dao.consultaEnunciadoUnidadDidactica(idUnidad);
        System.out.println("+------------------------------+");
        System.out.println("|   Enunciados de la Unidad    |");
        System.out.println("+------------------------------+");
        enunciados.values().forEach((enunciado) -> {
            System.out.println("| " + enunciado);
        });
        System.out.println("+------------------------------+");
    }

    @Override
    public void opcionConsultaConvocatoriasConEnunciado() {
        Map<Integer, Enunciado> enunciados = dao.cargarEnunciados();
        System.out.println("+---------------------------------+");
        System.out.println("| Enunciados          Disponibles |");
        System.out.println("+---------------------------------+");
        enunciados.values().forEach((enunciado) -> {
            System.out.println("| " + enunciado);
        });
        System.out.println("+------------------------------+");
        int idEnunciado = Utilidades.introducirInteger("Ingrese ID del Enunciado para ver en qu e convocatorias se usa:");
        Map<Integer, ConvocatoriaExamen> convocatorias = dao.consultaEnunciadoConvocatoria(idEnunciado);
        System.out.println("+------------------------------+");
        System.out.println("|   Enunciados de la Unidad    |");
        System.out.println("+------------------------------+");
        convocatorias.values().forEach((convocatoria) -> {
            System.out.println("| " + convocatoria);
        });
        System.out.println("+------------------------------+");
    }

    @Override
    public void opcionVisualizaDocumento() {
        String rutaDocumento = Utilidades.introducirCadena("Ingrese la ruta del documento:");
        // Lógica para visualizar el documento (puede ser abrirlo, leerlo, etc.)
        System.out.println("Visualizando documento en: " + rutaDocumento);
        // Asegúrate de manejar excepciones y validar la ruta
    }

    @Override
    public void opcionAsignarEnunciadoAConvocatoria() {
        int idEnunciado = Utilidades.introducirInteger("Ingrese ID del enunciado:");
        int idConvocatoria = Utilidades.introducirInteger("Ingrese ID de la convocatoria:");
        dao.agregarConvocatoriaEnunciado(idEnunciado, idConvocatoria);
        System.out.println("Enunciado asignado a la convocatoria exitosamente.");
    }

}
