import javax.lang.model.util.ElementFilter;
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
public class ListaNaves {

    /**
     * Atributo que contiene el array donde estan las naves
     */
    private Nave[] naves;

    /**
     * Atributo que devuelve la capacidad de la Lista de naves
     */
    private int capacidad;

    /**
     * Atributo que devuelve la ocupación de la nave de la lista
     */
    private int ocupacion;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad especifica la capacidad de la lista que contiene las naves
     */
    public ListaNaves(int capacidad) {
        this.ocupacion = 0;
        this.capacidad = capacidad;
        this.naves = new Nave[capacidad];
    }

    /**
     * Getter del atributo ocupación
     *
     * @return Devuelve la cantidad de las naves que hay en el array "naves" con la variable ocupación
     */
    public int getOcupacion() {
        return this.ocupacion;
    }

    /**
     * Devuelve true si la lista "naves" está llena, si no, devuelve false
     * @return  Devuelve estaLlena
     */
    public boolean estaLlena() {
        boolean estaLlena = false;
        if (ocupacion == capacidad){
            estaLlena = true;
        }
        return estaLlena;
    }

    /**
     * Getter para conseguir la nave deseada
     *
     * @param posicion es la posición que se pasa por parámetro
     * @return Devuelve la nave que se encuentra en las posición pasada por parámetro
     */
    public Nave getNave(int posicion) {
        return naves[posicion - 1];
    }

    /**
     * TODO: insertamos una nueva nave en la lista
     * @param nave que se quiere insertar en la lista "naves"
     * @return Devuelve true en caso de que se añada correctamente, false en caso de lista llena o error
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
     * @param matricula código que identifica a una nave
     * @return Devuelve la nave que tenga la matrícula pasada por parámetro, si no, devuelve null al no existir esta misma
     */
    public Nave buscarNave(String matricula) {
        Nave resul = null;
        for (int i = 0; i < ocupacion; i++) {
            if (Objects.equals(naves[i].getMatricula(), matricula));
            resul = naves[i];
        }
        return resul;
    }

    /**
     * TODO: Muestra por pantalla las naves de la lista con el formato indicado en el enunciado
     */
    public void mostrarNaves() {
        for (int i = 0; i < ocupacion; i ++){
            if (naves[i] != null){
                System.out.println("\n" + naves[i].toString());
            }
        }
    }

    /**
     * TODO: Permite seleccionar una nave existente a partir de su matrícula, y comprueba si dispone de un alcance
     *  mayor o igual que el pasado como argumento, usando el mensaje pasado como argumento para la solicitud y
     *  siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente la matrícula de la nave hasta que se introduzca una con alcance suficiente
     * @param teclado por donde se solicita la informacion al usuario para que la escriba
     * @param mensaje por el cual se le preguntan los datos al ususario
     * @param alcance de la nave por la cual se van a transladar los envios
     * @return Devuelve la nave seleccionada para transportar los envíos entre portes
     */
    public Nave seleccionarNave(Scanner teclado, String mensaje, double alcance) {
        Nave nave = null;
        String matricula = "";
        boolean stop = false;

        do {
            System.out.println(mensaje);
            matricula = teclado.nextLine();
            nave = buscarNave(matricula);

            // Comprobar el alcance de la nave VER
            if (nave == null){
                System.out.println("Matrícula de nave no encontrada.");
            } else if (nave.getAlcance() < alcance){
                System.out.printf(String.format("Nave seleccionada con alcance insuficiente.", alcance).replace(',', '.'));
            } else {
                stop = true;
            }
        } while (!stop);
        //} while (nave == null || !stop);
        return nave;
    }

    /**
     * TODO: Genera un fichero CSV con la lista de Naves, SOBREESCRIBIÉNDOLO
     * @param nombre del fichero en donde se van a guardar los datos de la lista de naves
     * @return Devuelve true si el fichero se escribio y guardo correctamente, si no es asi, devuelve false
     */
    public boolean escribirNavesCsv(String nombre) {
        PrintWriter pw = null;
        Nave nave;
        boolean ficheroEscrito = true;

        try {
            pw = new PrintWriter(nombre);
            for (int i = 0; i < ocupacion; i++) {
                nave = naves[i];
                pw.print(nave.getMarca() + ";" + nave.getModelo() + ";" + nave.getMatricula() + ";" + nave.getFilas() + ";" + nave.getColumnas() + ";" + nave.getAlcance());
                if (i != ocupacion -1) pw.println();
            }

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Fichero " + nombre + " no encontrado.");
            ficheroEscrito = false;
        } catch(IOException ioException) {
            System.out.println("Error de escritura en fichero " + nombre + ".");
            ficheroEscrito = false;
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return ficheroEscrito;
    }

    /**
     * TODO: Genera una lista de naves a partir del fichero CSV, usando el argumento como capacidad máxima de la lista
     * @param fichero en el cual se van a guardar los datos de las naves
     * @param capacidad de la lista de naves
     * @return Devuelve una lista de la naves utilizadas en el programa  con sus atributos
     */
    public static ListaNaves leerNavesCsv(String fichero, int capacidad) {
        ListaNaves listaNaves = new ListaNaves(capacidad);
        Scanner sc = null;
        Nave nave;
        String arrayNave[];

        try {
            sc = new Scanner(new FileReader(fichero));
            do {
                arrayNave = sc.nextLine().split(";");
                nave = new Nave(arrayNave[0], arrayNave[1], arrayNave[2], Integer.parseInt(arrayNave[3]), Integer.parseInt(arrayNave[4]), Double.parseDouble(arrayNave[5]));
                listaNaves.insertarNave(nave);
            }while(sc.hasNext());
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Fichero " + fichero + " no encontrado.");
        } catch(IOException ioException){
            System.out.println("Error de lectura en fichero " + fichero + ".");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        return listaNaves;
    }
}