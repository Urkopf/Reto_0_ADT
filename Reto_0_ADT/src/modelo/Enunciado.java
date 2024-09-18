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
public class Enunciado {

    private Integer idEnunciado;
    private String descripcion;
    private Enum dificultad;
    private Boolean disponible;
    private String ruta;
    private Map<Integer, UnidadDidactica> listaUnidades;
    private Map<Integer, ConvocatoriaExamen> listaConvocatorias;

    public Enunciado() {
        listaUnidades = new TreeMap<>();
        listaConvocatorias = new TreeMap<>();
    }

    public Integer getidEnunciado() {
        return idEnunciado;
    }

    public void setidEnunciado(Integer idEnunciado) {
        this.idEnunciado = idEnunciado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Enum getDificultad() {
        return dificultad;
    }

    public void setDificultad(Enum dificultad) {
        this.dificultad = dificultad;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Map<Integer, UnidadDidactica> getListaUnidades() {
        return listaUnidades;
    }

    public void setListaUnidades(Map<Integer, UnidadDidactica> listaUnidades) {
        this.listaUnidades = listaUnidades;
    }

    public Map<Integer, ConvocatoriaExamen> getListaConvocatorias() {
        return listaConvocatorias;
    }

    public void setListaConvocatorias(Map<Integer, ConvocatoriaExamen> listaConvocatorias) {
        this.listaConvocatorias = listaConvocatorias;
    }

    @Override
    public String toString() {
        return "Enunciado{" + "idEnunciado=" + idEnunciado + ", descripcion=" + descripcion + ", dificultad=" + dificultad + ", disponible=" + disponible + ", ruta=" + ruta + ", listaUnidades=" + listaUnidades + ", listaConvocatorias=" + listaConvocatorias + '}';
    }

}
