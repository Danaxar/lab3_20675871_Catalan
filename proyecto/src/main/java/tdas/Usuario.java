package tdas;

import java.util.ArrayList;

public class Usuario {
    private String nombre;
    private int id;
    private String password;
    private Fecha fechaRegistro;
    private ArrayList<Documento> listaDocumentos;
    private static int cont;

    // Inicializar clase
    public static void iniciarClaseUsuario(){
        Usuario.cont = 0;
    }

    // Constructor
    public Usuario(String nombre, String password, Fecha fechaRegistro) {
        this.nombre = nombre;
        this.id = Usuario.cont + 1;
        Usuario.cont++;
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

    public String listaDocumentosToString(){
        String salida = "";
        int n = this.listaDocumentos.size();
        for (int i = 0; i < n; i++){
            Documento actual = this.listaDocumentos.get(i);
            salida += actual.toString();
        }
        return salida;
    }

    public void printNombresDocumentos(){
        ArrayList<Documento> listaDoc = this.getListaDocumentos();
        int n = listaDoc.size();
        for(int i = 0; i < n; i++){
            System.out.println("\t" + Integer.toString(i) + ". " + listaDoc.get(i).getNombre() + "\n");
        }
    }

    public void crearDocumento(String nombre, String contenido, Fecha fechaCreacion, String creador){
        this.listaDocumentos.add(new Documento(nombre, contenido, fechaCreacion, creador));
    }

    public boolean puedeEditar(Documento x){
        // Recorrer lista de permisos
        int n = x.getListaAccesos().size();
        for(int i = 0; i < n; i++){
            Acceso actual = x.getListaAccesos().get(i);
            // Buscar coincidencias por nombre de usuario
            if(this.nombre.equals(actual.getNombreUsuario())) {
                System.out.println("Se ha encontrado una coincidencia");  // Error - no está encontrando las coincidencias
                if(actual.getTipoAcceso() == 'w'){
                    System.out.println("El usuario tiene permisos de escritura");
                   return true;
                }else{
                    // Para no seguir buscando más casos cuando ya se sabe que no tiene acceso
                    System.out.println("El usuario no tiene permisos de escritura 1");
                    return false;
                }
            }
        }

        System.out.println("El usuario no tiene permisos de escritura 2");
        return false;
    }


    public boolean esCreador(Documento x){
        if(this.nombre.equals(x.getCreador())){
            return true;
        }
        return false;
    }

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

    public void restoreVersion(Documento x){
        // Hay que eliminar el objeto pasado por entrada y marcar como activo el ultimo
        int idAnt = x.getVersionAnterior();
        // Eliminar objeto documento de la lista
        this.listaDocumentos.remove(x);

        // Buscar la nueva última versión
        this.buscarDocumento(idAnt).setEsVersionActiva(true);
    }


}
