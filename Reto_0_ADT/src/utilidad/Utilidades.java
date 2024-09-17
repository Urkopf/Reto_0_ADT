package utilidades;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utilidades {
  // Introducir cadenas de texto
  public static String introducirCadena(String cadena) {
    BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
    String cadenaString = "";
    try {
      System.out.println(cadena);
      cadenaString = lector.readLine();
    } catch (IOException e) {
      System.out.println("Error al introducir la cadena");
    }
    return cadenaString;
  }

  // Introducir enteros
  public static int introducirInteger(String cadena) {
    int entero = 0;
    String cadeString;
    boolean ok;
    do {
      ok = true;
      cadeString = introducirCadena(cadena);
      try {
        entero = Integer.parseInt(cadeString);
      } catch (Exception e) {
        System.out.println("No ha introducido un numero entero");
        ok = false;
      }
    } while (!ok);
    return entero;
  }

  // Introducir floats
  public static float introducirFloat(String cadena) {
    float decimal = 0;
    String cadeString;
    boolean ok;
    do {
      ok = true;
      cadeString = introducirCadena(cadena);
      try {
        decimal = Float.parseFloat(cadeString);
      } catch (Exception e) {
        System.out.println("No ha introducido un numero decimal");
        ok = false;
      }
    } while (!ok);
    return decimal;
  }

  // Introducir Boleanos
  public static boolean esBoolean(String message) {
    String respuesta;
    boolean ok;
    do {
      ok = true;
      respuesta = introducirCadena(message);
      if (!respuesta.equals("1") && !respuesta.equals("SI") && !respuesta.equals("NO")
              && !respuesta.equals("0") && !respuesta.equals("true") && !respuesta.equals("false")) {
        System.out.println("Respuesta incorrecta");
        ok = false;
      }
    } while (!ok);
    return !respuesta.equals("SI");
  }

  // Introducir Fechas
  public static LocalDate introducirFecha(String cadena) {
    LocalDate fecha = null;
    String cadeString;
    DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    boolean ok;
    do {
      ok = true;
      cadeString = introducirCadena(cadena);
      try {
        fecha = LocalDate.parse(cadeString, formateador);
      } catch (Exception e) {
        System.out.println("Formato de fecha incorrecto");
        ok=false;
      }
    } while (!ok);
    return fecha;
  }

  // Leer una respuesta
  public static boolean introducirRespuesta(String message) {
    String respu;
    do {
      respu = introducirCadena(message).toLowerCase();
    } while (!respu.equals("0") && !respu.equals("1") && !respu.equals("si") && !respu.equals("no")
        && !respu.equals("s") && !respu.equals("n") && !respu.equals("true") && !respu.equals("false"));
    if (respu.equals("1") || respu.equals("si") || respu.equals("s") || respu.equals("true")) {
      return true;
    } else {
      return false;
    }
  }

  // leer int entre un rango
  public static int introducirIntRango(int x, int y, String message) {
    int num = 0;
    boolean ok;
    do {
      try {
        ok = true;
        num = Integer.parseInt(introducirCadena(message));

      } catch (NumberFormatException e) {
        System.out.println("Hay que introducir numeros");
        ok = false;
        num = x;

      }
      if (num < x || num > y) {
        System.out.println("Dato fuera de rango, introduce entre " + x + " y " + y);
        ok = false;
      }
    } while (!ok);
    return num;
  }

  // leer float entre un rango
  public static float introducirFloatRango(float x, float y, String message) {
    float fNumero = 0;
    boolean ok;
    do {
      try {
        ok = true;
        fNumero = Float.parseFloat(introducirCadena(message));
      } catch (NumberFormatException e) {
        System.out.println("Hay que introducir numeros. Vuelve aintroducir: ");
        ok = false;
        fNumero = x;
      }
      if (fNumero < x || fNumero > y) {
        System.out.println("Dato fuera de rando. Introduce entre " + x + " y " + y);
        ok = false;
      }
    } while (!ok);
    return fNumero;
  }

  // leer caracter
  public static char introducirChar(String message) {
    boolean error = false;
    String letra;

    do {
      error = false;
      letra = introducirCadena(message);
      if (letra.length() != 1) {
        System.out.println("Error, introduce un caracter: ");
        error = true;
      }

    } while (error);
    return letra.charAt(0);
  }

  public static char introducirCharArray(char caracteres[], String message) {
    int i;
    boolean error = false;
    String letra;
    char aux = 0;

    do {
      error = false;
      letra = introducirCadena(message);
      if (letra.length() != 1) {
        System.out.println("Error, introduce un caracter: ");
        error = true;
      } else {
        aux = letra.toUpperCase().charAt(0);
        for (i = 0; i < caracteres.length; i++) {
          if (Character.toUpperCase(caracteres[i]) == Character.toUpperCase(aux)) {
            break;
          }
        }
        if (i == caracteres.length) {
          error = true;
          System.out.println("Error, el caracter introducido no es valido. ");
        }
      }
    } while (error);
    return aux;
  }

  // Devuelve el n mero de objetos de un fichero
  public static int calculoFichero(File fich) {
    int cont = 0;
    if (fich.exists()) {
      FileInputStream fis = null;
      ObjectInputStream ois = null;
      try {
        fis = new FileInputStream(fich);
        ois = new ObjectInputStream(fis);

        Object aux = ois.readObject();

        while (aux != null) {
          cont++;
          aux = ois.readObject();
        }
      } catch (EOFException e1) {
        System.out.println(" ");
      } catch (Exception e2) {
        e2.printStackTrace();
      }
      try {
        ois.close();
        fis.close();
      } catch (IOException e) {
        System.out.println("Error al cerrar los flujos");
      }
    }
    return cont;
  }




  
}
