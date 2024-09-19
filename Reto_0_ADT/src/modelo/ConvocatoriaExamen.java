package modelo;

import java.time.LocalDate;

/**
 * Esta clase representa una convocatoria de examen en el sistema.
 * Contiene la información relevante de la convocatoria, como su identificador,
 * descripción, fecha, curso y el enunciado asociado.
 *
 * @author 2dam
 */
public class ConvocatoriaExamen {

    // Atributos privados de la clase
    /**
     * El identificador único de la convocatoria de examen.
     */
    private Integer idConvocatoria;

    /**
     * El nombre o título de la convocatoria.
     */
    private String convocatoria;

    /**
     * La descripción detallada de la convocatoria.
     */
    private String descripcion;

    /**
     * La fecha en que se llevará a cabo la convocatoria.
     */
    private LocalDate fecha;

    /**
     * El curso al que está asociada la convocatoria.
     */
    private String curso;

    /**
     * El identificador del enunciado relacionado con esta convocatoria.
     */
    private Integer idEnunciado;

    /**
     * Constructor por defecto de la clase ConvocatoriaExamen.
     */
    public ConvocatoriaExamen() {
    }

    // Métodos getters y setters
    /**
     * Obtiene el identificador de la convocatoria de examen.
     *
     * @return El identificador de la convocatoria.
     */
    public Integer getIdConvocatoria() {
        return idConvocatoria;
    }

    /**
     * Establece el identificador de la convocatoria de examen.
     *
     * @param idConvocatoria El identificador a asignar.
     */
    public void setIdConvocatoria(Integer idConvocatoria) {
        this.idConvocatoria = idConvocatoria;
    }

    /**
     * Obtiene el nombre o título de la convocatoria.
     *
     * @return El nombre de la convocatoria.
     */
    public String getConvocatoria() {
        return convocatoria;
    }

    /**
     * Establece el nombre o título de la convocatoria.
     *
     * @param convocatoria El nombre de la convocatoria a asignar.
     */
    public void setConvocatoria(String convocatoria) {
        this.convocatoria = convocatoria;
    }

    /**
     * Obtiene la descripción de la convocatoria.
     *
     * @return La descripción de la convocatoria.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción de la convocatoria.
     *
     * @param descripcion La descripción a asignar.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la fecha de la convocatoria en formato {@code LocalDate}.
     *
     * @return La fecha de la convocatoria.
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de la convocatoria.
     *
     * @param fecha La fecha a asignar en formato {@code LocalDate}.
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el curso asociado a la convocatoria.
     *
     * @return El curso de la convocatoria.
     */
    public String getCurso() {
        return curso;
    }

    /**
     * Establece el curso asociado a la convocatoria.
     *
     * @param curso El curso a asignar.
     */
    public void setCurso(String curso) {
        this.curso = curso;
    }

    /**
     * Obtiene el identificador del enunciado relacionado con esta convocatoria.
     *
     * @return El identificador del enunciado.
     */
    public Integer getIdEnunciado() {
        return idEnunciado;
    }

    /**
     * Establece el identificador del enunciado relacionado con esta
     * convocatoria.
     *
     * @param idEnunciado El identificador del enunciado a asignar.
     */
    public void setIdEnunciado(Integer idEnunciado) {
        this.idEnunciado = idEnunciado;
    }

    // Método toString
    /**
     * Devuelve una representación en formato de cadena de la convocatoria.
     *
     * @return Una cadena con los detalles de la convocatoria.
     */
    @Override
    public String toString() {
        return "ConvocatoriaExamen{"
                + "idConvocatoria=" + idConvocatoria
                + ", convocatoria='" + convocatoria + '\''
                + ", descripcion='" + descripcion + '\''
                + ", fecha=" + fecha
                + ", curso='" + curso + '\''
                + '}';
    }
}
