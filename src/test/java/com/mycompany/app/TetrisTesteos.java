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

    @Test
    public void testPiezaTRotarDerecha(){
        PiezasAbsAll pieza = new PiezaT(); // Creo una pieza T
        pieza.getForma(); // Obtengo su forma inicial
        pieza.rotarDerecha(); // Llamo a su metodo para rotar a la derecha
        int[][] formaEsperada = { // Forma esperada despues de rotar a la derecha
            {0, 1, 0},
            {0, 1, 1},
            {0, 1, 0}
        };

        pieza.getForma(); // Obtengo su nueva forma despues de rotar

        assertEquals(formaEsperada.length, pieza.getForma().length); // Verifico que las filas sean iguales
        assertEquals(formaEsperada[0].length, pieza.getForma()[0].length); // Verifico que las columnas sean iguales
        for (int i = 0; i < formaEsperada.length; i++) { // Recorro filas
            for (int j = 0; j < formaEsperada[0].length; j++) { // Recorro columnas
                assertEquals(formaEsperada[i][j], pieza.getForma()[i][j]); // Verifico que cada elemento sea igual
            }
        }
    }

    /*
    @Test
    public void testPiezaJRotarDerecha(){
        PiezasAbsAll pieza = new PiezaJ(); // Creo una pieza J
        pieza.getForma(); // Obtengo su forma inicial
        pieza.rotarDerecha(); // Llamo a su metodo para rotar a la derecha
        int[][] formaEsperada = { // Forma esperada despues de rotar a la derecha
            {0, 0, 1},
            {0, 0, 1},
            {0, 1, 1}
        };
        pieza.getForma(); // Obtengo su nueva forma despues de rotar
        assertEquals(formaEsperada.length, pieza.getForma().length); // Verifico que las filas sean iguales
        assertEquals(formaEsperada[0].length, pieza.getForma()[0].length); // Verifico que las columnas sean iguales
        for (int i = 0; i < formaEsperada.length; i++) { // Recorro filas
            for (int j = 0; j < formaEsperada[0].length; j++) { // Recorro columnas
                assertEquals(formaEsperada[i][j], pieza.getForma()[i][j]); // Verifico que cada elemento sea igual
            }
        }
    }
    */
    //debo definir limites del tablero y sus colisiones o choques
}
