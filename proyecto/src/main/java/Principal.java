import tdas.*;
import java.util.Scanner;



public class Principal {

    // Funciones de interacción con el programa
    public static int mostrarOpcionesInicio(){
        System.out.println("¿Que desea hacer?\n" +
                "\t1. Iniciar Sesion\n" +
                "\t2. Registrarse\n");

        Scanner respuesta = new Scanner(System.in);
        int respuesta_out = respuesta.nextInt();
        // Si la respuesta no es válida -> pedir entrada de nuevo
        if(respuesta_out != 1 && respuesta_out != 2){
            System.out.println("Respuesta invalida, por favor ingrese 1. o 2. según su preferencia.");
            return mostrarOpcionesInicio();
        }
        // Si la entrada es correcta se retorna la elección
        return respuesta_out;
    }


    public static int mostrarOpcionesSistema() {
        // ###Mostrar por pantalla el nombre de usuario logueado

        System.out.println("¿Que desea hacer?\n" +
                "\t1. Crear nuevo documento\n" +
                "\t2. Compartir documento\n" +
                "\t3. Agregar contenido a un documento\n" +
                "\t4. Restaurar version de un documento\n" +
                "\t5. Revocar acceso a un documento\n" +
                "\t6. Buscar en los documentos\n" +
                "\t7. Visualizar documentos\n" +
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

        // Crear tda sistema
        Editor sistema = new Editor();

        System.out.println("Ingrese el nombre del sistema: ");
        String nombreSistema = leerEntrada.nextLine();
        sistema.setNombre(nombreSistema);

        System.out.println("Ingrese la fecha del sistema:");
        sistema.setDate(new Fecha(leerEntrada.nextLine()));

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
                    switch(opcionSistema){
                        // Crear un nuevo documento
                        case 1:
                            System.out.println("Creando nuevo documento.");
                            break;

                        // Compartir un documento
                        case 2:
                            System.out.println("Compartiendo documento");
                            break;
                        // Agregar contenido a un documento
                        case 3:
                            System.out.println("Agregando contenido");
                            break;
                        // Restaurar versión de un documento
                        case 4:
                            System.out.println("Restaurando versión anterior");
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
                            System.out.println("Visualizando documento");
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
                            break;
                    }
                }

                // Mostrar opciones del sistema
            }else{
                // Quiere registrarse
                System.out.println("Registrando en la plataforma...");
                Scanner string = new Scanner(System.in);
                System.out.println("Ingrese el nombre de usuario");
                String nombreUsuario = string.nextLine();
                System.out.println("Ingrese la password");
                String password = string.nextLine();

                // Hacer el registro
                sistema.register(nombreUsuario, password);
            }

        }



        System.out.println("Ejecucion finalizada");
    }
}
