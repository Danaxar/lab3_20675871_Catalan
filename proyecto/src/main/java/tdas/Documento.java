package tdas;

import java.util.ArrayList;

public class Documento {
    // Atributos
    public int id;
    public String nombre;
    private String contenido;
    public Fecha fechaCreacion;
    public Fecha fechaModificacion;
    public ArrayList<Acceso> listaAccesos;
    public int versionAnterior;
    public boolean esVersionActiva;


    public Documento(int id, String nombre, String contenido, Fecha fechaCreacion, Fecha fechaModificacion, ArrayList<Acceso> listaAccesos, int versionAnterior, boolean esVersionActiva) {
        this.id = id;
        this.nombre = nombre;
        this.contenido = contenido;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.listaAccesos = listaAccesos;
        this.versionAnterior = versionAnterior;
        this.esVersionActiva = esVersionActiva;
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
}
