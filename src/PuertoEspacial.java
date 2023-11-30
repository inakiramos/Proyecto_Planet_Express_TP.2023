/**
 * Description of the class
 *
 * @author Iñaki Ramos Iturria
 * @author Cristiana Velislavova Tsekova
 * @version     1.0
 */
public class PuertoEspacial {

    /**
     * Nombre del puerto espacial
     */
    private String nombre;

    /**
     * Código identificativo GFSC
     */
    private String codigo;

    /**
     * Radio (distancia del Puerto en cuanto al Sol)
     */
    private double radio;

    /**
     * Ángulo del eje positivo x hasta un punto en el plano xy
     */
    private double azimut;

    /**
     * Ángulo desde el eje positivo z hasta el puerto
     */
    private double polar;

    /**
     * Número de muelles de carga del puerto
     */
    private int numMuelles;

    /**
     * Constructor of the class
     *
     * @param nombre del puerto espacial.
     * @param codigo identificativo (GFSC), el codigo que identifica cada Puerto Espacial.
     * @param radio distancia del puerto segun distancia al Sol (Unidades Astonómicas (UA)).
     * @param azimut ángulo desde el eje positivo x hasta la proyección del punto en el plano xy.
     * @param polar ángulo desde el eje positivo z hasta el puerto.
     * @param numMuelles número de muelles de carga del puerto espacial.
     */
    public PuertoEspacial(String nombre, String codigo, double radio, double azimut, double polar, int numMuelles) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.radio = radio;
        this.azimut = azimut;
        this.polar = polar;
        this.numMuelles = numMuelles;
    }
    public String getNombre() {
        return nombre;
    }
    public String getCodigo() {
        return codigo;
    }
    public double getRadio() {
        return radio;
    }
    public double getAzimut() {
        return azimut;
    }

    public double getPolar() {
        return polar;
    }
    public int getMuelles() {
        return numMuelles;
    }

    /**
     * TODO: Método para calcular la distancia entre el puerto espacial que recibe el mensaje y el puerto
     *  espacial "destino" siguiendo las ecuaciones del enunciado (Las formulas se encuentran en el enunciado)
     * @param destino
     * @return devuelve la distancia entre el puerto emisor y receptor
     */
    public double distancia(PuertoEspacial destino) {
        // TODO: Para calcular la distancia entre dos Puertos Espaciales, se transforman sus coordenadas esféricas a cartesianas
        double x, y, z;
        double distancia = 0;
        x = radio * Math.sin(azimut) * Math.cos(polar);
        y = radio * Math.sin(azimut) * Math.sin(polar);
        z = radio * Math.cos(azimut);

        // TODO: Una vez se tienen las coordenadas cartesianas, basta con calcular la distancia euclídea entre ellas:
        distancia = Math.sqrt(Math.pow(radio - x,2) + Math.pow(azimut - y,2) + Math.pow(polar - z,2));
        return distancia;
    }

    /**
     * TODO: Método que crea un String con los datos de un puerto espacial con el siguiente formato:
     * @return ejemplo -> "Gaia Galactic Terminal(GGT), en (1.0 90.0 0.0), con 8 muelles" (Radio, Azimut, Polar)
     */
    public String toString() {
        return this.nombre + "Terminal(" + this.codigo + "), en (" + this.radio + this.azimut + this.polar + "), " +
                "con " + this.numMuelles + " muelles (Radio, Azimut, Polar)";
    }

    /**
     * TODO: Método que crea un String con los datos de un aeropuerto con el siguiente formato:
     * @return ejemplo -> "Gaia Galactic Terminal (GGT)"
     */
    public String toStringSimple() {
        return this.nombre + "Terminal (" + this.codigo + ")";
    }
}