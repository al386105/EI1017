package principal.acciones;

import interfaces.Accion;
import principal.BaseDeDatos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ImportarDatos implements Accion {
    @Override
    public void ejecutaAccion(BaseDeDatos baseDeDatos) {
        ObjectInputStream ois = null;
        try {
            try {
                FileInputStream fis = new FileInputStream("baseDeDatos.bin");
                ois = new ObjectInputStream(fis);
                SeleccionaOpcionPrincipal.baseDeDatos = (BaseDeDatos) ois.readObject();
                System.out.println("\n-------> DATOS IMPORTADOS CORRECTAMENTE <-------");
            } finally {
                if (ois != null) ois.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("\n-----> ERROR: NO SE HA PODIDO ENCONTRAR EL FICHERO <-----");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
