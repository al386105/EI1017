package datos.clientes;

import datos.contrato.Factura;
import datos.contrato.Tarifa;
import datos.ComparadorFechaHora;
import interfaces.TieneFecha;
import datos.llamadas.Llamada;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public abstract class Cliente implements TieneFecha, Serializable {
    private String nombre;
    private String NIF;
    private String telf;
    private Direccion direccion;
    private String email;
    private LocalDateTime fechaDeAlta;
    private Tarifa tarifa;
    private Set<Factura> facturas; //conjunto con todas las facturas del cliente
    private Set<Llamada> llamadas; //conjunto con todas las llamadas del cliente

    //CONSSTRUCTOR POR DEFECTO
    public Cliente() {
        this.nombre = "";
        this.telf = "";
        this.NIF = "";
        this.direccion = null;
        this.email = "";
        this.fechaDeAlta = null;
        this.tarifa = null;
        ComparadorFechaHora comparador = new ComparadorFechaHora<>();
        this.facturas = new TreeSet<Factura>(comparador);
        this.llamadas = new TreeSet<Llamada>(comparador);
    }

    public Cliente(final String nombre, final String telefono, final String NIF, final Direccion direccion, final String email, final Tarifa tarifa) {
        this.nombre = nombre;
        this.telf = telefono;
        this.NIF = NIF;
        this.direccion = direccion;
        this.email = email;
        this.fechaDeAlta = LocalDateTime.now();
        this.tarifa = tarifa;
        ComparadorFechaHora comparador = new ComparadorFechaHora<>();
        this.facturas = new TreeSet<Factura>(comparador);
        this.llamadas = new TreeSet<Llamada>(comparador);
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelf() {
        return telf;
    }

    public String getNIF() {
        return NIF;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public LocalDate getFecha() {
        return fechaDeAlta.toLocalDate();
    }

    public LocalTime getHora() {
        return fechaDeAlta.toLocalTime();
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public Set<Llamada> getLlamadas() {
        return llamadas;
    }

    public Set<Factura> getFacturas() {
        return facturas;
    }

    public void cambiarTarifa(float nuevaTarifa) {
        tarifa.setTarifa(nuevaTarifa);
    }

    public void anadirLlamada(Llamada llamada) {
        llamadas.add(llamada);
    }

    @Override
    public String toString() {
        Formatter obj = new Formatter();
        StringBuilder sb = new StringBuilder();
        sb.append(nombre);
        sb.append("\n\tNIF: " + NIF);
        sb.append("\n\tTelefono: " + telf);
        sb.append("\n\tDireccion: " + direccion);
        sb.append("\n\tEmail: " + email);
        sb.append("\n\tFecha de alta: " + getFecha().toString());
        sb.append("\n\tHora de alta: " + obj.format("%02d:%02d", getHora().getHour(), getHora().getMinute()));
        sb.append("\n\tTarifa: " + tarifa);
        return sb.toString();
    }
}