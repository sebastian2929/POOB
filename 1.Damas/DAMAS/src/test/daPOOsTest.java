package test;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.*;

public class daPOOsTest {

	private daPOOs Juego;
	private List<String> jugadores = new ArrayList<>();
	private List<String> colores = new ArrayList<>();
	private List<String> comodines = new ArrayList<>();

	@Before
	public void setUp() {
		jugadores.add("Andres");
		jugadores.add("Sebastian");
		colores.add("Black");
		colores.add("White");
		Juego = new daPOOs(jugadores, colores, comodines);
	}

	@Test
	public void deberiaMoverFichaNegra() {
		Jugador enTurno = Juego.getJugadorTurno();
		String nombre = enTurno.getName();
		Juego.CambiarTurno();
		enTurno = Juego.getJugadorTurno();
		nombre = enTurno.getName();
		Juego.juego(9, 0);
		Juego.juego(5, 2);
		Juego.juego(6, 1);
		Juego.juego(5, 2);
		enTurno = Juego.getJugadorTurno();
		nombre = enTurno.getName();
		Tablero tablero = Juego.getTableroJuego();
		String color = tablero.getFicha(5, 2).getColor();
		assertTrue(nombre.equals("Andres"));
		assertTrue(color.equals("White"));
	}

	@Test
	public void MovimientoInvalido() {
		Juego.juego(3, 0);
		Juego.juego(5, 2);
		Tablero tablero = Juego.getTableroJuego();
		boolean bandera = tablero.estaVacia(3, 0);
		assertTrue(bandera == false);
		bandera = tablero.estaVacia(5, 2);
		assertTrue(bandera == true);
	}

	@Test
	public void deberiaComerFichaSencilla() {

		Juego.juego(3, 2);
		Juego.juego(4, 3);

		Juego.juego(6, 3);
		Juego.juego(5, 4);

		Juego.juego(3, 8);
		Juego.juego(4, 9);

		Juego.juego(5, 4);
		Juego.juego(3, 2);

		Tablero tablero = Juego.getTableroJuego();
		boolean bandera = tablero.estaVacia(4, 3);
		assertTrue(bandera == true);
		String color = tablero.getFicha(3, 2).getColor();
		assertTrue(color.equals("White"));

	}

	@Test
	public void deberiaComerVarias() {
		Juego.juego(3, 4);
		Juego.juego(4, 5);

		Juego.juego(6, 5);
		Juego.juego(5, 6);

		Juego.juego(3, 2);
		Juego.juego(4, 1);

		Juego.juego(6, 9);
		Juego.juego(5, 8);

		Juego.juego(2, 1);
		Juego.juego(3, 2);

		Juego.juego(6, 1);
		Juego.juego(5, 0);

		Juego.juego(1, 2);
		Juego.juego(2, 1);

		Juego.juego(5, 6);
		Juego.juego(3, 4);

		Juego.juego(3, 4);
		Juego.juego(1, 2);



		Tablero tablero = Juego.getTableroJuego();
		boolean bandera = tablero.estaVacia(1, 2);
		assertTrue(bandera == false);

	}

	@Test
	public void comerInvalido(){
		Juego.juego(3,4);
		Juego.juego(4,3);

		Juego.juego(6,5);
		Juego.juego(5,4);

		Juego.juego(3,8);
		Juego.juego(4,9);

		Juego.juego(5,4);
		Juego.juego(3,2);

		//debe seguir existiendo la ficha negra de la posicion 4,3
		Tablero tablero = Juego.getTableroJuego();
		boolean bandera = tablero.estaVacia(4, 3);
		assertTrue(bandera == false);
		String color = tablero.getFicha(4, 3).getColor();
		assertTrue(color.equals("Black"));
	}
}
