import java.util.Scanner;

/**
 * Description of the class
 *
 * @author Iñaki Ramos Iturria
 * @author Cristiana Velislavova Tsekova
 * @version     1.0
 */
public class Utilidades {

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado
     * @param mensaje
     * @param minimo
     * @param maximo
     * @return int numero
     */
    public static int leerNumero(Scanner teclado, String mensaje, int minimo, int maximo) {
        int numero;

        do {
            System.out.print(mensaje);
            numero = teclado.nextInt();
        }while (numero < minimo || numero > maximo);
        return numero;
    }

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado
     * @param mensaje
     * @param minimo
     * @param maximo
     * @return long numero
     */
    public static long leerNumero(Scanner teclado, String mensaje, long minimo, long maximo) {
        long numero;

        do {
            System.out.println(mensaje);
            numero = Long.parseLong(teclado.nextLine());
        }while(numero < minimo || numero > maximo);
        return numero;
    }

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado
     * @param mensaje
     * @param minimo
     * @param maximo
     * @return double numero
     */
    public static double leerNumero(Scanner teclado, String mensaje, double minimo, double maximo) {
        double numero;

        do {
            System.out.print(mensaje);
            numero = teclado.nextDouble();
        }while (numero < minimo || numero > maximo);
        return numero;
    }

    /**
     * TODO: TODO: Solicita una letra repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado
     * @param mensaje
     * @param minimo
     * @param maximo
     * @return char letra
     */
    public static char leerLetra(Scanner teclado, String mensaje, char minimo, char maximo) {
        char letra;

        do {
            System.out.print(mensaje);
            letra = teclado.next().charAt(0);
        }while (letra < minimo || letra > maximo);
        return letra;
    }

    /**
     * TODO: Solicita una fecha repetidamente hasta que se introduzca una correcta
     * @param teclado
     * @param mensaje
     * @return Fecha
     */
    public static Fecha leerFecha(Scanner teclado, String mensaje) {
        int dia, mes, anio;
        System.out.println(mensaje);

        do {
            dia = leerNumero(teclado, "Ingrese dia:", 1, 31);
            mes = leerNumero(teclado, "Ingrese mes:", 1, 12);
            anio = leerNumero(teclado, "Ingrese año:", 1900, 3000);

            if (!Fecha.comprobarFecha(dia, mes, anio)){
                System.out.println("\t\t\t  Fecha introducida incorrecta.");
            }
        }while(!Fecha.comprobarFecha(dia, mes, anio));
        return new Fecha(dia, mes, anio);
    }

    /**
     * TODO: Solicita una fecha y hora repetidamente hasta que se introduzcan unas correctas
     * @param teclado
     * @param mensaje
     * @return Fecha
     */
    public static Fecha leerFechaHora(Scanner teclado, String mensaje) {
        int dia, mes, anio, hora, minuto, segundo;
        System.out.println(mensaje);
        do {
            dia = leerNumero(teclado,"Ingrese día:",1,31);
            mes = leerNumero(teclado,"Ingrese mes:",1,12);
            anio = leerNumero(teclado,"Ingrese año:",1900,3000);

            hora = leerNumero(teclado,"Ingrese hora:",0,23);
            minuto = leerNumero(teclado,"Ingrese minuto:",0,59);
            segundo = leerNumero(teclado,"Ingrese segundo:",0,59);

        } while(!Fecha.comprobarFecha(dia,mes,anio) || !Fecha.comprobarHora(hora,minuto,segundo));

        return new Fecha(dia, mes, anio, hora, minuto, segundo);
    }

    /**
     * TODO: Imprime por pantalla el String pasado por parámetro
     * @param teclado
     * @param s
     * @return devuelve el variable string pasado por parametro
     */
    public static String leerCadena(Scanner teclado, String s) {
        System.out.print(s);
        return teclado.next();
    }
}