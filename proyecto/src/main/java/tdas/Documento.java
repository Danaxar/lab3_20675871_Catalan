package tdas;

import java.util.ArrayList;

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
    public static void activarClaseDoc(){
        Documento.contador = 0;
    }

    // Crear un documento nuevo
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
    public Documento(Documento x){
        this.id = x.getId();
        this.nombre = x.getNombre();
        this.contenido = x.getContenido();
        this.fechaCreacion = x.getFechaCreacion();
        this.fechaModificacion = x.getFechaModificacion();
        this.listaAccesos = x.getListaAccesos();
        this.versionAnterior = x.getVersionAnterior();
        this.esVersionActiva = x.isEsVersionActiva();
    }

    // Getters
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
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setFechaCreacion(Fecha fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaModificacion(Fecha fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
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
    /*
    public Documento agregarTexto(){
        Documento salida = new Documento(getId(this),
                getNombre(this),
                )
    }
    */

    public void printDocumento(){
        System.out.println("Nombre: " + this.nombre + "ID: " +
                Integer.toString(this.id) +  "\n\n" + this.contenido +
                "\n\n" );
    }

    public void agregarAcceso(Acceso x){
        this.listaAccesos.add(x);
    }


    public String toString(){
        String salida = "Documento:\n\tNombre: ";
        salida += this.getNombre() + "\n\tCreador";
        salida += this.getCreador() + "\n\tVersion: ";
        salida += Integer.toString(this.getId()) + "\n\tVersion anterior: ";
        salida += Integer.toString(this.getVersionAnterior()) + "\n\tFecha de creacion: ";
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

    public boolean puedeCompartir(String nombre){
        if(this.creador.equals(nombre)){
            return true;
        }
        return false;
    }

}
