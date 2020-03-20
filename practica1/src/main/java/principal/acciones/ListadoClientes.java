package principal.acciones;

import interfaces.Accion;
import principal.BaseDeDatos;

public class ListadoClientes implements Accion {
    @Override
    public void ejecutaAccion(BaseDeDatos baseDeDatos) {
        System.out.println("\nRECUPERAR EL LISTADO DE TODOS LOS CLIENTES");
        System.out.println(baseDeDatos.listarClientes());
    }
}
