/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.IDao;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.TreeMap;
import utilidades.MyObjectOutputStream;

/**
 *
 * @author 2dam
 */
public class ImplementacionDB implements IDao {

    private ResourceBundle fichConf;
    private String URL, DBROOT, DBROOTPASS;
    private Connection conexion;
    private PreparedStatement declaracion;

    File fich = new File("ConvocatoriaExamenFich.obj");

    private final String CONSULTA_TODAS_UNIDADES = "SELECT * FROM UNIDADDIDACTICA";
    private final String INSERCION_UNIDAD = "INSERT INTO UNIDADDIDACTICA VALUES (?,?,?,?,?)";
    private final String INSERCION_CONVOCATORIA = "INSERT INTO CONVOCATORIAEXAMEN(ID,CONVOCATORIA,DESCRIPCION,FECHA,CURSO) VALUES(?,?,?,?,?)";
    private final String INSERCION_ENUNCIADO = "INSERT INTO ENUNCIADO VALUES(?,?,?,?,?)";
    private final String INSERCION_UNIDADENUNCIADO = "INSERT INTO UNIDADENUNCIADO VALUES (?,?)";

    private final String CONSULTA_TODOS_ENUNCIADOS = "SELECT * FROM ENUNCIADO";
    private final String CONSULTA_LISTA_IDS_UNIDADES_DE_ENUNCIADO = "SELECT UNIDADID AS ID FROM UNIDADENUNCIADO WHERE ENUNCIADOID = ?";
    private final String CONSULTA_LISTA_IDS_CONVOCATORIAS_DE_ENUNCIADO = "SELECT ID FROM CONVOCATORIAEXAMEN WHERE ENUNCIADOID = ?";
    private final String CONSULTA_LISTA_IDS_ENUNCIADOS_DE_UNIDAD = "SELECT ENUNCIADOID  AS ID FROM UNIDADENUNCIADO WHERE UNIDADID = ?";
    private final String CONSULTA_TODAS_CONVOCATORIAS_EXAMEN = "SELECT * FROM CONVOCATORIAEXAMEN";
    private final String CONSULTA_CONVOCATORIAS_EXAMEN_DE_ENUNCIADO = "SELECT * FROM CONVOCATORIAEXAMEN WHERE ENUNCIADOSID = ?";
    private final String CONSULTA_ENUNCIADO_DE_UNIDAD = "SELECT * FROM UNIDADESDIDACICAS WHERE ID IN (SELECT UNIDADID FROM UNIDADENUNCIADO WHERE UNIDADID = ?)?";
    private final String UPDATE_CONVOCATORIA = "UPDATE CONVOCATORIAEXAMEN SET ENUNCIADOID = ? WHERE ID = ?";
    private final String CONSULTA_CONVOCATORIA = "SELECT * FROM CONVOCATORIAEXAMEN WHERE ID = ?";

    public ImplementacionDB() {
        fichConf = ResourceBundle.getBundle("modelo.dbConfig");
        URL = fichConf.getString("URL");
        DBROOT = fichConf.getString("DBROOT");
        DBROOTPASS = fichConf.getString("DBROOTPASS");
    }

    private void openConnection() throws SQLException {

        conexion = DriverManager.getConnection(URL, DBROOT, DBROOTPASS);

    }

    private void closeConnection() {
        try {
            if (declaracion != null) {
                declaracion.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException evento) {
            evento.printStackTrace();
        }
    }

    /**
     * Inserta una nueva unidad didáctica en la base de datos.
     *
     * <p>
     * Este método establece una conexión con la base de datos y ejecuta una
     * sentencia SQL de inserción utilizando los valores proporcionados en el
     * objeto {@link UnidadDidactica}. Después de la inserción, se cierra la
     * conexión automáticamente, ya sea que la operación sea exitosa o no.
     *
     * @param unidad El objeto {@link UnidadDidactica} que contiene la
     * información de la unidad didáctica a insertar, incluyendo id, acrónimo,
     * título, evaluación y descripción.
     * @throws SQLException Si ocurre algún error en la ejecución de la
     * sentencia SQL.
     */
    @Override
    public void insertarUnidadDidactica(UnidadDidactica unidad) {
        try {
            openConnection();
            //Preparamos la insercion con la sql de arriba
            declaracion = conexion.prepareStatement(INSERCION_UNIDAD);
            declaracion.setInt(1, unidad.getidUnidad());
            declaracion.setString(2, unidad.getAcronimo());
            declaracion.setString(3, unidad.getTitulo());
            declaracion.setString(4, unidad.getEvaluacion());
            declaracion.setString(5, unidad.getDescripcion());
            declaracion.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    /**
     * Inserta una nueva convocatoria de examen en la base de datos.
     *
     * <p>
     * Este método establece una conexión con la base de datos y ejecuta una
     * sentencia SQL de inserción utilizando los valores proporcionados en el
     * objeto {@link ConvocatoriaExamen}. La inserción se realiza sin la clave
     * foránea (FK) relacionada con el enunciado. Después de la inserción, la
     * conexión se cierra automáticamente, independientemente de si la operación
     * fue exitosa o no.
     *
     * @param convocatoria El objeto {@link ConvocatoriaExamen} que contiene la
     * información de la convocatoria a insertar, incluyendo id, nombre,
     * descripción, fecha y curso.
     * @throws SQLException Si ocurre algún error en la ejecución de la
     * sentencia SQL.
     */
    @Override
    public void insertarConvocatoria(ConvocatoriaExamen convocatoria) {
        //Insercion de convocatoria sin el FK -> EnunciadoId
        try {
            openConnection();
            //Preparamos la insercion con la sql de arriba
            declaracion = conexion.prepareStatement(INSERCION_CONVOCATORIA);
            declaracion.setInt(1, convocatoria.getIdConvocatoria());
            declaracion.setString(2, convocatoria.getConvocatoria());
            declaracion.setString(3, convocatoria.getDescripcion());
            declaracion.setDate(4, java.sql.Date.valueOf(convocatoria.getFecha()));
            declaracion.setString(5, convocatoria.getCurso());
            declaracion.executeUpdate();
            anadirAFichero(convocatoria);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    /**
     * Inserta un nuevo enunciado en la base de datos.
     *
     * <p>
     * Este método establece una conexión con la base de datos y ejecuta una
     * sentencia SQL de inserción utilizando los valores proporcionados en el
     * objeto {@link Enunciado}. Después de la inserción, la conexión se cierra
     * automáticamente, independientemente de si la operación fue exitosa o no.
     *
     * @param enunciado El objeto {@link Enunciado} que contiene la información
     * del enunciado a insertar, incluyendo id, descripción, dificultad,
     * disponibilidad y ruta.
     * @throws SQLException Si ocurre algún error en la ejecución de la
     * sentencia SQL.
     */
    @Override
    public void insertarEnunciado(Enunciado enunciado) {
        try {
            openConnection();
            //Preparamos la insercion con la sql de arriba
            declaracion = conexion.prepareStatement(INSERCION_ENUNCIADO);
            declaracion.setInt(1, enunciado.getIdEnunciado());
            declaracion.setString(2, enunciado.getDescripcion());
            declaracion.setString(3, String.valueOf(enunciado.getNivel()));
            declaracion.setBoolean(4, enunciado.getDisponible());
            declaracion.setString(5, enunciado.getRuta());
            declaracion.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    /**
     * Carga todas las unidades didácticas desde la base de datos y las devuelve
     * en un mapa.
     *
     * <p>
     * Este método establece una conexión con la base de datos, ejecuta una
     * consulta SQL para obtener todas las unidades didácticas, y luego recorre
     * los resultados, asignando los valores obtenidos a objetos
     * {@link UnidadDidactica}. Cada unidad se almacena en un {@link Map}, con
     * la clave siendo el id de la unidad y el valor el objeto
     * {@link UnidadDidactica}.
     *
     * @return Un {@link Map} que contiene todas las unidades didácticas
     * cargadas desde la base de datos, donde la clave es el id de la unidad y
     * el valor es el objeto {@link UnidadDidactica}.
     * @throws SQLException Si ocurre algún error al ejecutar la consulta SQL.
     */
    @Override
    public Map<Integer, UnidadDidactica> cargarUnidadesDidacticas() {
        UnidadDidactica unidad;
        Map<Integer, UnidadDidactica> lista = new TreeMap<>();
        ResultSet resultado;

        try {
            // Abrimos la conexión con la base de datos
            openConnection();

            // Preparamos la consulta para obtener todos los enunciados
            declaracion = conexion.prepareStatement(CONSULTA_TODAS_UNIDADES);

            // Ejecutamos la consulta y obtenemos el resultado
            resultado = declaracion.executeQuery();

            // Recorremos los resultados de la consulta
            while (resultado.next()) {
                unidad = new UnidadDidactica();

                // Asignamos los valores del ResultSet al objeto Enunciado
                unidad.setidUnidad(resultado.getInt("ID"));
                unidad.setAcronimo(resultado.getString("ACRONIMO"));
                unidad.setTitulo(resultado.getString("TITULO"));
                unidad.setEvaluacion(resultado.getString("EVALUACION"));
                unidad.setDescripcion(resultado.getString("DESCRIPCION"));

                // Cargamos las unidades asociadas al enunciado
                unidad.setListaUnidades(consultaListaIds(unidad.getidUnidad(), "unidades"));

                // Añadimos el enunciado al Map
                lista.put(unidad.getidUnidad(), unidad);
            }

        } catch (SQLException evento) {
            // Manejamos la excepción en caso de fallo en la consulta
            evento.printStackTrace();
        } finally {
            // Cerramos la conexión con la base de datos
            closeConnection();
        }

        // Devolvemos el TreeMap con los enunciados
        return lista;
    }

    /**
     * Método que carga todos los enunciados disponibles en la base de datos.
     * Devuelve los enunciados en un mapa ordenado por su identificador.
     *
     * @return Un {@code TreeMap<Integer, Enunciado>} que contiene todos los
     * enunciados donde la clave es el ID del enunciado y el valor es el objeto
     * {@code Enunciado}.
     */
    @Override
    public TreeMap<Integer, Enunciado> cargarEnunciados() {
        Enunciado enunciado;
        TreeMap<Integer, Enunciado> lista = new TreeMap<>();
        ResultSet resultado;

        try {
            // Abrimos la conexión con la base de datos
            openConnection();

            // Preparamos la consulta para obtener todos los enunciados
            declaracion = conexion.prepareStatement(CONSULTA_TODOS_ENUNCIADOS);

            // Ejecutamos la consulta y obtenemos el resultado
            resultado = declaracion.executeQuery();

            // Recorremos los resultados de la consulta
            while (resultado.next()) {
                enunciado = new Enunciado();

                // Asignamos los valores del ResultSet al objeto Enunciado
                enunciado.setIdEnunciado(resultado.getInt("ID"));
                enunciado.setDescripcion(resultado.getString("DESCRIPCION"));
                enunciado.setNivel(Dificultad.convertirStringEnum(resultado.getString("NIVEL")));
                enunciado.setRuta(resultado.getString("RUTA"));

                // Cargamos las unidades asociadas al enunciado
                enunciado.setListaUnidades(consultaListaIds(enunciado.getIdEnunciado(), "unidades"));

                // Cargamos las convocatorias asociadas al enunciado
                enunciado.setListaConvocatorias(consultaListaIds(enunciado.getIdEnunciado(), "convocatorias"));

                // Añadimos el enunciado al TreeMap
                lista.put(enunciado.getIdEnunciado(), enunciado);
            }

        } catch (SQLException evento) {
            // Manejamos la excepción en caso de fallo en la consulta
            evento.printStackTrace();
        } finally {
            // Cerramos la conexión con la base de datos
            closeConnection();
        }

        // Devolvemos el TreeMap con los enunciados
        return lista;
    }

    /**
     * Método que carga todas las convocatorias de examen disponibles en la base
     * de datos. Devuelve un mapa ordenado por el identificador de la
     * convocatoria.
     *
     * @return Un {@code TreeMap<Integer, ConvocatoriaExamen>} que contiene
     * todas las convocatorias donde la clave es el ID de la convocatoria y el
     * valor es el objeto {@code ConvocatoriaExamen}.
     */
    @Override
    public Map<Integer, ConvocatoriaExamen> cargarConvocatoriasExamen() {
        ConvocatoriaExamen convocatoria;
        TreeMap<Integer, ConvocatoriaExamen> lista = new TreeMap<>();
        ResultSet resultado;

        try {
            // Abrimos la conexión con la base de datos
            openConnection();

            // Preparamos la consulta para obtener todas las convocatorias
            declaracion = conexion.prepareStatement(CONSULTA_TODAS_CONVOCATORIAS_EXAMEN);

            // Ejecutamos la consulta y obtenemos el resultado
            resultado = declaracion.executeQuery();

            // Recorremos los resultados de la consulta
            while (resultado.next()) {
                convocatoria = new ConvocatoriaExamen();

                // Asignamos los valores del ResultSet al objeto ConvocatoriaExamen
                convocatoria.setIdConvocatoria(resultado.getInt("ID"));
                convocatoria.setConvocatoria(resultado.getString("CONVOCATORIA"));
                convocatoria.setDescripcion(resultado.getString("DESCRIPCION"));
                convocatoria.setFecha(resultado.getDate("FECHA").toLocalDate());
                convocatoria.setCurso(resultado.getString("CURSO"));
                convocatoria.setIdEnunciado(resultado.getInt("ENUNCIADOID"));

                // Añadimos la convocatoria al TreeMap
                lista.put(convocatoria.getIdConvocatoria(), convocatoria);
            }

        } catch (SQLException evento) {
            // Manejamos la excepción en caso de fallo en la consulta
            evento.printStackTrace();
        } finally {
            // Cerramos la conexión con la base de datos
            closeConnection();
        }

        // Devolvemos el TreeMap con las convocatorias
        return lista;
    }

    /**
     * Método que consulta todas las convocatorias asociadas a un enunciado en
     * particular.
     *
     * @param idEnunciado El identificador del enunciado para el cual se desean
     * consultar las convocatorias.
     * @return Un {@code TreeMap<Integer, ConvocatoriaExamen>} que contiene
     * todas las convocatorias relacionadas con el enunciado especificado.
     */
    @Override
    public Map<Integer, ConvocatoriaExamen> consultaEnunciadoConvocatoria(Integer idEnunciado) {
        ConvocatoriaExamen convocatoria;
        TreeMap<Integer, ConvocatoriaExamen> lista = new TreeMap<>();
        ResultSet resultado;

        try {
            // Abrimos la conexión con la base de datos
            openConnection();

            // Preparamos la consulta para obtener las convocatorias del enunciado
            declaracion = conexion.prepareStatement(CONSULTA_CONVOCATORIAS_EXAMEN_DE_ENUNCIADO);

            // Asignamos el valor del parámetro idEnunciado
            declaracion.setInt(1, idEnunciado);

            // Ejecutamos la consulta y obtenemos el resultado
            resultado = declaracion.executeQuery();

            // Recorremos los resultados de la consulta
            while (resultado.next()) {
                convocatoria = new ConvocatoriaExamen();

                // Asignamos los valores del ResultSet al objeto ConvocatoriaExamen
                convocatoria.setIdConvocatoria(resultado.getInt("ID"));
                convocatoria.setConvocatoria(resultado.getString("CONVOCATORIA"));
                convocatoria.setDescripcion(resultado.getString("DESCRIPCION"));
                convocatoria.setFecha(resultado.getDate("FECHA").toLocalDate());
                convocatoria.setCurso(resultado.getString("CURSO"));
                convocatoria.setIdEnunciado(resultado.getInt("ENUNCIADOID"));

                // Añadimos la convocatoria al TreeMap
                lista.put(convocatoria.getIdConvocatoria(), convocatoria);
            }

        } catch (SQLException evento) {
            // Manejamos la excepción en caso de fallo en la consulta
            evento.printStackTrace();
        } finally {
            // Cerramos la conexión con la base de datos
            closeConnection();
        }

        // Devolvemos el TreeMap con las convocatorias
        return lista;
    }

    public ConvocatoriaExamen consultaConvocatoria(Integer idConvocatoria) {
        ConvocatoriaExamen convocatoria = null;
        TreeMap<Integer, ConvocatoriaExamen> lista = new TreeMap<>();
        ResultSet resultado;

        try {
            // Abrimos la conexión con la base de datos
            openConnection();

            // Preparamos la consulta para obtener las convocatorias del enunciado
            declaracion = conexion.prepareStatement(CONSULTA_CONVOCATORIA);

            // Asignamos el valor del parámetro idEnunciado
            declaracion.setInt(1, idConvocatoria);

            // Ejecutamos la consulta y obtenemos el resultado
            resultado = declaracion.executeQuery();

            // Recorremos los resultados de la consulta
            while (resultado.next()) {
                convocatoria = new ConvocatoriaExamen();

                // Asignamos los valores del ResultSet al objeto ConvocatoriaExamen
                convocatoria.setIdConvocatoria(resultado.getInt("ID"));
                convocatoria.setConvocatoria(resultado.getString("CONVOCATORIA"));
                convocatoria.setDescripcion(resultado.getString("DESCRIPCION"));
                convocatoria.setFecha(resultado.getDate("FECHA").toLocalDate());
                convocatoria.setCurso(resultado.getString("CURSO"));
                convocatoria.setIdEnunciado(resultado.getInt("ENUNCIADOID"));
            }

        } catch (SQLException evento) {
            // Manejamos la excepción en caso de fallo en la consulta
            evento.printStackTrace();
        } finally {
            // Cerramos la conexión con la base de datos
            closeConnection();
        }

        // Devolvemos el TreeMap con las convocatorias
        return convocatoria;
    }

    /**
     * Método que actualiza una convocatoria asignando un enunciado a ella.
     *
     * @param idEnunciado El identificador del enunciado que se quiere asociar a
     * la convocatoria.
     * @param idConvocatoria El identificador de la convocatoria que se va a
     * actualizar.
     */
    @Override
    public void agregarConvocatoriaEnunciado(Integer idEnunciado, Integer idConvocatoria) {
        try {
            // Abrimos la conexión con la base de datos
            openConnection();

            // Preparamos la sentencia SQL para actualizar la convocatoria
            declaracion = conexion.prepareStatement(UPDATE_CONVOCATORIA);

            // Asignamos los valores de los parámetros
            declaracion.setInt(1, idEnunciado);
            declaracion.setInt(2, idConvocatoria);

            // Ejecutamos la actualización
            declaracion.executeUpdate();
            modificarFichero(consultaConvocatoria(idConvocatoria));
        } catch (SQLException e) {
            // Manejamos la excepción en caso de fallo en la actualización
            e.printStackTrace();
        } finally {
            // Cerramos la conexión con la base de datos
            closeConnection();
        }
    }

    /**
     * Método que consulta una lista de identificadores relacionados con un id.
     * Dependiendo del tipo, consulta las unidades, convocatorias o enunciados
     * relacionados con dicho id.
     *
     * @param id El identificador de referencia.
     * @param tipo El tipo de consulta que se desea realizar, puede ser
     * 'unidades', 'enunciados' o 'convocatorias'.
     * @return Una lista de identificadores relacionados con el enunciado.
     */
    public List<Integer> consultaListaIds(Integer id, String tipo) {
        List<Integer> lista = new ArrayList<>();
        ResultSet resultado;

        try {
            // Abrimos la conexión con la base de datos
            openConnection();

            // Dependiendo del tipo, seleccionamos la consulta adecuada
            switch (tipo) {
                case "unidades":
                    declaracion = conexion.prepareStatement(CONSULTA_LISTA_IDS_UNIDADES_DE_ENUNCIADO);
                    break;
                case "enunciados":
                    declaracion = conexion.prepareStatement(CONSULTA_LISTA_IDS_ENUNCIADOS_DE_UNIDAD);
                    break;
                case "convocatorias":
                    declaracion = conexion.prepareStatement(CONSULTA_LISTA_IDS_CONVOCATORIAS_DE_ENUNCIADO);
                    break;
            }

            // Asignamos el valor del parámetro idEnunciado
            declaracion.setInt(1, id);

            // Ejecutamos la consulta y obtenemos el resultado
            resultado = declaracion.executeQuery();

            // Recorremos los resultados de la consulta y añadimos los IDs a la lista
            while (resultado.next()) {
                lista.add(resultado.getInt("ID"));
            }

        } catch (SQLException evento) {
            // Manejamos la excepción en caso de fallo en la consulta
            evento.printStackTrace();
        } finally {
            // Cerramos la conexión con la base de datos
            closeConnection();
        }

        // Devolvemos la lista de identificadores
        return lista;
    }

    @Override
    public Map<Integer, Enunciado> consultaEnunciadoUnidadDidactica(Integer idUnidad) {
        Enunciado enunciado;
        TreeMap<Integer, Enunciado> lista = new TreeMap<>();
        ResultSet resultado;

        try {
            // Abrimos la conexión con la base de datos
            openConnection();

            // Preparamos la consulta para obtener las convocatorias del enunciado
            declaracion = conexion.prepareStatement(CONSULTA_ENUNCIADO_DE_UNIDAD);

            // Asignamos el valor del parámetro idEnunciado
            declaracion.setInt(1, idUnidad);

            // Ejecutamos la consulta y obtenemos el resultado
            resultado = declaracion.executeQuery();

            // Recorremos los resultados de la consulta
            while (resultado.next()) {
                enunciado = new Enunciado();

                // Asignamos los valores del ResultSet al objeto Enunciado
                enunciado.setIdEnunciado(resultado.getInt("ID"));
                enunciado.setDescripcion(resultado.getString("DESCRIPCION"));
                enunciado.setNivel(Dificultad.convertirStringEnum(resultado.getString("NIVEL")));
                enunciado.setRuta(resultado.getString("RUTA"));

                // Cargamos las unidades asociadas al enunciado
                enunciado.setListaUnidades(consultaListaIds(enunciado.getIdEnunciado(), "unidades"));

                // Cargamos las convocatorias asociadas al enunciado
                enunciado.setListaConvocatorias(consultaListaIds(enunciado.getIdEnunciado(), "convocatorias"));

                // Añadimos el enunciado al TreeMap
                lista.put(enunciado.getIdEnunciado(), enunciado);
            }

        } catch (SQLException evento) {
            // Manejamos la excepción en caso de fallo en la consulta
            evento.printStackTrace();
        } finally {
            // Cerramos la conexión con la base de datos
            closeConnection();
        }

        // Devolvemos el TreeMap con las convocatorias
        return lista;
    }

    private void anadirAFichero(ConvocatoriaExamen nuevo) {

        ObjectOutputStream oos = null;

        try {
            if (fich.exists()) {
                oos = new MyObjectOutputStream(new FileOutputStream(fich, true));
            } else {
                oos = new ObjectOutputStream(new FileOutputStream(fich));
            }

            oos.writeObject(nuevo);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                oos.flush();
                oos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    private void modificarFichero(ConvocatoriaExamen convocatoria) {

        File fichAux = null;
        ConvocatoriaExamen nuevo = null;
        boolean encontrado = false;
        ObjectOutputStream oosAux = null;
        ObjectInputStream ois = null;

        if (fich.exists()) {

            try {
                ois = new ObjectInputStream(new FileInputStream(fich));
                oosAux = new ObjectOutputStream(new FileOutputStream(fichAux));
                while (true) {
                    nuevo = (ConvocatoriaExamen) ois.readObject();
                    if (Objects.equals(nuevo.getIdConvocatoria(), convocatoria.getIdConvocatoria())) {
                        encontrado = true;
                        nuevo = convocatoria;
                    }
                    oosAux.writeObject(nuevo);
                }

            } catch (EOFException e) {
                System.out.println("Fin de fichero.");
            } catch (FileNotFoundException | ClassNotFoundException e) {
                System.out.println("Fichero o clase no encontrada.");
            } catch (IOException e) {
                System.out.println("Error.");
            } finally {
                try {
                    ois.close();
                    oosAux.flush();
                    oosAux.close();
                    if (!encontrado) {
                        System.out.println("No encontrada la Convocatoria de Examen.");
                    } else {
                        fich = fichAux;
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        } else {

            anadirAFichero(convocatoria);
        }
    }

    @Override
    public void agregarUnidadEnunciado(Integer idenunciado, Integer idunidad) {
        try {
            openConnection();
            //Preparamos la insercion con la sql de arriba
            declaracion = conexion.prepareStatement(INSERCION_UNIDADENUNCIADO);
            declaracion.setInt(1, idunidad);
            declaracion.setInt(2, idenunciado);
            declaracion.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

    }
}
