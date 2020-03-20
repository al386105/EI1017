package menus;

import interfaces.Accion;
import principal.BaseDeDatos;
import principal.acciones.ExportarDatosYsalir;
import principal.acciones.SeleccionaOpcionPrincipal;
import principal.acciones.*;
import principal.excepciones.OpcionIncorrectaException;

public enum MenuClientes { // implements DescripcionMenu
    DAR_ALTA_CLIENTE("Dar de alta un nuevo cliente.", new DarAltaCliente()),
    BORRAR_CLIENTE("Borrar un cliente.", new BorrarCliente()),
    CAMBIAR_TARIFA("Cambiar la tarifa de un cliente.", new CambiarTarifa()),
    DATOS_CLIENTE("Recuperar los datos de un cliente a partir de su NIF.", new DatosCliente()),
    LISTAR_CLIENTES("Recuperar el listado de todos los clientes.", new ListadoClientes()),
    CLIENTES_ENTRE_FECHAS("Mostrar listado de clientes dados de alta entre dos fechas.", new ClientesEntreFechas()),
    VOLVER_MENU_PRINCIPAL("Volver al menu principal.", new SeleccionaOpcionPrincipal()),
    SALIR_GUARDAR("Salir y guardar datos.", new ExportarDatosYsalir());

    //-------------------------

    private String textoOpcion;
    private Accion accion;

    private MenuClientes(final String textoOpcion, final Accion accion) {
        this.textoOpcion = textoOpcion;
        this.accion = accion;
    }

    public static MenuClientes getOpcion(int posicion) {
        return values()[posicion];
    }

    public static String getMenu() {
        StringBuilder sb = new StringBuilder();
        for(MenuClientes opcion: MenuClientes.values()) {
            sb.append(opcion.ordinal());
            sb.append(".- ");
            sb.append(opcion.textoOpcion);
            sb.append("\n");
        }
        return sb.toString();
    }

    public void ejecutaOpcion(BaseDeDatos baseDeDatos) {
        try {
            accion.ejecutaAccion(baseDeDatos);
        } catch(OpcionIncorrectaException e) {
            e.printStackTrace();
        }
    }

    //-------------------------

    /*private String descripcion;

    private MenuClientes(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static MenuClientes getOpcion(int posicion) {
        return values()[posicion];
    }

    public static String getMenu() {
        StringBuilder sb = new StringBuilder();
        for (MenuClientes opcion : MenuClientes.values()) {
            sb.append(opcion.ordinal());
            sb.append(".- ");
            sb.append(opcion.getDescripcion());
            sb.append("\n");
        }
        return sb.toString();
    }*/
}
