import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase principal de Planet Express App, la práctica de Taller de Programación
 *
 * @author      Taller de Progamación
 * @version     1.0
 */
public class PlanetExpress {

    /**
     * Atributo que representa la capacidad máxima de la lista de Portes
     */
    private final int maxPortes;

    /**
     * Atributo que representa la capacidad máxima de la lista de Naves
     */
    private final int maxNaves;

    /**
     * Atributo que representa la capacidad máxima de la lista de Clientes
     */
    private final int maxClientes;

    /**
     * Atributo que representa la capacidad máxima de la lista de Envíos
     */
    private final int maxEnviosPorCliente;

    /**
     * Atributo que representa la capacidad máxima de la lista de Aeropuertos
     */
    private final int maxPuertosEspaciales;

    /**
     * Objeto que contiene los Puertos Espaciales disponibles del programa
     */
    private ListaPuertosEspaciales listaPuertosEspaciales = null;

    /**
     * Objeto que contiene las naves disponibles del programa
     */
    private ListaNaves listaNaves = null;

    /**
     * Objeto que contiene los clientes disponibles del programa
     */
    private ListaClientes listaClientes = null;

    /**
     * Objeto que contiene los portes disponibles del programa
     */
    private ListaPortes listaPortes = null;


    /**
     * TODO: Rellene el constructor de la clase
     *
     * @param maxPuertosEspaciales Máximo número de puertos espaciales que tendrá la lista de puertos espaciales de PlanetExpress App.
     * @param maxNaves Máximo número de naves que tendrá la lista de naves de PlanetExpress App.
     * @param maxPortes Máximo número de portes que tendrá la lista de portes de PlanetExpress App.
     * @param maxClientes Máximo número de clientes que tendrá la lista de clientes de PlanetExpress App.
     * @param maxEnviosPorCliente Máximo número de envíos por cliente.
     */
    public PlanetExpress(int maxPuertosEspaciales, int maxNaves, int maxPortes, int maxClientes, int maxEnviosPorCliente) {
        this.maxPuertosEspaciales = maxPuertosEspaciales;
        this.maxNaves = maxNaves;
        this.maxPortes = maxPortes;
        this.maxClientes = maxClientes;
        this.maxEnviosPorCliente = maxEnviosPorCliente;
    }

    /**
     * TODO: Metodo para leer los datos de los ficheros específicados en el enunciado y los agrega a
     *  la información de PlanetExpress (listaPuertosEspaciales, listaNaves, listaPortes, listaClientes)
     * @param ficheroPuertos fichero que contiene la lista de los Puertos Espaciales
     * @param ficheroNaves fichero que contiene la lista de las naves
     * @param ficheroPortes fichero que contiene la lista de portes
     * @param ficheroClientes fichero que contiene la lista de clientes
     * @param ficheroEnvios fichero que contiene la lista de los envios
     */
    // Lee los datos de los ficheros especificados y los agrega a Planet Express
    public void cargarDatos(String ficheroPuertos, String ficheroNaves, String ficheroPortes, String ficheroClientes, String ficheroEnvios) {
        listaPuertosEspaciales = ListaPuertosEspaciales.leerPuertosEspacialesCsv(ficheroPuertos, maxPuertosEspaciales);
        listaNaves = ListaNaves.leerNavesCsv(ficheroNaves, maxNaves);
        listaPortes = ListaPortes.leerPortesCsv(ficheroPortes, maxPortes, listaPuertosEspaciales, listaNaves);
        listaClientes = ListaClientes.leerClientesCsv(ficheroClientes, maxClientes, maxEnviosPorCliente);
        ListaEnvios.leerEnviosCsv(ficheroEnvios, listaPortes, listaClientes);
    }

    /**
     * TODO: Metodo para almacenar los datos de PlanetExpress en los ficheros .csv especificados
     *  en el enunciado de la práctica
     *
     * @param ficheroPuertos  fichero en donde se guardan los Puertos Espaciales
     * @param ficheroNaves    fichero en donde se guardan las naves
     * @param ficheroPortes   fichero en donde se guardan los portes
     * @param ficheroClientes fichero en donde se guardan los clientes
     * @param ficheroEnvios   fichero donde se guardan los envios
     * @return
     */
    public boolean guardarDatos(String ficheroPuertos, String ficheroNaves, String ficheroPortes, String ficheroClientes, String ficheroEnvios) {
        boolean ficheroPuertosEspaciales = listaPuertosEspaciales.escribirPuertosEspacialesCsv(ficheroPuertos);
        boolean ficheroNave = listaNaves.escribirNavesCsv(ficheroNaves);
        boolean ficheroPorte = listaPortes.escribirPortesCsv(ficheroPortes);
        boolean ficheroCliente = listaClientes.escribirClientesCsv(ficheroClientes);

        FileWriter fileWriter = null;
        boolean datosGuardados = false;

        try {
            fileWriter = new FileWriter(ficheroEnvios);
            fileWriter.write("");
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        //Sobreescribimos el fichero de los billetes paar que se guarden de manera correcta
        for (int i = 1; i <= listaPortes.getOcupacion(); i++){
            listaPortes.getPorte(i).generarListaEnvios(ficheroEnvios);
        }

        if (ficheroPuertosEspaciales && ficheroNave && ficheroPorte && ficheroCliente){
            datosGuardados = true;
        }
        return datosGuardados;
    }

    /**
     * @return devuelve verdadero si se ha alcanzado el numero maximo de portes, en caso contrario devuelve falso
     */
    public boolean maxPortesAlcanzado() {
        return listaPortes.estaLlena();
    }

    public boolean insertarPorte (Porte porte) {
        return listaPortes.insertarPorte(porte);
    }

    /**
     * @return devuelve verdadero si se ha alcanzado el numero maximo de clientes, en caso contrario devuelve falso
     */
    public boolean maxClientesAlcanzado() {
        return listaClientes.estaLlena();
    }

    /**
     * Inserta un cliente en la lista de clientes
     * @param cliente  a insertar por parámetro
     * @return devuelve verdadero si se ha insertado correctamente y falso si no se inserta
     */
    public boolean insertarCliente (Cliente cliente) {
        return listaClientes.insertarCliente(cliente);
    }

    /**
     * TODO: Metodo para buscar los portes especificados tal y como aparece en el enunciado de la práctica,
     *  Devuelve una lista de los portes entre dos puertos espaciales con una fecha de salida solicitados por teclado
     *  al usuario en el orden y con los textos establecidos (tomar como referencia los ejemplos de ejecución en el
     *  enunciado de la prática)
     * @param teclado objeto para guardar lo que escribe el usuario
     * @return devuelve la lista de portes con el los Puertos Espaciales de llegada y salida.
     * Tambien, devuelve las fechas de llegada y salida.
     */
    public ListaPortes buscarPorte(Scanner teclado) {
        System.out.print("Ingrese código de puerto Origen:");
        String codigoOrigen = teclado.nextLine().toUpperCase();
        System.out.print("Ingrese código de puerto Destino:");
        String codigoDestino = teclado.nextLine().toUpperCase();
        Fecha fecha = Utilidades.leerFecha(teclado,"Introduzca la fecha de salida:");
        return listaPortes.buscarPortes(codigoOrigen, codigoDestino, fecha);
    }

    /**
     * TODO: Metodo para contratar un envio tal y como se indica en el enunciado de la práctica. Se contrata un envio para un porte
     *  especificado, pidiendo por teclado los datos necesarios al usuario en el orden y con los textos (tomar como referencia los
     *  ejemplos de ejecución en el enunciado de la prática)
     * @param teclado objeto paraa guardar lo que escribe el usuario
     * @param rand número aleatorio para crear el identificador de un envío (aleatorio)
     * @param porte de el cual se va a hacer un envío (crear el envío)
     */
    public void contratarEnvio(Scanner teclado, Random rand, Porte porte) {
        if (porte != null) {




        }
    }

    /**
     * TODO Metodo statico con la interfaz del menú de entrada a la App.
     * Tiene que coincidir con las trazas de ejecución que se muestran en el enunciado
     * @param teclado
     * @return opción seleccionada
     */
    public static int menu(Scanner teclado) {
        int opcion;
        System.out.println("1. Alta de Porte");
        System.out.println("2. Alta de Cliente");
        System.out.println("3. Buscar Porte");
        System.out.println("4. Mostrar envíos de un cliente");
        System.out.println("5. Generar lista de envíos");
        System.out.println("0. Salir");
        opcion = Utilidades.leerNumero(teclado, "Seleccione opción:", 0, 5 );
        return opcion;
    }

    /**
     * TODO: Método Main que carga los datos de los ficheros CSV pasados por argumento (consola) en PlanetExpress,
     *  llama iterativamente al menú y realiza la opción especificada hasta que se indique la opción Salir. Al finalizar
     *  guarda los datos de PlanetExpress en los mismos ficheros CSV.
     * @param args argumentos de la línea de comandos, recibe **10 argumentos** estrictamente en el siguiente orden:
     * 1. Número máximo de puertos espaciales que tendrá la lista de puertos espaciales de PlanetExpress App.
     * 2. Número máximo de naves que tendrá la lista de naves de PlanetExpress App.
     * 3. Número máximo de portes que tendrá la lita de portes de PlanetExpress App.
     * 4. Número máximo de clientes que tendrá la lista de clientes de PlanetExpress App.
     * 5. Número máximo de envíos por pasajero.
     * 6. Nombre del fichero CSV que contiene la lista de puertos espaciales de PlanetExpress App (tanto para lectura como escritura).
     * 7. Nombre del fichero CSV que contiene la lista de naves de PlanetExpress App (tanto para lectura como escritura).
     * 8. Nombre del fichero CSV que contiene la lista de portes de PlanetExpress App (tanto para lectura como escritura).
     * 9. Nombre del fichero CSV que contiene la lista de clientes de PlanetExpress App (tanto para lectura como escritura).
     * 10. Nombre del fichero CSV que contiene los envíos adquiridos en PlanetExpress App (tanto para lectura como escritura).
     * En el caso de que no se reciban exactamente estos argumentos, el programa mostrará el siguiente mensaje
     * y concluirá la ejecución del mismo: `Número de argumentos incorrecto`.
     */
    public static void main(String[] args) {
        if (args.length != 10) {
            System.out.println("Número de argumentos incorrecto");
            return;
        }
        Scanner teclado = new Scanner(System.in);
        PlanetExpress planetExpress = new PlanetExpress(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
        planetExpress.cargarDatos(args[5], args[6], args[7], args[8], args[9]);
        Random rand = new Random();
        ListaPortes listaPortes;
        Porte porte;
        int opcion;

        do {
            opcion = menu(teclado);
            switch (opcion) {
                case 0:
                    //PlanetExpress.guardarDatos(args[5], args[6], args[7], args[8], args[9]);
                    break;

                case 1:     // TODO: Alta de Cliente
                    if (!planetExpress.maxPortesAlcanzado())
                        Porte.altaPorte(teclado, rand, planetExpress.listaPuertosEspaciales, planetExpress.listaNaves, planetExpress.listaPortes);
                    else System.out.println("No se pueden dar de alta más portes.");
                    break;

                case 2:     // TODO: Buscar Porte
                    if (!planetExpress.maxClientesAlcanzado()) {
                        Cliente nuevoCliente = Cliente.altaCliente(teclado, planetExpress.listaClientes, planetExpress.maxEnviosPorCliente);
                        if (nuevoCliente != null)
                            System.out.println("Cliente con email " + nuevoCliente.getEmail() + " dado de alta con éxito.");
                    } else System.out.println("No se pueden dar de alta más clientes.");
                    break;

                case 3:     // TODO: Listado de envíos de un cliente
                    listaPortes = planetExpress.buscarPorte(teclado);
                    if (listaPortes.getOcupacion() != 0) {
                        listaPortes.listarPortes();
                        porte = listaPortes.seleccionarPorte(teclado, "Ingrese ID de vuelo para comprar billete o escriba CANCELAR:", "CANCELAR");
                        if (porte != null){
                            planetExpress.contratarEnvio(teclado,rand, porte);
                        }
                    } else System.out.println("No se ha encontrado ningún vuelo.");
                    break;

                case 4:     // TODO: Lista de envíos de un porte
                    Cliente cliente = planetExpress.listaClientes.seleccionarCliente(teclado, "Email del cliente:");
                    if (cliente.numBilletesComprado() != 0) {
                        cliente.getListaEnvios().listarEnvios();
                        Envio envio = cliente.getListaEnvios().seleccionarEnvio(teclado, "Seleccione un envío:");
                        char character;

                        do {
                            //Sólo se aceptan respuestas en minúsculas
                            //character = Utilidades.leerLetra(scanner, "¿Generar factura del billete (f), cancelarlo (c) o volver al menú (m)?", 'a', 'z');

                            //Acepta respuestas en mayúsculas y minúsculas indistintamente
                            do {
                                System.out.print("¿Cancelar envío (c), o generar factura (f)");
                                character = Character.toLowerCase(teclado.nextLine().charAt(0));
                            } while (character < 'a' || character > 'z');

                            //if (character != 'f' && character != 'c') System.out.println("El valor de entrada debe ser 'f', 'c'");
                        } while (character != 'f' && character != 'c');

                        if (character == 'f') {
                            System.out.print("Nombre del fichero:");
                            String rutaFichero = teclado.nextLine();
                            envio.generarFactura(rutaFichero);
                            System.out.println(" Factura generada correctamente en" + rutaFichero);
                        } else if (character == 'c') {
                            String localizadorEnvio = envio.getLocalizador();
                            if (envio.cancelar()) System.out.println("Billete " + localizadorEnvio + " cancelado.");
                        }
                    } else System.out.println("El pasajero seleccionado no ha adquirido ningún billete.");
                    break;

                case 5:
                    Porte porte2 = planetExpress.listaPortes.seleccionarPorte(teclado, "Seleccione un porte: ","CANCELAR");
                    if (porte2 != null){
                        System.out.print("Nombre del fichero: ");
                        String rutaPorte = teclado.nextLine();
                        if (porte2.generarListaEnvios(rutaPorte)){
                            //System.out.println("Lista de pasajeros del Vuelo " + porte2.getID() + " generada en " + rutaPorte);
                            System.out.println("Fichero creado correctamente.");
                        }
                    } else System.out.println("No se ha encontrado ningún vuelo.");
                    break;
            }
        } while (opcion != 0);


    }
}