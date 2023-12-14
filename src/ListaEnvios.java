import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author Iñaki Ramos Iturria
 * @author Cristiana Velislavova Tsekova
 * @version     1.0
 */
public class ListaEnvios {

    /**
     * Atributo que contiene el array donde están los envios
     */
    private Envio[] envios;

    /**
     * Atributo que contiene la ocupación de la lista de envios
     */
    private int ocupacion;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad especifica la capacidad de la losta que contiene los envios
     */
    public ListaEnvios(int capacidad) {
        this.ocupacion = 0;
        envios = new Envio[capacidad];
		
    }

    /**
     * Getter del atributo ocupación
     *
     * @return devuelve la cantidad de envios que hay en el array envios como una variable ocupación
     */
    // TODO: Devuelve el número de envíos que hay en la lista
    public int getOcupacion() {
        return this.ocupacion;
    }

    /**
     * Comprueba si la lista de envios esta llena, es decir, si no se pueden generar más
     *
     * @return estaLlena es true si se ha alcanzado el máximo sino, devuelve false
     */
    // TODO: ¿Está llena la lista de envíos?
    public boolean estaLlena() {
        boolean estaLlena = false;
        if (ocupacion == envios.length){
            estaLlena = true;
        }
        return estaLlena;
    }

    /**
     * Getter  para conseguir un envío
     *
     * @param i variable que toma la posición del envío dentro del array
     * @return Devuelve la posición (i) de un envío dentro del array lista envios
     */
	//TODO: Devuelve el envio dado un indice
    public Envio getEnvio(int i) {
        return envios[i - 1];
    }

    /**
     * TODO: insertamos un nuevo envío en la lista
     * @param envio que se quiere insertar dentro de la lista envios
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarEnvio(Envio envio) {
        boolean insertado = false;
        if (!estaLlena()){
            envios[ocupacion] = envio;
            ocupacion ++;
            insertado = true;
        }
        return insertado;
    }

    /**
     * TODO: Buscamos el envio a partir del localizador pasado como parámetro
     * @param localizador que es único por cada envío generado
     * @return el envio que encontramos o null si no existe
     */
    public Envio buscarEnvio(String localizador) {
        Envio resul = null;
        for (int i = 0; i < ocupacion; i ++){
            if (envios[i].getLocalizador().equals(localizador)){
                resul = envios[i];
            }
        }
        return resul;
    }

    /**
     * TODO: Buscamos el envio a partir del idPorte, fila y columna pasados como parámetros
     * @param idPorte es el identificador de porte correspondiente a un envío
     * @param fila del hueco donde se encuentra el envío deseado
     * @param columna del hueco donde se encuentra el envío deseado
     * @return el envio que encontramos o null si no existe
     */
    public Envio buscarEnvio(String idPorte, int fila, int columna) {
        Envio resul = null;
        for (int i = 0; i < ocupacion; i ++){
            if (envios[i].getPorte().getNave().equals(idPorte)){
                if ((envios[i].getFila() == fila) && (envios[i].getColumna() == columna)){
                    resul = envios[i];
                }
            }
        }
        return resul;
    }

    /**
     * TODO: Eliminamos un envio a través del localizador pasado por parámetro
     * @param localizador que es único por envío
     * @return True si se ha borrado correctamente, false en cualquier otro caso
     */
    public boolean eliminarEnvio (String localizador) {
        boolean eliminado = false;
        for (int i = 0; i < ocupacion; i ++){
            if (envios[i].getLocalizador().equals(localizador)){
                for (int j = i; j < ocupacion - 1; j ++){
                    envios[j] = envios[j + 1];
                }
                eliminado = true;
            }
        }
        ocupacion --;
        return eliminado;
    }

    /**
     * TODO: Muestra por pantalla los Envios de la lista, con el formato que aparece en el enunciado
     */
    public void listarEnvios() {
        for (int i = 0; i < ocupacion; i ++){
            System.out.println(envios[i].toString());
        }
    }

    /**
     * TODO: Permite seleccionar un Envio existente a partir de su localizador, usando el mensaje pasado como argumento
     *  para la solicitud y siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente hasta que se introduzca un localizador correcto
     * @param teclado por donde el usuario escribe las opciones e informacón pedida
     * @param mensaje que se le muestra por pantalla al usuario
     * @return devuelve el envío seleccionado, si coincide con el localizador de envío
     * que pasa el usuario
     */
    public Envio seleccionarEnvio(Scanner teclado, String mensaje) {
        Envio envio = null;
        do {
            System.out.println(mensaje);
            String localizador = teclado.nextLine();
            envio = buscarEnvio(localizador);
            if (envio == null){
                System.out.println("Localizador no encontrado.");
            }
        }while (envio == null);
        return envio;
    }

    /**
     * TODO: Añade los Envios al final de un fichero CSV, SIN SOBREESCRIBIR la información
     * @param fichero
     * @return
     */
    public boolean aniadirEnviosCsv(String fichero) {
        PrintWriter pw = null;
        try {

            return true;
        } catch (Exception e) {
            return false;
        } finally {

        }
    }

    /**
     * TODO: Lee los Envios del fichero CSV y los añade a las listas de sus respectivos Portes y Clientes
     * @param ficheroEnvios
     * @param portes
     * @param clientes
     */
    public static void leerEnviosCsv(String ficheroEnvios, ListaPortes portes, ListaClientes clientes) {
        Scanner sc = null;
        try {

        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero de envíos");
        } finally {

        }
    }
}