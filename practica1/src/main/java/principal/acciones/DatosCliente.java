package principal.acciones;

import interfaces.Accion;
import principal.BaseDeDatos;

public class DatosCliente implements Accion {
    @Override
    public void ejecutaAccion(BaseDeDatos baseDeDatos) {
        System.out.println("\nRECUPERAR LOS DATOS DE UN CLIENTE");
        //String nif = pedirNifExistente();
        String nif = PedirNifExistente.pedir(baseDeDatos);
        System.out.println(baseDeDatos.listarDatosCliente(nif) + "\n");
    }
}
