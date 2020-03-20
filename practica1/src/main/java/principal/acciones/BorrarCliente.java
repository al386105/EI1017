package principal.acciones;

import interfaces.Accion;
import principal.BaseDeDatos;

public class BorrarCliente implements Accion {
    @Override
    public void ejecutaAccion(BaseDeDatos baseDeDatos) {
        System.out.println("\nBORRAR UN CLIENTE");
        //String telf = pedirTelfExistente();
        String telf = PedirNifExistente.pedir(baseDeDatos);
        baseDeDatos.borrarCliente(telf);
        System.out.println("\n\tCliente con numero " + telf + " borrado con exito.\n");
    }
}
