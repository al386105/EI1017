package datos;

import interfaces.TieneFecha;
import java.io.Serializable;
import java.util.Comparator;

public class ComparadorFechaHora < T extends TieneFecha > implements Comparator< T >, Serializable {
    @Override
    public int compare(T obj1, T obj2) {
        if(obj1.getFecha().isBefore(obj2.getFecha())) return -1;     //obj1 con fecha anterior
        else if(obj1.getFecha().isAfter(obj2.getFecha())) return 1;  //obj1 con fecha posterior
        else { //misma fecha, se compara por horas
            if(obj1.getHora().isBefore(obj2.getHora())) return -1;       //obj1 con hora anterior, mismo dia
            else if(obj1.getHora().isAfter(obj2.getHora())) return 1;  //obj1 con hora posterior, mismo dia
            return 0; //misma fecha
        }
    }
}