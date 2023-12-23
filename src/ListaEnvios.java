import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.io.*;
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
            System.out.print(mensaje);
            String localizador = teclado.nextLine();
            if (envio == null){
                envio = buscarEnvio(localizador);
                System.out.println("\tLocalizador incorrecto");
            }
        }while (envio == null);
        return envio;
    }

    /**
     * TODO: Añade los Envios al final de un fichero CSV, SIN SOBREESCRIBIR la información
     * @param fichero donde se sobreescriben los datos de los envios
     * @return Devuelve true si se ha podido escribir la información y false si no se ha podido
     */
    public boolean aniadirEnviosCsv(String fichero) {
        FileWriter fW = null;
        boolean envioAniadido = false;

        try {
             fW = new FileWriter(fichero, true);
             for (int i = 0; i < ocupacion; i ++){
                 Envio infoEnvio = envios[i];
                 fW.write(infoEnvio.getLocalizador() + ";" + infoEnvio.getPorte().getID() + ";" + infoEnvio.getCliente().getEmail() + ";" + infoEnvio.getFila() + ";" + infoEnvio.getColumna() + ";" + infoEnvio.getPrecio());
                 if (i != ocupacion) fW.write("\n");
             }
        } catch (FileNotFoundException fileNotFoundException){
            System.out.println("Fichero " + fichero + " no encontrado.");
            envioAniadido = false;
        } catch (IOException ioException) {
            System.out.println("Error de escritura en fichero " + fichero + ".");
            envioAniadido = false;
        } finally {
            if(fW != null){
                try {
                    fW.close();
                } catch (IOException ioException) {
                    //throw new RuntimeException(ioException);
                }
            }
        }
        return envioAniadido;
    }

    /**
     * TODO: Lee los Envios del fichero CSV y los añade a las listas de sus respectivos Portes y Clientes
     * @param ficheroEnvios ; fichero donde se encuentran los envios
     * @param portes porte actual de los envios
     * @param clientes de los envios del fichero
     */
    public static void leerEnviosCsv(String ficheroEnvios, ListaPortes portes, ListaClientes clientes) {
        Scanner sc = null;
        String arrayEnvio[];
        Envio infoEnvios;
        Porte porte;
        Cliente cliente;

        try {
            sc = new Scanner(new FileReader(ficheroEnvios));
            do {
                arrayEnvio = sc.nextLine().split(";");
                porte = portes.buscarPorte(arrayEnvio[1]);
                cliente = clientes.buscarClienteEmail(arrayEnvio[2]);

                infoEnvios = new Envio(arrayEnvio[0], porte, cliente, Integer.parseInt(arrayEnvio[3]), Integer.parseInt(arrayEnvio[4]), Double.parseDouble(arrayEnvio[5]));

                porte.getListaEnvios().insertarEnvio(infoEnvios);
                cliente.getListaEnvios().insertarEnvio(infoEnvios);
                porte.ocuparHueco(infoEnvios);

                /*
                try {
            sc = new Scanner(new FileReader(ficheroEnvios));
            String line;
            String[] values;
            Envio envio;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                values = line.split(";");
                envio = new Envio(values[0], portes.buscarPorte(values[1]), clientes.buscarClienteEmail(values[2]),
                        Integer.parseInt(values[3]), Integer.parseInt(values[4]), Double.parseDouble(values[5]));
                portes.buscarPorte(envio.getPorte().getID()).ocuparHueco(envio);
                clientes.buscarClienteEmail(envio.getCliente().getEmail()).aniadirEnvio(envio);
            }
                */

            }while (sc.hasNext()); // Mientras tenga líneas de texto para leer
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Fichero " + ficheroEnvios + " no encontrado.");
        }
        catch (IOException IOException) {
            System.out.println("Error de lectura en fichero " + ficheroEnvios + ".");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }
}