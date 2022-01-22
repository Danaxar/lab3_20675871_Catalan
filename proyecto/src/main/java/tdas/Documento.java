package tdas;

import java.util.ArrayList;

/**
 * Clase que representa el objeto documento
 * @autor Daniel Catalán
 * @version java 11
 */
public class Documento {
    // Atributos
    private int id; 
    private String nombre;  
    private String contenido; 
    private Fecha fechaCreacion; 
    private Fecha fechaModificacion; 
    private ArrayList<Acceso> listaAccesos; 
    private int versionAnterior; 
    private boolean esVersionActiva; 
    private String creador;
    private static int contador;


    // Activar clase documento

    /**
     * Inicializa la clase objeto para que tenga un contador desde 0
     */
    public static void activarClaseDoc(){
        Documento.contador = 0;
    }

    // Crear un documento nuevo

    /**
     * Constructor de la clase documento
     * @param nombre nombre del docoumento
     * @param contenido contenido del documento
     * @param fechaCreacion fecha de creación del documento
     * @param creador nombre del creador del documento
     */
    public Documento(String nombre, String contenido, Fecha fechaCreacion, String creador) {
        this.id = Documento.contador + 1;   // Id
        Documento.contador++;
        
        this.nombre = nombre;  // Nombre
        this.contenido = contenido;  // Contenido
        this.fechaCreacion = fechaCreacion;  // Fecha de creación
        this.fechaModificacion = fechaCreacion;    // Fecha de modificación 
        this.listaAccesos = new ArrayList<Acceso>(1);  // Lista de accesos
        this.listaAccesos.add(new Acceso(creador, 'w'));
        this.versionAnterior = -1;  // versión anterior
        this.esVersionActiva = true;  // Es version activa
        this.creador = creador; // Creador del documento
    }

    // Copiar un documento

    /**
     * Constructor que se utiliza para copiar la información de un objeto a otro
     * @param x objeto documento
     */
    public Documento(Documento x){
        this.id = x.getId();
        this.nombre = x.getNombre();
        this.contenido = x.getContenido();
        this.fechaCreacion = x.getFechaCreacion();
        this.fechaModificacion = x.getFechaModificacion();
        this.listaAccesos = x.getListaAccesos();
        this.versionAnterior = x.getVersionAnterior();
        this.esVersionActiva = x.isEsVersionActiva();
        this.creador = x.getCreador();
    }

    // Getters

    /**
     * Getters de la clase objeto, retornan los atributos del objeto instanciado
     */

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContenido() {
        return contenido;
    }

    public Fecha getFechaCreacion() {
        return fechaCreacion;
    }

    public Fecha getFechaModificacion() {
        return fechaModificacion;
    }

    public ArrayList<Acceso> getListaAccesos() {
        return listaAccesos;
    }

    public int getVersionAnterior() {
        return versionAnterior;
    }

    public boolean isEsVersionActiva() {
        return esVersionActiva;
    }

    public String getCreador(){
        return creador;
    }

    // setters

    /**
     * Setters de la clase objeto, cambian los atributos del objeto instanciado
     */

    public void setId(int id) {
        this.id = id;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setListaAccesos(ArrayList<Acceso> listaAccesos) {
        this.listaAccesos = listaAccesos;
    }

    public void setVersionAnterior(int versionAnterior) {
        this.versionAnterior = versionAnterior;
    }

    public void setEsVersionActiva(boolean esVersionActiva) {
        this.esVersionActiva = esVersionActiva;
    }


    // Otros

    /**
     * Agrega un acceso a un documento
     * @param x objeto acceso
     */
    public void agregarAcceso(Acceso x){
        this.listaAccesos.add(x);
    }

    /**
     * Transforma la información de un documento a string
     * @return String con la información del documento
     */
    public String toString(){
        String salida = "Documento:\n\tNombre: ";
        salida += this.getNombre() + "\n\tCreador: ";
        salida += this.getCreador() + "\n\tVersion: ";
        salida += Integer.toString(this.getId()) + "\n\tVersion anterior: ";
        salida += Integer.toString(this.getVersionAnterior()) + "\n\tFecha de creacion    : ";
        salida += this.getFechaCreacion().formatearFecha() + "\n\tFecha de modificacion: ";
        salida += this.getFechaModificacion().formatearFecha() + "\n\tVersion activa: ";  // Aqui está el error
        if(this.esVersionActiva){
            salida += "Si";
        }else{
            salida += "No";
        }
        salida += "\n\tLista de accesos:\n";

        // Obtener la lista de accesos
        ArrayList<Acceso> listaAccesos = this.getListaAccesos();
        int n = listaAccesos.size();
        for(int i = 0; i < n; i++){
            salida += "\t" + listaAccesos.get(i).toString() + "\n";
        }

        salida += "\tContenido:\n\t\t" + this.getContenido();

        salida += "\n\n";
        return salida;

    }

    /**
     * Método que verifica si el nombre de una persona sobre el documento instanciado tiene permisos para compartir
     * @param nombre nombre de usuario que desea compartir
     * @return booleano de respuesta a la pregunta
     * true en caso de que la persona pueda compartir
     * false en caso de que la persona no puede compartir
     */
    public boolean puedeCompartir(String nombre){
        if(this.creador.equals(nombre)){
            return true;
        }
        return false;
    }

    /**
     * Método que verifica si el documento instanciado se puede restaurar a una versión anterior o no
     * @return booleano con respuesta a la pregunta
     * true en caso de que el documento se puede restaurar 1 versión atrás
     * false en caso de que el objeto no se puede restaurar 1 versión atrás (está en su versión inicial)
     */
    public boolean puedeRestaurar(){
        if(this.id == -1){
            return false;
        }
        return true;
    }

    /**
     * Método que verificac si el texto pasado por parámetro está contenido dentro del contenido del documento
     * instanciado
     * @param texto texto que se desea buscar dentro del contenido del documento
     * @return booleano de respuesta a la pregunta
     * true en caso de que el texto está contenido dentro del contenido del documento
     * false en caso contrario
     */
    public boolean contieneTexto(String texto){
        return this.contenido.contains(texto);
    }

}
