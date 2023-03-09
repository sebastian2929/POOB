package domain;

/**
 * Clase que crea una ficha de tipo normal
 * 
 * @version 0.3
 */
public class Peon extends Ficha {

    /**
     * Constructor de la clase Peon
     * @param color color de la ficha
     */
    public Peon(int x, int y, String color) {
        super(x, y, color);
        this.tipo = "normal";
    }

    /**
     * Metodo que verifica que un Peon puede comer.
     * @param escogidaX posicion x de la ficha escogida.
     * @param escogidaY posicion y de la ficha escogida.
     * @param tablero   tablero del juego.
     * @param color     color de la ficha escogida.
     * @return bandera , true si puede comer de lo contratio false.
     */
    public boolean puedeComer(int escogidaX, int escogidaY, Casilla[][] tablero, String color) {
        boolean bandera = false;
        if (comerIzquierda(escogidaX, escogidaY, tablero, color)
                || comerDerecha(escogidaX, escogidaY, tablero, color)) {
            bandera = true;
        }
        return bandera;
    }

    /*
     * Metodo que verifica si la ficha puede comer por izquierda.
     * @param escogidaX posicion x de la ficha escogida.
     * @param escogidaY posicion y de la ficha escogida.
     * @param tablero tablero del juego.
     * @param color color de la ficha escogida.
     * @return bandera , true si puede comer de lo contratio false.
     */
    private boolean comerIzquierda(int escogidaX, int escogidaY, Casilla[][] tablero, String color) {
        boolean bandera = false;
        Casilla casillaEnemigo;
        Casilla casillaVacia;
        String enemigo;
        if (escogidaX + 2 < 10 && escogidaY - 2 >= 0) {
            if (color.equals("Black")) {
                enemigo = "White";
                casillaEnemigo = tablero[escogidaX + 1][escogidaY - 1];
                if (casillaEnemigo.estaVacia() == false && casillaEnemigo.getFicha().getColor().equals(enemigo)) {
                    casillaVacia = tablero[escogidaX + 2][escogidaY - 2];
                    if (casillaVacia.estaVacia()) {
                        bandera = true;
                    }
                }
            }
        }
        if (escogidaX - 2 >= 0 && escogidaY - 2 >= 0) {
            if (color.equals("White")) {
                enemigo = "Black";
                casillaEnemigo = tablero[escogidaX - 1][escogidaY - 1];
                if (casillaEnemigo.estaVacia() == false && casillaEnemigo.getFicha().getColor().equals(enemigo)) {
                    casillaVacia = tablero[escogidaX - 2][escogidaY - 2];
                    if (casillaVacia.estaVacia()) {
                        bandera = true;
                    }
                }
            }
        }
        return bandera;
    }

    /*
     * Metodo que verifica si la ficha puede comer por derecha.
     * @param escogidaX posicion x de la ficha escogida.
     * @param escogidaY posicion y de la ficha escogida.
     * @param tablero tablero del juego.
     * @param color color de la ficha escogida.
     * @return bandera , true si puede comer de lo contratio false.
     */
    private boolean comerDerecha(int escogidaX, int escogidaY, Casilla[][] tablero, String color) {
        boolean bandera = false;
        Casilla casillaEnemigo;
        Casilla casillaVacia;
        String enemigo;
        if (escogidaX + 2 < 10 && escogidaY + 2 < 10) {
            if (color.equals("Black")) {
                enemigo = "White";
                casillaEnemigo = tablero[escogidaX + 1][escogidaY + 1];
                if (casillaEnemigo.estaVacia() == false && casillaEnemigo.getFicha().getColor().equals(enemigo)) {
                    casillaVacia = tablero[escogidaX + 2][escogidaY + 2];
                    if (casillaVacia.estaVacia()) {
                        bandera = true;
                    }
                }
            }
        }
        if (escogidaX - 2 >= 0 && escogidaY + 2 < 10) {
            if (color.equals("White")) {
                enemigo = "Black";
                casillaEnemigo = tablero[escogidaX - 1][escogidaY + 1];
                if (casillaEnemigo.estaVacia() == false && casillaEnemigo.getFicha().getColor().equals(enemigo)) {
                    casillaVacia = tablero[escogidaX - 2][escogidaY + 2];
                    if (casillaVacia.estaVacia()) {
                        bandera = true;
                    }
                }
            }
        }
        return bandera;
    }

    /**
     * Metodo que verifica que un Peon comio.
     * @param escogidaX posicion x de la ficha escogida.
     * @param escogidaY posicion y de la ficha escogida.
     * @param x         posicion x a mover la ficha escogida.
     * @param y         posicion y a mover la ficha escogida.
     * @param tablero   tablero del juego.
     * @param color     color de la ficha escogida.
     * @return bandera , true si puede comer de lo contratio false.
     */
    public boolean comerFicha(int escogidaX, int escogidaY, int x, int y, Casilla[][] tablero, String color) {
        boolean bandera = false;
        if (comerIzquierda(escogidaX, escogidaY, x, y, tablero, color)
                || comerDerecha(escogidaX, escogidaY, x, y, tablero, color)) {
            bandera = true;
        }
        return bandera;
    }

    /*
     * Metodo que comprueba si un peon comio por izquierda.
     * @param escogidaX posicion x de la ficha escogida.
     * @param escogidaY posicion y de la ficha escogida.
     * @param x posicion x a mover la ficha.
     * @param y posicion y a mover la ficha.
     * @param tablero tablero del juego.
     * @param color color de la ficha escogida.
     * @return bandera , true si puede comer de lo contratio false.
     */
    private boolean comerIzquierda(int escogidaX, int escogidaY, int x, int y, Casilla[][] tablero, String color) {
        boolean bandera = false;
        Casilla casillaEnemigo;
        Casilla casillaVacia;
        if (color.equals("Black") && escogidaX + 2 < 10 && escogidaY - 2 >= 0) {
            casillaVacia = tablero[escogidaX + 2][escogidaY - 2];
            if (casillaVacia.getPosX() == x && casillaVacia.getPosY() == y) {
                casillaEnemigo = tablero[escogidaX + 1][escogidaY - 1];
                Ficha fichaComida = casillaEnemigo.getFicha();
                casillaEnemigo.setFichaComida(fichaComida);
                casillaEnemigo.removeFicha();
                tablero[escogidaX + 1][escogidaY - 1] = casillaEnemigo;
                bandera = true;
            }
        }
        if (color.equals("White") && escogidaX - 2 >= 0 && escogidaY - 2 >= 0) {
            casillaVacia = tablero[escogidaX - 2][escogidaY - 2];
            if (casillaVacia.getPosX() == x && casillaVacia.getPosY() == y) {
                casillaEnemigo = tablero[escogidaX - 1][escogidaY - 1];
                Ficha fichaComida = casillaEnemigo.getFicha();
                casillaEnemigo.setFichaComida(fichaComida);
                casillaEnemigo.removeFicha();
                tablero[escogidaX - 1][escogidaY - 1] = casillaEnemigo;
                bandera = true;
            }
        }
        return bandera;
    }

    /*
     * Metodo que comprueba si un peon comio por derecha.
     * @param escogidaX posicion x de la ficha escogida.
     * @param escogidaY posicion y de la ficha escogida.
     * @param x posicion x a mover la ficha.
     * @param y posicion y a mover la ficha.
     * @param tablero tablero del juego.
     * @param color color de la ficha escogida.
     * @return bandera , true si puede comer de lo contratio false.
     */
    private boolean comerDerecha(int escogidaX, int escogidaY, int x, int y, Casilla[][] tablero, String color) {
        boolean bandera = false;
        Casilla casillaEnemigo;
        Casilla casillaVacia;

        if (color.equals("Black") && escogidaX + 2 < 10 && escogidaY + 2 < 10) {
            casillaVacia = tablero[escogidaX + 2][escogidaY + 2];
            if (casillaVacia.getPosX() == x && casillaVacia.getPosY() == y) {
                casillaEnemigo = tablero[escogidaX + 1][escogidaY + 1];
                Ficha fichaComida = casillaEnemigo.getFicha();
                casillaEnemigo.setFichaComida(fichaComida);
                casillaEnemigo.removeFicha();
                tablero[escogidaX + 1][escogidaY + 1] = casillaEnemigo;
                bandera = true;
            }
        }
        if (color.equals("White") && escogidaX - 2 >= 0 && escogidaY + 2 < 10) {
            casillaVacia = tablero[escogidaX - 2][escogidaY + 2];
            if (casillaVacia.getPosX() == x && casillaVacia.getPosY() == y) {
                casillaEnemigo = tablero[escogidaX - 1][escogidaY + 1];
                Ficha fichaComida = casillaEnemigo.getFicha();
                casillaEnemigo.setFichaComida(fichaComida);
                casillaEnemigo.removeFicha();
                tablero[escogidaX - 1][escogidaY + 1] = casillaEnemigo;
                bandera = true;
            }
        }
        return bandera;
    }

    /**
     * Metodo que verifica que un Peon puede mover.
     * @param escogidaX posicion x de la ficha escogida.
     * @param escogidaY posicion y de la ficha escogida.
     * @param tablero   tablero del juego.
     * @param color     color de la ficha escogida.
     * @return bandera , true si puede comer de lo contratio false.
     */
    public boolean puedeMover(int escogidaX, int escogidaY, Casilla[][] tablero, String color) {
        boolean bandera = false;
        if (moverIzquierda(escogidaX, escogidaY, tablero, color)
                || moverDerecha(escogidaX, escogidaY, tablero, color)) {
            bandera = true;
        }
        return bandera;
    }

    /*
     * Metodo que verifica que un Peon comio.
     * @param escogidaX posicion x de la ficha escogida.
     * @param escogidaY posicion y de la ficha escogida.
     * @param tablero tablero del juego.
     * @param color color de la ficha escogida.
     * @return bandera , true si puede comer de lo contratio false.
     */
    private boolean moverIzquierda(int escogidaX, int escogidaY, Casilla[][] tablero, String color) {
        boolean bandera = false;
        Casilla casillaVacia;
        if (escogidaX + 1 < 10 && escogidaY - 1 >= 0) {
            if (color.equals("Black")) {
                casillaVacia = tablero[escogidaX + 1][escogidaY - 1];
                if (casillaVacia.estaVacia()) {
                    bandera = true;
                }
            }
        }
        if (escogidaX - 1 >= 0 && escogidaY - 1 >= 0) {
            if (color.equals("White")) {
                casillaVacia = tablero[escogidaX - 1][escogidaY - 1];
                if (casillaVacia.estaVacia()) {
                    bandera = true;
                }
            }
        }
        return bandera;
    }

    /*
     * Metodo que verifica que un Peon puede mover por derecha.
     * @param escogidaX posicion x de la ficha escogida.
     * @param escogidaY posicion y de la ficha escogida.
     * @param tablero tablero del juego.
     * @param color color de la ficha escogida.
     * @return bandera , true si puede comer de lo contratio false.
     */
    private boolean moverDerecha(int escogidaX, int escogidaY, Casilla[][] tablero, String color) {
        boolean bandera = false;
        Casilla casillaVacia;
        if (escogidaX + 1 < 10 && escogidaY + 1 < 10) {
            if (color.equals("Black")) {
                casillaVacia = tablero[escogidaX + 1][escogidaY + 1];
                if (casillaVacia.estaVacia()) {
                    bandera = true;
                }
            }
        }
        if (escogidaX - 1 >= 0 && escogidaY + 1 < 10) {
            if (color.equals("White")) {
                casillaVacia = tablero[escogidaX - 1][escogidaY + 1];
                if (casillaVacia.estaVacia()) {
                    bandera = true;
                }
            }
        }
        return bandera;
    }

    /**
     * Metodo que verifica que un Peon se movio.
     * @param escogidaX posicion x de la ficha escogida.
     * @param escogidaY posicion y de la ficha escogida.
     * @param x         posicion x a mover la ficha escogida.
     * @param y         posicion y a mover la ficha escogida.
     * @param tablero   tablero del juego.
     * @param color     color de la ficha escogida.
     * @return bandera , true si puede comer de lo contratio false.
     */
    public boolean moverFicha(int escogidaX, int escogidaY, int x, int y, Casilla[][] tablero, String color) {
        boolean bandera = false;
        if (moverIzquierda(escogidaX, escogidaY, x, y, tablero, color)
                || moverDerecha(escogidaX, escogidaY, x, y, tablero, color)) {
            bandera = true;
        }
        return bandera;
    }

    /*
     * Metodo que comprueba si un peon se movio por izquierda.
     * @param escogidaX posicion x de la ficha escogida.
     * @param escogidaY posicion y de la ficha escogida.
     * @param x posicion x a mover la ficha.
     * @param y posicion y a mover la ficha.
     * @param tablero tablero del juego.
     * @param color color de la ficha escogida.
     * @return bandera , true si puede comer de lo contratio false.
     */
    private boolean moverIzquierda(int escogidaX, int escogidaY, int x, int y, Casilla[][] tablero, String color) {
        boolean bandera = false;
        Casilla casillaVacia;
        if (escogidaX + 1 < 10 && escogidaY - 1 >= 0) {
            if (color.equals("Black")) {
                casillaVacia = tablero[escogidaX + 1][escogidaY - 1];
                if (casillaVacia.getPosX() == x && casillaVacia.getPosY() == y) {
                    bandera = true;
                }
            }
        }
        if (escogidaX - 1 >= 0 && escogidaY - 1 >= 0) {
            if (color.equals("White")) {
                casillaVacia = tablero[escogidaX - 1][escogidaY - 1];
                if (casillaVacia.getPosX() == x && casillaVacia.getPosY() == y) {
                    bandera = true;
                }
            }
        }
        return bandera;
    }

    /*
     * Metodo que comprueba si un peon comio por derecha.
     * @param escogidaX posicion x de la ficha escogida.
     * @param escogidaY posicion y de la ficha escogida.
     * @param x posicion x a mover la ficha.
     * @param y posicion y a mover la ficha.
     * @param tablero tablero del juego.
     * @param color color de la ficha escogida.
     * @return bandera , true si puede comer de lo contratio false.
     */
    private boolean moverDerecha(int escogidaX, int escogidaY, int x, int y, Casilla[][] tablero, String color) {
        boolean bandera = false;
        Casilla casillaVacia;
        if (escogidaX + 1 < 10 && escogidaY + 1 < 10) {
            if (color.equals("Black")) {
                casillaVacia = tablero[escogidaX + 1][escogidaY + 1];
                if (casillaVacia.getPosX() == x && casillaVacia.getPosY() == y) {
                    bandera = true;
                }
            }
        }
        if (escogidaX - 1 >= 0 && escogidaY + 1 < 10) {
            if (color.equals("White")) {
                casillaVacia = tablero[escogidaX - 1][escogidaY + 1];
                if (casillaVacia.getPosX() == x && casillaVacia.getPosY() == y) {
                    bandera = true;
                }
            }
        }
        return bandera;
    }
}
