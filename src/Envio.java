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
public class Envio {

    /**
     * Atributo que contiene el localizador de un envío
     */
    private String localizador;

    /**
     * Atributo que contiene el porte de donde se ha hecho un envío
     */
    private Porte porte;

    /**
     * Atributo que contiene el cliente que compro ha hecho un envío
     */
    private Cliente cliente;

    /**
     * Atributo que contiene la fila del hueco del envío
     */
    private int fila;

    /**
     * Atributo que contiene la columna del hueco del envío
     */
    private int columna;

    /**
     * Atributo que contiene el precio del envío
     */
    private double precio;

    /**
     * Constructor of the class; Crea un envío con los parámetros que recibe
     *
     * @param localizador del envío
     * @param porte correspondiente al envío
     * @param cliente que tiene que recibir el envío
     * @param fila del hueco del paquete a enviar
     * @param columna del hueco del paquete a enviar
     * @param precio precio del envío
     */
    public Envio(String localizador, Porte porte, Cliente cliente, int fila, int columna, double precio) {
        this.localizador = localizador;
        this.porte = porte;
        this.cliente = cliente;
        this.fila = fila;
        this.columna = columna;
        this.precio = precio;
    }

    /**
     * Getter del atributo localizador
     *
     * @return Devuelve el localizador de un envío
     */
    public String getLocalizador() {
        return this.localizador;
    }

    /**
     * Getter del atributo porte
     *
     * @return Devuelve el porte del envío
     */
    public Porte getPorte() {
        return this.porte;
    }

    /**
     * Getter del atributo pasajero
     *
     * @return Devuelve el cliente relacionado al envío
     */
    public Cliente getCliente() {
        return this.cliente;
    }

    /**
     * Getter del atributo fila
     *
     * @return Devuelve la fila del hueco del envío
     */
    public int getFila() {
        return this.fila;
    }

    /**
     * Getter del atributo columna
     *
     * @return Devuelve la columna del hueco del envío
     */
    public int getColumna() {
        return this.columna;
    }

    /**
     * Getter del para conseguir un hueco dependiendo de una fila y una columna
     *
     * @return Devuelve el hueco de un cliente dependiendo de la fila y la columna seleccionada
     */
    // TODO: Ejemplos: "1A" para el hueco con fila 1 y columna 1, "3D" para el hueco con fila 3 y columna 4
    public String getHueco() {
        char[] columna = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        int numcolumna = getColumna();
        return getFila() + "" + columna[numcolumna - 1];
    }

    /**
     * Getter del atributo precio
     *
     * @return Devuelve el precio de un envío
     */
    public double getPrecio() {
        return this.precio;
    }

    /**
     * Función que muestra la información completa del envío de una forma específica según el enunciado
     *
     * @return Devuelve un String con toda la información del envío
     */
    //TODO: Texto que debe generar: Envío PM1111AAAABBBBC para Porte PM0066 de GGT M5 (01/01/2023 08:15:00) a CID M1 (01/01/2024 11:00:05) en hueco 6C por 13424,56 SSD
    public String toString() {
        return "Envío " + localizador + " para Porte " + porte.getID() + " de " + porte.getOrigen().getCodigo() + " M"
                + porte.getMuelleOrigen() + " (" + porte.getSalida().toString() + ") a "
                + porte.getDestino().getCodigo() + " M" + porte.getMuelleDestino() + " (" + porte.getLlegada().toString() + ") en hueco " +
                getHueco() + " por " + String.format("%.2f", precio).replace(".", ",") + "SSD";
    }

    /**
     * TODO: Cancela este envío, eliminándolo de la lista de envíos del porte y del cliente correspondiente
     *
     * @return Devuelve true si se cancela el envío y false si este no se cancela
     */
    public boolean cancelar() {
        boolean cancelar = true;
        if (!porte.desocuparHueco(localizador)){
            cancelar = false;
        }
        if (!cliente.cancelarEnvio(localizador)){
            cancelar = false;
        }
        return cancelar;
    }

    /**
     * TODO: Método para imprimir la información de este envío en un fichero que respecta el formato descrito en el
     *  enunciado
     * @param fichero en el que se escribe la informaión del envío
     * @return Devuelve la información con el siguiente formato como ejemplo ->
     *     -----------------------------------------------------
     *     --------- Factura del envío PM1111AAAABBBBC ---------
     *     -----------------------------------------------------
     *     Porte: PM0066
     *     Origen: Gaia Galactic Terminal (GGT) M5
     *     Destino: Cidonia (CID) M1
     *     Salida: 01/01/2023 08:15:00
     *     Llegada: 01/01/2024 11:00:05
     *     Cliente: Zapp Brannigan, zapp.brannigan@dop.gov
     *     Hueco: 6C
     *     Precio: 13424,56 SSD
     */
    public boolean generarFactura(String fichero) {
        PrintWriter pw = null;
        boolean facturaGenerada = true;

        try {
            pw = new PrintWriter(fichero);
            pw.write("-----------------------------------------------------\n");
            pw.write("--------- Factura del envío " + localizador + " ---------\n");
            pw.write("-----------------------------------------------------\n");
            pw.write("Porte: " + porte.getID() + "\n");
            pw.write("Origen: " + porte.getOrigen().toStringSimple() + " (M" + porte.getMuelleOrigen() +")" + "\n");
            pw.write("Destino: " + porte.getDestino().toStringSimple() + " M" + porte.getMuelleDestino() + "\n");
            pw.write("Salida: " + porte.getSalida().toString() + "\n");
            pw.write("Llegada: " + porte.getLlegada().toString() + "\n");
            pw.write("Cliente: " + cliente.toString() + "\n");
            pw.write("Hueco: " + porte.getNave().getFilas() + porte.getNave().getColumnas());

            String precio = String.format("%.2f", getPrecio());
            pw.write("Precio: " + precio + "SSD");

        } catch (FileNotFoundException fileNotFoundException){
            System.out.println("Fichero " + fichero + " no encontrado.");
            facturaGenerada = false;
        }
        catch (IOException ioException){
            System.out.println("Error de escritura en fichero " + fichero + ".");
            facturaGenerada = false;
        } finally {
            if (pw != null){
                pw.close();
            }
        }

        //Si la factura se genera correctamente
        /*
        if (facturaGenerada){
            System.out.print("Factura de Envio " + localizador);
        }
        */
        return facturaGenerada;
    }

    /**
     *	TODO: Genera un localizador de envío. Este consistirá en una cadena de 15 caracteres, de los cuales los seis
	 *   primeros será el ID del porte asociado y los 9 siguientes serán letras mayúsculas aleatorias. Ejemplo: PM0123ABCD
     *   NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.
     * @param rand
     * @param idPorte
     * @return
     */
    public static String generarLocalizador(Random rand, String idPorte) {
        StringBuilder localizador = new StringBuilder(idPorte);
        Character posicion1 = (char) ('A' + rand.nextInt(26));
        Character posicion2 = (char) ('A' + rand.nextInt(26));
        Character posicion3 = (char) ('A' + rand.nextInt(26));
        Character posicion4 = (char) ('A' + rand.nextInt(26));
        return idPorte + posicion1 + posicion2 + posicion3 + posicion4;
        //return localizador.toString();
    }


    /**
     * TODO: Método para crear un nuevo envío para un porte y cliente específico, pidiendo por teclado los datos
     *  necesarios al usuario en el orden y con los textos indicados en los ejemplos de ejecución del enunciado
     *  La función solicita repetidamente los parámetros hasta que sean correctos
     * @param teclado
     * @param rand
     * @param porte
     * @param cliente
     * @return Envio para el porte y cliente especificados
     */
    public static Envio altaEnvio(Scanner teclado, Random rand, Porte porte, Cliente cliente) {
        /*
        String hueco;
        char columna;
        int codOrigen, codDestino;
        char letraColumna;
        */
        Envio nuevoEnvio = null;

        nuevoEnvio = new Envio(generarLocalizador(rand, porte.getID()), porte, cliente, nuevoEnvio.getFila(), nuevoEnvio.getColumna(), nuevoEnvio.getPrecio());

        //Ocupamos el hueco
        porte.ocuparHueco(nuevoEnvio);

        //Insertamos el hueco en las listas
        cliente.getListaEnvios().insertarEnvio(nuevoEnvio);
        porte.getListaEnvios().insertarEnvio(nuevoEnvio);

        return nuevoEnvio;
    }
}