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
     * Atributo que contiene la capacidad de la lista de portes
     */
    private int capacidad;

    /**
     * TODO: Constructor de la clase para inicializar la lista de portes a una capacidad determinada
     *
     * @param capacidad especifica la capacidad de la lista de portes, que contiene las naves
     */
    public ListaPortes(int capacidad) {
        this.ocupacion = 0;
        this.capacidad = capacidad;
        this.portes = new Porte[capacidad];
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
        if (ocupacion == capacidad){
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
        return portes[i];
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
        Porte resultado = null;
        for (int i = 0; i < ocupacion; i ++){
            if (portes[i].getID().equals(id))
            resultado = portes[i];
        }
        return resultado;
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
        ListaPortes portesBuscados = new ListaPortes(this.ocupacion);
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
        String porteID;
        boolean existe;
        do {
            porteID = Utilidades.leerCadena(teclado, "Seleccione un porte: ");
            existe = buscarPorte(porteID) != null;
            if (!Porte.correctoID(porteID) || !existe) {
                System.out.println("Porte no encontrado.");
            }
        } while (!Porte.correctoID(porteID) || existe);
        return buscarPorte(porteID);
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
                        + ";" + porte.getSalida().toString() + ";" + porte.getDestino().getCodigo() + ";" + porte.getMuelleDestino() + ";" +
                        porte.getLlegada().toString() + ";" + porte.getPrecio());
                //if (i != ocupacion - 1) pw.println();
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
        try (Scanner sc = new Scanner(new FileReader(fichero))) {
            String line;
            String[] values;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                values = line.split(";");
                listaPortes.insertarPorte(new Porte(values[0], naves.buscarNave(values[1]), puertosEspaciales.buscarPuertoEspacial(values[2]),
                        Integer.parseInt(values[3]), Fecha.fromString(values[4]), puertosEspaciales.buscarPuertoEspacial(values[5]),
                        Integer.parseInt(values[6]), Fecha.fromString(values[7]), Double.parseDouble(values[8])));
            }
        } catch (FileNotFoundException ex) {
            System.out.printf("Fichero %s no encontrado.\n", fichero);
        }
        return listaPortes;
    }
}