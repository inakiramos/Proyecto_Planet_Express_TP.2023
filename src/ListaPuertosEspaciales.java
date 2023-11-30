import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author Iñaki Ramos Iturria
 * @author Cristiana Velislavova Tsekova
 * @version     1.0
 */
public class ListaPuertosEspaciales {
    private PuertoEspacial[] lista;
    private int capacidad;
    private int ocupacion;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     */
    public ListaPuertosEspaciales(int capacidad) {
        this.ocupacion = 0;
        this.capacidad = capacidad;
        lista = new PuertoEspacial[capacidad];
    }

    // TODO: Devuelve el número de puertos espaciales que hay en la lista
    public int getOcupacion() {
        return this.ocupacion;
    }

    // TODO: ¿Está llena la lista?
    public boolean estaLlena() {
        boolean estaLlena = false;

        if (ocupacion == capacidad){
            estaLlena = true;
        }
        return estaLlena;
    }

	// TODO: Devuelve un puerto espacial dado un indice
    public PuertoEspacial getPuertoEspacial(int i) {
        return lista[i - 1];
    }

    /**
     * TODO: insertamos un Puerto espacial nuevo en la lista
     * @param puertoEspacial
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
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
     * @param codigo
     * @return Puerto espacial que encontramos o null si no existe
     */
    public PuertoEspacial buscarPuertoEspacial(String codigo) {
        PuertoEspacial resul = null;

        for (int i = 0; i < lista.length ; i++) {
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
     * @param teclado
     * @param mensaje
     * @return
     */
    public PuertoEspacial seleccionarPuertoEspacial(Scanner teclado, String mensaje) {
        PuertoEspacial puertoEspacial = null;
        String codigoGFSC;

        do {
            System.out.println(mensaje);
            codigoGFSC = teclado.nextLine().toUpperCase();
            if (buscarPuertoEspacial(codigoGFSC)){
                System.out.println("Código del puerto espacial no encontrado.");
            }
        }while(buscarPuertoEspacial(codigoGFSC));

        return buscarPuertoEspacial(codigoGFSC);
    }

    /**
     * TODO: Genera un fichero CSV con la lista de puertos espaciales, SOBREESCRIBIENDOLO
     * @param nombre
     * @return
     */
    public boolean escribirPuertosEspacialesCsv(String nombre) {
        PrintWriter pw = null;
        try {

            return true;
        } catch (Exception e) {
            return false;
        } finally {

        }
    }


    /**
     * TODO: Genera una lista de PuertosEspaciales a partir del fichero CSV, usando el argumento como capacidad máxima
     *  de la lista
     * @param fichero
     * @param capacidad
     * @return
     */
    public static ListaPuertosEspaciales leerPuertosEspacialesCsv(String fichero, int capacidad) {
        ListaPuertosEspaciales listaPuertosEspaciales = new ListaPuertosEspaciales(capacidad);
        Scanner scanner = null;
        String arrayPuertosEspaciales[];

        try {
            scanner = new Scanner(new FileReader(fichero));
            do {
                arrayPuertosEspaciales = scanner.nextLine().split(";");
                // Posiciones del array en el aeropuerto
                PuertoEspacial puertoEspacial = new PuertoEspacial(arrayPuertosEspaciales[0], arrayPuertosEspaciales[1], Double.parseDouble(arrayPuertosEspaciales[2]), Double.parseDouble(arrayPuertosEspaciales[3]), Double.parseDouble(arrayPuertosEspaciales[4]), Integer.parseInt(arrayPuertosEspaciales[5]));
                // Insertar el Puerto Espacial en la lista
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