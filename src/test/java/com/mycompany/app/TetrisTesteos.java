package com.mycompany.app;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TetrisTesteos {

    @Test
    public void testTableroVacio() {
        Tablero tablero = new Tablero();
        assertTrue(tablero.tableroVacio());
    }


    @Test
    public void testTableroLleno(){
        Tablero tablero = new Tablero();
        assertFalse(!tablero.tableroVacio());
    }


    @Test
    public void testMoverPiezaAbajo(){
        PiezasAbsAll pieza = new PiezaT(); // Creo una pieza T

        assertEquals(0, pieza.getX()); // Pregunto si esta en posicion inicial

        pieza.moverAbajo(); // llamo a su metodo para mover abajo

        assertEquals(1, pieza.getX()); // Pregunto si se movio verificando que se aumento su fila (x)
    }
    

    @Test
    public void testMoverPiezaDerecha(){
        PiezasAbsAll pieza = new PiezaJ(); // Creo una pieza J

        assertEquals(0, pieza.getY()); // Pregunto si esta en posicion inicial

        pieza.moverDerecha(); // llamo a su metodo para mover derecha

        assertEquals(1, pieza.getY()); // Pregunto si se movio verificando que se aumento su columna (y)
    }


    @Test
    public void testMoverPiezaIzquierda(){
        PiezasAbsAll pieza = new PiezaT(); // Creo una pieza T

        assertEquals(0, pieza.getY()); // Pregunto si esta en posicion inicial

        pieza.moverIzquierda(); // llamo a su metodo para mover izquierda

        //esto no es logico, debemos definir los limites del tablero y sus colisones o choques cuando una pieza ya se encuentra en la posicion
        assertEquals(-1, pieza.getY()); // Pregunto si se movio verificando que se disminuyo su columna (y)
    }
}
