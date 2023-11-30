import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author Iñaki Ramos Iturria
 * @author Cristiana Velislavova Tsekova
 * @version     1.0
 */
public class Porte {
    private boolean[][] huecos; //Matriz de hueco (indicando para filas y columnas si el hueco esta libre)
    private String id; //ID del porte "unico", 6 caracteres (dos primeros "PM" y los 4 siguientes aleatorios)
    private Nave nave;
    private PuertoEspacial origen;
    private int muelleOrigen;
    private Fecha salida;
    private PuertoEspacial destino;
    private int muelleDestino;
    private Fecha llegada;
    private double precio;
    private ListaEnvios listaEnvios;

    /**
     * TODO: Completa el constructo de la clase
     * 
     * @param id
     * @param nave
     * @param origen
     * @param muelleOrigen
     * @param salida
     * @param destino
     * @param muelleDestino
     * @param llegada
     * @param precio
     */
    public Porte(String id, Nave nave, PuertoEspacial origen, int muelleOrigen, Fecha salida, PuertoEspacial destino, int muelleDestino, Fecha llegada, double precio) {
        this.id = id;
        this.nave = nave;
        this.origen = origen;
        this.muelleOrigen = muelleOrigen;
        this.salida = salida;
        this.destino = destino;
        this.muelleDestino = muelleDestino;
        this.llegada = llegada;
        this.precio = precio;
    }

    public String getID() {
        return id;
    }
    public Nave getNave(){
        return nave;
    }
    public PuertoEspacial getOrigen() {
        return origen;
    }
    public int getMuelleOrigen() {
        return muelleOrigen;
    }
    public Fecha getSalida(){
        return salida;
    }
    public PuertoEspacial getDestino() {
        return destino;
    }
    public int getMuelleDestino() {
        return muelleDestino;
    }
    public Fecha getLlegada() {
        return llegada;
    }
    public double getPrecio() {
        return precio;
    }

    // TODO: Devuelve el número de huecos libres que hay en el porte
    public int numHuecosLibres() {
        int huecoLibres = 0;
        for (int i = 0; i < nave.getFilas() ; i++) {
            for (int j = 0; j < nave.getColumnas() ; j++) {
                if (huecos[i][j] == false){
                    huecoLibres ++;
                }
            }
        }
        return huecoLibres;
    }

    // TODO: ¿Están llenos todos los huecos?
    public boolean porteLleno() {
        boolean porteLleno = false;
        if (numHuecosLibres() == 0){
            porteLleno = true;
        }
        return porteLleno;
    }

    // TODO: ¿Está ocupado el hueco consultado?
    public boolean huecoOcupado(int fila, int columna) {
        return huecos[fila - 1][columna - 1]; // Se le quita un valor porque la matriz comienza en 0
    }

    public Envio buscarEnvio(String localizador) {
        return listaEnvios.buscarEnvio(localizador);
    }

    /**
     * TODO: Devuelve el objeto Envio que corresponde con una fila o columna,
     * @param fila
     * @param columna
     * @return el objeto Envio que corresponde, o null si está libre o se excede en el límite de fila y columna
     */
    public Envio buscarEnvio(int fila, int columna) {
        Envio localizado = listaEnvios.buscarEnvio(id, fila, columna);
        return localizado;
    }

    /**
     * TODO: Método que Si está desocupado el hueco que indica el envio, lo pone ocupado y devuelve true,
     *  si no devuelve false
     * @param envio
     * @return
     */
    public boolean ocuparHueco(Envio envio) {
        boolean ocupado = false;

        if (!huecoOcupado(envio.getFila(),envio.getColumna())){
            huecos[envio.getFila() - 1][envio.getColumna() - 1] = true;
            ocupado = true;
        }

        return ocupado;
    }

    /**
     * TODO: A través del localizador del envio, se desocupará el hueco
     * @param localizador
     * @return
     */
    public boolean desocuparHueco(String localizador) {
        boolean desocupado = false;
        Envio envio = listaEnvios.buscarEnvio(localizador);

        if (huecoOcupado(envio.getFila(), envio.getColumna())){
            huecos[envio.getFila() - 1][envio.getColumna() - 1] = false;
            desocupado = true;
        }

        return desocupado;
    }

    /**
     * TODO: Devuelve una cadena con información completa del porte
     * @return ejemplo del formato -> "Porte PM0066 de Gaia Galactic Terminal(GGT) M5 (01/01/2023 08:15:00) a
     *  Cidonia(CID) M1 (01/01/2024 11:00:05) en Planet Express One(EP-245732X) por 13424,56 SSD, huecos libres: 10"
     */
    public String toString() {
        return "Porte " + this.id + " de " + this.origen.getNombre() + "Terminal(" + this.origen.getCodigo() + ")" + "M" +
                this.muelleOrigen + "(" + this.salida + ") a " + this.destino.getNombre() + "(" + this.destino.getCodigo() +
                ")" + "M" + this.muelleDestino + " (" + this.llegada + ") en " + this.nave.getMarca() + " " + this.nave.getModelo()
                + "(" + this.nave.getMatricula() + ") por " + String.format("%2f", this.precio) + " SSD, huecos libres: " + numHuecosLibres();
    }


    /**
     * TODO: Devuelve una cadena con información abreviada del vuelo
     * @return ejemplo del formato -> "Porte PM0066 de GGT M5 (01/01/2023 08:15:00) a CID M1 (01/01/2024 11:00:05)"
     */
    public String toStringSimple() {
        return "Porte " + this.id + " de " + this.origen.getCodigo() + "M" + this.muelleOrigen + "(" + this.salida + ") a "
                + this.destino.getCodigo() + "M" + this.muelleDestino + "(" + this.llegada + ")";
    }

    /**
     * TODO: Devuelve true si el código origen, destino y fecha son los mismos que el porte
     * @param codigoOrigen
     * @param codigoDestino
     * @param fecha
     * @return
     */
    public boolean coincide(String codigoOrigen, String codigoDestino, Fecha fecha) {
        boolean correcto = false;

        if (origen.getCodigo().equals(codigoOrigen) && destino.getCodigo().equals(codigoDestino) && salida.coincide(fecha)){
            correcto = true;
        }
        return correcto;
    }

    /**
     * TODO: Muestra la matriz de huecos del porte, ejemplo:
     *    A  B  C  D  E  F
     *   1[ ][X][ ][ ][ ][ ]
     *   2[X][X][ ][ ][ ][ ]
     *   3[ ][ ][ ][X][X][X]
     *   4[ ][ ][ ][ ][ ][ ]
     *   5[ ][ ][X][ ][ ][ ]
     *   6[ ][ ][ ][ ][ ][ ]
     *   7[X][X][X][X][X][X]
     *   8[X][X][X][ ][ ][ ]
     *   9[X][X][X][ ][ ][X]
     *  10[X][X][X][ ][ ][ ]
     */
    public void imprimirMatrizHuecos() {
        System.out.print("  ");
        for (int i = 1; i <= nave.getColumnas(); i ++){
            System.out.printf("%c  ", 64 + i);
        }
        System.out.println();
        System.out.printf("  1");
        for (int j = 1; j <= nave.getColumnas(); j ++){
            if (huecoOcupado(1, j)){
                System.out.println("[X]");
            }else{
                System.out.println("[ ]");
            }
        }
        System.out.println();
        for (int k = 2; k < 6; k ++){
            System.out.printf(" %2d", k);
            for (int l = 1; l <= nave.getColumnas() ; l++) {
                if (huecoOcupado(2, l)){
                    System.out.println("[X]");
                }else{
                    System.out.println("[ ]");
                }
            }
            System.out.println();
        }
        for (int m = 6; m < nave.getColumnas(); m ++){
            System.out.printf(" %2d", m);
            for (int n = 1; n <= nave.getColumnas(); n ++){
                if (huecoOcupado(m, n)){
                    System.out.println("[x]");
                }else{
                    System.out.println("[ ]");
                }
            }
            System.out.println();
        }
    }

    /**
     * TODO: Devuelve true si ha podido escribir en un fichero la lista de envíos del porte, siguiendo las indicaciones
     *  del enunciado
     * @param fichero
     * @return
     */
    public boolean generarListaEnvios(String fichero) {
        boolean ficheroEnvios = false;
        Envio envio = null;
        PrintWriter printW = null;
        char[] letrasHuecos = { 'A', 'B', 'C','D','E','F'};

        try {
            printW = new PrintWriter(fichero);
            printW.write("--------------------------------------------------");
            printW.write("-------- Lista de envíos del porte " + this.id + " --------");
            printW.write("--------------------------------------------------");
            printW.write("Hueco\tCliente");

            for (int i = 0; i < nave.getFilas(); i ++){
                for (int j = 0; j < nave.getColumnas(); j ++){
                    envio = buscarEnvio(i + 1, j + 1);
                    printW.write(String.valueOf(i + 1) + ((char) (j + 65)) + "\t\t");

                    if (envio != null){
                        printW.write(envio.getCliente().getNombre() + "\t");

                    }

                    if ((i + 1) != listaEnvios.getOcupacion() - 1) printW.write("\n");
                }
            }
            ficheroEnvios = true;
        } catch (FileNotFoundException ex1) {
            System.out.println("Fichero " + fichero + " no encontrado.");
            ficheroEnvios = false;
        }catch(IOException ex2){
            System.out.println("Error de escritura en fichero " + fichero + ".");
        }finally {
            if (printW != null){
                printW.close();
            }
        }
        return ficheroEnvios;
    }

    /**
     * TODO: Genera un ID de porte. Este consistirá en una cadena de 6 caracteres, de los cuales los dos primeros
     *  serán PM y los 4 siguientes serán números aleatorios.
     *  NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.
     * @param rand
     * @return ejemplo -> "PM0123"
     */
    public static String generarID(Random rand) {
        return String.format("PM%04d", rand.nextInt(9999));
    }

    /**
     * TODO: Crea y devuelve un objeto Porte de los datos que selecciona el usuario de puertos espaciales
     *  y naves y la restricción de que no puede estar repetido el identificador, siguiendo las indicaciones
     *  del enunciado.
     *  La función solicita repetidamente los parametros hasta que sean correctos
     * @param teclado
     * @param rand
     * @param puertosEspaciales
     * @param naves
     * @param portes
     * @return
     */
    public static Porte altaPorte(Scanner teclado, Random rand, ListaPuertosEspaciales puertosEspaciales, ListaNaves naves, ListaPortes portes) {

        return null;
    }
}