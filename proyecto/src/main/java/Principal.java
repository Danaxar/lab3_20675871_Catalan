import tdas.*;

import java.util.ArrayList;
import java.util.Scanner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Principal {

    // Funciones de interacción con el programa
    public static int mostrarOpcionesInicio(){
        System.out.println("Que desea hacer?\n" +
                "\t1. Iniciar Sesion\n" +
                "\t2. Registrarse\n" +
                "\t3. Cerrar el programa\n");

        Scanner respuesta = new Scanner(System.in);
        int respuesta_out = respuesta.nextInt();
        // Si la respuesta no es válida -> pedir entrada de nuevo
        if(respuesta_out != 1 && respuesta_out != 2 && respuesta_out != 3){
            System.out.println("Respuesta invalida, por favor ingrese 1, 2 o 3. según su preferencia.");
            return mostrarOpcionesInicio();
        }
        // Si la entrada es correcta se retorna la elección
        return respuesta_out;
    }


    public static int mostrarOpcionesSistema() {
        // ###Mostrar por pantalla el nombre de usuario logueado

        System.out.println("Que desea hacer?\n" +
                "\t1. Crear nuevo documento\n" +
                "\t2. Compartir documento\n" +
                "\t3. Agregar contenido a un documento\n" +
                "\t4. Restaurar version de un documento\n" +
                "\t5. Revocar todos los accesos a un documento\n" +
                "\t6. Buscar en los documentos\n" +
                "\t7. Visualizar sistema\n" +
                "\t8. Cerrar sesion\n" +
                "\t9. Cerrar el programa\n"
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

        // Inicializar clases
        Usuario.iniciarClaseUsuario();
        Documento.activarClaseDoc();


        // Crear tda sistema
        Editor sistema = new Editor();

        System.out.println("Ingrese el nombre del sistema: ");
        //String nombreSistema = leerEntrada.nextLine();
        sistema.setNombre("Paradigmadocs");

        // Obtener la fecha actual del sistema
        // System.out.println("Ingrese la fecha del sistema: (DD-MM-YYYY)");
        DateTimeFormatter fechaActual = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss");
        String fechaActualFormateado = fechaActual.format(LocalDateTime.now());
        sistema.setDate(new Fecha(fechaActualFormateado));
        System.out.println("Fecha actual: " + sistema.getDate().getFormated());




        //sistema.setDate(new Fecha(leerEntrada.nextLine()));

        boolean encendido = true;
        while(encendido){

            // Pantalla externa (Login - Register)
            int respuesta = mostrarOpcionesInicio();
            if(respuesta == 1){
                // Quiere iniciar sesión
                System.out.println("Iniciando sesion...");
                Scanner string2 = new Scanner(System.in);
                System.out.println("Ingrese el nombre de usuario");
                String nombreUser = string2.nextLine();
                System.out.println("Ingrese la password");
                String passUser = string2.nextLine();

                sistema.iniciarSesion(nombreUser, passUser);

                // Una vez iniciada la sesión se puede tener acceso
                // A las demás opciones -> Otro loop
                while(sistema.isSesionIniciada()){
                    int opcionSistema = mostrarOpcionesSistema();
                    // Variables reutilizadas constantemente
                    Fecha fechaSis = sistema.getDate();
                    String nombSesionActiva = sistema.getSesionActiva();
                    Usuario userActivo = sistema.buscarUsuario(nombSesionActiva);

                    switch(opcionSistema){
                        // Crear un nuevo documento
                        case 1:
                            System.out.println("Creando nuevo documento.");
                            // Pedir entradas
                            Scanner leerDoc = new Scanner(System.in);

                            // Nombre del documento
                            System.out.println("Ingrese el nombre del documento: ");
                            String nombreDoc = leerDoc.nextLine();

                            // Contenido del documento
                            System.out.println("Ingrese el contenido del documento: ");
                            String contDoc = leerDoc.nextLine();

                            // Crear objeto documento
                            Documento newDoc = new Documento(nombreDoc, contDoc, fechaSis, nombSesionActiva);

                            // Obtener el usuario
                            Usuario user = sistema.buscarUsuario(sistema.getSesionActiva());
                            // Obtener su indice en la lista de usuarios
                            int index_user = sistema.getListaUsuarios().indexOf(user);

                            // Agregar objeto documento a objeto usuario
                            user.crearDocumento(nombreDoc, contDoc, fechaSis, nombSesionActiva);


                            // Modificar el sistema -> modificar usuario
                            sistema.getListaUsuarios().set(index_user, user);
                            break;

                        // Compartir un documento
                        case 2:
                            System.out.println("Compartiendo documento");
                            if(userActivo.getListaDocumentos().size() == 0){
                                System.out.println("No existen documentos para compartir");
                                break;
                            }

                            // Escoger documento
                            System.out.println("Que documento deseas compartir?");
                            userActivo.printNombresDocumentos();
                            Scanner leerNumDocumento = new Scanner(System.in);
                            int resp2 = leerNumDocumento.nextInt();
                            Documento doc = userActivo.getListaDocumentos().get(resp2);
                            // Verificar que el nombre del creador sea el mismo que el de la sesion
                            // Iniciada
                            if(doc.getCreador().equals(userActivo.getNombre()) == false){
                                break;
                            }else{
                                // Crear acceso
                                System.out.println("A quien quieres dar permiso?");
                                Scanner leerPermiso = new Scanner(System.in);
                                String usuarioConPermiso = leerPermiso.nextLine();
                                if(sistema.existeUsuario(usuarioConPermiso) == false){
                                    System.out.println("Error, el usuario no existe.");
                                    break;
                                }
                                System.out.println("Que tipo de permiso quieres darle?\n"
                                + "\t'r': Lectura\n\t'w': Escritura\n\t'c': Comentarios\n");
                                char resp = leerPermiso.next().charAt(0);  // Leer caracter
                                Acceso nuevoAcceso = new Acceso(usuarioConPermiso, resp);

                                // Modificar objeto documento
                                doc.agregarAcceso(nuevoAcceso);
                                sistema.buscarUsuario(nombSesionActiva).getListaDocumentos().set(resp2, doc);
                                // Agregar a la lista de documentos de la persona compartida para que lo pueda ver
                                sistema.buscarUsuario(usuarioConPermiso).getListaDocumentos().add(doc);
                                break;
                            }
                        // Agregar contenido a un documento
                        case 3:
                            System.out.println("Agregando contenido");
                            if(userActivo.getListaDocumentos().size() == 0){
                                System.out.println("No existen documentos para editar");
                                break;
                            }
                                                       
                            // Obtener numero de documento
                            System.out.println("Que documento deseas editar?");
                            userActivo.printNombresDocumentos();
                            Scanner leerNumDocumento2 = new Scanner(System.in);
                            int resp3 = leerNumDocumento2.nextInt();

                            // Obtener objeto documento
                            Documento documentoAntiguo = userActivo.getListaDocumentos().get(resp3);
                            Documento newDocument = new Documento(documentoAntiguo);  // Copiar objeto

                            // Verificar si el nombre de la persona tiene el permiso 'w' ERROR
                            if(userActivo.puedeEditar(documentoAntiguo) == false){
                                System.out.println("No tienes acceso a editar este documento");
                                break;
                            }else{
                                // Obtener contenido a agregar
                                System.out.println("Escribe lo que deseas agregar: ");
                                Scanner leerContenidoDoc = new Scanner(System.in);
                                String contenidoAgregar = leerContenidoDoc.nextLine();
                                
                                // Modificar nuevo documento
                                newDocument.setContenido(newDocument.getContenido() + contenidoAgregar);
                                newDocument.setId(documentoAntiguo.getId() + 1);
                                newDocument.setVersionAnterior(documentoAntiguo.getId());
                                // Hacer cambio en el sistema
                                sistema.buscarUsuario(nombSesionActiva).getListaDocumentos().add(newDocument);  // Agregar documento nuevo
                                sistema.buscarUsuario(nombSesionActiva).getListaDocumentos().get(resp3).setEsVersionActiva(false);
                                break;
                            }
                        // Restaurar versión de un documento
                        case 4:
                            System.out.println("Restaurando version anterior");

                            // Verificar si existen documentos
                            if(userActivo.getListaDocumentos().size() == 0){
                                System.out.println("No existen documentos para restaurar");
                                break;
                            }

                            // Mostrar lista de documentos
                            System.out.println("Que documento deseas restaurar?");
                            userActivo.printNombresDocumentos();

                            // Obtener documento
                            Scanner leerDocRestaurar = new Scanner(System.in);
                            int doc4 = leerDocRestaurar.nextInt();
                            Documento actual = userActivo.getListaDocumentos().get(doc4);

                            // Verificar si es creador del documento
                            if(actual.puedeCompartir(userActivo.getNombre())){
                                sistema.buscarUsuario(nombSesionActiva).restoreVersion(actual);
                            }else{
                                System.out.println("Usted no es el creador del documento");
                            }

                            break;
                        // Reevocar acceso a un documento
                        case 5:
                            System.out.println("Revocando accesos del documento");
                            break;
                        // Buscar en los documentos
                        case 6:
                            System.out.println("Buscando en los documentos");
                            break;
                        // Visualizar documentos
                        case 7:
                            System.out.println("Visualizando Sistema");
                            sistema.printEditor();
                            break;
                        // Cerrar sesión
                        case 8:
                            System.out.println("Cerrando sesion...");
                            sistema.cerrarSesion();
                            break;
                        // Cerrar el programa
                        case 9:
                            System.out.println("Cerrando el editor");
                            encendido = false;
                            return;
                    }
                }

                // Mostrar opciones del sistema
            }if (respuesta == 2){
                // Quiere registrarse
                System.out.println("Registrando en la plataforma...");
                Scanner string = new Scanner(System.in);
                System.out.println("Ingrese el nombre de usuario");
                String nombreUsuario = string.nextLine();
                System.out.println("Ingrese la password");
                String password = string.nextLine();

                // Hacer el registro
                sistema.register(nombreUsuario, password);
            }else if(respuesta == 3){
                System.out.println("Cerrando programa...");
                // Quiere cerrar el programa
                encendido = false;
                
            }

        }






        System.out.println("Ejecucion finalizada");
    }
}
