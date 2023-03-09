package domain;

/**
 * Clase que crea una casilla de tipo teleport
 * 
 * @version 0.6
 */
public class Teleport extends Casilla {

    /**
     * Constructor de la clase teleport
     * 
     * @param posX           posicion en X
     * @param posY           posicion en Y
     * @param listaComodines Lista de los comodines escogidos por el ususario
     */
    public Teleport(int posX, int posY) {
        super(posX, posY);
        tipo = "Teleport";
    }

    /**
     * Metodo que verifica el comportamiento de la casilla
     */
    public void getComportamiento(Casilla[][] tablero, Jugador jugador, Jugador enemigo) {

        if (this.ficha != null) {
            int[] numeros = numRamdom();
            int k = numeros[0];
            int l = numeros[1];
            Casilla casilla = tablero[k][l];
            while (!casilla.estaVacia()) {
                numeros = numRamdom();
                k = numeros[0];
                l = numeros[1];
                casilla = tablero[k][l];
            }
            Casilla casillaAnterior = tablero[posX][posY];
            Ficha ficha = casillaAnterior.getFicha();
            ficha.setPosX(k);
            ficha.setPosY(l);
            casilla.setFicha(ficha);
            tablero[k][l] = casilla;
            jugador.cambiarPosicionFicha(casillaAnterior.getFicha(), ficha);
            casillaAnterior.removeFicha();
        }
    }

    /**
     * Metodo que verifica el comportamiento de la casilla
     */
    public void getComportamiento(Casilla[][] tablero, Jugador jugador, daPOOs juego) {
    }

    /*
     * Metodo que retorna una lista de numeros al azar
     * 
     * @return Lista de numeros
     */
    private int[] numRamdom() {
        boolean bandera = true;
        int[] numeros = new int[2];
        while (bandera) {
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);
            if ((x + y) % 2 == 1) {
                numeros[0] = x;
                numeros[1] = y;
                bandera = false;
            }
        }
        return numeros;
    }
}
