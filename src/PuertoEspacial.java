/**
 * Puerto Espacial es una clase que encapsula las variables enteras usadas para definir un puerto en concreto
 *
 * @author Iñaki Ramos Iturria
 * @author Cristiana Velislavova Tsekova
 * @version     1.0
 */
public class PuertoEspacial {

    /**
     * Atributo que contiene el nombre del puerto espacial
     */
    private String nombre;

    /**
     * Atributo que contiene el código identificativo GFSC
     */
    private String codigo;

    /**
     * Atributo que contiene el radio (distancia del Puerto en cuanto al Sol)
     */
    private double radio;

    /**
     * Atributo que contiene el ángulo del eje positivo x hasta un punto en el plano xy
     */
    private double azimut;

    /**
     * Atributo que contiene el ángulo desde el eje positivo z hasta el puerto
     */
    private double polar;

    /**
     * Atributo que contiene el número de muelles de carga del puerto
     */
    private int numMuelles;

    /**
     * Constructor of the class
     *
     * @param nombre del puerto espacial
     * @param codigo identificativo (GFSC), el codigo que identifica cada Puerto Espacial
     * @param radio distancia del puerto segun distancia al Sol (Unidades Astonómicas (UA))
     * @param azimut ángulo desde el eje positivo x hasta la proyección del punto en el plano xy
     * @param polar ángulo desde el eje positivo z hasta el puerto
     * @param numMuelles número de muelles de carga del puerto espacial
     */
    public PuertoEspacial(String nombre, String codigo, double radio, double azimut, double polar, int numMuelles) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.radio = radio;
        this.azimut = azimut;
        this.polar = polar;
        this.numMuelles = numMuelles;
    }

    /**
     * Getter del atributo nombre
     *
     * @return Devuelve el nombre del Puerto Espacial
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Getter del atributo código GFSC
     *
     * @return Devuelve el código GFSC del Puerto Espacial
     */
    public String getCodigo() {
        return this.codigo;
    }

    /**
     * Getter del atributo radio
     *
     * @return Devuelve el radio del Puerto Espacial segun la distancia al Sol
     */
    public double getRadio() {
        return this.radio;
    }

    /**
     * Getter del atributo azimut
     *
     * @return Devuelve azimut que es el ángulo desde el eje positivo x hasta
     * la proyección del punto en el plano xy
     */
    public double getAzimut() {
        return this.azimut;
    }

    /**
     * Getter del atributo polar
     *
     * @return Devuelve la variablepolar que es el ángulo desde el eje
     * positivo z hasta el puerto
     */
    public double getPolar() {
        return this.polar;
    }

    /**
     * Getter del atributo muelles
     *
     * @return Devuelve el número de muelles del Puerto Espacial
     */
    public int getMuelles() {
        return this.numMuelles;
    }

    /**
     * TODO: Método para calcular la distancia entre el puerto espacial que recibe el mensaje y el puerto
     *  espacial "destino" siguiendo las ecuaciones del enunciado (Las formulas se encuentran en el enunciado)
     * @param destino distancia destio a otro Puerto Espacial
     * @return Devuelve la distancia entre el puerto de origen y el de destino
     */
    public double distancia(PuertoEspacial destino) {
        // TODO: Para calcular la distancia entre dos Puertos Espaciales, se transforman sus coordenadas esféricas a cartesianas
        double x0, y0, z0, x1, y1, z1;
        x0 = this.radio * Math.sin(this.azimut) * Math.cos(this.polar);
        y0 = this.radio * Math.sin(this.azimut) * Math.sin(this.polar);
        z0 = this.radio * Math.cos(this.azimut);

        x1 = destino.radio * Math.sin(destino.azimut) * Math.cos(destino.polar);
        y1 = destino.radio * Math.sin(destino.azimut) * Math.sin(destino.polar);
        z1 = destino.radio * Math.cos(destino.azimut);

        // TODO: Una vez se tienen las coordenadas cartesianas, basta con calcular la distancia euclídea entre ellas:
        return Math.sqrt(Math.pow(x1 - x0,2) + Math.pow(y1 - y0,2) + Math.pow(z1 - z0,2));
    }

    /**
     * TODO: Método que crea un String con los datos de un puerto espacial con el siguiente formato:
     * @return Ejemplo -> "Gaia Galactic Terminal(GGT), en (1.0 90.0 0.0), con 8 muelles" (Radio, Azimut, Polar)
     */
    public String toString() {
        return this.nombre + "Terminal(" + this.codigo + "), en (" + this.radio + this.azimut + this.polar + "), " +
                "con " + this.numMuelles + " muelles (Radio, Azimut, Polar)";
    }

    /**
     * TODO: Método que crea un String con los datos de un aeropuerto con el siguiente formato:
     * @return Ejemplo -> "Gaia Galactic Terminal (GGT)"
     */
    public String toStringSimple() {
        return this.nombre + "Terminal (" + this.codigo + ")";
    }
}