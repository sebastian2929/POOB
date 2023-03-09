import java.util.HashMap;

public class Estacion {
    private String nombre;
    private String nivelOcupacion;
    private int tiempo = 0;
    private HashMap<String, Ruta> rutas = new HashMap<String, Ruta>();

    public Estacion(String nombre, String nivelOcupacion, int tiempo) {
        this.nombre = nombre;
        if (nivelOcupacion == "alto" || nivelOcupacion == "medio " || nivelOcupacion == "bajo") {
            this.nivelOcupacion = nivelOcupacion;
        }
        this.tiempo = tiempo;
    }

    public int getTiempoEspera() {
        return tiempo;
    }

    public Ruta getRuta(String nombre) {
        Ruta ruta = rutas.get(nombre);
        return ruta;
    }
}
