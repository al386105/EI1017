package principal.acciones;

import principal.BaseDeDatos;
import java.util.Scanner;

public class PedirTelfExistente {
    private static transient Scanner sc = new Scanner(System.in);

    //antes sc ni el metodo eran static
    public static String pedir(BaseDeDatos baseDeDatos) {
        System.out.print("- Introduce el telefono del cliente: ");
        String telf = sc.next();
        while (!baseDeDatos.existeTelf(telf)) {
            System.out.print("* Telefono del cliente no existente en la base de datos.\nVuelve a introducirlo: ");
            telf = sc.next();
        }
        return telf;
    }
}
