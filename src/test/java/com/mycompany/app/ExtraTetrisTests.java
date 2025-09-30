package com.mycompany.app;

import org.junit.Test;
import static org.junit.Assert.*;

public class ExtraTetrisTests {

    @Test
    public void testColocarSiValida_NoMutaSiInvalida() {
        Board tablero = new Board();
        PieceBase square = new PieceSquare();
        // Colocar un bloque que ocupe (0,0) manualmente
        tablero.setBoard(0, 0, 9);
        boolean pudo = tablero.colocarSiValida(square, 0, 0); // intentamos colocar encima
        assertFalse("No debería colocar si hay colisión", pudo);
        // Verificar que la celda original sigue sin cambiar (valor 9)
        assertEquals(9, tablero.getBoard()[0][0]);
    }

    @Test
    public void testVerificarYEliminarLineas_TableroCompletamenteLleno() {
        Board tablero = new Board();
        // Llenar todo el tablero
        for (int fila = 0; fila < tablero.getFilas(); fila++) {
            for (int columna = 0; columna < tablero.getColumnas(); columna++) {
                tablero.setBoard(fila, columna, 1);
            }
        }
        tablero.verificarYEliminarLineas();
        // Debería haberse incrementado en filas totales
        assertEquals("Se deben eliminar todas las filas llenas", tablero.getFilas(), tablero.getLineasEliminadas());
        // Y el tablero debería quedar vacío
        for (int fila = 0; fila < tablero.getFilas(); fila++) {
            for (int columna = 0; columna < tablero.getColumnas(); columna++) {
                assertEquals("Tablero debe quedar limpio tras eliminar todas las filas", 0, tablero.getBoard()[fila][columna]);
            }
        }
    }

    @Test
    public void testLimpiarTablero_NoReseteaLineasEliminadas() {
        Board tablero = new Board();
        // llenamos y eliminamos una fila para incrementar contador
        for (int columna = 0; columna < tablero.getColumnas(); columna++) tablero.setBoard(tablero.getFilas() - 1, columna, 1);
        tablero.verificarYEliminarLineas();
        int antes = tablero.getLineasEliminadas();
        tablero.limpiarTablero();
        assertEquals("limpiarTablero no debe modificar lineasEliminadas", antes, tablero.getLineasEliminadas());
    }

    @Test
    public void testEsFinDelJuego_PiezaActualNull() {
        Board tablero = new Board();
        // llenar todo
        for (int r = 0; r < tablero.getFilas(); r++) for (int c = 0; c < tablero.getColumnas(); c++) tablero.setBoard(r, c, 1);
        tablero.setPiezaActual(null);
        // Segun la implementación actual, si piezaActual==null devuelve false
        assertFalse("Con piezaActual null, esFinDelJuego debe devolver false según diseño actual", tablero.esFinDelJuego(tablero));
    }

    @Test
    public void testNuevaPiezaAleatoria_NoPuedeEntrar_PrimeraFilaLlena() {
        Tetris tetris = new Tetris();
        tetris.start();
        Board tablero = tetris.getBoard();

        for (int columna = 0; columna < tablero.getColumnas(); columna++) // llenar la primera fila (fila 0) con 1s
            tablero.setBoard(0, columna, 1);

        boolean pudo = tetris.nuevaPiezaAleatoria(new PieceSquare()); // intenta colocar una pieza
        // 
        assertFalse("Si primera fila está llena, nuevaPiezaAleatoria debe devolver false", pudo); // false porque no pudo colocar
        // Y Tetris debe haber terminado (nuevaPiezaAleatoria llama end() si no puede colocar)
        assertEquals("Tras fallo de spawn, el estado debe ser game over (2)", 2, tetris.getState());
    }

    @Test
    public void testGameWin_TrasEliminarLineas() {
        Tetris tetris = new Tetris();
        Board tablero = tetris.getBoard();

        for (int fila = 0; fila < tablero.getFilas(); fila++) { // llenar todo el tablero con 1s
            for (int columna = 0; columna < tablero.getColumnas(); columna++) {
                tablero.setBoard(fila, columna, 1);
            }
        }

        tablero.setPiezaActual(new PieceSquare()); // Asignar una pieza actual para evitar null
        tablero.verificarYEliminarLineas(); // Esto debería eliminar todas las líneas
        assertTrue("Se debieron eliminar varias líneas", tablero.getLineasEliminadas() >= tablero.getLineasParaGanar());// Asegurarse de que se alcanzó la meta
        tetris.actualizarEstadoJuego(); // Actualizar el estado del juego en Tetris
        assertEquals("Tetris debe reportar estado game win (3)", 3, tetris.getState()); // Verificar estado de "win"
    }

    @Test
    public void testMoverPieza_FueraLimites() {
        Board tablero = new Board();
        PieceBase p = new PieceSquare();
        tablero.setPiezaActual(p);
        tablero.colocarPiezaEnTableroVerificada(p, 0, 0);
        // intentar mover hacia la izquierda (fuera)
        assertFalse("Mover fuera de bordes debe devolver false (validator)", tablero.moverPieza(0, -1));
        // intentar mover hacia arriba (fuera)
        assertFalse("Mover arriba fuera del tablero debe devolver false", tablero.moverPieza(-1, 0));
    }

}
