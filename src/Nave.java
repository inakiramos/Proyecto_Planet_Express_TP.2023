/**
 * Description of the class
 *
 * @author Iñaki Ramos Iturria
 * @author Cristiana Velislavova Tsekova 
 * @version     1.0
 */
public class Nave {

    /**
     * Atributo que contiene la marca de la nave
     */
    private String marca;

    /**
     * Atributo que contiene el modelo de la nave
     */
    private String modelo;

    /**
     * Atributo que contiene la matrícula de la nave
     */
    private String matricula;

    /**
     * Atributo que contiene las columnas de huecos del aviòn
     */
    private int columnas;

    /**
     * Atributo que contiene las filas de huecos de la nave
     */
    private int filas;

    /**
     * Atributo que contiene el alcance de la nave
     */
    private double alcance;


    /**
     * Constructor que crea una nave con los parámetros recbidos (String marca, String modelo, String matricula, int columnas, int filas, double alcance)
     *
     * @param marca de la nave
     * @param modelo de la nave
     * @param matricula de la nave
     * @param columnas especifica las columnas de la nave
     * @param filas especifica las filas de la nave
     * @param alcance especifica el alcance máximo de la nave
     */
    public Nave(String marca, String modelo, String matricula, int columnas, int filas, double alcance) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.columnas = columnas;
        this.filas = filas;
        this.alcance = alcance;
    }

    /**
     * Getter del atributo marca
     * @return Devuelve la marca de la nave
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Getter del atributo modelo
     * @return Devuelve el modelo de la nave
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Getter del atributo matrícula
     * @return Devuelve la matricula de la nave
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Getter del atributo columnas
     * @return Devuelve las columnas de los asientos de la nave
     */
    public int getColumnas() {
        return columnas;
    }

    /**
     * Getter del atributo filas
     * @return Devuelve las filas de los asientos de la nave
     */
    public int getFilas() {
        return filas;
    }

    /**
     * Getter del atributo alcance
     * @return Devuelve el alcance máximo de la nave
     */
    public double getAlcance() {
        return alcance;
    }

    /**
     * TODO: Crea un String con los datos de una nave con el siguiente formato:
     * @return ejemplo del formato -> "Planet Express One (EP-245732X): 40 contenedores, hasta 1.57 UA"
     */
    public String toString() {
        return this.marca + this.modelo + "(" + this.matricula + "): " + this.filas * this.columnas +
                " contenedores, hasta " + this.alcance + " UA";
    }

    /**
     * TODO: Crea un String con los datos de una nave con el siguiente formato:
     * @return ejemplo del formato -> "Planet Express One (EP-245732X)"
     */
    public String toStringSimple() {
        return this.marca + this.modelo + "(" + this.matricula + ")";
    }
}