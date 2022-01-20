package tdas;

import java.util.ArrayList;

public class Editor {
    private String nombre;  // Nombre del editor
    private Fecha date;
    private String sesionActiva;  // Nombre del usuario activo
    private boolean sesionIniciada;  // True -> sesión iniciada
    private ArrayList<Usuario> listaUsuarios;




    // Constructor
    public Editor() {
        this.sesionActiva = "";
        this.sesionIniciada = false;
        this.listaUsuarios = new ArrayList<Usuario>(0);
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return this.listaUsuarios;
    }

    public Fecha getDate() {
        return date;
    }

    public String getSesionActiva() {
        return sesionActiva;
    }

    public boolean isSesionIniciada() {
        return sesionIniciada;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDate(Fecha date) {
        this.date = date;
    }

    public void setSesionIniciada(boolean sesionIniciada) {
        this.sesionIniciada = sesionIniciada;
    }

    // Otros
    // Verificar si existe un usuario en la plataforma (por nombre)
    // Los nombres deben ser únicos
    public boolean existeUsuario(String nombre){
        int n = this.listaUsuarios.size();
        for(int i = 0; i < n; i++){
            String nombreActual = this.listaUsuarios.get(i).getNombre();
            // Si los nombre son iguales, retornar true
            if(nombreActual.equals(nombre)){
                return true;
            }
        }
        // Si llega hasta acá entonces el usuario no existe
        return false;
    }

    public Usuario buscarUsuario(String nombre){
        int n = this.listaUsuarios.size();
        for(int i = 0; i < n; i++){
            Usuario actual = this.listaUsuarios.get(i);
            // Si los nombre son iguales, retornar true
            if(actual.getNombre().equals(nombre)){
                return actual;
            }
        }
        // Si llega hasta acá entonces el usuario no existe
        return null;
    }

    // Iniciar sesión
    public void iniciarSesion(String nombre, String pass){
        Usuario encontrado = this.buscarUsuario(nombre);
        if(encontrado != null && encontrado.getPassword().equals(pass)){
            System.out.println("Inicio de sesion exitoso");
            this.sesionActiva = nombre;
            this.sesionIniciada = true;
        }else{
            System.out.println("Inicio de sesion fallido");
        }
    }

    public void cerrarSesion(){
        this.sesionActiva = "";
        this.sesionIniciada = false;
    }

    // Buscar el id más alto de usuario
    public int getMaxUserId(){
        int n = this.listaUsuarios.size();
        int max = 0;
        // Recorrer lista de usuarios
        for(int i = 0; i < n; i++){
            int idActual = this.listaUsuarios.get(i).getId();
            if(idActual >= max){
                max = idActual;
            }
        }
        return max;
    }

    // Imprimir la los nombres de los usuarios registrados
    public void printListaUsuarios(){
        int n = this.listaUsuarios.size();
        for(int i = 0; i < n; i++){
            Usuario actual = this.listaUsuarios.get(i);
            System.out.println("\t" + actual.getNombre());
        }
        return;
    }

    public String listaUsuariosToString(){
        String salida = "";
        ArrayList<Usuario> listaUsuarios = this.getListaUsuarios();
        int n = listaUsuarios.size();
        for(int i = 0; i < n; i++){
            salida += "\t";
            salida += listaUsuarios.get(i).getNombre();  // Obtener el nombre
            salida += "\n";
        }

        return salida;
    }



    // Registrar a un usuario
    public void register(String nombre, String pass){
        // Verificar que no exista el nombre
        if(this.existeUsuario(nombre)){
            System.out.println("El nombre de usuario ya existe" +
                    " por favor escoja otro.");
            return;
        }
        // Registrar al usuario
        //int max_id = this.getMaxUserId();
        Usuario nuevoUsuario = new Usuario(nombre, pass, this.date);
        this.listaUsuarios.add(nuevoUsuario);
    }



    // Mostrar por pantalla toda la info del editor
    public void printEditor(){
        System.out.println(this.nombre + "\n" + "Fecha: ");
        System.out.println(this.date.formatearFecha());
        System.out.println("Sesion Activa: " +
                this.sesionActiva + "\n" +
                "Usuarios registrados: ");


        System.out.println("XD");

        // Lista de usuarios registrados en el sistema
        System.out.println(this.listaUsuariosToString());

        // Lista de documentos del usuario
        System.out.println(this.buscarUsuario(this.sesionActiva).listaDocumentosToString());
        return;
    }


}
