package tdas;

/**
 * Clase que representa el objeto fecha del sistema
 * @autor Daniel Catalán
 * @version java 11
 */
public class Fecha {
    // Atributos
    private int day;
    private int month;
    private int year;
    private String formated;

    // Metodos
    // Constructor

    /**
     * Constructor del objeto fecha
     * @param d dia
     * @param m mes
     * @param y año
     */
    public Fecha(int d, int m, int y){
        this.day = d;
        this.month = m;
        this.year = y;
    }

    /**
     * Constructor sobrecargado del objeto fecha
     * @param formateado fecha en formato DD/MM/YYYY
     */
    public Fecha(String formateado){
        // "yy/MM/dd HH:mm:ss"
        String[] partes = formateado.split("/");

        int day = Integer.parseInt(partes[0]);
        int month = Integer.parseInt(partes[1]);

        this.day = day;
        this.month = month;


        // partes[2] = dd HH:mm:ss
        String[] ultimaParte = partes[2].split(" ");
        int year = Integer.parseInt(ultimaParte[0]);
        this.year = year;

        this.formated = this.formatearFecha();
    }

    // Formatear la fecha

    /**
     * Retorna la fecha representación en string de sus valores numéricos en formato
     * DD/MM/YYYY
     * @return Fecha formateada en formato string
     */
    public String formatearFecha(){
        String salida = "";
        salida = salida.concat(String.valueOf(this.day));
        salida = salida.concat(" - ");
        salida = salida.concat(String.valueOf(this.month));
        salida = salida.concat(" - ");
        salida = salida.concat(String.valueOf(this.year));
        return salida;
    }

    /**
     * Retorna la fecha en formato formateado (string)
     * @return fecha en formato string con formato
     */
    public String getFormated() {
        return formated;
    }
}
