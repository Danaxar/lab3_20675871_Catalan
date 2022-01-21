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

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String getFormated() {
        return formated;
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
