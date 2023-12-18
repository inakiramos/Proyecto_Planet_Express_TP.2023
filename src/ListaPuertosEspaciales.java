import java.io.*;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author Iñaki Ramos Iturria
 * @author Cristiana Velislavova Tsekova
 * @version     1.0
 */
public class ListaPuertosEspaciales {

    /**
     *  Atributo que contiene el array donde se guardan los Puertos Espaciales
     */
    private PuertoEspacial[] lista;

    /**
     * Atributo que contiene la capacidad de la Lista de Puertos Espaciales
     */
    private int capacidad;

    /**
     * Atributo que contiene la ocupación del Puerto Espacial dentro de la lista
     */
    private int ocupacion;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad especifica la capacidad del Puerto Espacial
     */
    public ListaPuertosEspaciales(int capacidad) {
        this.ocupacion = 0;
        this.capacidad = capacidad;
        lista = new PuertoEspacial[capacidad];
    }

    /**
     * Getter del atributo ocupación
     *
     * @return Devuelve la cantidad de Puertos Espaciales que hay en la lista como una variable "ocupación"
     */
    // TODO: Devuelve el número de puertos espaciales que hay en la lista
    public int getOcupacion() {
        return this.ocupacion;
    }

    /**
     * Devuelve true si la Lista de Puertos Espaciales esta llena, si no, devuelve false
     * @return  Devuelve estaLlena
     */
    // TODO: ¿Está llena la lista?
    public boolean estaLlena() {
        boolean estaLlena = false;
        if (ocupacion == capacidad){
            estaLlena = true;
        }
        return estaLlena;
    }

    /**
     * Getter para conseguir el Puerto Espacial deseado
     *
     * @param i especifica la posición del Puerto Espacial dentro del array
     * @return Devuelve el Puerto Espacial que se encuentre en la psición recibida como parámetro
     */
	// TODO: Devuelve un puerto espacial dado un indice
    public PuertoEspacial getPuertoEspacial(int i) {
        return lista[i - 1];
    }

    /**
     * TODO: insertamos un Puerto espacial nuevo en la lista
     *
     * @param puertoEspacial es el Puerto Espacial que se quiere insertar en la lista
     * @return Devuelve true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarPuertoEspacial(PuertoEspacial puertoEspacial) {
        boolean insertado = false;
        if (!estaLlena()){
            lista[ocupacion] = puertoEspacial;
            ocupacion ++;
            insertado = true;
        }
        return insertado;
    }

    /**
     * TODO: Buscamos un Puerto espacial a partir del codigo pasado como parámetro
     *
     * @param codigo especifica que Puerto Espacial se esta considerando y comparando para ver si existe
     * @return Devuelve el Puerto Espacial que encontramos o null si no existe
     */
    public PuertoEspacial buscarPuertoEspacial(String codigo) {
        PuertoEspacial resul = null;

        for (int i = 0; i < ocupacion ; i++) {
            if (lista[i].getCodigo().equals(codigo)){
                resul = lista[i];
            }
        }
        return resul;
    }

    /**
     * TODO: Permite seleccionar un puerto espacial existente a partir de su código, usando el mensaje pasado como
     *  argumento para la solicitud y siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente el código hasta que se introduzca uno correcto
     *
     * @param teclado códigoGFSC que introduce el usuario
     * @param mensaje mensaje que se muestra por pantalla
     * @return Devuelve el Puerto Espacial, si este existe
     */
    public PuertoEspacial seleccionarPuertoEspacial(Scanner teclado, String mensaje) {
        PuertoEspacial puertoEspacial = null;
        String codigoGFSC;

        do {
            System.out.print(mensaje);
            codigoGFSC = teclado.nextLine().toUpperCase();
            if (buscarPuertoEspacial(codigoGFSC) == null){
                System.out.println("Código de puerto no encontrado.");
            }
        }while(buscarPuertoEspacial(codigoGFSC) != null);

        return buscarPuertoEspacial(codigoGFSC);
    }

    /**
     * TODO: Genera un fichero CSV con la lista de puertos espaciales, SOBREESCRIBIENDOLO
     *
     * @param nombre nombre del fichero en el que se guardan los datos
     * @return Devuelve true si se ha escrito correctamente en el fichero
     */
    public boolean escribirPuertosEspacialesCsv(String nombre) {
        PrintWriter pw = null;
        PuertoEspacial puertoEspacial;
        String infoPuertoEspacial;
        boolean ficheroEscrito = true;

        try {
            pw = new PrintWriter(nombre);
            for (int i = 0; i < ocupacion; i ++){
                //Cogemos el puerto espacial de la posición i, luego imprimimos
                puertoEspacial = lista[i];
                //info del puerto espacial
                infoPuertoEspacial = puertoEspacial.getNombre() + ";" + puertoEspacial.getCodigo() + ";" + puertoEspacial.getRadio() + ";" + puertoEspacial.getAzimut() + ";" + puertoEspacial.getPolar() + ";" + puertoEspacial.getMuelles();
                pw.write(infoPuertoEspacial);
                //Escribimos el salto de línea
                if (i != ocupacion - 1) pw.println();
            }

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Fichero " + nombre + " no encontrado.");
            ficheroEscrito = false;
        } catch(IOException ioException) {
            System.out.println("Error de escritura en fichero " + nombre + ".");
            ficheroEscrito = false;
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return ficheroEscrito;
    }

    /**
     * TODO: Genera una lista de PuertosEspaciales a partir del fichero CSV, usando el argumento como capacidad máxima
     *  de la lista
     *
     * @param fichero nombre del fichero por el cual se van a leer los datos
     * @param capacidad capacidad máxima que tendrá la lista
     * @return Genera una lista de los aeropuertos con los datos del fichero CSV
     */
    public static ListaPuertosEspaciales leerPuertosEspacialesCsv(String fichero, int capacidad) {
        ListaPuertosEspaciales listaPuertosEspaciales = new ListaPuertosEspaciales(capacidad);
        Scanner scanner = null;
        String arrayPuertosEspaciales[];

        try {
            scanner = new Scanner(new FileReader(fichero));
            do {
                arrayPuertosEspaciales = scanner.nextLine().split(";");
                // Posiciones del array del puerto espacial con sus atributos
                PuertoEspacial puertoEspacial = new PuertoEspacial(arrayPuertosEspaciales[0], arrayPuertosEspaciales[1], Double.parseDouble(arrayPuertosEspaciales[2]), Double.parseDouble(arrayPuertosEspaciales[3]), Double.parseDouble(arrayPuertosEspaciales[4]), Integer.parseInt(arrayPuertosEspaciales[5]));
                // Insertar el puerto espacial en la lista
                listaPuertosEspaciales.insertarPuertoEspacial(puertoEspacial);
            }while (scanner.hasNextLine());

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Fichero " + fichero + " no encontrado.");

        } catch (java.io.IOException IOException){
            System.out.println("Error de lectura en fichero " + fichero + ".");
        } finally {
            if (scanner != null){
                scanner.close();
            }
        }
        return listaPuertosEspaciales;
    }
}