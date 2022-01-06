package tdas;

public class Acceso {
    public String nombreUsuario;
    public char tipoAcceso;

    // Constructor
    public Acceso(String n, char t){
        this.nombreUsuario = n;
        this.tipoAcceso = t;
    }

    // Getters
    // Obtener el nombre de usuario del acceso
    public String getNombreUsuario(Acceso x){
        return x.nombreUsuario;
    }
    // Obtener el tipo de acceso del acceso
    public char getTipoAcceso(Acceso x){
        return x.tipoAcceso;
    }

    // Setters
    public void cambiarAcceso(Acceso x, char y){
        x.tipoAcceso = y;
    }

    // Otros
    // Convertir a string el acceso
    public String toString(){
        String salida = "";
        salida = salida + this.nombreUsuario + " - " + this.tipoAcceso;
        return salida;
    }
}
