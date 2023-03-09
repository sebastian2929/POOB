import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

public class Transmilenio {

    private HashMap<String, Estacion> estaciones = new HashMap<String, Estacion>();
    private HashMap<String, Ruta> rutas = new HashMap<String, Ruta>();

    public int tiempoEspera(String nombre) {
        Estacion estacion = estaciones.get(nombre);
        int tiempo = estacion.getTiempoEspera();
        return tiempo;
    }

    public int numParadas(String nombreRuta, String nombre1, String nombre2) {
        Estacion estacion1 = estaciones.get(nombre1);
        Estacion estacion2 = estaciones.get(nombre2);
        int paradas = 0;
        if (estacion1 != null && estacion2 != null) {
            Ruta ruta = estacion1.getRuta(nombreRuta);
            paradas = ruta.numeroParadas(nombre1, nombre2);
        }
        return paradas;
    }

    public HashMap<Integer, ArrayList<String>> sinHacerTransbordo(String nombre1, String nombre2) {
        HashMap<Integer, ArrayList<String>> rutasT = new HashMap<Integer, ArrayList<String>>();
        for (Ruta r : rutas.values()) {
            if (r.sinHacerTrasnbordo(nombre1, nombre2)) {
                int paradas = r.numeroParadas(nombre1, nombre2);
                if (!rutasT.containsKey(paradas)) {
                    rutasT.put(paradas, new ArrayList<String>());
                    ArrayList<String> nombres = rutasT.get(paradas);
                    nombres.add(r.getNombre());
                } else {
                    ArrayList<String> nombres = rutasT.get(paradas);
                    nombres.add(r.getNombre());
                    rutasT.put(paradas, nombres);
                }
            }
        }
        for (Integer key : rutasT.keySet()) {
            ArrayList<String> name = rutasT.get(key);
            Collections.sort(name);
            rutasT.put(key, name);

        }

        return rutasT;
    }

    public int tiempoRecorrido(String[][] plan) {
        int timepo = 0;
        for (int i = 0; i < plan.length; i++) {
            String nombreEstacion = plan[i][0];
            Estacion estacion = estaciones.get(nombreEstacion);
            timepo += estacion.getTiempoEspera();
        }
        return timepo;
    }
}
