package datos.clientes;

import java.io.Serializable;

public class Direccion implements Serializable {
    private String CP;
    private String provincia;
    private String poblacion;

    public Direccion() {
        this.CP = "";
        this.provincia = "";
        this.poblacion = "";
    }

    public Direccion(final String CP, final String provincia, final String poblacion) {
        this.CP = CP;
        this.provincia = provincia;
        this.poblacion = poblacion;
    }

    @Override
    public String toString() {
        return this.poblacion + " - " + this.provincia + " - " + this.CP;
    }
}
