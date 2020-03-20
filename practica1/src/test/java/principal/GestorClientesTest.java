package principal;

import datos.clientes.Cliente;
import datos.clientes.Direccion;
import datos.llamadas.Llamada;
import es.uji.www.GeneradorDatosINE;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import principal.excepciones.DuracionNegativaException;
import principal.excepciones.NifRepetidoException;
import principal.excepciones.TelfRepetidoException;

import java.time.LocalDate;
import java.time.LocalTime;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class GestorClientesTest {
    private static BaseDeDatos baseDeDatos;
    private static Cliente alberto;
    private static Direccion dirAlberto;
    private static Cliente pamesa;
    private static Direccion dirPamesa;

    @BeforeAll
    public static void inicializa() throws NifRepetidoException, TelfRepetidoException {
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
    }

    @Test
    public void testExisteCliente() { //devuelve true si el nif existe
        assertThat(baseDeDatos.existeCliente("20925403"), is(true));
        assertThat(baseDeDatos.existeCliente("22921854"), is(false));
        assertThat(baseDeDatos.existeCliente("63302284"), is(true));
        assertThat(baseDeDatos.existeCliente("00000000"), is(false));
    }

    @Test
    public void testExisteTelf() { //devuelve true si el telf existe
        assertThat(baseDeDatos.existeTelf("692242216"), is(true));
        assertThat(baseDeDatos.existeTelf("999999999"), is(false));
        assertThat(baseDeDatos.existeTelf("964246252"), is(true));
        assertThat(baseDeDatos.existeTelf("00000000"), is(false));
    }

    @Test
    public void testAñadirParticular() {
        //Se busca el cliente alberto anadido en el BeforeAll
        assertEquals(alberto.getNIF(), "20925403");
        assertEquals(alberto.getNombre(), "alberto");
        assertEquals(alberto.getEmail(), "albertoprado@gmail.com");
        assertEquals(alberto.getTelf(), "692242216");
        assertEquals(alberto.getFecha(), LocalDate.now());
        assertEquals(alberto.getDireccion(), dirAlberto);
        //la tarifa se comrpueba en testCambiarTarifa
    }

    @Test
    public void testAñadirEmpresa() {
        //Se busca la empresa pamesa anadida en el BeforeAll
        assertEquals(pamesa.getNIF(), "63302284");
        assertEquals(pamesa.getNombre(), "pamesa");
        assertEquals(pamesa.getEmail(), "pamesa@gmail.com");
        assertEquals(pamesa.getTelf(), "964246252");
        assertEquals(pamesa.getFecha(), LocalDate.now());
        assertEquals(pamesa.getDireccion(), dirPamesa);
        assertEquals(pamesa.getTarifa().getTarifa(), 0.05f, 0);
    }

    @Test
    public void testBorrarCliente() throws NifRepetidoException, TelfRepetidoException {
        //creamos un cliente
        baseDeDatos.anadirParticular("maria", "gracia rubio", "123456789", "X1234567S", dirAlberto, "mariagracia@gmail.com");
        //vemos que se ha anadido
        assertThat(baseDeDatos.existeCliente("X1234567S"), is(true));
        //lo borramos
        baseDeDatos.borrarCliente("123456789");
        //vemos que se ha borrado
        assertEquals(baseDeDatos.existeCliente("X1234567S"), false);
    }

    @Test
    public void testCambiarTarifa() {
        //comprobamos la tarifa de alberto
        assertEquals(alberto.getTarifa().getTarifa(), 0.05f, 0);
        //cambiamos la tarifa de alberto y comprobamos el cambio
        baseDeDatos.cambiarTarifa(0.15f, alberto.getNIF());
        assertEquals(alberto.getTarifa().getTarifa(), 0.15f, 0);
    }

    @Test
    public void testListarDatosCliente() {
        assertEquals(baseDeDatos.listarDatosCliente("20925403"), "prado banarro, alberto, NIF: 20925403, Telf: 692242216, " +
                "Direccion: Castelllon - Castellon de la plana - 12005, Email: albertoprado@gmail.com, Fecha de alta: " + LocalDate.now() +
                ", Tarifa: " + alberto.getTarifa() + ". ");
        assertEquals(baseDeDatos.listarDatosCliente("63302284"), "pamesa, NIF: 63302284, Telf: 964246252, " +
                "Direccion: Castelllon - VillaReal - 12006, Email: pamesa@gmail.com, Fecha de alta: " + LocalDate.now() + ", Tarifa: 0.05 €/min. ");
    }

    //comprueba darDeAltaLlamada
    @Test
    public void testDarDeAltaLlamada() throws DuracionNegativaException { //podria fallar si justo cambia el minuto al comprobar el test
        baseDeDatos.darDeAltaLlamada("692242216", "000000000", 120);
        assertEquals(baseDeDatos.listarLlamadasCliente("692242216"), "Llamada realizada el " + LocalDate.now() + " a las " +
                LocalTime.now().getHour() + " horas y " + LocalTime.now().getMinute() + " minutos con una duracion de 120 segundos " +
                "al telefono 000000000\n");
        for(Llamada llamada : alberto.getLlamadas()) { //solo hay una
            assertEquals(llamada.getTelfDest(), "000000000");
            assertEquals(llamada.getDuracion(), 120);
            assertEquals(llamada.getFecha(), LocalDate.now());
        }
    }

    @AfterAll
    public static void borraTodo(){
        baseDeDatos = null;
    }
}