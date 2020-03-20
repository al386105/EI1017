package principal.excepciones;

public class DuracionNegativaException extends Exception {
    public DuracionNegativaException() { super("No se puede introducir un valor negativo como tiempo de llamada."); }
}
