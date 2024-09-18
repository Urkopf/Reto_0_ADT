/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author 2dam
 */
public class UnidadDidactica {

    private Integer idUnidad;
    private String acronimo;
    private String titulo;
    private String evaluacion;
    private String descripcion;
    private Map<Integer, Enunciado> listaUnidades;

    public UnidadDidactica() {
        listaUnidades = new TreeMap<>();
    }

    public Integer getidUnidad() {
        return idUnidad;
    }

    public void setidUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(String evaluacion) {
        this.evaluacion = evaluacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Map<Integer, Enunciado> getListaUnidades() {
        return listaUnidades;
    }

    public void setListaUnidades(Map<Integer, Enunciado> listaUnidades) {
        this.listaUnidades = listaUnidades;
    }

    @Override
    public String toString() {
        return "UnidadDidactica{" + "idUnidad=" + idUnidad + ", acronimo=" + acronimo + ", titulo=" + titulo + ", evaluacion=" + evaluacion + ", descripcion=" + descripcion + ", listaUnidades=" + listaUnidades + '}';
    }

}
