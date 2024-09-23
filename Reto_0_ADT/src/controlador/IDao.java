package controlador;

import java.util.List;
import java.util.Map;
import modelo.ConvocatoriaExamen;
import modelo.Enunciado;
import modelo.UnidadDidactica;

/**
 * Interface que define las operaciones básicas para el acceso a datos de las
 * entidades {@code UnidadDidactica}, {@code ConvocatoriaExamen} y
 * {@code Enunciado}. Proporciona métodos para insertar y cargar datos, así como
 * para gestionar la relación entre enunciados y convocatorias.
 *
 * @author 2dam
 */
public interface IDao {

    /**
     * Inserta una nueva unidad didáctica en la base de datos.
     *
     * @param unidad La unidad didáctica a insertar.
     */
    public void insertarUnidadDidactica(UnidadDidactica unidad);

    /**
     * Inserta una nueva convocatoria de examen en la base de datos.
     *
     * @param convocatoria La convocatoria de examen a insertar.
     */
    public void insertarConvocatoria(ConvocatoriaExamen convocatoria);

    /**
     * Inserta un nuevo enunciado en la base de datos.
     *
     * @param enunciado El enunciado a insertar.
     */
    public void insertarEnunciado(Enunciado enunciado);

    /**
     * Carga todas las unidades didácticas desde la base de datos.
     *
     * @return Un mapa donde las claves son los identificadores de las unidades
     * didácticas y los valores son los objetos {@code UnidadDidactica}.
     */
    public Map<Integer, UnidadDidactica> cargarUnidadesDidacticas();

    /**
     * Carga todos los enunciados desde la base de datos.
     *
     * @return Un mapa donde las claves son los identificadores de los
     * enunciados y los valores son los objetos {@code Enunciado}.
     */
    public Map<Integer, Enunciado> cargarEnunciados();

    /**
     * Carga todas las convocatorias de examen desde la base de datos.
     *
     * @return Un mapa donde las claves son los identificadores de las
     * convocatorias y los valores son los objetos {@code ConvocatoriaExamen}.
     */
    public Map<Integer, ConvocatoriaExamen> cargarConvocatoriasExamen();

    /**
     * Consulta las convocatorias de examen asociadas a un enunciado específico.
     *
     * @param idEnunciado El identificador del enunciado para consultar las
     * convocatorias.
     * @return Un mapa donde las claves son los identificadores de las
     * convocatorias y los valores son los objetos {@code ConvocatoriaExamen}.
     */
    public Map<Integer, ConvocatoriaExamen> consultaEnunciadoConvocatoria(Integer idEnunciado);

    /**
     * Asocia una convocatoria de examen a un enunciado específico en la base de
     * datos.
     *
     * @param idEnunciado El identificador del enunciado al que se asociará la
     * convocatoria.
     * @param idConvocatoria El identificador de la convocatoria a asociar.
     */
    public void agregarConvocatoriaEnunciado(Integer idEnunciado, Integer idConvocatoria);
}
