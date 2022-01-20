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
        for(int i = 0; i < 0; i++){
            Acceso actual = x.getListaAccesos().get(i);

            if(this.nombre.equals(actual.getNombreUsuario())) {
                if(actual.getTipoAcceso() == 'w'){
                   return true;
                }else{
                    // Para no seguir buscando más casos cuando ya se sabe que no tiene acceso
                    return false;
                }
            }
        }

        return false;
    }


}
