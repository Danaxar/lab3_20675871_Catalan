package tdas;

import java.util.ArrayList;
/**
 * Clase que representa el sistema que almacena y administra todos los documentos y las interacciones con el usuario
 * @version 11, 22/01/21
 * @autor Daniel Catalán
 * @see Usuario*/
public class Editor {
    private String nombre;  // Nombre del editor
    private Fecha date;
    private String sesionActiva;  // Nombre del usuario activo
    private boolean sesionIniciada;  // True -> sesión iniciada
    private ArrayList<Usuario> listaUsuarios;


    // Constructor

    /**
     * Construye el objeto Editor
     */
    public Editor() {
        this.sesionActiva = "";
        this.sesionIniciada = false;
        this.listaUsuarios = new ArrayList<Usuario>(0);
    }

    // Getters

    /**
     * Los siguientes métodos retornan el atributo del objeto instanciado
     */

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
    /**
     * los siguientes métodos modifican el valor de los atributos del objeto instanciado
     * */

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
    /**
     * Verificar si existe un usuario en la plataforma (por nombre)
     * Los nombres deben ser únicos
     * @param nombre es el nombre del usuario que se quiere consultar si existe en la plataforma
     */
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

    /**
     * Busca un usuario dado un nombre y retorna el objeto usuario encontrado
     * @param nombre es el nombre del usuario que se quiere buscar
     * @return Usuario retorna un objeto de tipo Usuario
     */
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

    /**
     * Inicia sesión en la plataforma, marcando como true la verificación de que la sesión está iniciada
     * y agregando su nombre como atributo del nombre de usuario que ha iniciado sesión
     * @param nombre nombre de usuario que quiere entrar en el editor
     * @param pass contraseña del usuario que quiere entrar en el editor
     */
    public void iniciarSesion(String nombre, String pass){
        Usuario encontrado = this.buscarUsuario(nombre);
        if(encontrado != null && encontrado.getPassword().equals(pass)){
            System.out.println("\nInicio de sesion exitoso!\n");
            this.sesionActiva = nombre;
            this.sesionIniciada = true;
        }else{
            System.out.println("\nInicio de sesion fallido\n");
        }
    }

    /**
     * Cierra cesión en la plataforma quitando el nombre del usuario y marcando el verificador como falso
     */
    public void cerrarSesion(){
        this.sesionActiva = "";
        this.sesionIniciada = false;
    }

    /**
     * Retorna la información de todos los usuarios en un string
     * @return Devuelve la info en string
     */

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


    /**
     * Registrar a un usuario en la plataforma
     * @param nombre nombre del usuario que se quiere registrar
     * @param pass clave del usuario que se quiere registrar
     */
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

    /**
     * Retorna en string toda la info del editor desde la vista del usuario
     * @return Información en string
     */
    public String editorUsuarioToString(){
        String salida = "";
        salida = salida.concat(this.nombre + "\n" + "Fecha: " + this.date.formatearFecha() + "\n");
        Usuario actual = this.buscarUsuario(this.sesionActiva);
        salida = salida.concat("Sesion Activa:" + "\n\tNombre de usuario: " + actual.getNombre() + "\n\t" +
                "Id: " + Integer.toString(actual.getId()) + "\n\t" + "Password: " +
                actual.getPassword() + "\n\t" + "Fecha de registro: " + actual.getFechaRegistro().getFormated() +
                "\n\n");
        // + "\n" + "Usuarios registrados: ");
        //salida = salida.concat(this.listaUsuariosToString()); // Lista de usuarios registrados en el sistema

        // Lista de documentos del usuario -> Mostrar solo los activos
        salida = salida.concat(this.buscarUsuario(this.sesionActiva).listaDocumentosToString(1));
        return salida;
    }

    /**
     * Retorna en string toda la info del editor de todos los usuarios
     * @return Información en string
     */
    public String editorGeneralToString(){
        String salida = "";
        salida = salida.concat(this.nombre + "\n" + "Fecha: " + this.date.formatearFecha() + "\n");
        salida = salida.concat("Sesion Activa: " + this.sesionActiva + "\n" + "Usuarios registrados:\n");
        salida = salida.concat(this.listaUsuariosToString()); // Lista de usuarios registrados en el sistema

        // Mostrar todos los documentos de todos los usuarios registrados
        for(int i = 0; i < this.listaUsuarios.size(); i++){
            Usuario actual = this.listaUsuarios.get(i);
            salida = salida.concat("\n# " + actual.getNombre() + " #\n");
            salida = salida.concat(actual.listaDocumentosToString(2)); // Lista de documentos del usuario
            salida = salida.concat("\n\n");
        }

        return salida;
    }

    /**
     * Imprime por pantalla la info del sistema en string
     * @param info información del editor que tiene que ser mostrada por pantalla con formato
     */
    public void imprimirEditor(String info){
        System.out.println(info);
    }



}
