package principal.acciones;

import principal.BaseDeDatos;
import java.util.Scanner;

public class PedirNifExistente {
    private static transient Scanner sc = new Scanner(System.in);

    //antes ni el metodo ni sc eran static
    public static String pedir(BaseDeDatos baseDeDatos) {
        System.out.print("- Introduce el NIF del cliente: ");
        String NIF = sc.next();
        while (!baseDeDatos.existeCliente(NIF)) {
            System.out.print("* Cliente no existente en la base de datos.\nVuelve a introducir el NIF: ");
            NIF = sc.next();
        }
        return NIF;
    }
}
