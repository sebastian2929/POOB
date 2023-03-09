import java.util.ArrayList;
import java.util.HashMap;

public class Ruta {
    private String nombre;
    private ArrayList<String> parada = new ArrayList<String>();
    private HashMap<String, Estacion> paradas = new HashMap<String, Estacion>();

    public int numeroParadas(String nombre1, String nombre2) {
        Estacion estacion1 = paradas.get(nombre1);
        Estacion estacion2 = paradas.get(nombre2);
        ArrayList<Estacion> estaciones = new ArrayList<Estacion>();
        int posX = 0;
        int posY = 0;
        int cont = 0;
        for (Estacion estacion : paradas.values()) {
            estaciones.add(estacion);
        }
        for (int i = 0; i < estaciones.size(); i++) {
            Estacion estacion = estaciones.get(i);
            if (estacion == estacion1) {
                posX = i;
            }
            if (estacion == estacion2) {
                posY = i;
            }
        }
        for (int j = posX + 1; j < posY; j++) {
            cont++;
        }
        return cont;
    }

    public boolean sinHacerTrasnbordo(String nombre1, String nombre2) {
        boolean bandera = false;
        if (paradas.containsKey(nombre1) && paradas.containsKey(nombre2)) {
            bandera = true;
        }
        return bandera;
    }

    public String getNombre() {
        return this.nombre;
    }

    public ArrayList<Estacion> getParadas() {
        ArrayList<Estacion> estaciones = new ArrayList<Estacion>();
        for (Estacion estacion : paradas.values()) {
            estaciones.add(estacion);
        }
        return estaciones;
    }
}
