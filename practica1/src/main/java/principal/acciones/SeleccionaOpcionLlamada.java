package principal.acciones;

import interfaces.Accion;
import menus.MenuLlamadas;
import principal.BaseDeDatos;
import principal.excepciones.OpcionIncorrectaException;

public class SeleccionaOpcionLlamada implements Accion {
    @Override
    public void ejecutaAccion(BaseDeDatos baseDeDatos) throws OpcionIncorrectaException {
        System.out.println("\n* * * * * * * OPCIONES DE LLAMADAS * * * * * * *\n");
        System.out.println(MenuLlamadas.getMenu());
        System.out.print("Introduce una opción: ");
        byte opcion = sc.nextByte();
        if(opcion < 0 || opcion > 4)
            throw new OpcionIncorrectaException();
        else {
            MenuLlamadas opcionLlamadas = MenuLlamadas.getOpcion(opcion);
            opcionLlamadas.ejecutaOpcion(baseDeDatos);
        }
    }
}
