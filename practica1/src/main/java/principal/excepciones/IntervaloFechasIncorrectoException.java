package principal.excepciones;

public class IntervaloFechasIncorrectoException extends Exception {
    public IntervaloFechasIncorrectoException() { super("La fecha inicial debe ser anterior a la fecha final."); }
}
