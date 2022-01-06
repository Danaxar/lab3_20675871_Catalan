package tdas;

import java.util.ArrayList;

public class Usuario {
    public String nombre;
    public int id;
    public String password;
    public Fecha fechaRegistro;
    public ArrayList<Documento>;

    public Usuario(String nombre, int id, String password, Fecha fechaRegistro) {
        this.nombre = nombre;
        this.id = id;
        this.password = password;
        this.fechaRegistro = fechaRegistro;
    }

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


}
