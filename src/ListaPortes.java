import java.io.*;
import java.util.Objects;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author Iñaki Ramos Iturria
 * @author Cristiana Velislavova Tsekova
 * @version     1.0
 */
public class ListaPortes {

    /**
     * Atributo que contiene el array donde están los portes
     */
    private Porte[] portes;

    /**
     * Atributo que contiene la ocupación de un porte dentro de la lista
     */
    private int ocupacion;

    /**
     * TODO: Constructor de la clase para inicializar la lista de portes a una capacidad determinada
     *
     * @param capacidad especifica la capacidad de la lista de portes, que contiene las naves
     */
    public ListaPortes(int capacidad) {
        this.ocupacion = 0;
        portes = new Porte[capacidad];
    }

    /**
     * Getter del atributo ocupación
     *
     * @return Dvuelve la cantidad de portes que hay en "portes" como una variable ocupación
     */
    // TODO: Devuelve el número de portes que hay en la lista
    public int getOcupacion() {
        return ocupacion;
    }

    /**
     * Devuelve verdadero si la lista de portes está llena
     *
     * @return estaLlena
     */
    // TODO: ¿Está llena la lista?
    public boolean estaLlena() {
        boolean estaLlena = false;
        if (ocupacion == portes.length){
            estaLlena = true;
        }
        return estaLlena;
    }

    /**
     * Getter para conseguir un porte
     *
     * @param i variable que toma la posición del porte dentro del array
     * @return Devuelve la posición (i) de un porte dentro del array porte
     */
	//TODO: devuelve un porte dado un indice
    public Porte getPorte(int i) {
        return portes[i - 1];
    }

    /**
     * TODO: Devuelve true si puede insertar el porte
     * @param porte que se quiere insertar en la lista
     * @return Devuelve false en caso de estar llena la lista o de error
     */
    public boolean insertarPorte(Porte porte) {
        boolean insertado = false;
        if (!estaLlena()){
            portes[ocupacion] = porte;
            ocupacion ++;
            insertado = true;
        }
        return insertado;
    }

    /**
     * TODO: Devuelve el objeto Porte que tenga el identificador igual al parámetro id
     * @param id código que idetifica al porte que se esta buscando
     * @return Devuelve el objeto Porte que encontramos o null si no existe
     */
    public Porte buscarPorte(String id) {
        Porte resul = null;

        for (int i = 0; i < portes.length; i++) {
            if (Objects.equals(portes[i].getID(), id)){
                resul = portes[i];
            }
        }
        return resul;
    }

    /**
     * TODO: Devuelve un nuevo objeto ListaPortes conteniendo los Portes que vayan de un puerto espacial a otro
     *  en una determinada fecha
     * @param codigoOrigen código que depende del Puerto Espacial de salida de un porte
     * @param codigoDestino código que depende del Puerto Espacial de destino de un porte
     * @param fecha fecha que esta determinada por la salida de un porte específico
     * @return Devuelve el porte buscado mediante los parámetros introducidos y si este tiene las caracteristicas introducidas lo coge
     */
    public ListaPortes buscarPortes(String codigoOrigen, String codigoDestino, Fecha fecha) {
        ListaPortes portesBuscados = new ListaPortes(ocupacion);
        for (int i = 0; i < ocupacion; i ++){
            if ((codigoOrigen.equals(portes[i].getOrigen().getCodigo())) && (codigoDestino.equals(portes[i].getDestino().getCodigo())) && (fecha.equals(portes[i].getSalida()))){
                portesBuscados.insertarPorte(portes[i]);
            }
        }
        return portesBuscados;
    }

    /**
     * TODO: Muestra por pantalla los Portes siguiendo el formato de los ejemplos del enunciado
     */
    public void listarPortes() {
        for (int i = 0; i < ocupacion; i++) {
            if (portes[i] != null){
                System.out.println("\n" + portes[i].toString());
            }
        }
    }

    /**
     * TODO: Permite seleccionar un Porte existente a partir de su ID, usando el mensaje pasado como argumento para
     *  la solicitud y siguiendo el orden y los textos mostrados en el enunciado, y usando la cadena cancelar para
     *  salir devolviendo null. La función solicita repetidamente hasta que se introduzca un ID correcto
     * @param teclado el usuario introduce el ID del porte que desea
     * @param mensaje mensaje que se muestra por pantalla
     * @param cancelar cancela la búsqueda del porte
     * @return Devuelve el porte seleccionado y si cumple los requisitos (exista y que tenga ID correspondiente)
     */
    public Porte seleccionarPorte(Scanner teclado, String mensaje, String cancelar) {
        listarPortes();
        Porte porte = null;
        boolean pararDePreguntar = false;

        do {
            System.out.println(mensaje);
            String pantalla = teclado.nextLine();

            if (pantalla.equalsIgnoreCase(cancelar)) {
                pararDePreguntar = true;
            }
            /*
            if (pantalla.equalsIgnoreCase(cancelar)) pararDePreguntar = true;
            else {
                porte =  buscarPorte(pantalla);
                if (porte == null) System.out.println("Porte no encontrado.");
                else pararDePreguntar = true;
            }
            */
        } while(!pararDePreguntar);

        return porte;
    }

    /**
     * TODO: Ha de escribir la lista de Portes en la ruta y nombre del fichero pasado como parámetro.
     *  Si existe el fichero, SE SOBREESCRIBE, si no existe se crea.
     * @param fichero nombre del fichero en el que se quardan los datos
     * @return Devuelve true si se ha logrado escribir y guardar los datos
     */
    public boolean escribirPortesCsv(String fichero) {
        PrintWriter pw = null;
        Porte porte;
        boolean ficheroEscrito = true;

        try {
            pw = new PrintWriter(new FileWriter(fichero, false));
            for (int i = 0; i < ocupacion; i++) {
                porte = portes[i];
                pw.print(porte.getID() + ";" + porte.getNave().getMatricula() + ";" + porte.getOrigen().getCodigo() + ";" + porte.getMuelleOrigen()
                        + ";" + porte.getSalida().toString() + ";" + porte.getDestino().getCodigo() + ";" + porte.getMuelleDestino() + ";" + porte.getLlegada().toString()
                        + ";" + porte.getPrecio());
                if (i != ocupacion - 1) pw.println();
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Fichero " + fichero + " no encontrado.");
            ficheroEscrito = false;
        } catch (IOException ioException) {
            System.out.println("Error de escritura en fichero " + fichero + ".");
            ficheroEscrito = false;
        }finally {
            if (pw != null) {
                pw.close();
            }
        }
        return ficheroEscrito;
    }

    /**
     * TODO: Genera una lista de Portes a partir del fichero CSV, usando los límites especificados como argumentos para
     *  la capacidad de la lista
     * @param fichero nombre del fichero del que se leen los portes
     * @param capacidad capacidad máxima para la lista de vuelos devuelta
     * @param puertosEspaciales lista de Puertos Espaciales de la cual seleccionr las naves para el porte
     * @param naves lista de naves de la cual seleccionar naves para el porte
     * @return Devuelve la lista de portes leída
     */
    public static ListaPortes leerPortesCsv(String fichero, int capacidad, ListaPuertosEspaciales puertosEspaciales, ListaNaves naves) {
        ListaPortes listaPortes = new ListaPortes(capacidad);
        Scanner sc = null;
        Porte porte;
        String arrayPortes[];

        try {
            sc = new Scanner(new FileReader(fichero));
            do {
                arrayPortes = sc.nextLine().split(";");
                porte = new Porte(arrayPortes[0], naves.buscarNave(arrayPortes[1]), puertosEspaciales.buscarPuertoEspacial(arrayPortes[2]), Integer.parseInt(arrayPortes[3]),
                        Fecha.fromString(arrayPortes[4]), puertosEspaciales.buscarPuertoEspacial(arrayPortes[5]), Integer.parseInt(arrayPortes[6]), Fecha.fromString(arrayPortes[7]),
                        Double.parseDouble(arrayPortes[8]));
                listaPortes.insertarPorte(porte);
            } while (sc.hasNext());
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Fichero " + fichero + " no encontrado.");
        } catch (IOException IOException) {
            System.out.println("Error de escritura en fichero " + fichero + ".");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        return listaPortes;
    }
}