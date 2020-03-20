package datos.contrato;

import datos.llamadas.Llamada;
import interfaces.TieneFecha;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Formatter;
import java.util.Set;

public class Factura implements TieneFecha, Serializable {
    private Tarifa tarifa;
    private LocalDateTime fechaEmision;
    private PeriodoFacturacion periodoFact;
    private float importe;
    private String nifCliente;
    private Set<Llamada> llamadas;

    //CONSTRUCTORES

    public Factura() {
        this.tarifa = null;
        this.fechaEmision = null;
        this.periodoFact = null;
        this.importe = 0.0f;
        this.nifCliente = null;
        this.llamadas = null;
    }

    public Factura(PeriodoFacturacion periodoFact, String nifCliente, Set<Llamada> llamadas, Tarifa tarifa) {
        this.tarifa = tarifa;
        this.fechaEmision = LocalDateTime.now();
        this.periodoFact = periodoFact;
        this.importe = calcularImporte(tarifa, llamadas);
        this.nifCliente = nifCliente;
        this.llamadas = llamadas;
    }

    public int getCodigo() {
        return this.hashCode();
    }

    public Tarifa getTarifa() {
        return this.tarifa;
    }

    public float getImporte() {
        return this.importe;
    }

    public String getNifCliente() {
        return this.nifCliente;
    }

    @Override
    public LocalDate getFecha() {
        return this.fechaEmision.toLocalDate();
    }

    public LocalTime getHora() { return this.fechaEmision.toLocalTime(); }

    private float calcularImporte(Tarifa tarifa, Set<Llamada> llamadas) {
        int segundosTotales = 0;
        for (Llamada llamada : llamadas) {
            LocalDate fecha = llamada.getFecha();
            if (fecha.isAfter(periodoFact.getFechaIni()) && fecha.isBefore(periodoFact.getFechaFin()) ||
                    (fecha.isEqual(periodoFact.getFechaIni()) || fecha.isEqual(periodoFact.getFechaFin())))
                segundosTotales += llamada.getDuracion();
        }
        float importe = (segundosTotales / 60.0f) * tarifa.getTarifa();
        //codigo para redondear a dos decimales:
        BigDecimal redondeado = new BigDecimal(importe).setScale(2, RoundingMode.HALF_EVEN);
        return redondeado.floatValue();
    }

    @Override
    public String toString() {
        Formatter obj = new Formatter();
        StringBuilder sb = new StringBuilder();
        sb.append("\nCodigo de factura: " + this.hashCode() + ":");
        sb.append("\n\tNIF: " + nifCliente);
        sb.append("\n\tTarifa: " + tarifa);
        sb.append("\n\tFecha de emision: " + getFecha().toString());
        sb.append("\n\tHora de emision: " + obj.format("%02d:%02d", getHora().getHour(), getHora().getMinute()));
        sb.append("\n\tPeriodo de facturacion: " + periodoFact);
        sb.append("\n\tImporte: " + importe + "€");
        sb.append("\n\tLista de llamadas de esta factura:\n");
        for (Llamada llamada : llamadas) sb.append("\n" + llamada.toString());
        return sb.toString();
    }
}
