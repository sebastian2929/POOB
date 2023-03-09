package domain;

public class Ficha {
    protected int posX;
    protected int posY;
    protected String color;
    protected String tipo;
    protected Ficha fichaComida;
    protected int comidaX;
    protected int comidaY;

    public Ficha(int posX, int posY, String color) {
        this.posX = posX;
        this.posY = posY;
        this.color = color;
        this.tipo = "normal";
        this.fichaComida = null;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getColor() {
        return this.color;
    }

    public String getTipo() {
        return this.tipo;
    }

    public boolean moverFicha(int escogidaX, int escogidaY, int x, int y, Casilla[][] tablero, String color) {
        boolean bandera = false;
        if (moverIzquierda(escogidaX, escogidaY, x, y, color) || moverDerecha(escogidaX, escogidaY, x, y, color)) {
            bandera = true;
        }
        return bandera;
    }

    public boolean comerFicha(int escogidaX, int escogidaY, int x, int y, Casilla[][] tablero, String color) {
        boolean bandera = false;
        if (comerIzquierda(escogidaX, escogidaY, x, y, tablero, color)
                || comerDerecha(escogidaX, escogidaY, x, y, tablero, color)) {
            bandera = true;
        }
        return bandera;
    }

    public boolean volverAComer(int escogidaX, int escogidaY, Casilla[][] tablero, String color) {
        boolean bandera = false;
        if (comerIzquierda(escogidaX, escogidaY, tablero, color)
                || comerDerecha(escogidaX, escogidaY, tablero, color)) {
            bandera = true;
        }
        return bandera;
    }

    public int getComidaPosX() {
        return comidaX;
    }

    public int getComidaPosY() {
        return comidaY;
    }

    private boolean moverIzquierda(int escogidaX, int escogidaY, int x, int y, String color) {
        boolean bandera = false;
        if (color.equals("Black")) {
            if (escogidaX + 1 == x && escogidaY - 1 == y) {
                bandera = true;
            }
        }
        if (color.equals("White")) {
            if (escogidaX - 1 == x && escogidaY - 1 == y) {
                bandera = true;
            }
        }
        return bandera;
    }

    private boolean moverDerecha(int escogidaX, int escogidaY, int x, int y, String color) {
        boolean bandera = false;
        if (color.equals("Black")) {
            if (escogidaX + 1 == x && escogidaY + 1 == y) {
                bandera = true;
            }
        }
        if (color.equals("White")) {
            if (escogidaX - 1 == x && escogidaY + 1 == y) {
                bandera = true;
            }
        }
        return bandera;
    }

    private boolean comerIzquierda(int escogidaX, int escogidaY, int x, int y, Casilla[][] tablero, String color) {
        boolean bandera = false;
        Ficha ficha;
        Casilla casilla;
        Casilla casilla2;
        String enemigo;
        
        if (escogidaY - 2 >= 0 && escogidaX - 2 >= 0 && escogidaX + 2 < 10 && escogidaY + 2 < 10) {
            if (color.equals("Black")) {
                enemigo = "White";
                casilla = tablero[escogidaX + 1][escogidaY - 1];
                ficha = casilla.getFicha();
                // posible error de ficha comparada dos veces
                if (ficha != null && ficha.getColor().equals(enemigo)) {
                    casilla2 = tablero[escogidaX + 2][escogidaY - 2];
                    comidaX = escogidaX + 1;
                    comidaY = escogidaY - 1;
                    Ficha ficha2 = casilla2.getFicha();
                    if (ficha2 == null && casilla2.getPosX() == x && casilla2.getPosY() == y) {
                        bandera = true;
                        ficha = null;
                        casilla.setFicha(ficha);
                        tablero[comidaX][comidaY] = casilla;
                    }
                }
            }
            if (color.equals("White")) {
                enemigo = "Black";
                casilla = tablero[escogidaX - 1][escogidaY - 1];
                ficha = casilla.getFicha();
                // posible error de ficha comparada dos veces
                if (ficha != null && ficha.getColor().equals(enemigo)) {
                    casilla2 = tablero[escogidaX - 2][escogidaY - 2];
                    Ficha ficha2 = casilla2.getFicha();
                    comidaX = escogidaX - 1;
                    comidaY = escogidaY - 1;
                    if (ficha2 == null && casilla2.getPosX() == x && casilla2.getPosY() == y) {
                        bandera = true;
                        ficha = null;
                        casilla.setFicha(ficha);
                        tablero[comidaX][comidaY] = casilla;
                    }
                }
            }
        }
        return bandera;
    }

    private boolean comerDerecha(int escogidaX, int escogidaY, int x, int y, Casilla[][] tablero, String color) {
        boolean bandera = false;
        Ficha ficha;
        Casilla casilla;
        Casilla casilla2;
        String enemigo;
        if (escogidaY - 2 >= 0 && escogidaX - 2 >= 0 && escogidaX + 2 < 10 && escogidaY + 2 < 10) {
            if (color.equals("Black")) {
                enemigo = "White";
                casilla = tablero[escogidaX + 1][escogidaY + 1];
                ficha = casilla.getFicha();
                // posible error de ficha comparada dos veces
                if (ficha != null && ficha.getColor().equals(enemigo)) {
                    casilla2 = tablero[escogidaX + 2][escogidaY + 2];
                    Ficha ficha2 = casilla2.getFicha();
                    comidaX = escogidaX + 1;
                    comidaY = escogidaY + 1;
                    if (ficha2 == null && casilla2.getPosX() == x && casilla2.getPosY() == y) {
                        bandera = true;
                        ficha = null;
                        casilla.setFicha(ficha);
                        tablero[comidaX][comidaY] = casilla;
                    }
                }
            }
            if (color.equals("White")) {
                enemigo = "Black";
                casilla = tablero[escogidaX - 1][escogidaY + 1];
                ficha = casilla.getFicha();
                // posible error de ficha comparada dos veces
                if (ficha != null && ficha.getColor().equals(enemigo)) {
                    casilla2 = tablero[escogidaX - 2][escogidaY + 2];
                    Ficha ficha2 = casilla2.getFicha();
                    comidaX = escogidaX - 1;
                    comidaY = escogidaY + 1;
                    if (ficha2 == null && casilla2.getPosX() == x && casilla2.getPosY() == y) {
                        bandera = true;
                        ficha = null;
                        casilla.setFicha(ficha);
                        tablero[comidaX][comidaY] = casilla;
                    }
                }
            }
        }
        return bandera;
    }

    private boolean comerIzquierda(int escogidaX, int escogidaY, Casilla[][] tablero, String color) {
        boolean bandera = false;
        Casilla casilla;
        Casilla casilla2;
        String enemigo;
        if (escogidaY - 2 >= 0 && escogidaX - 2 >= 0 && escogidaX + 2 < 10 && escogidaY + 2 < 10) {
            if (color.equals("Black")) {
                enemigo = "White";
                casilla = tablero[escogidaX + 1][escogidaY - 1];
                Ficha ficha = casilla.getFicha();
                // posible error de ficha comparada dos veces
                if (ficha != null && ficha.getColor().equals(enemigo)) {
                    casilla2 = tablero[escogidaX + 2][escogidaY - 2];
                    Ficha ficha2 = casilla2.getFicha();
                    if (ficha2 == null) {
                        bandera = true;
                    }
                }
            }
            if (color.equals("White")) {
                enemigo = "Black";
                casilla = tablero[escogidaX - 1][escogidaY - 1];
                Ficha ficha = casilla.getFicha();
                // posible error de ficha comparada dos veces
                if (ficha != null && ficha.getColor().equals(enemigo)) {
                    casilla2 = tablero[escogidaX - 2][escogidaY - 2];
                    Ficha ficha2 = casilla2.getFicha();
                    if (ficha2 == null) {
                        bandera = true;
                    }
                }
            }
        }
        return bandera;
    }

    private boolean comerDerecha(int escogidaX, int escogidaY, Casilla[][] tablero, String color) {
        boolean bandera = false;
        Casilla casilla;
        Casilla casilla2;
        String enemigo;
        if (escogidaY - 2 >= 0 && escogidaX - 2 >= 0 && escogidaX + 2 < 10 && escogidaY + 2 < 10) {
            if (color.equals("Black")) {
                enemigo = "White";
                casilla = tablero[escogidaX + 1][escogidaY + 1];
                Ficha ficha = casilla.getFicha();
                // posible error de ficha comparada dos veces
                if (ficha != null && ficha.getColor().equals(enemigo)) {
                    casilla2 = tablero[escogidaX + 2][escogidaY + 2];
                    Ficha ficha2 = casilla2.getFicha();
                    if (ficha2 == null) {
                        bandera = true;
                    }
                }
            }
            if (color.equals("White")) {
                enemigo = "Black";
                casilla = tablero[escogidaX - 1][escogidaY + 1];
                Ficha ficha = casilla.getFicha();
                // posible error de ficha comparada dos veces
                if (ficha != null && ficha.getColor().equals(enemigo)) {
                    casilla2 = tablero[escogidaX - 2][escogidaY + 2];
                    Ficha ficha2 = casilla2.getFicha();
                    if (ficha2 == null) {
                        bandera = true;
                    }
                }
            }
        }
        return bandera;
    }
}

//aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa edicion desde aqui aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
//aaaaaaaaaaaaaaaaaaaaaaaaaaaaa
//aaaaaaaaaaaaaaaaaaaaaaaa