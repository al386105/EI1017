package principal.acciones;

import interfaces.Accion;
import principal.BaseDeDatos;

public class FacturasCliente implements Accion {
    @Override
    public void ejecutaAccion(BaseDeDatos baseDeDatos) {
        System.out.println("\nLISTAR LAS FACTURAS DE UN CLIENTE");
        //String nif = pedirNifExistente();
        String nif = PedirNifExistente.pedir(baseDeDatos);
        System.out.println(baseDeDatos.listarFacturasCliente(nif));
    }
}
