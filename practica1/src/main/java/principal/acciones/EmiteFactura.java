package principal.acciones;

import interfaces.Accion;
import principal.BaseDeDatos;
import principal.excepciones.IntervaloFechasIncorrectoException;

import java.time.LocalDate;

public class EmiteFactura implements Accion {
    @Override
    public void ejecutaAccion(BaseDeDatos baseDeDatos) {
        try {
            System.out.println("\nEMITIR FACTURA PARA UN CLIENTE");
            //String nif = pedirNifExistente();
            String nif = PedirNifExistente.pedir(baseDeDatos);
            System.out.print("- Introduce la fecha de inicio de la factura (formato aaaa-mm-dd): ");
            LocalDate fechaIni = LocalDate.parse(sc.next());
            System.out.print("- Introduce la fecha de fin de la factura (formato aaaa-mm-dd): ");
            LocalDate fechaFin = LocalDate.parse(sc.next());
            //comprobar que la fecha de inicio de factura sea anterior a la fecha de fin
            baseDeDatos.emitirFactura(fechaIni, fechaFin, nif);
            System.out.println("\n\tFactura del cliente con NIF " + nif + " emitida con exito.\n");
        } catch (IntervaloFechasIncorrectoException e) {
            e.printStackTrace();
        }
    }
}
