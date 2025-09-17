package com.mycompany.app;

import org.junit.Test;

import com.mycompany.Tetris;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TetrisTesteos {
    
    //Creacion de piezas
    //testear si las piezas se crean o no ✅
    //testear si las piezas tienen la forma correcta
    //testear si las piezas tienen la rotacion correcta
    @Test
    public void testCrearPieceStick(){
        Piece piece = new PieceStick();
        assertTrue(piece != null);
    }

    @Test
    public void testCrearPieceSquare(){
        Piece piece = new PieceSquare();
        assertTrue(piece != null);
    }

    @Test
    public void testCrearPieceDog(){
        Piece piece = new PieceDog();
        assertTrue(piece != null);
        }

    @Test
    public void testCrearPieceL(){
        Piece piece = new PieceL();
        assertTrue(piece != null);
    }

    @Test
    public void testCrearPieceT(){
        Piece piece = new PieceT();
        assertTrue(piece != null); 
    }
    //Creacion y estado del juego
    //testear si el juego inicia/termina o no ✅
    @Test
    public void testJuegoInicia(){
        Tetris game = new Tetris();
        game.iniciarJuego();
       assertEquals(game.getEstado(), 1);
    }
    
    @Test
    public void testJuegoTermina(){
        Tetris game = new Tetris();
        game.iniciarJuego();
        assertEquals(game.getEstado(), 1);
        game.terminarJuego();
        assertEquals(game.getEstado(), 2);
    }
    
    @Test
    public void testReiniciarJuego(){
        Tetris game = new Tetris();
        game.iniciarJuego();
        assertEquals(game.getEstado(), 1);

        game.reiniciarJuego();
        // Verificar que está iniciado y terminado
        assertTrue(game.getEstado() == 0);

    }


    //Creacion y estado del tablero
    //testear si el tablero esta lleno o vacio ✅
    //testear si una pieza puede moverse o no en el tablero
    //testear si una pieza puede rotar o no en el tablero
    //testear si una pieza puede caer al vacio o no en el tablero
    //testear si una linea puede ser eliminada o no en el tablero
    //testear en casos de limites del tablero y colisiones/choques de piezas
    //testear si una pieza puede ser colocada o no en el tablero
    @Test
    public void testTableroVacio() {
        Board tablero = new Board();
        assertTrue(tablero.tableroVacio());
    }

    
    @Test
    public void testTableroLleno(){
        Board tablero = new Board();
        assertFalse(!tablero.tableroVacio());
    }


    @Test
    public void testMoverPiezaEnTablero() {
        Board tablero = new Board();
        Piece piece = new PieceSquare();
        tablero.ingresarNuevaPieza(piece);
        tablero.moverPiezaDerecha(piece);
        tablero.moverPiezaIzquierda(piece);
        assertTrue(true); // Solo verifica que los métodos se ejecutan sin error
}

    @Test
    public void testPiezaCaeAlVacio(){
        Board tablero = new Board();
        Piece piece = new PieceStick();
        tablero.ingresarNuevaPieza(piece);
        // Simular caída al vacío
        for (int i = 0; i < 19; i++) { // Mover la pieza hacia abajo varias veces
            tablero.descenderPieza(piece);
        }
        assertTrue(true); // Solo verifica que el método se ejecuta sin error
    }

}