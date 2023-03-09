package domain;

import java.util.ArrayList;

/**
 * Jugador para el juego Tant Fant.
 * 
 * @Autor Andres Arias - Sebastian Blanco
 * @Version 13-11-22
 */
public class Jugador {

	private int idJugador;
	private boolean enTurno;
	private ArrayList<String> misFichas = new ArrayList<String>();
	private String color = null;

	/**
	 * Constructor de un jugador.
	 * 
	 * @param numeroJugador Numero que se le asigna a un jugador para identificarlo.
	 * @param turno         Indica True si el jugador esta en turno, de lo contrario
	 *                      False.
	 */
	public Jugador(int numeroJugador, boolean turno) {
		this.idJugador = numeroJugador;
		this.enTurno = turno;
	}

	/**
	 * Obtiene el id del jugador.
	 * 
	 * @return Entero con el numero del jugador.
	 */
	public int getIdJugador() {
		return this.idJugador;
	}

	/**
	 * Obtiene si el jugador actualmente esta en su turno para jugar.
	 * 
	 * @return True si el jugador esta en turno, de lo contrario False.
	 */
	public boolean getTurno() {
		return this.enTurno;
	}

	/**
	 * Hace que el jugar este en turno o por el contrario hace que ya no lo este.
	 * 
	 * @param turno Indica True si el jugador esta en turno, de lo contrario False.
	 */
	public void setTurno(boolean turno) {
		this.enTurno = turno;
	}

	/**
	 * Metodo que verifica si la ficha en la posicion x y en la posicion y petenece.
	 * al Jugador.
	 * 
	 * @param posX  , posiciion x de la ficha.
	 * @param posY, posiciion y de la ficha.
	 */
	public boolean setFichas(int posX, int posY) {
		String posicionX = String.valueOf(posX);
		String posicionY = String.valueOf(posY);
		String ficha = posicionX + "," + posicionY;
		boolean bandera = false;
		for (int i = 0; i < misFichas.size(); i++) {
			if (ficha.equals(misFichas.get(i))) {
				bandera = true;
			}
		}
		return bandera;
	}

	/**
	 * Metodo que obtiene las fichas del jugador.
	 */

	public ArrayList<String> getFichas() {
		return misFichas;
	}

	/**
	 * Metodo que determina las ficas del jugador.
	 * 
	 * @param ArrayList<String> , fichas del jugador.
	 */

	public void setFichas(ArrayList<String> fichas) {
		misFichas = fichas;
	}

	/**
	 * Metodo que annade una ficha al jugador.
	 * 
	 * @param posX  , posiciion x de la ficha.
	 * @param posY, posiciion y de la ficha.
	 */

	public void addFicha(int posX, int posY) {
		String posicionX = String.valueOf(posX);
		String posicionY = String.valueOf(posY);
		String ficha = posicionX + "," + posicionY;

		if (!misFichas.contains(ficha)) {
			misFichas.add(ficha);
		}

	}

	/**
	 * Metodo que elimina una ficha al jugador.
	 * 
	 * @param posX  , posiciion x de la ficha.
	 * @param posY, posiciion y de la ficha.
	 */

	public void removeFicha(int posX, int posY) {
		String posicionX = String.valueOf(posX);
		String posicionY = String.valueOf(posY);
		String ficha = posicionX + "," + posicionY;

		if (misFichas.contains(ficha)) {
			misFichas.remove(ficha);
		}

	}

	/**
	 * Metodo que verifica si es una ficha del jugador.
	 * 
	 * @param int posX, posicion x de la fiha.
	 * @param int posY, posicion Y de la fiha.
	 * @return boolean bandera, confirma si pertenece al jugador.
	 */
	public boolean miFicha(int posX, int posY) {
		boolean bandera = false;
		String posicionX = String.valueOf(posX);
		String posicionY = String.valueOf(posY);
		String ficha = posicionX + "," + posicionY;

		if (misFichas.contains(ficha)) {
			bandera = true;
		}

		return bandera;

	}

	public ArrayList<Integer> pasarEnteros() {
		ArrayList<Integer> enteros = new ArrayList<Integer>();
		for (int i = 0; i < misFichas.size(); i++) {
			String[] a = misFichas.get(i).split(",");
			for (int j = 0; j < a.length;) {
				int num = Integer.parseInt(a[j]);
				enteros.add(num);
			}
		}
		return enteros;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getColor() {
		return color;
	}
}
