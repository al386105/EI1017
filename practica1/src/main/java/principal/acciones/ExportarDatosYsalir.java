package principal.acciones;

import interfaces.Accion;
import menus.MenuPrincipal;
import principal.BaseDeDatos;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ExportarDatosYsalir implements Accion {
    @Override
    public void ejecutaAccion(BaseDeDatos baseDeDatos) {
        ObjectOutputStream oos = null;
        try {
            try {
                SeleccionaOpcionPrincipal.opcionMenu = MenuPrincipal.SALIR_GUARDAR;
                FileOutputStream fos = new FileOutputStream("baseDeDatos.bin");
                oos = new ObjectOutputStream(fos);
                oos.writeObject(baseDeDatos);
                System.out.println("\n -----> Datos guardados y programa cerrado <----- ");
            } finally {
                oos.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
