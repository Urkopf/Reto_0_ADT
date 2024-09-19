/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import java.util.Map;
import modelo.ConvocatoriaExamen;
import modelo.Enunciado;
import modelo.UnidadDidactica;

/**
 *
 * @author 2dam
 */
public interface IDao {

    public List<UnidadDidactica> get();

    public void crearUnidadDidactica(UnidadDidactica unidad);

    public void insertarUnidadDidactica(UnidadDidactica unidad);

    public void insertarConvocatoria(ConvocatoriaExamen convocatoria);

    public void insertarEnunciado(Enunciado enunciado);

    public Map<Integer, UnidadDidactica> cargarUnidadesDidacticas();

    public Map<Integer, Enunciado> cargarEnunciados();

    public Map<Integer, ConvocatoriaExamen> cargarConvocatoriasExamen();

    public Map<Integer, ConvocatoriaExamen> consultaEnunciadoConvocatoria(Integer idEnunciado);

    //public Vector<Integer> visualizaDocumento(Integer idEnunciado);

    public void agregarConvocatoriaEnunciado(Integer idEnunciado, Integer idConvocatoria);

}
