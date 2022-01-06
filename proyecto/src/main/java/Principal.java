import tdas.*;
import java.util.Scanner;



public class Principal {

    // Funciones de interacción con el programa
    public static int mostrarOpcionesInicio(){
        System.out.println("¿Qué desea hacer?\n" +
                "\t1. Iniciar Sesión\n" +
                "\t2. Registrarse\n");

        Scanner respuesta = new Scanner(System.in);
        int respuesta_out = respuesta.nextInt();
        // Si la respuesta no es válida -> pedir entrada de nuevo
        if(respuesta_out != 1 && respuesta_out != 2){
            System.out.println("Respuesta inválida, por favor ingrese 1. o 2. según su preferencia.");
            return mostrarOpcionesInicio();
        }
        // Si la entrada es correcta se retorna la elección
        return respuesta_out;
    }


    public static int mostrarOpcionesSistema() {
        // ###Mostrar por pantalla el nombre de usuario logueado

        System.out.println("¿Qué desea hacer?\n" +
                "\t1. Crear nuevo documento\n" +
                "\t2. Compartir documento\n" +
                "\t3. Agregar contenido a un documento\n" +
                "\t4. Restaurar versión de un documento\n" +
                "\t5. Revocar acceso a un documento\n" +
                "\t6. Buscar en los documentos\n" +
                "\t7. Visualizar documentos\n" +
                "\t8. Cerrar sesión\n" +
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






    public static void main(String[] args) {
        System.out.println("Ejecución iniciada");
        /**
        // Probar tda Fecha
        Fecha fechaPrueba = new Fecha(6,1,2022);
        fechaPrueba.formated = fechaPrueba.formatearFecha(fechaPrueba);
        fechaPrueba.printFecha();
         */

        // Probar tda Acceso
        /*
        Acceso accesoPrueba = new Acceso("Daniel", 'w');
        System.out.println(accesoPrueba.toString());

         */
        // Probar tda Documento



        // Crear tda sistema

        /*
        boolean encendido = true;
        while(encendido){
            int respuesta = mostrarOpcionesInicio();
            if(respuesta == 1){
                // Quiere iniciar sesión
                System.out.println("Iniciando sesión...");
                int opcionSistema = mostrarOpcionesSistema();

                switch(opcionSistema){
                    case 9:
                        encendido = false;
                }

                // Mostrar opciones del sistema
            }else{
                // Quiere registrarse
                System.out.println("Registrando en la plataforma...");
            }

        }
        */


        System.out.println("Ejecución finalizada");
    }
}
