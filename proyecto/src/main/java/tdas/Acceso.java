package tdas;

/**
 * Clase que representa el objeto acceso a un documento
 * @author Daniel Catalán
 * @version java 11
 */
public class Acceso {
    private String nombreUsuario;
    private char tipoAcceso;

    // Constructor

    /**
     * Constructor de la clase Acceso
     * @param n nombre del usuario con acceso
     * @param t tipo de acceso sobre el documento
     */
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

    // Otros
    /**
     * Convierte a string el objeto acceso
     * @return string con la información del objeto acceso
     */
    public String toString(){
        String salida = "\t";
        salida = salida + this.nombreUsuario + " - " + this.tipoAcceso;
        return salida;
    }
}
