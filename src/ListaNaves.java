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
     * @return Devuelve la cantidad de las naves que hay en el array "naves" con la variable ocupación
     */
    public int getOcupacion() {
        return this.ocupacion;
    }

    /**
     * Devuelve true si la lista "naves" está llena, si no, devuelve false
     * @return  estaLlena
     */
    public boolean estaLlena() {
        boolean estaLlena = false;
        if (ocupacion == capacidad){
            estaLlena = true;
        }
        return estaLlena;
    }

    /**
     * @param posicion es la posición que se pasa por parámetro
     * @return Devuelve la nave que se encuentra en las posición pasada por parámetro
     */
    public Nave getNave(int posicion) {
        return naves[posicion - 1];
    }

    /**
     * TODO: insertamos una nueva nave en la lista
     * @param nave nave que se quiere insertar en la lista "naves"
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
     * @param matricula código que identifica a una nave
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

    /**
     *
     */
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
        }
        finally {
            if (pw != null) {
                pw.close();
            }
        }
        return ficheroEscrito;
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
        Nave nave;
        String arrayNave[];

        try {
            sc = new Scanner(new FileReader(fichero));
            do {
                arrayNave = sc.nextLine().split(";");
                nave = new Nave(arrayNave[0], arrayNave[1], arrayNave[2], arrayNave[3], arrayNave[4], arrayNave[5]);
                listaNaves.insertarNave(nave);
            }while(sc.hasNext());
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Fichero " + fichero + " no encontrado.");
        } catch(IOException ioException){
            System.out.println("Error de lectura en fichero " + fichero + ".");
        }
        finally {
            if (sc != null) {
                sc.close();
            }
        }
        return listaNaves;
    }
}