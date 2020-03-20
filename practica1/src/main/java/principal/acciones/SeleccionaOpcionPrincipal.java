package principal.acciones;

import interfaces.Accion;
import menus.MenuPrincipal;
import principal.BaseDeDatos;
import principal.excepciones.OpcionIncorrectaException;
import java.io.Serializable;
import java.util.Scanner;

public class SeleccionaOpcionPrincipal implements Accion, Serializable {
    private transient Scanner sc = new Scanner(System.in);
    static transient MenuPrincipal opcionMenu = null;
    static BaseDeDatos baseDeDatos;

    //Constructor por defecto no sé qué poner pero si no pongo esta línea da error en los Menus
    public SeleccionaOpcionPrincipal() {
    }

    public SeleccionaOpcionPrincipal(BaseDeDatos baseDeDatos) {
        this.baseDeDatos = baseDeDatos;
    }

    @Override
    public void ejecutaAccion(BaseDeDatos bbdd) {
        do {
            System.out.println("\n* * * * * * * OPCIONES DISPONIBLES * * * * * * *\n");
            System.out.println(MenuPrincipal.getMenu());
            try {
                byte opcion = pedirOpcion();
                opcionMenu = MenuPrincipal.getOpcion(opcion);
                opcionMenu.ejecutaOpcion(this.baseDeDatos);
                sc.reset(); //No sé si es necesario, lo vi en internet !!!!!!!!!
            } catch (OpcionIncorrectaException e) {
                e.printStackTrace();
            }
        } while (opcionMenu != MenuPrincipal.SALIR_GUARDAR);
    }

    private byte pedirOpcion() throws OpcionIncorrectaException {
        System.out.print("Introduce una opción: ");
        byte opcion = sc.nextByte();
        if (opcion < 0 || opcion > 4)
            throw new OpcionIncorrectaException();
        return opcion;
    }
}
