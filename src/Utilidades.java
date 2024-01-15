import java.util.Scanner;

/**
 * Utilidades es una clase con la funcionalidad de leer números (fecha) y letras dependiendo del tipo de variable (int, double...).
 * Clase utilizada para números de tipo int long y double y asi como para leer letras de tipo char.
 * Se recomienda utilizar esta clase para definir cifras, mensajes, asi como fechas (siendo estas introducidas por un usuario)
 * como la fecha de salida o llegada de un envío.
 *
 * @author Iñaki Ramos Iturria
 * @author Cristiana Velislavova Tsekova
 * @version     1.0
 */
public class Utilidades {

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado Teclado por donde el usuario ficilita la información
     * @param mensaje Mensaje que se le muestra al usuario de la información que tiene que aportar
     * @param minimo  límite inferior que se permite de los datos pasados por parámetro
     * @param maximo  límite superior que se permite de los datos pasados por parámetro
     * @return Devuelve el carácter que cumple los límites y que ha introducido el usuario
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
     * @param teclado Teclado por donde el usuario ficilita la información
     * @param mensaje Mensaje que se le muestra al usuario de la información que tiene que aportar
     * @param minimo  límites inferior que se permite de los datos pasados por parámetro
     * @param maximo  límites superior que se permite de los datos pasados por parámetro
     * @return Devuelve el caracter que cumple los límites y que ha introducido el usuario
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
     * @param teclado Teclado por donde el usuario ficilita la información
     * @param mensaje Mensaje que se le muestra al usuario de la información que tiene que aportar
     * @param minimo  límites inferior que se permite de los datos pasados por parámetro
     * @param maximo  límites superior que se permite de los datos pasados por parámetro
     * @return Devuelve el caracter que cumple los límites y que ha introducido el usuario
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
     * @param teclado Teclado por donde el usuario ficilita la información
     * @param mensaje Mensaje que se le muestra al usuario de la información que tiene que aportar
     * @param minimo  límites inferior que se permite de los datos pasados por parámetro
     * @param maximo  límites superior que se permite de los datos pasados por parámetro
     * @return Devuelve el caracter que cumple los límites y que ha introducido el usuario
     */
    public static char leerLetra(Scanner teclado, String mensaje, char minimo, char maximo) {
        char letra;

        do {
            System.out.print(mensaje);
            letra = teclado.nextLine().charAt(0);
        }while (letra < minimo || letra > maximo);
        return letra;
    }

    /**
     * TODO: Solicita una fecha repetidamente hasta que se introduzca una correcta
     * @param teclado Teclado por donde el usuario facilita la información
     * @param mensaje Mensaje que se le muestra al usuario de la información que tiene que aportar
     * @return Devuelve el día, mes, año cuando sean correctos
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
     * @param teclado Teclado por donde el usuario facilita la información
     * @param mensaje Mensaje que se le muestra al usuario de la información que tiene que aportar
     * @return Devuelve una fecha con los datos pasados por parámetro
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

            if (!Fecha.comprobarFecha(dia,mes,anio) || !Fecha.comprobarHora(hora,minuto,segundo)){
                System.out.println();
                System.out.println("Fecha u hora introducida incorrecta.");
                System.out.println("Fecha de Salida:");
            }
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