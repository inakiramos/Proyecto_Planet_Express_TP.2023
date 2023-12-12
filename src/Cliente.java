import java.util.Scanner;

/**
 * Description of the class
 *
 * @author Taller de Programación
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
     * @param nombre Nombre del cliente
     * @param apellidos Apellidos del cliente
     * @param email Email del cliente
     * @param maxEnvios Número máximo de envíos que puede tener el cliente
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
     * @return Devuelve el nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Getter del atributo apellidos
     * @return Devuelve los apellidos del cliente
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Getter del atributo email
     * @return Devuelve el email de un cliente
     */
    public String getEmail() {
        return email;
    }

    /**
     * Función que imprime la información de un cliente con un formato específico,
     * Zapp Brannigan, zapp.brannigan@dop.gov
     * @return String que muestra nombre, apellidos y email del cliente
     */
    // TODO: Texto que debe generar: Zapp Brannigan, zapp.brannigan@dop.gov
    public String toString() {
        return nombre + " " + apellidos + " " + email;
    }

    /**
     * Función que comprueba si se ha alcanzado el máximo de envíos posibles
     * @return devuelve true si se ha alcanzado el máximo de envíos que se pueden hacer
     */
    // TODO: Devuelve un booleano que indica si se ha alcanzado el número máximo de envíos
    public boolean maxEnviosAlcanzado() {
        return maxEnvios == ;
    }

    /**
     * Getter para conseguir un envío de la lista
     * @param i posición del envío en la lista
     * @return envío que se encuentra en la posición pasada por parámetro (i)
     */
    // TODO: Devuelve un envío en función de su posición
    public Envio getEnvio(int i) {
        return listaEnvios.getEnvio(i);
    }


    public ListaEnvios getListaEnvios() {
        return listaEnvios;
    }
    // TODO: Añade un envío al cliente
    public boolean aniadirEnvio(Envio envio) {
        return null;
    }

    /**
     * Función que busca un envío mediante el localizador pasado por parámetro
     * @param localizador que es único de cada envío y que lo identifica
     * @return devuelve el envío que se estaba buscando
     */
    public Envio buscarEnvio(String localizador) {
        return listaEnvios.buscarEnvio(localizador);
    }

    /**
     * Función que devuelve true si se ha podido cancelar el envío mediante el localizador pasado por parámetro
     * @param localizador  único de cada envío y que lo identifica
     * @return devuelve true si se ha cancelado el envío y false si no se ha podido
     */
    // TODO: Elimina el envío de la lista de envíos del pasajero
    public boolean cancelarEnvio(String localizador) {
        return listaEnvios.eliminarEnvio(localizador);
    }

    /**
     * Función que encapsulado la función listarEnvios de la clase ListaEnvio
     */
    public void listarEnvios() {
        listaEnvios.listarEnvios();
    }

    /**
     * Función que encapsula la funcionalidad seleccionarEnvio de listaEnvio
     * @param teclado son los datos que aporta el usuario
     * @param mensaje mensajes que se le muestra al usuario
     * @return devuelve el envío seleccionado
     */
    // Encapsula la funcionalidad seleccionarEnvio de ListaEnvios
    public Envio seleccionarEnvio(Scanner teclado, String mensaje) {
        return listaEnvios.seleccionarEnvio(teclado, mensaje);
    }

    /**
     * TODO: Método estático para crear un nuevo cliente "no repetido", se pide por teclado los datos necesarios
     * al usuario en el orden y con los textos indicados en los ejemplos de ejecución del enunciado
     * La función tiene que solicitar repetidamente los parámetros hasta que sean correctos
     * @param teclado informacíon que ofrece el usuario
     * @param clientes pasajeros que realizan el vuelo
     * @param maxEnvios envíos máximos de un vuelo específico
     * @return Cliente
     */
    public static Cliente altaCliente(Scanner teclado, ListaClientes clientes, int maxEnvios) {
        Cliente nuevoCliente = null;
        System.out.print("Ingrese nombre:");
        String nombre = teclado.nextLine();
        System.out.print("Ingrese apellidos:");
        String apellidos = teclado.nextLine();
        boolean emailIncorrecto;
        String email;

        do {
            emailIncorrecto = false;
            System.out.print("Ingrese email:");
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
     * @param email del cliente (pasado por parámetro)
     * @return devuelve true sie el email es correcto y false si no lo es (no cumple los requisitos o es erróneo)
     */
    public static boolean correctoEmail(String email) {
        String[] emailCompleto = email.split("@");
        String primeraParte = emailCompleto[0];
        String segundaParte = emailCompleto[1];
        int longitud = primeraParte.length();
        boolean correcto = true;
        if (segundaParte.equals("alumnos.upm.es") || segundaParte.equals("upm.es")) {
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
}