package test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.beans.Transient;

import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.*;

class TantFantTest {

    TantFant juego;

    @BeforeEach
    public void setUp() {
        juego = new TantFant();
    }

    @Test
    public void deberiaSerElJugadorEnTurno() {
        Jugador esperado = juego.getJugador(1);
        Jugador resultado = juego.getJugadorEnTurno();
        assertEquals(esperado, resultado);
    }

    private void assertEquals(Jugador esperado, Jugador resultado) {
    }
}