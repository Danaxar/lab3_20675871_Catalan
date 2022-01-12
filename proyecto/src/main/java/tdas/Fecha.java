package tdas;

public class Fecha {
    // Atributos
    private int day;
    private int month;
    private int year;
    private String formated;

    // Metodos
    // Constructor
    public Fecha(int d, int m, int y){
        this.day = d;
        this.month = m;
        this.year = y;
    }

    // Formatear la fecha
    public String formatearFecha(){
        String salida = "";
        salida = salida.concat(String.valueOf(this.day));
        salida = salida.concat(" - ");
        salida = salida.concat(String.valueOf(this.month));
        salida = salida.concat(" - ");
        salida = salida.concat(String.valueOf(this.year));
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
