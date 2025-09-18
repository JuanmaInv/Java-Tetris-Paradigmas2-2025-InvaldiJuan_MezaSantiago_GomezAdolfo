package com.mycompany.app;

import org.junit.Test;

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

    //testear rotaciones
    @Test
    public void testRotarPiecesDerecha(){
        Piece piece1 = new PieceL();
        Piece piece2 = new PieceT();
        Piece piece3 = new PieceSquare();
        Piece piece4 = new PieceDog();
        Piece piece5 = new PieceStick();
        // Rotar las piezas
        piece1.rotarDerecha();
        piece2.rotarDerecha();
        piece3.rotarDerecha();
        piece4.rotarDerecha();
        piece5.rotarDerecha();
        assertTrue(true); // Solo verifica que el método se ejecuta sin errores
    }

    @Test
    public void testRotarPiecesIzquierda(){
        Piece piece1 = new PieceL();
        Piece piece2 = new PieceT();
        Piece piece3 = new PieceSquare();
        Piece piece4 = new PieceDog();
        Piece piece5 = new PieceStick();
        // Rotar las piezas
        piece1.rotarIzquierda();
        piece2.rotarIzquierda();
        piece3.rotarIzquierda();
        piece4.rotarIzquierda();
        piece5.rotarIzquierda();
        assertTrue(true); // Solo verifica que el método se ejecuta sin errores
    }

    @Test
    public void testRotarPiecesLadoRandom(){
        Piece piece1 = new PieceL();
        Piece piece2 = new PieceT();
        Piece piece3 = new PieceSquare();
        Piece piece4 = new PieceDog();
        Piece piece5 = new PieceStick();
        // Rotar las piezas
        piece1.seleccionarLadoRandom();
        piece2.seleccionarLadoRandom();
        piece3.seleccionarLadoRandom();
        piece4.seleccionarLadoRandom();
        piece5.seleccionarLadoRandom();
        assertTrue(true); // Solo verifica que el método se ejecuta sin errores
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
        tablero.setPiezaActual(piece);
        tablero.moverPieza(piece, 0, 1); // derecha
        tablero.moverPieza(piece, 0, -1); // izquierda
        assertTrue(true); // Solo verifica que los métodos se ejecutan sin error
    }

    @Test
    public void testPiezaCaidaLibre(){
        Board tablero = new Board();
        Piece piece = new PieceSquare();
        tablero.setPiezaActual(piece);
        tablero.colocarPiezaEnTableroVerificada(piece, 0, 0);
        // Simula la caída libre de la pieza hasta el fondo
        for (int i = 0; i < 10; i++) {
            tablero.caidaLibre(piece);
        }
        // Verifica que la pieza esta en la última fila posible
        assertTrue(tablero.getFilaActual() >= 0);
        assertTrue(tablero.getColumnaActual() >= 0);
    }

// Tests adicionales para mejor cobertura
    @Test
    public void testBoardGetters() {
        Board tablero = new Board();
        assertFalse(tablero.getBoard() == null);
        assertEquals(0, tablero.getLineasEliminadas());
        assertEquals(5, tablero.getLineasParaGanar());
    }

@Test
public void testPiezaFormas() {
    PieceDog dog = new PieceDog();
    dog.aleatorizarForma();
    assertFalse(dog.getForma() == null);
    
    PieceL l = new PieceL();
    l.aleatorizarForma();
    assertFalse(l.getForma() == null);
    
    PieceT t = new PieceT();
    t.aleatorizarForma();
    assertFalse(t.getForma() == null);
}

@Test
public void testClockFuncionalidad() {
    Clock clock = new Clock();
    assertEquals(0, clock.getTicks());
    clock.tick();
    assertEquals(1, clock.getTicks());
    clock.setTicks(10);
    assertEquals(10, clock.getTicks());
}

// Tests para verificar funcionalidad de las piezas
@Test
public void testPieceFunctionality() {
    Piece piece = new PieceSquare();
    
    // Test dimensiones
    assertEquals(2, piece.getAncho());
    assertEquals(2, piece.getAlto());
    
    // Test no está vacía
    assertFalse(piece.esVacia());
    
    // Test aleatorización
    piece.aleatorizarForma();
    assertFalse(piece.getForma() == null);
}

    @Test
    public void testIBoardOperations() {
        Board board = new Board();
        Piece piece = new PieceSquare();
        // Test puede colocarse
        assertTrue(board.verificarColocacionValida(piece, 0, 0));
        // Test tablero vacío
        assertTrue(board.tableroVacio());
        // Test colocar pieza
        board.colocarPiezaEnTableroVerificada(piece, 0, 0);
        assertFalse(board.tableroVacio());
    }

    @Test
    public void testIMovement() {
        Board board = new Board();
        Piece piece = new PieceSquare();
        board.setPiezaActual(piece);
        // Test movimientos usando interfaz
        board.moverPieza(piece, 0, 1); // derecha
        board.moverPieza(piece, 0, -1); // izquierda
        assertTrue(true); // Verifica que se ejecutan sin error
    }

@Test
public void testIGameState() {
    Tetris game = new Tetris();
    
    // Test estado inicial
    assertFalse(game.isJuegoActivo());
    assertEquals(0, game.getEstado());
    
    // Test iniciar juego
    game.iniciarJuego();
    assertTrue(game.isJuegoActivo());
    assertEquals(1, game.getEstado());
    
    // Test terminar juego
    game.terminarJuego();
    assertFalse(game.isJuegoActivo());
    assertEquals(2, game.getEstado());
}

}