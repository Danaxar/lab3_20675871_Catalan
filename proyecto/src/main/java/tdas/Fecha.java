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

    public Fecha(String formateado){
        String[] partes = formateado.split("-");

        int day = Integer.parseInt(partes[0]);
        int month = Integer.parseInt(partes[1]);
        int year = Integer.parseInt(partes[2]);

        this.day = day;
        this.month = month;
        this.year = year;
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
    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setFormated(String formated) {
        this.formated = formated;
    }

    // Otros
    public void printFecha(){
        System.out.println(this.formated);
    }
}
