package tdas;

public class Acceso {
    private String nombreUsuario;
    private char tipoAcceso;

    // Constructor
    public Acceso(String n, char t){
        this.nombreUsuario = n;
        this.tipoAcceso = t;
    }

    // Getters
    // Obtener el nombre de usuario del acceso
    public String getNombreUsuario(){
        return this.nombreUsuario;
    }
    // Obtener el tipo de acceso del acceso
    public char getTipoAcceso(){
        return this.tipoAcceso;
    }

    // Setters
    public void cambiarAcceso(char y){
        this.tipoAcceso = y;
    }

    // Otros
    // Convertir a string el acceso
    public String toString(){
        String salida = "\t";
        salida = salida + this.nombreUsuario + " - " + this.tipoAcceso;
        return salida;
    }
}
