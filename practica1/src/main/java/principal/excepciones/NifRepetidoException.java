package principal.excepciones;

public class NifRepetidoException extends Exception {
    public NifRepetidoException() {
        super("No se puede crear un cliente con un NIF ya existente en la base de datos.");
    }
}