package tdas;

import java.util.ArrayList;

/** Clase que representa el objeto usuario
 * @autor Daniel Catalán
 * @version java 11
 */
public class Usuario {
    private String nombre;
    private int id;
    private String password;
    private Fecha fechaRegistro;
    private ArrayList<Documento> listaDocumentos;
    private static int cont;

    // Inicializar clase

    /**
     * Iniciliza la clase usuario para que parta contando desde 0
     */
    public static void iniciarClaseUsuario(){
        Usuario.cont = 0;
    }

    // Constructor

    /**
     * Constructor de la clase usuario
     * @param nombre nombre del usuario
     * @param password clave del usuario
     * @param fechaRegistro fehca de registro del usuario
     */
    public Usuario(String nombre, String password, Fecha fechaRegistro) {
        this.nombre = nombre;
        this.id = Usuario.cont + 1;
        Usuario.cont++;
        this.password = password;
        this.fechaRegistro = fechaRegistro;
        this.listaDocumentos = new ArrayList<Documento>(0);
    }

    // Getters

    /**
     * métodos para obtener los atributos de los objetos instanciados
     */

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

    /**
     * métodos para cambiar los atributos de los objetos instanciados
     */

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

    /**
     * Transforma toda la información de los documentos de un usuario a string
     * @param modo modo de selección de información
     *             Si es 1: Selecciona solo versiones activas
     *             Si es 2: Selelecciona todas las versiones
     * @return String con toda la info concadenada
     */
    public String listaDocumentosToString(int modo){
        String salida = "";
        int n = this.listaDocumentos.size();
        if(modo == 1){  // Modo usuario -> Mostrar solo versiones activas
            for (int i = 0; i < n; i++){
                Documento actual = this.listaDocumentos.get(i);
                // Agregar solo si es la version activa
                if(actual.isEsVersionActiva()){
                    salida += actual.toString();
                }
            }
        }else{ // modo = 2 -> Mostrar todas las versiones
            for (int i = 0; i < n; i++){
                Documento actual = this.listaDocumentos.get(i);
                salida += actual.toString();
            }
        }

        return salida;
    }

    /**
     * Imprime por pantalla la lista de documentos de un usuario dado un modo de selección
     *  1 para que imprima solo los casos activos
     *  2 para que imprima todos los documentos
     * @param modo modo de selección de documentos
     */
    public void printNombresDocumentos(int modo){
        ArrayList<Documento> listaDoc = this.getListaDocumentos();
        int n = listaDoc.size();

        if(modo == 1){  // Mostrar solo casos activos
            for(int i = 0; i < n; i++){
                Documento actual = listaDoc.get(i);
                if(actual.isEsVersionActiva()){
                    System.out.println("\t" + Integer.toString(i) + ". " + actual.getNombre() + "\n");
                }
            }
        }else{  // Mostrar todos los casos
            for(int i = 0; i < n; i++){
                System.out.println("\t" + Integer.toString(i) + ". " + listaDoc.get(i).getNombre() + "\n");
            }
        }

    }

    /**
     * Crea un documento dentro de la lista de documentos del usuario
     * @param nombre nombre del documento
     * @param contenido contenido del docuemento
     * @param fechaCreacion fecha de creación del documento
     * @param creador nombre del creador (nombre de usuario) del documento
     */
    public void crearDocumento(String nombre, String contenido, Fecha fechaCreacion, String creador){
        this.listaDocumentos.add(new Documento(nombre, contenido, fechaCreacion, creador));
    }

    /**
     * Verifica si el usuario instanciado puede agregar texto sobre el documento pasado por parámetro
     * @param x Documento que se quiere saber si el usuario tiene permiso sobre el
     * @return booleano que da respuesta a la pregunta
     *  true en caso de que el usuario puede editar
     *  false en caso de que el usuario no puede editar
     */
    public boolean puedeEditar(Documento x){
        // Recorrer lista de permisos
        int n = x.getListaAccesos().size();
        for(int i = 0; i < n; i++){
            Acceso actual = x.getListaAccesos().get(i);
            // Buscar coincidencias por nombre de usuario
            if(this.nombre.equals(actual.getNombreUsuario())) {
                //System.out.println("Se ha encontrado una coincidencia");  // Error - no está encontrando las coincidencias
                if(actual.getTipoAcceso() == 'w'){
                   return true;
                }else{
                    // Para no seguir buscando más casos cuando ya se sabe que no tiene acceso
                    System.out.println("El usuario no tiene permisos de escritura");
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Verifica si el usuario instanciado es creador del documento que se pasa por parámetro
     * @param x documento que se quiere saber si el usuario instanciado es creador o no
     * @return booleano que da respuesta a la pregunta
     * true en caso de que el usuario es creador del documento
     * false en caso de que el usuario no es creador del documento
     */
    public boolean esCreador(Documento x){
        if(this.nombre.equals(x.getCreador())){
            return true;
        }
        return false;
    }

    /**
     * Busca un documento dentro de la lista de documentos del usuario dada un id pasado por parámetro
     * @param id id del documento que se quiere buscar
     * @return objeto documento encontrado
     */
    public Documento buscarDocumento(int id){
        ArrayList<Documento> listaDoc = this.listaDocumentos;
        int n = listaDoc.size();
        for(int i = 0; i < n; i++){
            Documento actual = listaDoc.get(i);
            if(actual.getId() == id){
                return actual;
            }
        }
        return null;
    }

    /**
     * Restaura la versión anterior de un documento dado un id del documento actual
     * Elimina el documento encontrado y coloca como caso activo el de la id anterior
     * @param id id del documento que se le quiere restaurar una versión anterior
     */
    public void restoreVersion(int id){
        Documento actual = this.buscarDocumento(id); // Buscar documento por id
        int idAnterior = actual.getVersionAnterior(); // Obtener id de la versión anterior
        this.listaDocumentos.remove(actual);  // Remover objeto actual

        // Obtener el índice de la versión anterior
        int indice = this.listaDocumentos.indexOf(this.buscarDocumento(idAnterior));

        // Cambiar la versión anterior como la versión activa del documento
        this.listaDocumentos.get(indice).setEsVersionActiva(true);

    }


}
