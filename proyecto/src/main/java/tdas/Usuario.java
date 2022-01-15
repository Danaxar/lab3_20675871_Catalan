package tdas;

import java.util.ArrayList;

public class Usuario {
    private String nombre;
    private int id;
    private String password;
    private Fecha fechaRegistro;
    private ArrayList<Documento> listaDocumentos;

    // Constructor
    public Usuario(String nombre, int id, String password, Fecha fechaRegistro) {
        this.nombre = nombre;
        this.id = id;
        this.password = password;
        this.fechaRegistro = fechaRegistro;
        this.listaDocumentos = new ArrayList<Documento>(0);
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public Fecha getFechaRegistro() {
        return fechaRegistro;
    }

    public ArrayList<Documento> getListaDocumentos() {
        return listaDocumentos;
    }

    // Setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFechaRegistro(Fecha fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void setListaDocumentos(ArrayList<Documento> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    // Otros
    // Mostrar la lista de documentos
    public void printListaDocumentos(){
        int n = this.listaDocumentos.size();
        for (int i = 0; i < n; i++){
            Documento actual = this.listaDocumentos.get(i);
            actual.printDocumento();
        }
    }


}
