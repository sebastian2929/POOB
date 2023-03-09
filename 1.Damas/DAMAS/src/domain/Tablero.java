package domain;

import java.util.ArrayList;
import java.util.List;

public class Tablero {

    private Casilla[][] tablero;
    private ArrayList<Jugador> jugadores;
    private ArrayList<Ficha> fichas = new ArrayList<Ficha>();

    public Tablero(ArrayList<Jugador> jugadores, List<String> comodines) {
        tablero = new Casilla[10][10];
        this.jugadores = jugadores;
        for (Jugador j : this.jugadores) {
            fichas.addAll(j.getFichas());
        }
        int cont = 0;
        while (cont < 9) {
            int[] numeros = numRamdom();
            int k = numeros[0];
            int l = numeros[1];
            Casilla comodin = new Casilla(k, l, "comodin");
            if (comodin.getComodin() == null) {
                comodin.setComodin(comodines);
                tablero[k][l] = comodin;
                cont++;
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((i + j) % 2 == 1 && tablero[i][j] == null) {
                    tablero[i][j] = new Casilla(i, j, "normal");
                }

            }
        }
        setFichasTablero();
    }

    public void delFicha(int posX, int posY) {
        Casilla casilla = tablero[posX][posY];
        casilla.removeFicha();
        tablero[posX][posY] = casilla;

    }

    public void setFicha(int posX, int posY, String color) {
        Casilla casilla = tablero[posX][posY];
        Ficha ficha = casilla.getFicha();
        if (ficha == null) {
            ficha = new Ficha(posX, posY, color);
            casilla.setFicha(ficha);
            tablero[posX][posY] = casilla;
        }
    }

    public Ficha getFicha(int posX, int posY) {
        Casilla casilla = tablero[posX][posY];
        Ficha ficha = null;
        if (casilla != null) {
            ficha = casilla.getFicha();
        }
        return ficha;
    }

    public Casilla[][] getTablero() {
        return this.tablero;
    }

    public boolean estaVacia(int posX, int posY) {
        boolean bandera = false;
        Casilla casilla = tablero[posX][posY];
        Ficha ficha = casilla.getFicha();
        if (ficha == null) {
            bandera = true;
        }
        return bandera;
    }

    public boolean esCorrecta(int posX, int posY) {
        if (posX < 0 || posY < 0 || posX > 9 || posY > 9)
            return false;
        if ((posX + posY) % 2 == 0)
            return false;
        return true;
    }

    public Casilla getCasilla(int posX, int posY) {
        return tablero[posX][posY];
    }

    public boolean puedeMover(int escogidaX, int escogidaY, int x, int y) {
        boolean bandera = false;
        Ficha ficha = getFicha(escogidaX, escogidaY);
        if (ficha != null) {
            String color = ficha.getColor();
            bandera = ficha.moverFicha(escogidaX, escogidaY, x, y, this.tablero, color);
        }
        return bandera;
    }

    public boolean puedeComer(int escogidaX, int escogidaY, int x, int y) {
        boolean bandera = false;
        Ficha ficha = getFicha(escogidaX, escogidaY);
        if (ficha != null) {
            String color = ficha.getColor();
            bandera = ficha.comerFicha(escogidaX, escogidaY, x, y, this.tablero, color);
        }
        return bandera;
    }

    public int getComidaPosX() {
        Ficha ficha = new Ficha(100, 100, "red");
        int posX = ficha.getComidaPosX();
        return posX;
    }

    public int getComidaPosY() {
        Ficha ficha = new Ficha(100, 100, "red");
        int posY = ficha.getComidaPosY();
        return posY;
    }

    public boolean volverAComer(int escogidaX, int escogidaY) {
        boolean bandera = false;
        Ficha ficha = getFicha(escogidaX, escogidaY);
        if (ficha != null) {
            String color = ficha.getColor();
            bandera = ficha.volverAComer(escogidaX, escogidaY, this.tablero, color);
        }
        return bandera;

    }

    public ArrayList<Ficha> getFichaTablero(){
        return fichas;
    }

    private int[] numRamdom() {
        boolean bandera = true;
        int[] numeros = new int[2];
        while (bandera) {
            int x = (int) (Math.random() * 8 + 1);
            int y = (int) (Math.random() * 8 + 1);
            if ((x + y) % 2 == 1) {
                numeros[0] = x;
                numeros[1] = y;
                bandera = false;
            }
        }
        return numeros;
    }

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
        // añadir fichas blanacas al tablero
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
