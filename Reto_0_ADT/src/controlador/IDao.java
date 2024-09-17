/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import modelo.UnidadDidactica;

/**
 *
 * @author 2dam
 */
public interface IDao {

    public List<UnidadDidactica> get();
}
