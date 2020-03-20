package principal.acciones;

import datos.clientes.Direccion;
import interfaces.Accion;
import principal.BaseDeDatos;
import principal.excepciones.NifRepetidoException;
import principal.excepciones.TelfRepetidoException;

public class DarAltaCliente implements Accion {
    @Override
    public void ejecutaAccion(BaseDeDatos baseDeDatos) {
        try {
            //Scanner sc = new Scanner(System.in);
            System.out.println("\nDAR DE ALTA UN NUEVO CLIENTE");
            System.out.print("--> Introduce 'e' para empresa o 'p' para particular: ");
            String letra = sc.next();
            while (!letra.equals("e") && !letra.equals("p")) {
                System.out.print("* Parametro incorrecto. Vuelve a intentarlo: ");
                letra = sc.next();
            }
            System.out.print("- Introduce nombre: ");
            sc.nextLine();
            String nombre = sc.nextLine();
            String apellidos = null;
            if (letra.equals("p")) {
                System.out.print("- Introduce apellidos: ");
                apellidos = sc.nextLine();
            }
            System.out.print("- Introduce NIF: ");
            String nif = sc.next();
            System.out.print("- Introduce el telefono del cliente: ");
            String telf = sc.next();
            System.out.print("- Introduce CP: ");
            String cp = sc.next();
            System.out.print("- Introduce provincia: ");
            sc.nextLine();
            String provincia = sc.nextLine();
            System.out.print("- Introduce poblacion: ");
            String poblacion = sc.nextLine();
            Direccion direccion = new Direccion(cp, provincia, poblacion);
            System.out.print("- Introduce email: ");
            String email = sc.next();
            if (letra.equals("p"))
                baseDeDatos.anadirParticular(nombre, apellidos, telf, nif, direccion, email);
            else baseDeDatos.anadirEmpresa(nombre, telf, nif, direccion, email);
            System.out.println("\n\tCreado cliente " + nombre + " con NIF " + nif + " y telefono " + telf + ".\n");
        } catch (NifRepetidoException e) {
            e.printStackTrace();
        } catch (TelfRepetidoException e) {
            e.printStackTrace();
        }
    }
}
