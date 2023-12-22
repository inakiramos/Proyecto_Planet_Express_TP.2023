import java.util.Scanner;

/**
 * Description of the class
 *
 * @author Iñaki Ramos Iturria
 * @author Cristiana Velislavova Tsekova
 * @version     1.0
 */
public class Cliente {

    /**
     * Atributo que contiene el array donde se guardan los envíos (hechos) de un cliente
     */
    private final ListaEnvios listaEnvios;

    /**
     * Atributo que contiene el  nombre del cliente
     */
    private final String nombre;

    /**
     * Atributo que contiene los apellidos del cliente
     */
    private final String apellidos;

    /**
     * Atributo que contiene el email del cliente
     */
    private final String email;

    /**
     * Atributo que contiene la cantindad máxima de envíos que se pueden hacer por porte
     */
    private int maxEnvios;

    /**
     * Constructor of the class
     *
     * @param nombre del cliente
     * @param apellidos del cliente
     * @param email del cliente
     * @param maxEnvios número máximo de envíos que puede tener el cliente
     */
    public Cliente(String nombre, String apellidos, String email, int maxEnvios) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.listaEnvios = new ListaEnvios(maxEnvios);
        this.maxEnvios = maxEnvios;
    }

    /**
     * Getter del atributo nombre
     *
     * @return Devuelve el nombre del cliente
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Getter del atributo apellidos
     *
     * @return Devuelve los apellidos del cliente
     */
    public String getApellidos() {
        return this.apellidos;
    }

    /**
     * Getter del atributo email
     *
     * @return Devuelve el email de un cliente
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Función que imprime la información de un cliente con un formato específico,
     * Zapp Brannigan, zapp.brannigan@dop.gov
     *
     * @return String que muestra nombre, apellidos y email del cliente
     */
    // TODO: Texto que debe generar: Zapp Brannigan, zapp.brannigan@dop.gov
    public String toString() {
        return nombre + " " + apellidos + ", " + email;
    }

    /**
     * Función que comprueba si se ha alcanzado el máximo de envíos posibles
     *
     * @return Devuelve true si se ha alcanzado el máximo de envíos que se pueden hacer
     */
    // TODO: Devuelve un booleano que indica si se ha alcanzado el número máximo de envíos
    public boolean maxEnviosAlcanzado() {
        return maxEnvios == listaEnvios.getOcupacion();
    }

    /**
     * Getter para conseguir un envío de la lista
     *
     * @param i posición del envío en la lista
     * @return Devuelve el envío que se encuentra en la posición pasada por parámetro (i)
     */
    // TODO: Devuelve un envío en función de su posición
    public Envio getEnvio(int i) {
        return listaEnvios.getEnvio(i);
    }

    /**
     * Getter de la lista de envíos
     *
     * @return Devuelve la lista de envíos de un porte
     */
    public ListaEnvios getListaEnvios() {
        return this.listaEnvios;
    }

    /**
     * Añade un envio a la lista, comprobando que la misma no este llena
     *
     * @param envio que se quiere añiadir a la lista
     * @return
     */
    // TODO: Añade un envío al cliente
    public boolean aniadirEnvio(Envio envio) {
        boolean aniadido = false;
        if (!maxEnviosAlcanzado()){
            listaEnvios.insertarEnvio(envio);
            aniadido = true;
        }
        return aniadido;
    }

    /**
     * Función que busca un envío mediante el localizador pasado por parámetro
     *
     * @param localizador que es único de cada envío y que lo identifica
     * @return Devuelve el envío que se estaba buscando
     */
    public Envio buscarEnvio(String localizador) {
        return listaEnvios.buscarEnvio(localizador);
    }

    /**
     * Función que devuelve true si se ha podido cancelar el envío mediante el localizador pasado por parámetro
     *
     * @param localizador  único de cada envío y que lo identifica
     * @return Devuelve true si se ha cancelado el envío y false si no se ha podido
     */
    // TODO: Elimina el envío de la lista de envíos del pasajero
    public boolean cancelarEnvio(String localizador) {
        return listaEnvios.eliminarEnvio(localizador);
    }

    /**
     * Función que encapsulado la función listarEnvios de la clase ListaEnvios
     */
    public void listarEnvios() {
        listaEnvios.listarEnvios();
    }

    /**
     * Función que encapsula la funcionalidad seleccionarEnvio de listaEnvio
     *
     * @param teclado son los datos que aporta el usuario
     * @param mensaje mensajes que se le muestra al usuario
     * @return Devuelve el envío seleccionado
     */
    // Encapsula la funcionalidad seleccionarEnvio de ListaEnvios
    public Envio seleccionarEnvio(Scanner teclado, String mensaje) {
        return listaEnvios.seleccionarEnvio(teclado, mensaje);
    }

    /**
     * Método estático para crear un nuevo cliente "no repetido", se pide por teclado los datos necesarios
     * al usuario en el orden y con los textos indicados en los ejemplos de ejecución del enunciado
     *
     * La función tiene que solicitar repetidamente los parámetros hasta que sean correctos
     * @param teclado informacíon que ofrece el usuario
     * @param clientes pasajeros que realizan el vuelo
     * @param maxEnvios envíos máximos de un vuelo específico
     * @return Devuelve un cliente
     */
    public static Cliente altaCliente(Scanner teclado, ListaClientes clientes, int maxEnvios) {
        Cliente nuevoCliente = null;

        System.out.print("Nombre: ");
        String nombre = teclado.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = teclado.nextLine();

        boolean emailIncorrecto;
        String email;

        do {
            emailIncorrecto = false;
            System.out.print("Email: ");
            email = teclado.nextLine();
            if (clientes.buscarClienteEmail(email) != null){
                emailIncorrecto = true;
                System.out.println("Email ya existe.");
            }
            if (!correctoEmail(email)){
                emailIncorrecto = true;
            }
        }while(emailIncorrecto);

        nuevoCliente = new Cliente(nombre, apellidos, email, maxEnvios);
        if (nuevoCliente != null) clientes.insertarCliente(nuevoCliente);
        return nuevoCliente;
    }

    /**
     * TODO: Metodo para comprobar que el formato de email introducido sea correcto
     *
     * @param email del cliente (pasado por parámetro)
     * @return Devuelve true sie el email es correcto y false si no lo es (no cumple los requisitos o es erróneo)
     */
    public static boolean correctoEmail(String email) {
        String[] emailCompleto = email.split("@");
        String primeraParte = emailCompleto[0];
        String segundaParte = emailCompleto[1];
        int longitud = primeraParte.length();
        boolean correcto = true;

        if (segundaParte.equals("planetexpress.com")) {
            if (primeraParte.charAt(0) == '.' || primeraParte.charAt(longitud - 1) == '.') {
                System.out.println("Email incorrecto.");
                correcto = false;
            } else {
                for (int i = 0; i < longitud; i++) {
                    if ((primeraParte.charAt(i) < 65 || primeraParte.charAt(i) > 90) && (primeraParte.charAt(i) < 97 || primeraParte.charAt(i) > 122) && primeraParte.charAt(i) != '.') {
                        System.out.println("Email incorrecto");
                        correcto = false;
                    }
                }
            }
        } else {
            System.out.println("Email incorrecto.");
            correcto = false;
        }
        return correcto;
    }

    /**
     * Función que comprueba la ocupación de un billete dentro del array listaBilletesPasajero
     *
     * @return Devuelve la ocupación del billete comprado por un pasajero dentro del array listaBilletesPasajero
     */
    public int numBilletesComprado() {
        return listaEnvios.getOcupacion();
    }
}