import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author Iñaki Ramos Iturria
 * @author Cristiana Velislavova Tsekova
 * @version     1.0
 */
public class ListaNaves {
    private Nave[] naves;
    private int capacidad;
    private int ocupacion;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     */
    public ListaNaves(int capacidad) {
        this.ocupacion = 0;
        this.capacidad = capacidad;
        this.naves = new Nave[capacidad];
    }

    // TODO: Devuelve el número de naves que hay en la lista
    public int getOcupacion() {
        return this.ocupacion;
    }

    // TODO: ¿Está llena la lista de naves?
    public boolean estaLlena() {
        boolean estaLlena = false;
        if (ocupacion == capacidad){
            estaLlena = true;
        }
        return estaLlena;
    }

	// TODO: Devuelve nave dado un indice
    public Nave getNave(int posicion) {
        return naves[posicion - 1];
    }

    /**
     * TODO: insertamos una nueva nave en la lista
     * @param nave
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarNave(Nave nave) {
        boolean insertado = false;
        if (!estaLlena()){
            naves[ocupacion] = nave;
            ocupacion ++;
            insertado = true;
        }
        return insertado;
    }

    /**
     * TODO: Buscamos la nave a partir de la matricula pasada como parámetro
     * @param matricula
     * @return la nave que encontramos o null si no existe
     */
    public Nave buscarNave(String matricula) {
        Nave resul = null;
        for (int i = 0; i < ocupacion; i++) {
            if (Objects.equals(naves[i].getMatricula(), matricula));
            resul = naves[i];
        }
        return resul;
    }

    // TODO: Muestra por pantalla las naves de la lista con el formato indicado en el enunciado
    public void mostrarNaves() {
        for (int i = 0; i < ocupacion; i ++){
            System.out.println(naves[i].toString());
        }
    }

    /**
     * TODO: Permite seleccionar una nave existente a partir de su matrícula, y comprueba si dispone de un alcance
     *  mayor o igual que el pasado como argumento, usando el mensaje pasado como argumento para la solicitud y
     *  siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente la matrícula de la nave hasta que se introduzca una con alcance suficiente
     * @param teclado
     * @param mensaje
     * @param alcance
     * @return
     */
    public Nave seleccionarNave(Scanner teclado, String mensaje, double alcance) {
        Nave nave = null;
        boolean stop = false;

        do {
            System.out.println(mensaje);
            String pantalla = teclado.nextLine();

            // Comprobar el alcance de la nave VER
            if (pantalla.equalsIgnoreCase(alcance)) stop = true;

            else{
                nave = buscarNave(pantalla);
                if (nave == null){
                    System.out.println("Matricula de nave no encontrada.");
                }else{
                    stop = true;
                }
            }
        }while (!stop);
        return nave;
    }


    /**
     * TODO: Genera un fichero CSV con la lista de Naves, SOBREESCRIBIÉNDOLO
     * @param nombre
     * @return
     */
    public boolean escribirNavesCsv(String nombre) {
        PrintWriter pw = null;
        try {

            return true;
        } catch (Exception e) {
            return false;
        } finally {

        }
    }


    /**
     * TODO: Genera una lista de naves a partir del fichero CSV, usando el argumento como capacidad máxima de la lista
     * @param fichero
     * @param capacidad
     * @return
     */
    public static ListaNaves leerNavesCsv(String fichero, int capacidad) {
        ListaNaves listaNaves = new ListaNaves(capacidad);
        Scanner sc = null;
        try {

        } catch (Exception e) {
            return null;
        } finally {

        }
        return listaNaves;
    }
}
