package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase representa un enunciado en el sistema. Contiene la información
 * relevante del enunciado, como su identificador, descripción, nivel de nivel,
 * disponibilidad, ruta y listas de unidades y convocatorias relacionadas.
 *
 * @author 2dam
 */
public class Enunciado {

    // Atributos privados de la clase
    /**
     * El identificador único del enunciado.
     */
    private Integer idEnunciado;

    /**
     * La descripción del enunciado.
     */
    private String descripcion;

    /**
     * El nivel de nivel del enunciado. Este es un {@code Enum} que debe ser
     * especificado.
     */
    private Dificultad nivel;

    /**
     * Indica si el enunciado está disponible.
     */
    private Boolean disponible;

    /**
     * La ruta asociada al enunciado.
     */
    private String ruta;

    /**
     * Lista de identificadores de unidades didácticas relacionadas con el
     * enunciado.
     */
    private List<Integer> listaUnidades;

    /**
     * Lista de identificadores de convocatorias relacionadas con el enunciado.
     */
    private List<Integer> listaConvocatorias;

    /**
     * Constructor por defecto de la clase Enunciado. Inicializa las listas de
     * unidades y convocatorias.
     */
    public Enunciado() {
        listaUnidades = new ArrayList<>();
        listaConvocatorias = new ArrayList<>();
    }

    /**
     * Obtiene el identificador del enunciado.
     *
     * @return El identificador del enunciado.
     */
    public Integer getIdEnunciado() {
        return idEnunciado;
    }

    /**
     * Establece el identificador del enunciado.
     *
     * @param idEnunciado El identificador a asignar.
     */
    public void setIdEnunciado(Integer idEnunciado) {
        this.idEnunciado = idEnunciado;
    }

    /**
     * Obtiene la descripción del enunciado.
     *
     * @return La descripción del enunciado.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del enunciado.
     *
     * @param descripcion La descripción a asignar.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el nivel de nivel del enunciado.
     *
     * @return El nivel de nivel del enunciado.
     */
    public Dificultad getNivel() {
        return nivel;
    }

    /**
     * Establece el nivel de nivel del enunciado.
     *
     * @param nivel) El nivel de nivel a asignar.
     */
    public void setNivel(Dificultad nivel) {
        this.nivel = nivel;

    }

    /**
     * Obtiene la disponibilidad del enunciado.
     *
     * @return {@code true} si el enunciado está disponible, {@code false} en
     * caso contrario.
     */
    public Boolean getDisponible() {
        return disponible;
    }

    /**
     * Establece la disponibilidad del enunciado.
     *
     * @param disponible {@code true} si el enunciado está disponible,
     * {@code false} en caso contrario.
     */
    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Obtiene la ruta asociada al enunciado.
     *
     * @return La ruta del enunciado.
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * Establece la ruta asociada al enunciado.
     *
     * @param ruta La ruta a asignar.
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    /**
     * Obtiene la lista de identificadores de unidades didácticas relacionadas
     * con el enunciado.
     *
     * @return La lista de identificadores de unidades.
     */
    public List<Integer> getListaUnidades() {
        return listaUnidades;
    }

    /**
     * Establece la lista de identificadores de unidades didácticas relacionadas
     * con el enunciado.
     *
     * @param listaUnidades La lista de identificadores a asignar.
     */
    public void setListaUnidades(List<Integer> listaUnidades) {
        this.listaUnidades = listaUnidades;
    }

    /**
     * Obtiene la lista de identificadores de convocatorias relacionadas con el
     * enunciado.
     *
     * @return La lista de identificadores de convocatorias.
     */
    public List<Integer> getListaConvocatorias() {
        return listaConvocatorias;
    }

    /**
     * Establece la lista de identificadores de convocatorias relacionadas con
     * el enunciado.
     *
     * @param listaConvocatorias La lista de identificadores a asignar.
     */
    public void setListaConvocatorias(List<Integer> listaConvocatorias) {
        this.listaConvocatorias = listaConvocatorias;
    }

    /**
     * Devuelve una representación en formato de cadena del enunciado.
     *
     * @return Una cadena con los detalles del enunciado.
     */
    @Override
    public String toString() {
        return "Enunciado{"
                + "idEnunciado=" + idEnunciado
                + ", descripcion='" + descripcion + '\''
                + ", nivel=" + nivel
                + ", disponible=" + disponible
                + ", ruta='" + ruta + '\''
                + ", listaUnidades=" + listaUnidades
                + ", listaConvocatorias=" + listaConvocatorias
                + '}';
    }
}
