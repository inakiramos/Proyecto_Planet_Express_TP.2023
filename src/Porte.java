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

    /**
     * Matriz que indica los huecos libres de la nave
     */
    private boolean[][] huecos; // Matriz de hueco (indicando para filas y columnas si el hueco esta libre)

    /**
     * ID del Porte
     */
    private String id; // ID del porte "unico", 6 caracteres (dos primeros "PM" y los 4 siguientes aleatorios)

    /**
     * Nave empleada en dicho porte
     */
    private Nave nave;

    /**
     * Puerto Espacial de Origen
     */
    private PuertoEspacial origen;

    /**
     * Muelle del puerto espacial, muelleOrigen
     */
    private int muelleOrigen;

    /**
     * Fecha de salida del porte
     */
    private Fecha salida;

    /**
     * Puerto Espacial de Destino del porte
     */
    private PuertoEspacial destino;

    /**
     * Muelle del puerto espacial, muelleDestino
     */
    private int muelleDestino;

    /**
     * Fecha de llegada del porte
     */
    private Fecha llegada;

    /**
     * Precio de un contenedor para dicho Porte, en Solar System Dollars (SSD)
     */
    private double precio;

    /**
     * Lista de contratados para dicho Porte, limitada por el número de huecos del avión
     */
    private ListaEnvios listaEnvios;

    /**
     * TODO: Completa el constructo de la clase
     *
     * @param id del porte
     * @param nave que se emplea en dicho porte
     * @param origen Puerto Espacial de origen del porte
     * @param muelleOrigen del puerto de origen
     * @param salida fecha de salida del porte
     * @param destino Puerto Espacial de destino
     * @param muelleDestino del puerto de destino (localidad final del porte)
     * @param llegada fecha de llegada del porte
     * @param precio de un contenedor para dicho porte
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
        this.huecos = new boolean[nave.getFilas()][nave.getColumnas()];

        for (int i = 0; i < nave.getFilas(); i++) {
            for (int k = 0; k < nave.getColumnas(); k++) {
                huecos[i][k] = false;
            }
        }
        listaEnvios = new ListaEnvios(nave.getFilas() * nave.getColumnas());
    }

    /**
     * Getter del atributo ID
     *
     * @return Devuelve el id del envío
     */
    public String getID() {
        return this.id;
    }

    /**
     * Getter del atributo nave
     *
     * @return Devuelve la nave que se utiliza para el envío
     */
    public Nave getNave() {
        return this.nave;
    }

    /**
     * Getter del atributo "origen" (Origen del envío)
     *
     * @return Devuelve el Puerto Espacial por el cual sale la nave
     */
    public PuertoEspacial getOrigen() {
        return this.origen;
    }

    /**
     * Getter del atributo "Muelle de origen"
     *
     * @return Devuelve el muelle de origen del envío
     */
    public int getMuelleOrigen() {
        return this.muelleOrigen;
    }

    /**
     * Getter del atributo fecha de salida del envío
     *
     * @return Devuelve la fecha de salida del envío
     */
    public Fecha getSalida() {
        return this.salida;
    }

    /**
     * Getter del atributo "destino" (destino del envío)
     *
     * @return Devuelve el Puerto Espacial de destino
     */
    public PuertoEspacial getDestino() {
        return this.destino;
    }

    /**
     * Getter del atributo "Muelle de destino"
     *
     * @return Devuelve la muelle de llegada del envío
     */
    public int getMuelleDestino() {
        return this.muelleDestino;
    }

    /**
     * Getter de la fecha de llegada
     *
     * @return Devuelve la fecha de llegada del envío
     */
    public Fecha getLlegada() {
        return this.llegada;
    }

    /**
     * Getter del precio del coste del contenedor del porte
     *
     * @return Devuelve el precio del envío del paquete
     */
    public double getPrecio() {
        return this.precio;
    }

    public ListaEnvios getListaEnvios(){
        return this.listaEnvios;
    }

    /**
     * Método que devuelve el numero dde hueccos libres en dicho porte. Recorre
     * la matriz de huecos y busca si existen huecos libres, si los ahi, devuelve
     * la cantidad
     *
     * @return Dvuelve el número de huecos libres disponibles para ocupar
     */
    // TODO: Devuelve el número de huecos libres que hay en el porte
    public int numHuecosLibres() {
        int huecoLibres = 0;
        for (int i = 0; i < nave.getFilas(); i++) {
            for (int j = 0; j < nave.getColumnas(); j++) {
                if (huecos[i][j] == false) {
                    huecoLibres++;
                }
            }
        }
        return huecoLibres;
    }

    /**
     * Indica si el Porte de dicho envío esta lleno
     *
     * @return Devuelve true si no quedan huecos libres y false si no quedan
     */
    // TODO: ¿Están llenos todos los huecos?
    public boolean porteLleno() {
        boolean porteLleno = false;
        if (numHuecosLibres() == 0) {
            porteLleno = true;
        }
        return porteLleno;
    }

    /**
     * Verifica si un hueco del envío (por filas y columnas) está ocupado
     *
     * @param fila fila del hueco
     * @param columna columna del hueco
     * @return Devuelve true si el hueco está ocupado y false si este mismo no lo esta
     */
    // TODO: ¿Está ocupado el hueco consultado?
    public boolean huecoOcupado(int fila, int columna) {
        return huecos[fila - 1][columna - 1]; // Se le quita un valor porque la matriz comienza en 0
    }

    /**
     * Busca un envío, entre los de la lista de clientes del vuelo mediante un localizador introducido por parámetro
     *
     * @param localizador localizador del envío a buscar
     * @return Devuelve el envío que coincida con el introducido por parámetro, si no existe el envío devuelve null
     */
    public Envio buscarEnvio(String localizador) {
        return listaEnvios.buscarEnvio(localizador);
    }

    /**
     * TODO: Devuelve el objeto Envio que corresponde con una fila o columna,
     *
     * @param fila del envío
     * @param columna del envío
     * @return devuelve el objeto envío que corresponde, o null si está libre o se excede en el límite de fila y columna
     */
    public Envio buscarEnvio(int fila, int columna) {
        Envio localizado = listaEnvios.buscarEnvio(id, fila, columna);
        return localizado;
    }

    /**
     * TODO: Método que Si está desocupado el hueco que indica el envio, lo pone ocupado y devuelve true,
     *  si no devuelve false
     *
     * @param envio de la nave (indica el hueco a ocupar)
     * @return Devuelve true si el hueco no estaba ocupado antes de invocar a la función
     */
    //Si está desocupado el asiento que indica el billete, lo pone ocupado y devuelve true, si no devuelve false
    public boolean ocuparHueco(Envio envio) {
        boolean ocupado = false;
        if (!huecoOcupado(envio.getFila(), envio.getColumna())) {
            huecos[envio.getFila() - 1][envio.getColumna() - 1] = true; // True de ocupado
            ocupado = true;
        }
        return ocupado;
    }

    /**
     * TODO: A través del localizador del envio, se desocupará el hueco
     *
     * @param localizador del hueco (indica el hueco a ocupar)
     * @return Devuelve true si se ha desocupado el asiento correctamente y false si no se ha desocupado
     */
    public boolean desocuparHueco(String localizador) {
        boolean desocupado = false;
        Envio envio = listaEnvios.buscarEnvio(localizador);
        if (huecoOcupado(envio.getFila(), envio.getColumna())) {
            huecos[envio.getFila() - 1][envio.getColumna() - 1] = false;
            desocupado = true;
        }
        return desocupado;
    }

    /**
     * TODO: Devuelve una cadena con información completa del porte
     *
     * @return Ejemplo del formato -> "Porte PM0066 de Gaia Galactic Terminal(GGT) M5 (01/01/2023 08:15:00) a
     * Cidonia(CID) M1 (01/01/2024 11:00:05) en Planet Express One(EP-245732X) por 13424,56 SSD, huecos libres: 10"
     */
    public String toString() {
        return "Porte " + this.id + " de " + this.origen.getNombre() + "Terminal(" + this.origen.getCodigo() + ")" + "M" +
                this.muelleOrigen + "(" + this.salida + ") a " + this.destino.getNombre() + "(" + this.destino.getCodigo() +
                ")" + "M" + this.muelleDestino + " (" + this.llegada + ") en " + this.nave.getMarca() + " " + this.nave.getModelo()
                + "(" + this.nave.getMatricula() + ") por " + String.format("%2f", this.precio) + " SSD, huecos libres: " + numHuecosLibres();
    }

    /**
     * TODO: Devuelve una cadena con información abreviada del vuelo
     *
     * @return ejemplo del formato -> "Porte PM0066 de GGT M5 (01/01/2023 08:15:00) a CID M1 (01/01/2024 11:00:05)"
     */
    public String toStringSimple() {
        return "Porte " + this.id + " de " + this.origen.getCodigo() + "M" + this.muelleOrigen + "(" + this.salida + ") a "
                + this.destino.getCodigo() + "M" + this.muelleDestino + "(" + this.llegada + ")";
    }

    /**
     * TODO: Devuelve true si el código origen, destino y fecha son los mismos que el porte
     *
     * @param codigoOrigen código del porte de origen (comprobar)
     * @param codigoDestino código del porte de destino (comprobar)
     * @param fecha a comprobar
     * @return Devuelve true si el código de origen, destino y fecha son correctos y cinciden con los que se pasan por parámetro
     */
    public boolean coincide(String codigoOrigen, String codigoDestino, Fecha fecha) {
        boolean correcto = false;

        if (origen.getCodigo().equals(codigoOrigen) && destino.getCodigo().equals(codigoDestino) && salida.coincide(fecha)) {
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
        for (int i = 1; i <= nave.getColumnas(); i++) {
            System.out.printf("%c  ", 64 + i);
        }
        System.out.println();
        System.out.printf("  1");
        for (int j = 1; j <= nave.getColumnas(); j++) {
            if (huecoOcupado(1, j)) {
                System.out.println("[X]");
            } else {
                System.out.println("[ ]");
            }
        }
        System.out.println();
        for (int k = 2; k < 6; k++) {
            System.out.printf(" %2d", k);
            for (int l = 1; l <= nave.getColumnas(); l++) {
                if (huecoOcupado(2, l)) {
                    System.out.println("[X]");
                } else {
                    System.out.println("[ ]");
                }
            }
            System.out.println();
        }
        for (int m = 6; m < nave.getColumnas(); m++) {
            System.out.printf(" %2d", m);
            for (int n = 1; n <= nave.getColumnas(); n++) {
                if (huecoOcupado(m, n)) {
                    System.out.println("[x]");
                } else {
                    System.out.println("[ ]");
                }
            }
            System.out.println();
        }
    }

    /**
     * TODO: Devuelve true si ha podido escribir en un fichero la lista de envíos del porte, siguiendo las indicaciones
     *  del enunciado
     *
     * @param fichero donde se quiere generar la lista con los envíos
     * @return Devuelve true si se ha podido escribir en el fichero la lista de envíos del porte
     */
    public boolean generarListaEnvios(String fichero) {
        boolean ficheroEnvios = false;
        Envio envio = null;
        PrintWriter printW = null;
        char[] letrasHuecos = {'A', 'B', 'C', 'D', 'E', 'F'};

        try {
            printW = new PrintWriter(fichero);
            printW.write("--------------------------------------------------");
            printW.write("-------- Lista de envíos del porte " + this.id + " --------");
            printW.write("--------------------------------------------------");
            printW.write("Hueco\tCliente");

            for (int i = 0; i < nave.getFilas(); i++) {
                for (int j = 0; j < nave.getColumnas(); j++) {
                    envio = buscarEnvio(i + 1, j + 1);
                    printW.write(String.valueOf(i + 1) + ((char) (j + 65)) + "\t\t");

                    if (envio != null) {
                        printW.write(envio.getCliente().getNombre() + "\t");

                    }

                    if ((i + 1) != listaEnvios.getOcupacion() - 1) printW.write("\n");
                }
            }
            ficheroEnvios = true;
        } catch (FileNotFoundException ex1) {
            System.out.println("Fichero " + fichero + " no encontrado.");
            ficheroEnvios = false;
        } catch (IOException ex2) {
            System.out.println("Error de escritura en fichero " + fichero + ".");
        } finally {
            if (printW != null) {
                printW.close();
            }
        }
        return ficheroEnvios;
    }

    /**
     * TODO: Genera un ID de porte. Este consistirá en una cadena de 6 caracteres, de los cuales los dos primeros
     *  serán PM y los 4 siguientes serán números aleatorios.
     *  NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.
     *
     * @param rand parámetro aleatorio
     * @return Ejemplo -> "PM0123" (ID´s aleatorios)
     */
    public static String generarID(Random rand) {
        return String.format("PM%04d", rand.nextInt(9999));
    }

    /**
     * TODO: Crea y devuelve un objeto Porte de los datos que selecciona el usuario de puertos espaciales
     *  y naves y la restricción de que no puede estar repetido el identificador, siguiendo las indicaciones
     *  del enunciado.
     *  La función solicita repetidamente los parametros hasta que sean correctos
     *
     * @param teclado pasa por parámetro el teclado para no tener que declararlo
     * @param rand número aleatorio para generar el ID del porte
     * @param puertosEspaciales lista de Puertos Espaciales entre los que puede ir la nave
     * @param naves lista de naves que pueden usarse para envíar paquetes
     * @param portes lista de portes que ya existen para no generar y que no existan dos objetos con el mismo ID
     * @return Devuelve el envío que se acaba de crear
     */
    public static Porte altaPorte(Scanner teclado, Random rand, ListaPuertosEspaciales puertosEspaciales, ListaNaves naves, ListaPortes portes) {
        boolean envioCorrecto = false;
        boolean fechaCorrecta;
        Fecha fechaLlegada;
        Fecha fechaSalida;

        //Información de los Puertos Espaciales
        PuertoEspacial origen = puertosEspaciales.seleccionarPuertoEspacial(teclado, "Ingrese código de puerto Origen: ");
        String primerMensaje = "Ingrese el muelle de Origen (1 - " + origen.getMuelles() + "): ";
        int muelleOrigen = Utilidades.leerNumero(teclado, primerMensaje, 1, origen.getMuelles());

        PuertoEspacial destino = puertosEspaciales.seleccionarPuertoEspacial(teclado, "Ingrese código de puerto Destino: ");
        String segundoMensaje = "Ingrese el muelle de Destino (1 - " + destino.getMuelles() + "): ";
        int muelleDestino = Utilidades.leerNumero(teclado, segundoMensaje, 1, destino.getMuelles());

        //Información de la nave
        double distancia = origen.distancia(destino);
        Nave nave = naves.seleccionarNave(teclado, "Ingrese matrícula de la nave: ", distancia);

        //Comprobamos la fecha
        do {
            fechaSalida = Utilidades.leerFechaHora(teclado, "Introduzca la fecha de salida: ");
            fechaLlegada = Utilidades.leerFechaHora(teclado, "Introduzca la fecha de llegada: ");

            if (fechaSalida.anterior(fechaLlegada))
                fechaCorrecta = true;
            else {
                System.out.println("Llegada debe ser posterior a salida.");
                fechaCorrecta = false;
            }
        } while (!fechaCorrecta);

        //Información de ID
        String porteID;
        do {
            porteID = generarID(rand);
            for (int i = 0; i < portes.getOcupacion(); i++) {
                if (porteID.equals(portes.getPorte(i + 1).getID())) envioCorrecto = true;
            }
        } while (envioCorrecto);

        double precio;
        precio = Utilidades.leerNumero(teclado, "Ingrese precio del porte:", 0, (double) 999);
        Porte porteNuevo = new Porte(porteID, nave, origen, muelleOrigen,fechaSalida, destino, muelleDestino, fechaLlegada, precio);

        System.out.println("Porte " + porteID + " creado correctamente");
        portes.insertarPorte(porteNuevo);
        return porteNuevo;
    }
}