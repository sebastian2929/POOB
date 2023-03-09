package domain;

/**
 * Clase que crea una reina 
 * 
 * @version 2.1
 */
public class Reina extends Ficha {

    /**
     * Constructor de la clase reina
     * @param x
     * @param y
     * @param color
     */
    public Reina(int x, int y, String color) {
        super(x, y, color);
        this.tipo = "Reina";
    }

    /**
     * Metodo que verifica si puede mover
     * Metodo que comprueba si un peon comio por derecha.
     * @param escogidaX posicion x de la ficha escogida.
     * @param escogidaY posicion y de la ficha escogida.
     * @param tablero tablero del juego.
     * @param color color de la ficha escogida.
     * @return bandera , true si puede comer de lo contratio false.
     */
    public boolean puedeMover(int escogidaX, int escogidaY, Casilla[][] tablero, String color) {
        boolean bandera = false;
        if (moverIzquierdaAbajo(escogidaX, escogidaY, tablero, color)
                || moverIzquierdaArriba(escogidaX, escogidaY, tablero, color)
                || moverDerechaAbajo(escogidaX, escogidaY, tablero, color)
                || moverDerechaArriba(escogidaX, escogidaY, tablero, color)) {
            bandera = true;
        }
        return bandera;
    }

    /*
     * Metodo que verifica si puede mover la parte supereior izquierda
     * @param escogidaX posicion X
     * @param escogidaY posicion Y
     * @param tablero Casillas de todo el juego
     * @param color Color de la ficha
     * @return
     */
    private boolean moverIzquierdaArriba(int escogidaX, int escogidaY, Casilla[][] tablero, String color) {
        boolean bandera = false;
        int limiteX = escogidaX;
        int limiteY = escogidaY;
        Casilla casilla = null;
        int limX = 0;
        int limY = 0;
        if (escogidaX < escogidaY) {
            limX = 0;
            limY = Math.abs(escogidaY - escogidaX);
        } else {
            limX = Math.abs(escogidaX - escogidaY);
            limY = 0;
        }
        if (escogidaX > 0 && escogidaY > 0) {
            limiteX--;
            limiteY--;
            casilla = tablero[limiteX][limiteY];
            while (limiteX >= limX && limiteY >= limY && casilla.estaVacia() && limiteX >= 0 && limiteY >= 0
                    && limiteX <= 9 && limiteY <= 9) {
                casilla = tablero[limiteX][limiteY];
                limiteX--;
                limiteY--;
            }
        }
        if (limiteX != escogidaX && limiteY != escogidaY) {
            bandera = true;
        }
        return bandera;
    }

    /*
     * Metodo que verifica si puede mover la parte inferior izquierda
     * @param escogidaX posicion X
     * @param escogidaY posicion Y
     * @param tablero Casillas de todo el juego
     * @param color Color de la ficha
     * @return
     */
    private boolean moverIzquierdaAbajo(int escogidaX, int escogidaY, Casilla[][] tablero, String color) {
        boolean bandera = false;
        int limiteX = escogidaX;
        int limiteY = escogidaY;
        Casilla casilla = null;
        int limX = 0;
        int limY = 0;
        if (escogidaX + escogidaY <= 9) {
            limX = escogidaX + escogidaY;
            limY = 0;
        } else {
            limX = 9;
            limY = Math.abs((9 - escogidaY) - escogidaX);
        }
        if (escogidaX < 9 && escogidaY > 0) {
            limiteX++;
            limiteY--;
            casilla = tablero[limiteX][limiteY];
            while (limiteX <= limX && limiteY >= limY && casilla.estaVacia() && limiteX >= 0 && limiteY >= 0
                    && limiteX <= 9 && limiteY <= 9) {
                casilla = tablero[limiteX][limiteY];
                limiteX++;
                limiteY--;
            }
        }
        if (limiteX != escogidaX && limiteY != escogidaY) {
            bandera = true;
        }
        return bandera;
    }

    /*
     * Metodo que verifica si puede mover la parte superior derecha
     * @param escogidaX posicion X
     * @param escogidaY posicion Y
     * @param tablero Casillas de todo el juego
     * @param color Color de la ficha
     * @return
     */
    private boolean moverDerechaArriba(int escogidaX, int escogidaY, Casilla[][] tablero, String color) {
        boolean bandera = false;
        int limiteX = escogidaX;
        int limiteY = escogidaY;
        Casilla casilla = null;
        int limX = 0;
        int limY = 0;
        if (escogidaX + escogidaY <= 9) {
            limY = escogidaX + escogidaY;
            limX = 0;
        } else {
            limY = 9;
            limX = Math.abs((9 - escogidaX) - escogidaY);
        }
        if (escogidaY < 9 && escogidaX > 0) {
            limiteX--;
            limiteY++;
            casilla = tablero[limiteX][limiteY];
            while (limiteX >= limX && limiteY <= limY && casilla.estaVacia() && limiteX >= 0 && limiteY >= 0
                    && limiteX <= 9 && limiteY <= 9) {
                casilla = tablero[limiteX][limiteY];
                limiteX--;
                limiteY++;

            }
        }
        if (limiteX != escogidaX && limiteY != escogidaY) {
            bandera = true;
        }
        return bandera;
    }

    /*
     * Metodo que verifica si puede mover la parte inferior derecha
     * @param escogidaX posicion X
     * @param escogidaY posicion Y
     * @param tablero Casillas de todo el juego
     * @param color Color de la ficha
     * @return
     */
    private boolean moverDerechaAbajo(int escogidaX, int escogidaY, Casilla[][] tablero, String color) {
        boolean bandera = false;
        int limiteX = escogidaX;
        int limiteY = escogidaY;
        Casilla casilla = null;
        int limX = 9;
        int limY = 9;
        if (escogidaY < 9 && escogidaX < 9) {
            limiteX++;
            limiteY++;
            casilla = tablero[limiteX][limiteY];
            while (limiteX <= limX && limiteY <= limY && casilla.estaVacia() && limiteX >= 0 && limiteY >= 0
                    && limiteX <= 9 && limiteY <= 9) {
                casilla = tablero[limiteX][limiteY];
                limiteX++;
                limiteY++;

            }
        }
        if (limiteX != escogidaX && limiteY != escogidaY) {
            bandera = true;
        }
        return bandera;
    }

    /**
     * Metodo que permite mover a la ficha
     * @param escogidaX posicion X
     * @param escogidaY posicion Y
     * @param x posicion X a la que se va a mover
     * @param y posicion Y a la que se va a mover
     * @param tablero Casillas de todo el juego
     * @param color Color de la ficha
     * @return verifica si se movio una ficha
     */
    public boolean moverFicha(int escogidaX, int escogidaY, int x, int y, Casilla[][] tablero, String color) {
        boolean bandera = false;
        if (moverIzquierdaAbajo(escogidaX, escogidaY, x, y, tablero, color)
                || moverIzquierdaArriba(escogidaX, escogidaY, x, y, tablero, color)
                || moverDerechaAbajo(escogidaX, escogidaY, x, y, tablero, color)
                || moverDerechaArriba(escogidaX, escogidaY, x, y, tablero, color)) {
            bandera = true;
        }
        return bandera;
    }

    /*
     * Metodo que permite mover a la parte superior izquierda
     * @param escogidaX posicion X
     * @param escogidaY posicion Y
     * @param x posicion X a la que se va a mover
     * @param y posicion Y a la que se va a mover
     * @param tablero Casillas de todo el juego
     * @param color Color de la ficha
     * @return verifica si se movio
     */
    private boolean moverIzquierdaArriba(int escogidaX, int escogidaY, int x, int y, Casilla[][] tablero,
            String color) {
        boolean bandera = false;
        int limiteX = escogidaX;
        int limiteY = escogidaY;
        Casilla casilla = null;
        int limX = 0;
        int limY = 0;
        if (escogidaX < escogidaY) {
            limX = 0;
            limY = Math.abs(escogidaY - escogidaX);
        } else {
            limX = Math.abs(escogidaX - escogidaY);
            limY = 0;
        }
        if (escogidaX > 0 && escogidaY > 0) {
            limiteX--;
            limiteY--;
            casilla = tablero[limiteX][limiteY];
            while (limiteX >= limX && limiteY >= limY && casilla.estaVacia() && limiteX >= 0 && limiteY >= 0
                    && limiteX <= 9 && limiteY <= 9 && !bandera) {
                casilla = tablero[limiteX][limiteY];
                limiteX--;
                limiteY--;
                if (casilla.getPosX() == x && casilla.getPosY() == y) {
                    bandera = true;
                }
            }
        }
        return bandera;
    }

    /*
     * Metodo que permite mover a la parte inferior izquierda
     * @param escogidaX posicion X
     * @param escogidaY posicion Y
     * @param x posicion X a la que se va a mover
     * @param y posicion Y a la que se va a mover
     * @param tablero Casillas de todo el juego
     * @param color Color de la ficha
     * @return verifica si se movio
     */
    private boolean moverIzquierdaAbajo(int escogidaX, int escogidaY, int x, int y, Casilla[][] tablero, String color) {
        boolean bandera = false;
        int limiteX = escogidaX;
        int limiteY = escogidaY;
        Casilla casilla = null;
        int limX = 0;
        int limY = 0;
        if (escogidaX + escogidaY <= 9) {
            limX = escogidaX + escogidaY;
            limY = 0;
        } else {
            limX = 9;
            limY = Math.abs((9 - escogidaX) - escogidaY);
        }
        if (escogidaX < 9 && escogidaY > 0) {
            limiteX++;
            limiteY--;
            casilla = tablero[limiteX][limiteY];
            while (limiteX <= limX && limiteY >= limY && casilla.estaVacia() && limiteX >= 0 && limiteY >= 0
                    && limiteX <= 9 && limiteY <= 9 && !bandera) {
                casilla = tablero[limiteX][limiteY];
                limiteX++;
                limiteY--;
                if (casilla.getPosX() == x && casilla.getPosY() == y) {
                    bandera = true;
                }
            }
        }
        return bandera;
    }

    /*
     * Metodo que permite mover a la parte superior derecha
     * @param escogidaX posicion X
     * @param escogidaY posicion Y
     * @param x posicion X a la que se va a mover
     * @param y posicion Y a la que se va a mover
     * @param tablero Casillas de todo el juego
     * @param color Color de la ficha
     * @return verifica si se movio
     */
    private boolean moverDerechaArriba(int escogidaX, int escogidaY, int x, int y, Casilla[][] tablero, String color) {
        boolean bandera = false;
        int limiteX = escogidaX;
        int limiteY = escogidaY;
        Casilla casilla = null;
        int limX = 0;
        int limY = 0;
        if (escogidaX + escogidaY <= 9) {
            limY = escogidaX + escogidaY;
            limX = 0;
        } else {
            limY = 9;
            limX = Math.abs((9 - escogidaX) - escogidaY);
        }
        if (escogidaY < 9 && escogidaX > 0) {
            limiteX--;
            limiteY++;
            casilla = tablero[limiteX][limiteY];
            while (limiteX >= limX && limiteY <= limY && casilla.estaVacia() && limiteX >= 0 && limiteY >= 0
                    && limiteX <= 9 && limiteY <= 9 && !bandera) {
                casilla = tablero[limiteX][limiteY];
                limiteX--;
                limiteY++;
                if (casilla.getPosX() == x && casilla.getPosY() == y) {
                    bandera = true;
                }
            }
        }
        return bandera;
    }

    /*
     * Metodo que permite mover a la parte inferiror derecha
     * @param escogidaX posicion X
     * @param escogidaY posicion Y
     * @param x posicion X a la que se va a mover
     * @param y posicion Y a la que se va a mover
     * @param tablero Casillas de todo el juego
     * @param color Color de la ficha
     * @return verifica si se movio
     */
    private boolean moverDerechaAbajo(int escogidaX, int escogidaY, int x, int y, Casilla[][] tablero, String color) {
        boolean bandera = false;
        int limiteX = escogidaX;
        int limiteY = escogidaY;
        Casilla casilla = null;
        int limX = 9;
        int limY = 9;
        if (escogidaY < 9 && escogidaX < 9) {
            limiteX++;
            limiteY++;
            casilla = tablero[limiteX][limiteY];
            while (limiteX <= limX && limiteY <= limY && casilla.estaVacia() && limiteX >= 0 && limiteY >= 0
                    && limiteX <= 9 && limiteY <= 9 && !bandera) {
                casilla = tablero[limiteX][limiteY];
                limiteX++;
                limiteY++;
                if (casilla.getPosX() == x && casilla.getPosY() == y) {
                    bandera = true;
                }
            }
        }
        return bandera;
    }

    /**
     * Metodo que verica si puede comer la ficha
     * @param escogidaX posicion X
     * @param escogidaY posicion Y
     * @param tablero Casillas de todo el juego
     * @param color Color de la ficha
     * @return verifica si se puede comer
     */
    public boolean puedeComer(int escogidaX, int escogidaY, Casilla[][] tablero, String color) {
        boolean bandera = false;
        if (comerIzquierdaAbajo(escogidaX, escogidaY, tablero, color)
                || comerIzquierdaArriba(escogidaX, escogidaY, tablero, color)
                || comerDerechaAbajo(escogidaX, escogidaY, tablero, color)
                || comerDerechaArriba(escogidaX, escogidaY, tablero, color)) {
            bandera = true;
        }
        return bandera;
    }

    /*
     * Metodo que verica si puede comer la ficha en la parte superior izquierda
     * @param escogidaX posicion X
     * @param escogidaY posicion Y
     * @param tablero Casillas de todo el juego
     * @param color Color de la ficha
     * @return verifica si se puede comer
     */
    private boolean comerIzquierdaArriba(int escogidaX, int escogidaY, Casilla[][] tablero, String color) {
        boolean bandera = false;
        int limiteX = escogidaX;
        int limiteY = escogidaY;
        Casilla casilla = null;
        String enemigo = "";
        int limX = 0;
        int limY = 0;
        if (color.equals("Black")) {
            enemigo = "White";
        } else {
            enemigo = "Black";
        }
        if (escogidaX < escogidaY) {
            limX = 0;
            limY = Math.abs(escogidaY - escogidaX);
        } else {
            limX = Math.abs(escogidaX - escogidaY);
            limY = 0;
        }
        if (escogidaX > 1 && escogidaY > 1) {
            limiteX--;
            limiteY--;
            casilla = tablero[limiteX][limiteY];
            while (limiteX >= limX && limiteY >= limY && casilla.estaVacia() && limiteX >= 0 && limiteY >= 0
                    && limiteX <= 9 && limiteY <= 9) {
                casilla = tablero[limiteX][limiteY];
                limiteX--;
                limiteY--;
            }
        }
        if (limiteX >= 0 && limiteY >= 0 && limiteX <= 9 && limiteY <= 9) {
            if (casilla != null && !casilla.estaVacia()) {
                Casilla casillaVacia = tablero[casilla.getPosX() - 1][casilla.getPosY() - 1];
                if (casillaVacia != null && casillaVacia.estaVacia()) {
                    Ficha fichaEnemigo = casilla.getFicha();
                    if (fichaEnemigo.getColor().equals(enemigo)) {
                        bandera = true;
                    }
                }
            }
        }
        return bandera;
    }

    /*
     * Metodo que verica si puede comer la ficha en la parte inferior izquierda
     * @param escogidaX posicion X
     * @param escogidaY posicion Y
     * @param tablero Casillas de todo el juego
     * @param color Color de la ficha
     * @return verifica si se puede comer
     */
    private boolean comerIzquierdaAbajo(int escogidaX, int escogidaY, Casilla[][] tablero, String color) {
        boolean bandera = false;
        int limiteX = escogidaX;
        int limiteY = escogidaY;
        Casilla casilla = null;
        String enemigo = "";
        int limX = 0;
        int limY = 0;
        if (color.equals("Black")) {
            enemigo = "White";
        } else {
            enemigo = "Black";
        }
        if (escogidaX + escogidaY <= 9) {
            limX = escogidaX + escogidaY;
            limY = 0;
        } else {
            limX = 9;
            limY = Math.abs((9 - escogidaY) - escogidaX);
        }
        if (escogidaX < 8 && escogidaY > 1) {
            limiteX++;
            limiteY--;
            casilla = tablero[limiteX][limiteY];
            while (limiteX <= limX && limiteY >= limY && casilla.estaVacia() && limiteX >= 0 && limiteY >= 0
                    && limiteX <= 9 && limiteY <= 9) {
                casilla = tablero[limiteX][limiteY];
                limiteX++;
                limiteY--;

            }
        }
        if (limiteX >= 0 && limiteY >= 0 && limiteX <= 9 && limiteY <= 9) {
            if (casilla != null && !casilla.estaVacia()) {
                Casilla casillaVacia = tablero[casilla.getPosX() + 1][casilla.getPosY() - 1];
                if (casillaVacia != null && casillaVacia.estaVacia()) {
                    Ficha fichaEnemigo = casilla.getFicha();
                    if (fichaEnemigo.getColor().equals(enemigo)) {
                        bandera = true;
                    }
                }
            }
        }
        return bandera;
    }

    /*
     * Metodo que verica si puede comer la ficha en la parte superior dercha
     * @param escogidaX posicion X
     * @param escogidaY posicion Y
     * @param tablero Casillas de todo el juego
     * @param color Color de la ficha
     * @return verifica si se puede comer
     */
    private boolean comerDerechaArriba(int escogidaX, int escogidaY, Casilla[][] tablero, String color) {
        boolean bandera = false;
        int limiteX = escogidaX;
        int limiteY = escogidaY;
        String enemigo = "";
        Casilla casilla = null;
        int limX = 0;
        int limY = 0;
        if (color.equals("Black")) {
            enemigo = "White";
        } else {
            enemigo = "Black";
        }
        if (escogidaX + escogidaY <= 9) {
            limY = escogidaX + escogidaY;
            limX = 0;
        } else {
            limY = 9;
            limX = Math.abs((9 - escogidaX) - escogidaY);
        }
        if (escogidaY < 8 && escogidaX > 1) {
            limiteX--;
            limiteY++;
            casilla = tablero[limiteX][limiteY];
            while (limiteX >= limX && limiteY <= limY && casilla.estaVacia() && limiteX >= 0 && limiteY >= 0
                    && limiteX <= 9 && limiteY <= 9) {
                casilla = tablero[limiteX][limiteY];
                limiteX--;
                limiteY++;

            }
        }
        if (limiteX >= 0 && limiteY >= 0 && limiteX <= 9 && limiteY <= 9) {
            if (casilla != null && !casilla.estaVacia()) {
                Casilla casillaVacia = tablero[casilla.getPosX() - 1][casilla.getPosY() + 1];
                if (casillaVacia != null && casillaVacia.estaVacia()) {
                    Ficha fichaEnemigo = casilla.getFicha();
                    if (fichaEnemigo.getColor().equals(enemigo)) {
                        bandera = true;
                    }
                }
            }
        }
        return bandera;
    }

    /*
     * Metodo que verica si puede comer la ficha en la parte inferior derecha
     * @param escogidaX posicion X
     * @param escogidaY posicion Y
     * @param tablero Casillas de todo el juego
     * @param color Color de la ficha
     * @return verifica si se puede comer
     */
    private boolean comerDerechaAbajo(int escogidaX, int escogidaY, Casilla[][] tablero, String color) {
        boolean bandera = false;
        int limiteX = escogidaX;
        int limiteY = escogidaY;
        String enemigo = "";
        Casilla casilla = null;
        int limX = 9;
        int limY = 9;
        if (color.equals("Black")) {
            enemigo = "White";
        } else {
            enemigo = "Black";
        }
        if (escogidaY < 8 && escogidaX < 8) {
            limiteX++;
            limiteY++;
            casilla = tablero[limiteX][limiteY];
            while (limiteX <= limX && limiteY <= limY && casilla.estaVacia() && limiteX >= 0 && limiteY >= 0
                    && limiteX <= 9 && limiteY <= 9) {
                casilla = tablero[limiteX][limiteY];
                limiteX++;
                limiteY++;
            }
        }
        if (limiteX >= 0 && limiteY >= 0 && limiteX <= 9 && limiteY <= 9) {
            if (casilla != null && !casilla.estaVacia()) {
                Casilla casillaVacia = tablero[casilla.getPosX() + 1][casilla.getPosY() + 1];
                if (casillaVacia != null && casillaVacia.estaVacia()) {
                    Ficha fichaEnemigo = casilla.getFicha();
                    if (fichaEnemigo.getColor().equals(enemigo)) {
                        bandera = true;
                    }
                }
            }
        }
        return bandera;
    }

    /**
     * Metodo que permite comer una ficha.
     * @param escogidaX posicion X
     * @param escogidaY posicion Y
     * @param x posicion X donde se quiere comer
     * @param y posicion Y donde se quiere comer
     * @param tablero Casillas de todo el juego
     * @param color Color de la ficha
     * @return verifica si se puede comer
     */
    public boolean comerFicha(int escogidaX, int escogidaY, int x, int y, Casilla[][] tablero, String color) {
        boolean bandera = false;
        if (comerIzquierdaAbajo(escogidaX, escogidaY, x, y, tablero, color)
                || comerIzquierdaArriba(escogidaX, escogidaY, x, y, tablero, color)
                || comerDerechaAbajo(escogidaX, escogidaY, x, y, tablero, color)
                || comerDerechaArriba(escogidaX, escogidaY, x, y, tablero, color)) {
            bandera = true;
        }
        return bandera;
    }

    /*
     * Metodo que permite comer una ficha en la parte superior izquierda.
     * @param escogidaX posicion X
     * @param escogidaY posicion Y
     * @param x posicion X donde se quiere comer
     * @param y posicion Y donde se quiere comer
     * @param tablero Casillas de todo el juego
     * @param color Color de la ficha
     * @return verifica si se puede comer
     */
    private boolean comerIzquierdaArriba(int escogidaX, int escogidaY, int x, int y, Casilla[][] tablero,
            String color) {
        boolean bandera = false;
        int limiteX = escogidaX;
        int limiteY = escogidaY;
        Casilla casilla = null;
        String enemigo = "";
        int limX = 0;
        int limY = 0;
        if (color.equals("Black")) {
            enemigo = "White";
        } else {
            enemigo = "Black";
        }
        if (escogidaX < escogidaY) {
            limX = 0;
            limY = Math.abs(escogidaY - escogidaX);
        } else {
            limX = Math.abs(escogidaX - escogidaY);
            limY = 0;
        }
        if (escogidaX > 1 && escogidaY > 1) {
            limiteX--;
            limiteY--;
            casilla = tablero[limiteX][limiteY];
            while (limiteX >= limX && limiteY >= limY && casilla.estaVacia() && limiteX >= 0 && limiteY >= 0
                    && limiteX <= 9 && limiteY <= 9) {
                casilla = tablero[limiteX][limiteY];
                limiteX--;
                limiteY--;
                
            }
        }
        if (casilla != null && !casilla.estaVacia() && limiteX >= 0 && limiteY >= 0 && limiteX <= 9 && limiteY <= 9) {
            Casilla casillaVacia = tablero[casilla.getPosX() - 1][casilla.getPosY() - 1];
            if (casillaVacia != null && casillaVacia.estaVacia()) {
                Ficha fichaEnemigo = casilla.getFicha();
                if (fichaEnemigo.getColor().equals(enemigo)) {
                    if (casillaVacia.getPosX() == x && casillaVacia.getPosY() == y) {
                        casilla.setFichaComida(fichaEnemigo);
                        casilla.removeFicha();
                        tablero[fichaEnemigo.getPosX()][fichaEnemigo.getPosY()] = casilla;
                        bandera = true;
                    }
                }
            }
        }
        return bandera;
    }

    /*
     * Metodo que permite comer una ficha en la parte inferior izquierda.
     * @param escogidaX posicion X
     * @param escogidaY posicion Y
     * @param x posicion X donde se quiere comer
     * @param y posicion Y donde se quiere comer
     * @param tablero Casillas de todo el juego
     * @param color Color de la ficha
     * @return verifica si se puede comer
     */
    private boolean comerIzquierdaAbajo(int escogidaX, int escogidaY, int x, int y, Casilla[][] tablero, String color) {
        boolean bandera = false;
        int limiteX = escogidaX;
        int limiteY = escogidaY;
        String enemigo = "";
        Casilla casilla = null;
        int limX = 0;
        int limY = 0;
        if (color.equals("Black")) {
            enemigo = "White";
        } else {
            enemigo = "Black";
        }
        if (escogidaX + escogidaY <= 9) {
            limX = escogidaX + escogidaY;
            limY = 0;
        } else {
            limX = 9;
            limY = Math.abs((9 - escogidaY) - escogidaX);
        }
        if (escogidaX < 8 && escogidaY > 1) {
            limiteX++;
            limiteY--;
            casilla = tablero[limiteX][limiteY];
            while (limiteX <= limX && limiteY >= limY && casilla.estaVacia() && limiteX >= 0 && limiteY >= 0
                    && limiteX <= 9 && limiteY <= 9) {
                casilla = tablero[limiteX][limiteY];
                limiteX++;
                limiteY--;

            }
        }
        if (casilla != null && !casilla.estaVacia() && limiteX >= 0 && limiteY >= 0 && limiteX <= 9 && limiteY <= 9) {
            Casilla casillaVacia = tablero[casilla.getPosX() + 1][casilla.getPosY() - 1];
            if (casillaVacia != null && casillaVacia.estaVacia()) {
                Ficha fichaEnemigo = casilla.getFicha();
                if (fichaEnemigo.getColor().equals(enemigo)) {
                    if (casillaVacia.getPosX() == x && casillaVacia.getPosY() == y) {
                        casilla.setFichaComida(fichaEnemigo);
                        casilla.removeFicha();
                        tablero[fichaEnemigo.getPosX()][fichaEnemigo.getPosY()] = casilla;
                        bandera = true;
                    }
                }
            }
        }
        return bandera;
    }

    /*
     * Metodo que permite comer una ficha en la parte inferior derecha.
     * @param escogidaX posicion X
     * @param escogidaY posicion Y
     * @param x posicion X donde se quiere comer
     * @param y posicion Y donde se quiere comer
     * @param tablero Casillas de todo el juego
     * @param color Color de la ficha
     * @return verifica si se puede comer
     */
    private boolean comerDerechaAbajo(int escogidaX, int escogidaY, int x, int y, Casilla[][] tablero, String color) {
        boolean bandera = false;
        int limiteX = escogidaX;
        int limiteY = escogidaY;
        Casilla casilla = null;
        String enemigo = "";
        int limX = 9;
        int limY = 9;
        if (color.equals("Black")) {
            enemigo = "White";
        } else {
            enemigo = "Black";
        }
        if (escogidaY < 8 && escogidaX < 8) {
            limiteX++;
            limiteY++;
            casilla = tablero[limiteX][limiteY];
            while (limiteX <= limX && limiteY <= limY && casilla.estaVacia() && limiteX >= 0 && limiteY >= 0
                    && limiteX <= 9 && limiteY <= 9) {
                casilla = tablero[limiteX][limiteY];
                limiteX++;
                limiteY++;

            }
        }
        if (casilla != null && !casilla.estaVacia() && limiteX >= 0 && limiteY >= 0 && limiteX <= 9 && limiteY <= 9) {
            Casilla casillaVacia = tablero[casilla.getPosX() + 1][casilla.getPosY() + 1];
            if (casillaVacia != null && casillaVacia.estaVacia()) {
                Ficha fichaEnemigo = casilla.getFicha();
                if (fichaEnemigo.getColor().equals(enemigo)) {
                    if (casillaVacia.getPosX() == x && casillaVacia.getPosY() == y) {
                        casilla.setFichaComida(fichaEnemigo);
                        casilla.removeFicha();
                        tablero[fichaEnemigo.getPosX()][fichaEnemigo.getPosY()] = casilla;
                        bandera = true;
                    }
                }
            }
        }
        return bandera;
    }

    /*
     * Metodo que permite comer una ficha en la parte superior derecha.
     * @param escogidaX posicion X
     * @param escogidaY posicion Y
     * @param x posicion X donde se quiere comer
     * @param y posicion Y donde se quiere comer
     * @param tablero Casillas de todo el juego
     * @param color Color de la ficha
     * @return verifica si se puede comer
     */
    private boolean comerDerechaArriba(int escogidaX, int escogidaY, int x, int y, Casilla[][] tablero, String color) {
        boolean bandera = false;
        int limiteX = escogidaX;
        int limiteY = escogidaY;
        Casilla casillaEnemigo = null;
        Casilla casillaVacia = null;
        String enemigo = "";
        int limX = 0;
        int limY = 0;
        if (color.equals("Black")) {
            enemigo = "White";
        } else {
            enemigo = "Black";
        }
        if (escogidaX + escogidaY <= 9) {
            limY = escogidaX + escogidaY;
            limX = 0;
        } else {
            limY = 9;
            limX = Math.abs((9 - escogidaX) - escogidaY);
        }
        if (escogidaY < 8 && escogidaX > 1) {
            limiteX--;
            limiteY++;
            casillaEnemigo = tablero[limiteX][limiteY];
            while (limiteX >= limX && limiteY <= limY && casillaEnemigo.estaVacia() && limiteX >= 0 && limiteY >= 0
                    && limiteX <= 9 && limiteY <= 9) {
                casillaEnemigo = tablero[limiteX][limiteY];
                limiteX--;
                limiteY++;
            }
        }
        if (casillaEnemigo != null && casillaEnemigo.getPosX() > 0 && casillaEnemigo.getPosY() < 9) {
            casillaVacia = tablero[casillaEnemigo.getPosX() - 1][casillaEnemigo.getPosY() + 1];
            if (casillaVacia != null && casillaVacia.estaVacia()) {
                Ficha fichaEnemigo = casillaEnemigo.getFicha();
                if (fichaEnemigo.getColor().equals(enemigo)) {
                    if (casillaVacia.getPosX() == x && casillaVacia.getPosY() == y) {
                        casillaEnemigo.setFicha(fichaEnemigo);
                        casillaEnemigo.removeFicha();
                        tablero[casillaVacia.getPosX() + 1][casillaVacia.getPosY() - 1] = casillaEnemigo;
                        bandera = true;
                    }
                }
            }

        }
        return bandera;
    }
}
