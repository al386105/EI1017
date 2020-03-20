package principal.acciones;

import interfaces.Accion;
import principal.BaseDeDatos;

public class LlamadasCliente implements Accion {
    @Override
    public void ejecutaAccion(BaseDeDatos baseDeDatos) {
        System.out.println("\nLISTAR TODAS LAS LLAMADAS DE UN CLIENTE");
        //String telf = pedirTelfExistente();
        String telf = PedirTelfExistente.pedir(baseDeDatos);
        System.out.println(baseDeDatos.listarLlamadasCliente(telf));
    }
}
