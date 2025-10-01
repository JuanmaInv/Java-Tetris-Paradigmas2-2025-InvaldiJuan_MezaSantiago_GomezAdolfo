package com.mycompany.app;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;

public class TESTINGFULL {

    @Test
    public void testJuegoCompletoHastaVictoriaConCaida() {
        // Crear el juego
        Tetris tetris = new Tetris();
        Board board = tetris.getBoard();

        // Iniciar
        tetris.start();
        assertEquals(1, tetris.getState()); // estado "en juego"
        assertFalse(tetris.isGameWin());

        // Configurar meta baja para test
        board.setLineasParaGanar(2);
        assertEquals(2, board.getLineasParaGanar());
        board.getLineasEliminadas(); // Asegurarse de que empieza en 0
        assertEquals(0, board.getLineasEliminadas());

        // Colocar 10 cuadrados (2x2) lado a lado llenan 20 columnas
        for (int c = 0; c < board.getColumnas(); c += 2) {
            PieceBase pieza = new PieceSquare();
            // colocar en fila 0 y columna c
            boolean colocada = board.colocarSiValida(pieza, 0, c);
            assertTrue("La pieza debe colocarse", colocada);

            // bajarla hasta el fondo eb este caso (o hasta que no pueda mas)
            board.caidaLibre(pieza);
        }
   
        // Eliminar líneas
        board.verificarYEliminarLineas(); // Debería eliminar 2 líneas completas
        assertEquals(2, board.getLineasEliminadas()); // Verificar líneas eliminadas

        // Actualizar estado del juego
        tetris.actualizarEstadoJuego();

        // Verificaciones finales
        assertTrue("El juego debería estar ganado", tetris.isGameWin());
        assertEquals("El estado debería ser WIN (3)", 3, tetris.getState());
        assertFalse("El juego no debería estar en curso", tetris.isGameStart());
    }

    @Test
    public void testJuegoCompletoHastaDerrotaConCaida() {
        // Crear juego
        Tetris tetris = new Tetris();
        Board board = tetris.getBoard();

        // Iniciar el juego
        tetris.start();
        assertEquals(1, tetris.getState()); // Estado "en juego"
        assertFalse(tetris.isGameEnd()); // No ha terminado

        // Configurar meta para test
        board.setLineasParaGanar(5);
        assertEquals(5, board.getLineasParaGanar());

        boolean derrota = false;

        // Colocar PieceDog lado a lado para llenar el tablero hasta que no pueda mas y perder
        for (int c = 0; c < board.getColumnas(); c ++) {
            PieceBase pieza = new PieceDog();
            // colocar en fila 0 y columna c
            boolean colocada = board.colocarSiValida(pieza, 0, c);

            if (colocada) { // Si se pudo colocar
                assertTrue("La pieza debe colocarse", colocada);
                board.caidaLibre(pieza);

                // Verificar si se pueden eliminar líneas
                board.verificarYEliminarLineas(); //eliminar si es posible
                board.getLineasEliminadas(); // Actualizar contador

                // Verificar que no ha ganado aún comprobando que lineCount sea diferente de lineasParaGanar
                board.lineCount();
                board.getLineasParaGanar(); // Asegurarse de que no ha ganado aún
                assertNotEquals(board.lineCount(), board.getLineasParaGanar());

                // Verificar estado del juego
                assertFalse("No debe haber ganado aún", tetris.isGameWin());
                board.esFinDelJuego(board); // Verificar si es fin del juego
                assertFalse("No debe ser fin del juego aún", board.esFinDelJuego(board));

            } else { // No se pudo colocar
                derrota = true; // No se pudo colocar, es derrota
                tetris.end(); // Terminar el juego
                break; // Salir del bucle
            }
        }

        // Verificación final
        assertTrue("El juego debe terminar en derrota", derrota);
        assertTrue("El juego debe estar terminado", tetris.isGameEnd());
        assertEquals("El estado debe ser GAME OVER (2)", 2, tetris.getState());

        //las ultimas dos columnas deben estar vacias?? si o no??
        // deberian estar vacias
        for (int r = 0; r < board.getFilas(); r++) {
            assertEquals(0, board.getBoard()[r][board.getColumnas() - 1]);
            assertEquals(0, board.getBoard()[r][board.getColumnas() - 2]);
        }
    }
}


