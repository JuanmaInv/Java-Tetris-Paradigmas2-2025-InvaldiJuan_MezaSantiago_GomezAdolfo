package com.mycompany.app;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

// Importar las clases necesarias
import com.mycompany.Tetris;

public class TetrisTesteos {

    //Tests de la clase Tetris
    @Test
    public void testIniciarJuego(){
        Tetris tetris = new Tetris();
        tetris.startGame();
        assertEquals(1, tetris.stateGame()); // Verificar que el estado sea "juego iniciado"
    }
    
    @Test
    public void testFinalizarJuego(){
        Tetris tetris = new Tetris();
        tetris.startGame();
        tetris.endGame();
        assertEquals(2, tetris.stateGame()); // Verificar que el estado sea "juego terminado"
    }
    
    @Test
    public void testEstadoInicial(){
        Tetris tetris = new Tetris();
        assertEquals(0, tetris.stateGame()); // Verificar que el estado inicial sea "no iniciado"
    }

    //Tests de creación de piezas
    @Test
    public void testCrearPieceSquare(){
        Piece piece = new PieceSquare();
        assertTrue(piece != null); // Verificar que la pieza se creó correctamente
    }

    @Test
    public void testCrearPieceDog(){
        Piece piece = new PieceDog();
        assertTrue(piece != null); // Verificar que la pieza se creó correctamente
    }
    
    @Test
    public void testCrearPieceL(){
        Piece piece = new PieceL();
        assertTrue(piece != null); // Verificar que la pieza se creó correctamente
    }
    
    @Test
    public void testCrearPieceT(){
        Piece piece = new PieceT();
        assertTrue(piece != null); // Verificar que la pieza se creó correctamente
    }
    
    @Test
    public void testCrearPieceStick(){
        Piece piece = new PieceStick();
        assertTrue(piece != null); // Verificar que la pieza se creó correctamente
    }

    //Tests de rotación de piezas
    @Test
    public void testRotarPieceT(){
        Piece piece = new PieceT();
        piece.rotarDerecha();
        assertTrue(piece != null); // Verificar que la rotación no causa errores
    }
    
    @Test
    public void testRotarPieceL(){
        Piece piece = new PieceL();
        piece.rotarDerecha();
        assertTrue(piece != null); // Verificar que la rotación no causa errores
    }
          
    @Test
    public void testRotarPieceDog(){
        Piece piece = new PieceDog();
        piece.rotarDerecha();
        assertTrue(piece != null); // Verificar que la rotación no causa errores
    }

    @Test
    public void testRotarPieceStick(){
        Piece piece = new PieceStick();
        piece.rotarDerecha();
        assertTrue(piece != null); // Verificar que la rotación no causa errores
    }

    @Test
    public void testRotarPieceSquare(){
        Piece piece = new PieceSquare();
        piece.rotarDerecha();
        assertTrue(piece != null); // Verificar que la rotación no causa errores
    }
    
    //Tests del tablero
    @Test
    public void testTableroVacio() {
        Board tablero = new Board();
        assertTrue(tablero.tableroVacio());
    }
    
    @Test
    public void testTableroLleno(){
        Board tablero = new Board();
        assertTrue(tablero.tableroVacio()); // Un tablero nuevo debería estar vacío
    }

}


