package datos.contrato;

import java.io.Serializable;

public class Tarifa implements Serializable {
    private float valor;

    //CONSTRUCTORES
    public Tarifa() {
        valor = 0.05f;
    }

    public Tarifa(float valorTarifa) {
        this.valor = valorTarifa;
    }

    //METODOS
    public float getTarifa() {
        return valor;
    }

    public void setTarifa(float tarifa) {
        this.valor = tarifa;
    }

    @Override
    public String toString() {
        return valor + " €/min";
    }
}
