package tdas;

public class Fecha {
    // Atributos
    public int day;
    public int month;
    public int year;
    public String formated;

    // Metodos
    // Constructor
    public Fecha(int d, int m, int y){
        this.day = d;
        this.month = m;
        this.year = y;
    }

    // Formatear la fecha
    public String formatearFecha(Fecha x){
        String salida = "";
        salida = salida + Integer.toString(x.day) + " - ";
        salida = salida + Integer.toString(x.month) + " - ";
        salida = salida + Integer.toString(x.year) + ".";
        return salida;
    }

    // Copiar una fecha
    public Fecha cpyFecha(Fecha x){
        Fecha salida = new Fecha(x.day, x.month, x.year);
        return salida;
    }

    // setter
    /*public void settFormated(){
        this.formated = formatearFecha(this);
    }*/

    // Otros
    public void printFecha(){
        System.out.println(this.formated);
    }
}
