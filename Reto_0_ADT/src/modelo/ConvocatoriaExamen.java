/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;

/**
 *
 * @author 2dam
 */
public class ConvocatoriaExamen {

    private Integer idConvocatoria;
    private String convocatoria;
    private String descripcion;
    private LocalDate fecha;
    private String curso;

    public ConvocatoriaExamen() {
    }

    public Integer getidConvocatoria() {
        return idConvocatoria;
    }

    public void setidConvocatoria(Integer idConvocatoria) {
        this.idConvocatoria = idConvocatoria;
    }

    public String getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(String convocatoria) {
        this.convocatoria = convocatoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "ConvocatoriaExamen{" + "idConvocatoria=" + idConvocatoria + ", convocatoria=" + convocatoria + ", descripcion=" + descripcion + ", fecha=" + fecha + ", curso=" + curso + '}';
    }

    public void setIdConvocatoria(int introducirInteger) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
