package test;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.*;

/**
 * Clase de las pruebas de unidad de la aplicacion 
 * 
 * @version 0.5
 */
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
		comodines.add("Gun");
		Juego = new daPOOs(jugadores, colores, comodines, 0);
	}

	@Test
	public void deberiaHabercomodines() {
		int comodines = Juego.getComodines();
		assertTrue(comodines == 0);
	}

	@Test
	public void deberianTenerVeinteFichas() {
		Jugador enTurno = Juego.getJugadorTurno();
		int fichas = enTurno.getFichas().size();
		assertTrue(fichas == 20);
		Jugador enemigo = Juego.getEnemigo();
		int fichasEnemigo = enemigo.getFichas().size();
		assertTrue(fichasEnemigo == 20);
	}

	@Test
	public void deberiaMoverFichaNegra() {
		Jugador enTurno = Juego.getJugadorTurno();
		String nombre = enTurno.getName();
		assertTrue(nombre.equals("Andres"));

		Juego.juego(3, 8);
		Juego.juego(4, 9);

		Tablero tablero = Juego.getTableroJuego();
		Ficha ficha = tablero.getFicha(4, 9);
		String color = ficha.getColor();
		assertTrue(color.equals("Black"));
		int fichas = Juego.getJugadorTurno().getFichas().size();
		int fichasEnemigo = Juego.getEnemigo().getFichas().size();
		Juego.juego(3, 0);
		Juego.juego(4, 1);
		assertTrue(fichas == 20);
		assertTrue(fichasEnemigo == 20);

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
		Tablero tablero = Juego.getTableroJuego();
		// juega el jugador de ficas negras
		Juego.juego(3, 8);
		String color = tablero.getFicha(3, 8).getColor();
		assertTrue(color.equals("Black"));
		Juego.juego(4, 9);
		boolean bandera = tablero.estaVacia(4, 9);
		assertTrue(bandera == false);
		// juega jugador fichas blancas
		Juego.juego(6, 9);
		Juego.juego(5, 8);
		bandera = tablero.estaVacia(6, 9);
		color = tablero.getFicha(5, 8).getColor();
		assertTrue(color.equals("White"));
		// Juega jugador fichas negras
		Juego.juego(3, 6);
		Juego.juego(4, 7);
		bandera = tablero.estaVacia(3, 6);
		assertTrue(bandera = true);
		// Juega jugador fichas Blancas
		Juego.juego(5, 8);
		bandera = tablero.estaVacia(5, 8);
		assertTrue(bandera == false);
		Juego.juego(3, 6);
		color = tablero.getFicha(3, 6).getColor();
		assertTrue(color.equals("White"));
		int fichas = Juego.getJugadorTurno().getFichas().size();
		int fichasEnemigo = Juego.getEnemigo().getFichas().size();
		Juego.juego(3, 0);
		Juego.juego(4, 1);
		assertTrue(fichas == 19);
		assertTrue(fichasEnemigo == 20);

	}

	@Test
	public void deberiaComerVarias() {
		Juego.juego(3, 4);
		Juego.juego(4, 5);
		Juego.juego(6, 5);
		Juego.juego(5, 6);
		Juego.juego(2, 3);
		Juego.juego(3, 4);
		Juego.juego(6, 1);
		Juego.juego(5, 0);
		Juego.juego(1, 2);
		Juego.juego(2, 3);
		Juego.juego(7, 2);
		Juego.juego(6, 1);
		Juego.juego(3, 4);
		Juego.juego(4, 3);
		Juego.juego(5, 6);
		Juego.juego(3, 4);
		Juego.juego(3, 4);
		Juego.juego(1, 2);
		Tablero tablero = Juego.getTableroJuego();
		boolean bandera = tablero.estaVacia(1, 2);
		assertTrue(bandera == false);
	}

	@Test 
	public void noComioDesaparece(){
		Juego.juego(3, 4);
		Juego.juego(4, 5);
		Juego.juego(6, 5);
		Juego.juego(5, 6);
		Juego.juego(3, 8);
		Juego.juego(4, 9);
		Juego.juego(6, 1);
		Juego.juego(5, 0);

		Tablero tablero = Juego.getTableroJuego();
		boolean bandera = tablero.estaVacia(5, 6);
		assertTrue(bandera == true);
	}

	@Test
	public void moverReina(){
		Juego.juego(3, 6);
		Juego.juego(4, 5);

		Juego.juego(6, 5);
		Juego.juego(5, 6);

		Juego.juego(4, 5);
		Juego.juego(5, 4);

		Juego.juego(5, 6);
		Juego.juego(4, 5);

		Juego.juego(5, 4);
		Juego.juego(6, 5);

		Juego.juego(4, 5);
		Juego.juego(3, 6);

		Juego.juego(6, 5);
		Juego.juego(7, 6);

		Juego.juego(3, 6);
		Juego.juego(2, 7);

		Juego.juego(7, 6);
		Juego.juego(8, 7);

		Juego.juego(2, 7);
		Juego.juego(1, 8);

		Juego.juego(8, 7);
		Juego.juego(9, 8);

		Juego.juego(6, 1);
		Juego.juego(5, 0);

		Juego.juego(9, 8);
		Juego.juego(5, 4);

		Tablero tablero = Juego.getTableroJuego();
		boolean bandera = tablero.estaVacia(5, 4);
		assertTrue(bandera == false);
	}

	@Test
	public void comerReinaSencillo(){
		Juego.juego(3, 6);
		Juego.juego(4, 5);

		Juego.juego(6, 5);
		Juego.juego(5, 6);

		Juego.juego(4, 5);
		Juego.juego(5, 4);

		Juego.juego(5, 6);
		Juego.juego(4, 5);

		Juego.juego(5, 4);
		Juego.juego(6, 5);

		Juego.juego(4, 5);
		Juego.juego(3, 6);

		Juego.juego(6, 5);
		Juego.juego(7, 6);

		Juego.juego(3, 6);
		Juego.juego(2, 7);

		Juego.juego(7, 6);
		Juego.juego(8, 7);

		Juego.juego(2, 7);
		Juego.juego(1, 8);

		Juego.juego(8, 7);
		Juego.juego(9, 8);

		Juego.juego(7, 4);
		Juego.juego(6, 5);

		Juego.juego(9, 8);
		Juego.juego(5, 4);

		Tablero tablero = Juego.getTableroJuego();
		boolean bandera = tablero.estaVacia(6, 5);
		assertTrue(bandera == true);
	}

	@Test
	public void comerVariasReina(){
		Juego.juego(3, 6);
		Juego.juego(4, 5);

		Juego.juego(6, 5);
		Juego.juego(5, 6);

		Juego.juego(4, 5);
		Juego.juego(5, 4);

		Juego.juego(5, 6);
		Juego.juego(4, 5);

		Juego.juego(5, 4);
		Juego.juego(6, 5);

		Juego.juego(4, 5);
		Juego.juego(3, 6);

		Juego.juego(6, 5);
		Juego.juego(7, 6);

		Juego.juego(3, 6);
		Juego.juego(2, 7);

		Juego.juego(7, 6);
		Juego.juego(8, 7);

		Juego.juego(2, 7);
		Juego.juego(1, 8);

		Juego.juego(8, 7);
		Juego.juego(9, 8);

		Juego.juego(1, 8);
		Juego.juego(0, 9);

		Juego.juego(3, 0);
		Juego.juego(4, 1);

		Juego.juego(7, 8);
		Juego.juego(6, 7);

		Juego.juego(4, 1);
		Juego.juego(5, 0);

		Juego.juego(9, 6);
		Juego.juego(8, 7);

		Juego.juego(9, 8);
		Juego.juego(7, 6);

		Juego.juego(7, 6);
		Juego.juego(5, 8);

		Tablero tablero = Juego.getTableroJuego();
		boolean bandera = tablero.estaVacia(6, 7);
		boolean bandera2 = tablero.estaVacia(8, 7);
		assertTrue(bandera == true && bandera2 == true);
	}

	@Test 
	public void noPuedeMoverFicha(){
		Tablero tablero = Juego.getTableroJuego();
		boolean bandera = tablero.puedeMover(2, 1);
		assertTrue(bandera == false);
	}

	@Test
	public void noPuedeComer(){
		Tablero tablero = Juego.getTableroJuego();
		boolean bandera = tablero.puedeComer(2, 1);
		assertTrue(bandera == false);
	}

	@Test
	public void coronaFichaNegra(){
		Juego.juego(3, 6);
		Juego.juego(4, 5);

		Juego.juego(6, 5);
		Juego.juego(5, 6);

		Juego.juego(4, 5);
		Juego.juego(5, 4);

		Juego.juego(5, 6);
		Juego.juego(4, 5);

		Juego.juego(5, 4);
		Juego.juego(6, 5);

		Juego.juego(4, 5);
		Juego.juego(3, 6);

		Juego.juego(6, 5);
		Juego.juego(7, 6);

		Juego.juego(3, 6);
		Juego.juego(2, 7);

		Juego.juego(7, 6);
		Juego.juego(8, 7);

		Juego.juego(2, 7);
		Juego.juego(1, 8);

		Juego.juego(8, 7);
		Juego.juego(9, 8);

		Tablero tablero = Juego.getTableroJuego();
		Ficha ficha = tablero.getFicha(9, 8);
		String tipo = ficha.getTipo();
		assertTrue(tipo.equals("Reina"));
	}

	@Test
	public void coronaFichaBlanca(){
		Juego.juego(3, 6);
		Juego.juego(4, 5);

		Juego.juego(6, 5);
		Juego.juego(5, 6);

		Juego.juego(4, 5);
		Juego.juego(5, 4);

		Juego.juego(5, 6);
		Juego.juego(4, 5);

		Juego.juego(5, 4);
		Juego.juego(6, 5);

		Juego.juego(4, 5);
		Juego.juego(3, 6);

		Juego.juego(6, 5);
		Juego.juego(7, 6);

		Juego.juego(3, 6);
		Juego.juego(2, 7);

		Juego.juego(7, 6);
		Juego.juego(8, 7);

		Juego.juego(2, 7);
		Juego.juego(1, 8);

		Juego.juego(8, 7);
		Juego.juego(9, 8);

		Juego.juego(1, 8);
		Juego.juego(0, 9);
		Tablero tablero = Juego.getTableroJuego();
		Ficha ficha = tablero.getFicha(0, 9);
		String tipo = ficha.getTipo();
		assertTrue(tipo.equals("Reina"));
	}

	
}
