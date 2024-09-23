package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase representa una unidad didáctica en el sistema. Contiene la
 * información relevante de la unidad, incluyendo su identificador, acrónimo,
 * título, evaluación, descripción y una lista de unidades relacionadas.
 *
 * @author 2dam
 */
public class UnidadDidactica {

    // Atributos privados de la clase
    /**
     * El identificador único de la unidad didáctica.
     */
    private Integer idUnidad;

    /**
     * El acrónimo de la unidad didáctica.
     */
    private String acronimo;

    /**
     * El título de la unidad didáctica.
     */
    private String titulo;

    /**
     * La evaluación asociada a la unidad didáctica.
     */
    private String evaluacion;

    /**
     * La descripción de la unidad didáctica.
     */
    private String descripcion;

    /**
     * Lista de identificadores de unidades didácticas relacionadas.
     */
    private List<Integer> listaUnidades;

    /**
     * Constructor por defecto de la clase UnidadDidactica. Inicializa la lista
     * de unidades relacionadas.
     */
    public UnidadDidactica() {
        listaUnidades = new ArrayList<>();
    }

    /**
     * Obtiene el identificador de la unidad didáctica.
     *
     * @return El identificador de la unidad didáctica.
     */
    public Integer getidUnidad() {
        return idUnidad;
    }

    /**
     * Establece el identificador de la unidad didáctica.
     *
     * @param idUnidad El identificador a asignar.
     */
    public void setidUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    /**
     * Obtiene el acrónimo de la unidad didáctica.
     *
     * @return El acrónimo de la unidad didáctica.
     */
    public String getAcronimo() {
        return acronimo;
    }

    /**
     * Establece el acrónimo de la unidad didáctica.
     *
     * @param acronimo El acrónimo a asignar.
     */
    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    /**
     * Obtiene el título de la unidad didáctica.
     *
     * @return El título de la unidad didáctica.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título de la unidad didáctica.
     *
     * @param titulo El título a asignar.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene la evaluación asociada a la unidad didáctica.
     *
     * @return La evaluación de la unidad didáctica.
     */
    public String getEvaluacion() {
        return evaluacion;
    }

    /**
     * Establece la evaluación asociada a la unidad didáctica.
     *
     * @param evaluacion La evaluación a asignar.
     */
    public void setEvaluacion(String evaluacion) {
        this.evaluacion = evaluacion;
    }

    /**
     * Obtiene la descripción de la unidad didáctica.
     *
     * @return La descripción de la unidad didáctica.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción de la unidad didáctica.
     *
     * @param descripcion La descripción a asignar.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la lista de identificadores de unidades didácticas relacionadas.
     *
     * @return La lista de identificadores de unidades relacionadas.
     */
    public List<Integer> getListaUnidades() {
        return listaUnidades;
    }

    /**
     * Establece la lista de identificadores de unidades didácticas
     * relacionadas.
     *
     * @param listaUnidades La lista de identificadores a asignar.
     */
    public void setListaUnidades(List<Integer> listaUnidades) {
        this.listaUnidades = listaUnidades;
    }

    /**
     * Devuelve una representación en formato de cadena de la unidad didáctica.
     *
     * @return Una cadena con los detalles de la unidad didáctica.
     */
    @Override
    public String toString() {
        return "UnidadDidactica{"
                + "idUnidad=" + idUnidad
                + ", acronimo='" + acronimo + '\''
                + ", titulo='" + titulo + '\''
                + ", evaluacion='" + evaluacion + '\''
                + ", descripcion='" + descripcion + '\''
                + ", listaUnidades=" + listaUnidades
                + '}';
    }
}
