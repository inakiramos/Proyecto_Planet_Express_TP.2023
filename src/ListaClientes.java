import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 * ListaClientes es una clase que encapsula las variables enteras usadas para definir los clientes,
 * así como también contiene funciones bara buscar, seleccionar e insertar estos mismos clientes en el array
 * de nombre clientes
 *  También escribe un fichero.csv con los datos de cada cliente
 *
 * @author Iñaki Ramos Iturria
 * @author Cristiana Velislavova Tsekova
 * @version     1.0
 */
public class ListaClientes {

    /**
     * Atributo que contiene el array donde se guardan los clientes
     */
    private Cliente[] clientes;

    /**
     * Atributo que devuelve la capacidad de la Lista de clientes
     */
    private int capacidad;

    /**
     * Atributo que contiene la ocupación de los clientes dentro de la lista
     */
    private int ocupacion;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad especifica la capacidad de la lista de clientes
     */
    public ListaClientes(int capacidad) {
        this.ocupacion = 0;
        this.capacidad = capacidad;
        this.clientes = new Cliente[capacidad];
    }

    /**
     * Getter del atributo ocupación
     * @return Devuelve la cantidad de clientes que hay en la lista de clientes como una variable ocupación
     */
    // TODO: Devuelve el número de clientes que hay en la lista de clientes
    public int getOcupacion() {
        return this.ocupacion;
    }

    /**
     * Devuelve verdadero si la lista de clientes esta llena, si no, devuelve falso
     *
     * @return estaLlena
     */
    // TODO: ¿Está llena la lista de clientes?
    public boolean estaLlena() {
        boolean estaLlena = false;
        if (capacidad == ocupacion){
            estaLlena = true;
        }
        return estaLlena;
    }

    /**
     * Getter para conseguir al cliente que se encuentra en la posición i del array clientes
     *
     * @param i especifica la posición del cliente dentro del array
     * @return Devuelve el cliente que se encuentra en la posición recibida por el parámetro
     */
	// TODO: Devuelve el cliente dada el indice
    public Cliente getCliente(int i) {
        return clientes[i];
    }

    /**
     * Inserta un cliente en el array clientes
     *
     * @param cliente que se quiere insertar en la lista
     * @return Devuelve true si se ha insertado al cliente o false si no se ha añadido
     */
    // TODO: Inserta el cliente en la lista de clientes
    public boolean insertarCliente(Cliente cliente) {
        boolean insertado = false;
        if (!estaLlena()){
            clientes[ocupacion] = cliente;
            ocupacion ++;
            insertado = true;
        }
        return insertado;
    }

    /**
     * Función que busca a un cliente por medio de su email pasado por parámetro
     *
     * @param email que esta asociado a un cliente
     * @return Devuelve el cliente que coincide con el email
     */
    // TODO: Devuelve el cliente que coincida con el email, o null en caso de no encontrarlo
    public Cliente buscarClienteEmail(String email) {
        Cliente resultado = null;
        boolean encontrado = false;

        for (int i = 0; i < ocupacion; i ++){
            if (Objects.equals(clientes[i].getEmail(), email)){
                resultado = clientes[i];
            }
        }
        return resultado;
    }

    /**
     * TODO: Método para seleccionar un Cliente existente a partir de su email, usando el mensaje pasado como argumento
     *  para la solicitud y, siguiendo el orden y los textos mostrados en el enunciado.
     *  La función debe solicitar repetidamente hasta que se introduzca un email correcto
     *
     * @param teclado el usuario introduce el email del cliente que desea seleccionar
     * @param mensaje que se muestra por pantalla
     * @return Devuelve el cliente seleccionado que coincide con el email
     */
    public Cliente seleccionarCliente(Scanner teclado, String mensaje) {
        String emailCorrecto;
        do {
            emailCorrecto = Utilidades.leerCadena(teclado, mensaje);

            if(buscarClienteEmail(emailCorrecto) == null){
                System.out.println("Email no encontrado.");
            }
        }while(buscarClienteEmail(emailCorrecto) == null);

        return buscarClienteEmail(emailCorrecto);
    }

    /**
     * TODO: Método para guardar la lista de clientes en un fichero .csv, sobreescribiendo la información del mismo
     *  fichero
     *
     * @param fichero sobre el que se sobreescriben los datos de los clientes
     * @return Devuelve true si se ha sobreescrito el fichero y false si no se ha podido
     */
    public boolean escribirClientesCsv(String fichero) {
        PrintWriter pw = null;
        boolean ficheroEscrito = true;

        try {
            pw = new PrintWriter(new File(fichero));
            for (int i = 0; i < ocupacion; i ++){
                Cliente cliente1 = clientes[i];
                pw.write(cliente1.getNombre() + ";" + cliente1.getApellidos() + ";" + cliente1.getEmail());
                if (i != ocupacion -1) pw.println();
            }
        } catch (FileNotFoundException fileNotFoundException){
            System.out.println("Fichero " + fichero + " no encontrado.");
            ficheroEscrito = false;
        } catch (IOException ioException) {
            System.out.println("Error de escritura en fichero " + fichero + ".");
            ficheroEscrito = false;
        } finally {
            if(pw != null){
                pw.close();
            }
        }
        return ficheroEscrito;
    }

    /**
     * TODO: Genera una lista de Clientes a partir del fichero CSV, usando los límites especificados como argumentos
     *  para la capacidad de la lista y el número de billetes máximo por pasajero
     * @param fichero fichero donde lee los datos de los portes
     * @param capacidad del array clientes
     * @param maxEnviosPorCliente cantidad máxima de envíos posibles por clientes en un vuelo
     * @return Devuelve un fichero CSV con la lista de clientes
     */
    public static ListaClientes leerClientesCsv(String fichero, int capacidad, int maxEnviosPorCliente) {
        ListaClientes listaClientes = new ListaClientes(capacidad);
        Scanner sc = null;
        String arrayClientes[];
        Cliente cliente;

        try {
            sc = new Scanner(new FileReader(fichero));
            do {
                arrayClientes = sc.nextLine().split(";");
                cliente = new Cliente(arrayClientes[0], arrayClientes[1], arrayClientes[2], maxEnviosPorCliente);
                if (listaClientes.buscarClienteEmail(cliente.getEmail()) == null){
                    listaClientes.insertarCliente(cliente);
                }
            }while(sc.hasNext());
        }catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Fichero " + fichero + " no encontrado.");
        }
        catch (IOException IOException) {
            System.out.println("Error de lectura en fichero " + fichero + ".");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        return listaClientes;
    }
}