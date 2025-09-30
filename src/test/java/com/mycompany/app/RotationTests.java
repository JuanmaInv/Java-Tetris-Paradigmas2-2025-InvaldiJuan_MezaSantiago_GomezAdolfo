package com.mycompany.app;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RotationTests {

    // Unit test: rotateLeft on PieceL changes the forma matrix as expected
    @Test
    public void testPieceLRotateLeftUnit() {
        PieceL piece = new PieceL();
        // Forma inicial (3x2)
        int[][] inicial = piece.getForma();

        // Aplicar rotación a la izquierda
        piece.rotateLeft();

        // Forma esperada tras rotar a la izquierda (2x3)
        int[][] esperado = new int[][]{
            {0, 0, 1},
            {1, 1, 1}
        };

        // Comparar fila a fila
        for (int i = 0; i < esperado.length; i++) {
            assertArrayEquals(esperado[i], piece.getForma()[i]);
        }
    }

    // Integration test: Tetris.rotateLeft() applies rotation when valid
    @Test
    public void testTetrisRotateLeft_Valid() {
        Tetris tetris = new Tetris();
        Board board = tetris.getBoard();

        PieceSquare square = new PieceSquare();
        // Colocar la pieza en la esquina superior izquierda
        board.setPiezaActual(square);
        board.setFilaActual(0);
        board.setColumnaActual(0);
        board.colocarPiezaEnTableroVerificada(square, 0, 0);

        // Rotar a la izquierda a través de Tetris
        boolean resultado = tetris.rotateLeft();

        // Para el cuadrado la rotación no cambia la forma, debe ser true
        assertTrue("La rotación del cuadrado debería aplicarse correctamente", resultado);

        // Comprobar que las celdas ocupadas por el square siguen ocupadas
        int[][] b = board.getBoard();
        assertEquals(1, b[0][0]);
        assertEquals(1, b[0][1]);
        assertEquals(1, b[1][0]);
        assertEquals(1, b[1][1]);
    }

}
