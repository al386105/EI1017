package principal;

import datos.clientes.Cliente;
import datos.clientes.Direccion;
import datos.contrato.Factura;
import es.uji.www.GeneradorDatosINE;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import principal.excepciones.DuracionNegativaException;
import principal.excepciones.IntervaloFechasIncorrectoException;
import principal.excepciones.NifRepetidoException;
import principal.excepciones.TelfRepetidoException;

import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.Assert.assertEquals;

public class BaseDeDatosTest {
    private static BaseDeDatos baseDeDatos;
    private static Cliente alberto;
    private static Direccion dirAlberto;
    private static Cliente pamesa;
    private static Direccion dirPamesa;

    @BeforeAll
    public static void inicializa() throws NifRepetidoException, DuracionNegativaException, TelfRepetidoException {
        baseDeDatos = new BaseDeDatos(new GestorClientes(), new GestorFacturas());

        for (int i = 0; i < 100; i++) {
            GeneradorDatosINE generadorDatosINE = new GeneradorDatosINE();
            String nombre = generadorDatosINE.getNombre();
            String nif = generadorDatosINE.getNIF();
            String provincia = generadorDatosINE.getProvincia();
            //String poblacion = generadorDatosINE.getPoblacion(provincia); nos da error el generador
            String cp = "12005"; //GENERADOR INE NO TIENE CP
            Direccion direccion = new Direccion(cp, provincia, "Poblacion");
            //creamos 50 particulares y 50 empresas
            if (i < 50) {
                String apellidos = generadorDatosINE.getApellido();
                baseDeDatos.anadirParticular(nombre, apellidos, "5555555" + i, nif, direccion, "particular@gmail.com");
            } else baseDeDatos.anadirEmpresa(nombre, "6666666" + i, nif, direccion, "empresa@gmail.com");
        }

        dirAlberto = new Direccion("12005", "Castellon de la plana", "Castelllon");
        baseDeDatos.anadirParticular("alberto", "prado banarro", "692242216", "20925403", dirAlberto, "albertoprado@gmail.com");
        alberto = baseDeDatos.devuelveCliente("20925403");

        dirPamesa = new Direccion("12006", "VillaReal", "Castelllon");
        baseDeDatos.anadirEmpresa("pamesa", "964246252", "63302284", dirPamesa, "pamesa@gmail.com");
        pamesa = baseDeDatos.devuelveCliente("63302284");

        //realizamos llamadas para pamesa
        for (int i = 0; i < 50; i++)
            baseDeDatos.darDeAltaLlamada("964246252", "666666" + i, 120);
    }

    //comprueba listarLlamadasCliente() y listarLlamadasEntreFechas()
    @Test
    public void testLlamadasCliente() throws DuracionNegativaException, IntervaloFechasIncorrectoException {
        //alberto hace una llamada
        baseDeDatos.darDeAltaLlamada("692242216", "000000000", 120);
        //test listarLlamadasCliente()
        assertEquals(baseDeDatos.listarLlamadasCliente("692242216"),
                "Llamada realizada el " + LocalDate.now() + " a las " + LocalTime.now().getHour() + " horas y " +
                        LocalTime.now().getMinute() + " minutos con una duracion de 120 segundos al telefono 000000000\n");
        //test listarLlamadasEntreFechas()
        assertEquals(baseDeDatos.listarLlamadasEntreFechas("692242216", LocalDate.now(), LocalDate.now()),
                "Llamada realizada el " + LocalDate.now() + " a las " + LocalTime.now().getHour() + " horas y " +
                        LocalTime.now().getMinute() + " minutos con una duracion de 120 segundos al telefono 000000000\n");
    }

    //comprueba listarFacturasCliente() y listarFacturasEntreFechas()
    @Test
    public void testFacturasCliente() throws IntervaloFechasIncorrectoException {
        //emite una factura para pamesa con todas las llamadas desde ayer a hoy (las 50 anadidas)
        baseDeDatos.emitirFactura(LocalDate.now().minusDays(1), LocalDate.now(), "63302284");
        for (Factura factura : pamesa.getFacturas()) { //solo hay una
            int codFact = factura.getCodigo();
            assertEquals(baseDeDatos.listarFacturasCliente("63302284"),
                    "NIF: 63302284, Codigo: " + codFact + ", Tarifa: 0.05 €/min, Fecha de emision: " + LocalDate.now() +
                            ", Periodo de facturacion: " + LocalDate.now().minusDays(1) + " - " + LocalDate.now() + ", Importe: 5.0€.\n");
            assertEquals(baseDeDatos.listarFacturasEntreFechas("63302284", LocalDate.now(), LocalDate.now()),
                    "NIF: 63302284, Codigo: " + codFact + ", Tarifa: 0.05 €/min, Fecha de emision: " + LocalDate.now() +
                            ", Periodo de facturacion: " + LocalDate.now().minusDays(1) + " - " + LocalDate.now() + ", Importe: 5.0€.\n");
        }
    }

    @AfterAll
    public static void borraTodo(){
        baseDeDatos = null;
    }
}