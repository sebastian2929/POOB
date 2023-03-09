package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que crea el tablero del juego
 * 
 * @version 1.4
 */
public class Tablero {

    private Casilla[][] tablero;
    private ArrayList<Jugador> jugadores;
    private ArrayList<Ficha> fichas = new ArrayList<Ficha>();

    /**
     * Constructor de la clase tablero
     * 
     * @param jugadores  arreglo de los jugadores
     * @param comodines  lista de los comodines seleccionados
     * @param porcentaje porcentaje de los comodines que se van a crear
     */
    public Tablero(ArrayList<Jugador> jugadores, List<String> casillas, int porcentaje) {
        tablero = new Casilla[10][10];
        int contador = 0;
        String[] casillasEspeciales = new String[2];
        for (String casilla : casillas) {
            casillasEspeciales[contador] = casilla;
            contador++;
        }
        this.jugadores = jugadores;
        for (Jugador j : this.jugadores) {
            fichas.addAll(j.getFichas());
        }
        int random = 0;
        int cantidadCasillasEspeciales = porcentaje / 2;
        int cont = 0;
        if (porcentaje != 0 && casillasEspeciales.length != 0 && casillasEspeciales != null) {
            while (cont < cantidadCasillasEspeciales) {
                random = (int) (Math.random() * casillasEspeciales.length);
                int[] numeros = numRamdom();
                int k = numeros[0];
                int l = numeros[1];
                String casillaEspecial = casillasEspeciales[random];
                if (casillaEspecial.equals("Teleport")) {
                    if (tablero[k][l] == null) {
                        tablero[k][l] = new Teleport(k, l);
                        cont++;
                    }
                }
                if (casillaEspecial.equals("Mine")) {
                    if (tablero[k][l] == null) {
                        tablero[k][l] = new Mine(k, l);
                        cont++;
                    }
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((i + j) % 2 == 1 && tablero[i][j] == null) {
                    tablero[i][j] = new Basica(i, j);
                }
            }
        }
        setFichasTablero();
    }

    /**
     * Metodo que obtiene una ficha
     * 
     * @param posX posicion X
     * @param posY posicion Y
     * @return ficha en la casilla
     */
    public Ficha getFicha(int posX, int posY) {
        Casilla casilla = tablero[posX][posY];
        Ficha ficha = null;
        if (!casilla.estaVacia()) {
            ficha = casilla.getFicha();
        }
        return ficha;
    }

    /**
     * Metodo que remueve una ficha de la casilla
     * 
     * @param x posicion X
     * @param y posicion Y
     */
    public void removeFicha(int x, int y) {
        Casilla casilla = tablero[x][y];
        if (!casilla.estaVacia()) {
            Ficha ficha = casilla.getFicha();
            casilla.removeFicha();
            tablero[x][y] = casilla;
            fichas.remove(ficha);
        }
    }

    /**
     * Metodo que agrega una ficha a la casilla
     * 
     * @param ficha
     */
    public void addFicha(Ficha ficha) {
        Casilla casilla = tablero[ficha.getPosX()][ficha.getPosY()];
        if (casilla.estaVacia()) {
            casilla.setFicha(ficha);
            tablero[ficha.getPosX()][ficha.getPosY()] = casilla;
            fichas.add(ficha);
        }
    }

    /**
     * Metodo que obtiene el tablero del juego
     * 
     * @return las casilla del tablero
     */
    public Casilla[][] getTablero() {
        return this.tablero;
    }

    /**
     * Metodo que verifica si una casilla se encuentra vacia
     * 
     * @param posX posicion X
     * @param posY posicion Y
     * @return verificacion de su una casilla esta vacia
     */
    public boolean estaVacia(int posX, int posY) {
        boolean bandera = false;
        Casilla casilla = tablero[posX][posY];
        bandera = casilla.estaVacia();
        return bandera;
    }

    /**
     * Metodo que verifica si la posicion existe en el tablero
     * 
     * @param posX posicion X
     * @param posY posicion Y
     * @return Verificacion si existe la posicion
     */
    public boolean esCorrecta(int posX, int posY) {
        if (posX < 0 || posY < 0 || posX > 9 || posY > 9)
            return false;
        if ((posX + posY) % 2 == 0)
            return false;
        return true;
    }

    /**
     * Metodo que obtiene una casilla especifiaca de todo el tablero
     * 
     * @param posX posicion X
     * @param posY posicion Y
     * @return una casilla espesifica
     */
    public Casilla getCasilla(int posX, int posY) {
        return tablero[posX][posY];
    }

    /**
     * Metodo que cambia de posicion la ficha
     * 
     * @param posXvieja Posicion en X antigua
     * @param posYvieja Posicion en Y antigua
     * @param x         nueva posicion de X
     * @param y         nueva posicion en Y
     */
    public void cambiarPosicionFicha(int posXvieja, int posYvieja, int x, int y) {
        Casilla casillaAnterior = tablero[posXvieja][posYvieja];
        Casilla casillaNueva = tablero[x][y];
        Ficha ficha = casillaAnterior.getFicha();
        ficha.setPosX(x);
        ficha.setPosY(y);
        casillaNueva.setFicha(ficha);
        tablero[x][y] = casillaNueva;
    }

    /**
     * Metodo qque verifica si se puede mover a una posicion
     * 
     * @param escogidaX posicion en X
     * @param escogidaY posicion en Y
     * @return una verificacion sobre si se puede mover
     */
    public boolean puedeMover(int escogidaX, int escogidaY) {
        boolean bandera = false;
        Casilla casilla = tablero[escogidaX][escogidaY];
        Ficha ficha = casilla.getFicha();
        if (ficha != null) {
            String color = ficha.getColor();
            bandera = ficha.puedeMover(escogidaX, escogidaY, this.tablero, color);
        }
        return bandera;
    }

    /**
     * Metodo que verifica si el jugador puede comer alguna ficha
     * 
     * @param escogidaX posicion X
     * @param escogidaY posicion Y
     * @return Verificacion sobre si puede comer
     */
    public boolean puedeComer(int escogidaX, int escogidaY) {
        boolean bandera = false;
        Casilla casilla = tablero[escogidaX][escogidaY];
        Ficha ficha = casilla.getFicha();
        if (ficha != null) {
            String color = ficha.getColor();
            bandera = ficha.puedeComer(escogidaX, escogidaY, this.tablero, color);
        }
        return bandera;

    }

    /**
     * Metodo que mueve una ficha a una poasicion valida
     * 
     * @param escogidaX Posicion en X de la ficha
     * @param escogidaY Posicion en Y de la ficha
     * @param x         Posicion a donde se va a mover en X
     * @param y         Posicion a donde se va a mover en Y
     * @return Verificacion sobre si movio una ficha
     */
    public boolean moverFicha(int escogidaX, int escogidaY, int x, int y) {
        boolean bandera = false;
        Casilla casilla = tablero[escogidaX][escogidaY];
        Ficha ficha = casilla.getFicha();
        if (ficha != null) {
            String color = ficha.getColor();
            bandera = ficha.moverFicha(escogidaX, escogidaY, x, y, this.tablero, color);
        }
        return bandera;
    }

    /**
     * Metodo que verifica si puede comer una ficha
     * 
     * @param escogidaX Posicion en X de la ficha
     * @param escogidaY Posicion en Y de la ficha
     * @param x         Posicion a donde se va a mover en X
     * @param y         Posicion a donde se va a mover en Y
     * @return Verificacion sobre si comio una ficha
     */
    public boolean comerFicha(int escogidaX, int escogidaY, int x, int y) {
        boolean bandera = false;
        Casilla casilla = tablero[escogidaX][escogidaY];
        Ficha ficha = casilla.getFicha();
        if (ficha != null) {
            String color = ficha.getColor();
            bandera = ficha.comerFicha(escogidaX, escogidaY, x, y, this.tablero, color);
        }
        return bandera;
    }

    /**
     * Metodo que verifica si un jugador podia comer alguna ficha en su turno
     * 
     * @param jugador Jugador en tiurno
     * @return Arreglo de fichas que podian comer
     */
    public ArrayList<Ficha> podiaComer(Jugador jugador) {
        ArrayList<Ficha> fichasAborrar = jugador.podiaComer(this.tablero);
        return fichasAborrar;
    }

    /**
     * Mertodo que retorna una ficha que fue comida
     * 
     * @return Ficha comida
     */
    public Ficha fichaComida() {
        Ficha fichaComida = null;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Casilla casillaTablero = tablero[i][j];
                if (casillaTablero != null && casillaTablero.getFichaComida() != null) {
                    fichaComida = casillaTablero.getFichaComida();
                    fichas.remove(fichaComida);
                }
            }
        }
        return fichaComida;
    }

    /**
     * metodo para casilla
     * 
     * @param x
     * @param y
     * @param jugador
     */
    public void getComportamiento(int x, int y, Jugador jugador, Jugador enemigo) {
        Casilla casilla = tablero[x][y];
        casilla.getComportamiento(tablero, jugador, enemigo);
    }

    /**
     * metodo para comodin
     * 
     * @param x
     * @param y
     * @param jugador
     */
    public void getComportamiento(int x, int y, Jugador jugador, daPOOs juego) {
        Comodin comodin = tablero[x][y].getComodin();
        comodin.getComportamiento(tablero, jugador, juego);
    }

    /**
     * Metodo que verifica si un comodin se encuentra activo
     * 
     * @param x Posicion en X
     * @param y Posicion en Y
     * @return Verificacion si un comodin esta activo
     */
    public boolean comodinActivo(int x, int y) {
        boolean bandera = tablero[x][y].comodinActivo();
        return bandera;
    }

    /**
     * Metodo que verifica si una ficha llego al final y corona
     * 
     * @param jugador Jugador en turno
     */
    public void coronar(Jugador jugador) {
        String color = jugador.getColor();
        Ficha fichaNueva = null;
        ArrayList<Ficha> fichasJugador = jugador.getFichas();
        if (color.equals("Black")) {
            for (Ficha ficha : fichasJugador) {
                if (ficha.getTipo().equals("normal")) {
                    int posX = ficha.getPosX();
                    int posY = ficha.getPosY();
                    if (ficha.getPosX() == 9) {
                        jugador.removeFicha(ficha);
                        removeFicha(posX, posY);
                        fichaNueva = new Reina(posX, posY, color);
                        jugador.addFicha(fichaNueva);
                        addFicha(fichaNueva);
                        jugador.setFichasCoradas();
                    }
                }
            }
        }
        if (color.equals("White")) {
            for (Ficha ficha : fichasJugador) {
                if (ficha.getTipo().equals("normal")) {
                    int posX = ficha.getPosX();
                    int posY = ficha.getPosY();
                    if (ficha.getPosX() == 0) {
                        jugador.removeFicha(ficha);
                        removeFicha(posX, posY);
                        fichaNueva = new Reina(posX, posY, color);
                        jugador.addFicha(fichaNueva);
                        addFicha(fichaNueva);
                        jugador.setFichasCoradas();
                    }
                }
            }
        }
    }

    /**
     * Metodo que retorna las fichas que se encuentran en el atablero
     * 
     * @return Fichas que estan en el tablero
     */
    public ArrayList<Ficha> getFichasTablero() {
        return fichas;
    }

    /**
     * Metodo que retorna la cantidad de comodines
     * 
     * @return Cantidad de comodines
     */
    public int getComodines() {
        int num = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Casilla casilla = tablero[i][j];
                if (casilla != null) {
                    Comodin comodin = casilla.getComodin();
                    if (comodin != null) {
                        num++;
                    }
                }
            }
        }
        return num;
    }

    /*
     * Metodo que retorna una liusta de nuemeros al azar
     * 
     * @return
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

    /*
     * Metdo que agrega las fichas al tablero al comenzar la partida
     */
    private void setFichasTablero() {
        ArrayList<Ficha> fichasNegras = new ArrayList<Ficha>();
        ArrayList<Ficha> fichasBlancas = new ArrayList<Ficha>();
        // separar fichas por color
        for (Ficha ficha : fichas) {
            String color = ficha.getColor();
            if (color.equals("Black")) {
                fichasNegras.add(ficha);
            } else if (color.equals("White")) {
                fichasBlancas.add(ficha);
            }
        }
        // añadir fichas Negras al tablero
        int contNegras = 0;
        while (contNegras < fichasNegras.size()) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 10; j++) {
                    Casilla casilla = tablero[i][j];
                    if (casilla != null) {
                        casilla.setFicha(fichasNegras.get(contNegras));
                        contNegras++;
                    }

                }
            }
        }
        // añadir fichas blancas al tablero
        int contBlancas = 0;
        while (contBlancas < fichasBlancas.size()) {
            for (int i = 6; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    Casilla casilla = tablero[i][j];
                    if (casilla != null) {
                        casilla.setFicha(fichasBlancas.get(contBlancas));
                        contBlancas++;
                    }
                }
            }
        }
    }

}
