import tdas.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Principal {

    // Funciones de interacción con el programa
    public static int mostrarOpcionesInicio(){
        System.out.print("Que desea hacer? (Ingrese el numero de su opcion)\n" +
                "\t1. Iniciar Sesion\n" +
                "\t2. Registrarse\n" +
                "\t3. Visualizar sistema\n" +
                "\t4. Cerrar el programa\n>>> ");

        Scanner respuesta = new Scanner(System.in);
        int respuesta_out = respuesta.nextInt();
        // Si la respuesta no es válida -> pedir entrada de nuevo
        if(respuesta_out != 1 && respuesta_out != 2 && respuesta_out != 3 && respuesta_out != 4){
            System.out.println("Respuesta invalida, por favor ingrese 1, 2 o 3. según su preferencia.");
            return mostrarOpcionesInicio();
        }
        // Si la entrada es correcta se retorna la elección
        return respuesta_out;
    }


    public static int mostrarOpcionesSistema() {
        // ###Mostrar por pantalla el nombre de usuario logueado

        System.out.print("\nQue desea hacer? (Ingrese el numero de su opcion)\n" +
                "\t1. Crear nuevo documento\n" +
                "\t2. Compartir documento\n" +
                "\t3. Agregar contenido a un documento\n" +
                "\t4. Restaurar version de un documento\n" +
                "\t5. Revocar todos los accesos a un documento\n" +
                "\t6. Buscar en los documentos\n" +
                "\t7. Visualizar sistema\n" +
                "\t8. Cerrar sesion\n" +
                "\t9. Cerrar el programa\n>>> "
        );

        Scanner respuesta = new Scanner(System.in);
        int respuesta_out = respuesta.nextInt();
        // Si la respuesta no es válida -> pedir entrada de nuevo


        if(respuesta_out < 1 && respuesta_out > 9){
            System.out.println("Respuesta inválida, por favor ingrese 1. o 2. según su preferencia.");
            return mostrarOpcionesSistema();
        }
        // Si la entrada es correcta se retorna la elección
        return respuesta_out;
    }






    public static void main(String[] args){

        System.out.println("Ejecucion iniciada");
        Scanner leerEntrada = new Scanner(System.in);

        // Inicializar clases para los contadores (atributos static)
        Usuario.iniciarClaseUsuario();
        Documento.activarClaseDoc();

        // Crear tda sistema
        Editor sistema = new Editor();
        sistema.setNombre("Paradigmadocs");

        // Obtener la fecha actual del sistema
        DateTimeFormatter fechaActual = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss");
        String fechaActualFormateado = fechaActual.format(LocalDateTime.now());
        sistema.setDate(new Fecha(fechaActualFormateado));
        System.out.println("Fecha actual: " + sistema.getDate().getFormated());

        // Cargar 5 usuarios predeterminados al sistema
        sistema.register("Paula", "asdf");  // Registrando a un usuario
        sistema.register("Ximena", "1234");
        sistema.register("Juan", "kkk1");
        sistema.register("Nacho", "abcd");
        sistema.register("Daniel", "1212");

        // Cargando documentos al sistemas inicialmente
        // Paula
        sistema.buscarUsuario("Paula").
                crearDocumento("Doc1", "hola",
                        sistema.getDate(), "Paula");
        sistema.buscarUsuario("Paula").
                crearDocumento("Notas","Este es un documento de notas",
                        sistema.getDate(), "Paula");

        // Ximena
        sistema.buscarUsuario("Ximena").
                crearDocumento("Cosas","Aqui anoto cosas",
                        sistema.getDate(), "Ximena");
        sistema.buscarUsuario("Ximena").
                crearDocumento("Fechas","Aqui anoto fechas",
                        sistema.getDate(), "Ximena");

        // Juan
        sistema.buscarUsuario("Juan").
            crearDocumento("Transferencias","Aqui anoto transferencias",
                    sistema.getDate(), "Juan");
        sistema.buscarUsuario("Juan").
                crearDocumento("doc1","----",
                        sistema.getDate(), "Juan");

        // Nacho
        sistema.buscarUsuario("Nacho").
                crearDocumento("Claves gta","HESOYAM",
                        sistema.getDate(), "Nacho");
        sistema.buscarUsuario("Nacho").
                crearDocumento("Calificaciones","4 7 6 5",
                        sistema.getDate(), "Nacho");

        sistema.buscarUsuario("Daniel").
                crearDocumento("Notas paradigmas","7 7 7 7 7 7 7",
                        sistema.getDate(), "Daniel");
        sistema.buscarUsuario("Daniel").
                crearDocumento("Notas eda","7 7 7 7 7 7",
                        sistema.getDate(), "Daniel"); // Ojala fuera cierto :')


        // Inicio del programa
        boolean encendido = true;
        while(encendido){
            // Pantalla externa (Login - Register - Visualizar y Cerrar programa)
            int respuesta = mostrarOpcionesInicio();  // Muestra las opciones y pide entrada por consola

            if(respuesta == 1){ // Quiere iniciar sesión
                System.out.println("Iniciando sesion...");

                // Pedir nombre de usuario
                System.out.print("Ingrese el nombre de usuario: ");
                String nombreUser = leerEntrada.nextLine();

                // Pedir la contraseña
                System.out.print("Ingrese su clave: ");
                String passUser = leerEntrada.nextLine();

                // Iniciar la sesión en el sistema
                sistema.iniciarSesion(nombreUser, passUser);

                // Una vez iniciada la sesión se puede tener acceso las demás opciones
                while(sistema.isSesionIniciada()){  // Mientras la sesión esté iniciada
                    // Mostrar opciones y recibir respuesta del usuario
                    int opcionSistema = mostrarOpcionesSistema();

                    // Variables reutilizadas constantemente para disminuir sintaxis
                    Fecha fechaSis = sistema.getDate();
                    String nombSesionActiva = sistema.getSesionActiva();
                    Usuario userActivo = sistema.buscarUsuario(nombSesionActiva);

                    // Evaluar la opción del usuario
                    switch(opcionSistema){

                        case 1: // Crear un nuevo documento
                            System.out.println("Creando nuevo documento...");

                            // Pedir nombre del documento
                            System.out.print("Ingrese el nombre del documento: ");
                            String nombreDoc = leerEntrada.nextLine();

                            // Pedir contenido del documento
                            System.out.print("Ingrese el contenido del documento: ");
                            String contDoc = leerEntrada.nextLine();

                            // Agregar objeto documento a objeto usuario
                            userActivo.crearDocumento(nombreDoc, contDoc, fechaSis, nombSesionActiva);
                            break;


                        case 2: // Compartir un documento
                            System.out.println("Compartiendo documento...");
                            if(userActivo.getListaDocumentos().size() == 0){
                                System.out.println("Error, no existen documentos para compartir");
                                break;
                            }

                            // Escoger documento
                            System.out.println("Que documento deseas compartir?");
                            userActivo.printNombresDocumentos(1);
                            // Scanner leerNumDocumento = new Scanner(System.in);
                            System.out.print(">>> ");
                            int resp2 = leerEntrada.nextInt();
                            Documento doc = userActivo.getListaDocumentos().get(resp2);  // Obtener obj documento

                            // Verificar que el nombre del creador sea el mismo que el de la sesión iniciada
                            if(doc.getCreador().equals(nombSesionActiva) == false){
                                System.out.println("Error, usted no es el creador del documento");
                                break;
                            }else{
                                // Crear acceso
                                System.out.print("A quien quieres dar permiso?: ");
                                Scanner leerPermiso = new Scanner(System.in);
                                /*Por alguna razón usando el mismo objeto "leerEntrada" el sistema no dejaba tomar
                                * una entrada nueva para obtener el nombre de usuario al que se quiere compartir.
                                * Mi teoría es que leía automáticamente el salto de línea al presionar enter para
                                * poder escoger la opción de compartir, lo que inhabilitaba altiro la calidad
                                * de la respuesta del usuario imprimiendo por pantalla "Error, el usuario no existe"*/

                                // Escoger nombre de usuario
                                String usuarioConPermiso = leerPermiso.nextLine();
                                if(sistema.existeUsuario(usuarioConPermiso) == false){
                                    System.out.println("Error, el usuario no existe.");
                                    break;
                                }
                                // Escoger tipo de permiso (caracter)
                                System.out.print("Que tipo de permiso quieres darle? " +
                                        "(ingrese el caracter de su opcion)\n" +
                                        "\t'r': Lectura\n\t'w': Escritura\n\t'c': Comentarios\n>>> ");
                                char resp = leerPermiso.next().charAt(0);  // Leer carácter

                                // Modificar objeto documento
                                doc.agregarAcceso(new Acceso(usuarioConPermiso, resp));
                                // Agregar documento a la lista de documentos del usuario al que se comparte
                                sistema.buscarUsuario(usuarioConPermiso).getListaDocumentos().add(doc);
                                break;
                            }

                        case 3: // Agregar contenido a un documento
                            System.out.println("Agregando contenido");
                            if(userActivo.getListaDocumentos().size() == 0){  // Verificar existencia de documentos
                                System.out.println("No existen documentos para editar");
                                break;
                            }
                                                       
                            // Obtener numero de documento
                            System.out.println("Que documento deseas editar?");
                            userActivo.printNombresDocumentos(1);
                            System.out.print(">>> ");
                            int resp3 = leerEntrada.nextInt();

                            // Obtener objeto documento (actual)
                            Documento documentoAntiguo = userActivo.getListaDocumentos().get(resp3);
                            Documento newDocument = new Documento(documentoAntiguo);  // Copiar objeto en otro

                            // Verificar si el nombre de la persona tiene el permiso 'w'
                            if(userActivo.puedeEditar(documentoAntiguo) == false){
                                System.out.println("No tienes acceso a editar este documento");
                                break;
                            }else{  // -> Tiene permiso para editar
                                // Obtener contenido a agregar
                                System.out.print("Escribe lo que deseas agregar: ");
                                Scanner leerContenidoDoc = new Scanner(System.in);
                                String contenidoAgregar = leerContenidoDoc.nextLine();
                                /*Aqui ocurre el mismo error, se parchea haciendo un nuevo objeto Scanner,
                                * actualmente no manejo la razón de este error. */
                                
                                // Modificar nuevo documento
                                newDocument.setContenido(newDocument.getContenido() + contenidoAgregar);
                                newDocument.setId(documentoAntiguo.getId() + 1);
                                newDocument.setVersionAnterior(documentoAntiguo.getId());

                                // Hacer cambio en el sistema
                                userActivo.getListaDocumentos().add(newDocument);  // Agregar documento nuevo
                                userActivo.getListaDocumentos().get(resp3).setEsVersionActiva(false);

                                // Si la persona que agregó contenido no es el creador del documento
                                // Hay que agregar este nuevo objeto a la lista de documentos del creador
                                // Para que haya sincronización
                                if(userActivo.esCreador(newDocument) == false){
                                    sistema.buscarUsuario(newDocument.getCreador()).
                                            getListaDocumentos().add(newDocument);
                                }
                                break;
                            }

                        case 4: // Restaurar versión de un documento
                            System.out.println("Restaurando version anterior...");

                            // Verificar si existen documentos
                            if(userActivo.getListaDocumentos().size() == 0){
                                System.out.println("No existen documentos para restaurar");
                                break;
                            }

                            // Mostrar lista de documentos
                            System.out.println("Que documento deseas restaurar?");
                            userActivo.printNombresDocumentos(1);
                            System.out.print(">>> ");

                            // Obtener documento
                            //Scanner leerDocRestaurar = new Scanner(System.in);
                            int doc4 = leerEntrada.nextInt();
                            Documento actual = userActivo.getListaDocumentos().get(doc4);

                            // Verificar si el documento se puede restaurar
                            if(actual.puedeRestaurar()){
                                // Verificar si es creador del documento
                                if(actual.puedeCompartir(userActivo.getNombre())){
                                    System.out.println("Cambios hechos.");
                                    sistema.buscarUsuario(nombSesionActiva).restoreVersion(actual.getId());
                                }else{
                                    System.out.println("Error, usted no es el creador del documento");
                                }

                            }else{
                                System.out.println("Error, el documento no es restaurable");
                            }

                            break;
                        // Revocar acceso a un documento
                        case 5:
                            System.out.println("Revocando accesos del documento");
                            // Reemplazar la lista entera por una que contenga solo el acceso del creador

                            // Verificar si existen documentos
                            if(userActivo.getListaDocumentos().size() == 0){
                                System.out.println("No existen documentos para restaurar");
                                break;
                            }

                            // Mostrar lista de documentos
                            System.out.println("Que documento deseas revocar accesos?");
                            userActivo.printNombresDocumentos(1);  // Mostrar los documentos

                            // Obtener el documento actual y hacerle las modificaciones
                            int indexDoc5 = leerEntrada.nextInt();  // Obtener el indice del documento
                            Documento actual5 = userActivo.getListaDocumentos().get(indexDoc5);
                            // Crear nueva lista de accesos
                            ArrayList<Acceso> listaAccesosNuevo = new ArrayList<Acceso>(1);
                            listaAccesosNuevo.add(new Acceso(userActivo.getNombre(), 'w'));
                            // Hacer modificación en el sistema
                            sistema.buscarUsuario(nombSesionActiva).getListaDocumentos().
                                    get(indexDoc5).setListaAccesos(listaAccesosNuevo);
                            break;
                        // Buscar en los documentos
                        case 6:
                            System.out.println("Buscando en los documentos");
                            System.out.println("Que texto/palabra desea buscar?");
                            String texto = leerEntrada.nextLine();

                            ArrayList<Documento> lD = userActivo.getListaDocumentos();
                            ArrayList<Documento> encontrado = new ArrayList<Documento>(1);
                            int n = lD.size();
                            for(int i = 0; i < n; i++){
                                Documento actual6 = lD.get(i);
                                if(actual6.contieneTexto(texto)){
                                    encontrado.add(actual6);
                                }
                            }

                            if(encontrado.size() == 0){
                                System.out.println("No se ha encontrado ninguna coincidencia");
                            }else{
                                System.out.println("Documentos con coincidencias: ");
                                for(int i = 0; i < encontrado.size(); i++){
                                    System.out.println("ID: " + Integer.toString(encontrado.get(i).getId()) + ". "
                                    + encontrado.get(i).getNombre());
                                }
                            }
                            break;

                        case 7:
                            // Visualizar sistema desde el usuario
                            System.out.println("Visualizando Sistema");
                            sistema.imprimirEditor(sistema.editorUsuarioToString());
                            break;
                        // Cerrar sesión
                        case 8:
                            System.out.println("Cerrando sesion...");
                            sistema.cerrarSesion();
                            break;
                        // Cerrar el programa
                        case 9:
                            System.out.println("Cerrando el editor...");
                            encendido = false;
                            return;
                    }
                }

                // Mostrar opciones del sistema
            }if (respuesta == 2){
                // Quiere registrarse
                System.out.println("Registrando en la plataforma...");
                Scanner string = new Scanner(System.in);
                System.out.print("Ingrese el nombre de usuario: ");
                String nombreUsuario = string.nextLine();
                System.out.print("Ingrese la password: ");
                String password = string.nextLine();

                // Hacer el registro
                sistema.register(nombreUsuario, password);
            }else if(respuesta == 3){ // Visualizar el sistema desde fuera (sin iniciar sesión)
                sistema.imprimirEditor(sistema.editorGeneralToString());
            }else if(respuesta == 4){ // Quiere cerrar el programa
                System.out.println("Cerrando programa...");
                encendido = false;
            }
        }
        System.out.println("Ejecucion finalizada");
    }
}
