package com.mycompany.app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;

public class PieceNames {
    
    @Test
    public void TestDeNames(){
 
    PieceBase piece = new PieceT();
    assertEquals("Piece T",piece.getName());

    PieceBase piece1 = new PieceDog();
    assertEquals("Piece Dog",piece1.getName());
    
    PieceBase piece2 = new PieceL();
    assertEquals("Piece L",piece2.getName());
    
    PieceBase piece3 = new PieceSquare();
    assertEquals("Piece Square",piece3.getName());
    
    PieceBase piece4 = new PieceStick();
    assertEquals("Piece Stick",piece4.getName());
}


@Test
    public void testDeAltoYAnchoPiece (){
        PieceBase pieza1 = new PieceDog();
        PieceBase pieza2 = new PieceL();
        PieceBase pieza3 = new PieceStick();
        PieceBase pieza4 = new PieceSquare();
        PieceBase pieza5 = new PieceT();
        //verifico alto y ancho de cada pieza
        assertEquals(2, pieza1.getAlto());
        assertEquals(3, pieza1.getAncho());
        assertEquals(3, pieza2.getAlto());
        assertEquals(2, pieza2.getAncho());
        assertEquals(4, pieza3.getAlto());
        assertEquals(1, pieza3.getAncho());
        assertEquals(2, pieza4.getAlto());
        assertEquals(2, pieza4.getAncho());
        assertEquals(2, pieza5.getAlto());
        assertEquals(3, pieza5.getAncho());
    }

    @Test
    public void testMoverPiezaBooleanCollision() {
        Board board = new Board();
        PieceBase square = new PieceSquare();

        // Colocar square en (0,0)
        board.setPiezaActual(square);
        board.setFilaActual(0);
        board.setColumnaActual(0);
        board.colocarPiezaEnTableroVerificada(square, 0, 0);

        // Poner un bloqueo donde la pieza intentaría ocupar tras bajar 1 fila
        // La pieza square ocupa (0,0),(0,1),(1,0),(1,1)
        // Al bajar 1 fila ocuparía (1,0),(1,1),(2,0),(2,1) -> bloqueamos (2,0)
        board.setBoard(2, 0, 9);

        // moverPieza(deltaFila=1, deltaColumna=0) debería detectar la colisión y devolver false
        boolean puedeMover = board.moverPieza(1, 0);
        assertFalse("Mover debería devolver false por colisión", puedeMover);
    }


        @Test
    public void testLimpiarPiezaDelTablero() {
        Board board = new Board();
        PieceBase square = new PieceSquare();

        // Colocar square en (0,0)
        board.setPiezaActual(square);
        board.setFilaActual(0);
        board.setColumnaActual(0);
        board.colocarPiezaEnTableroVerificada(square, 0, 0);

        // Verificamos que las posiciones ocupadas son no-cero
        assertEquals(1, board.getBoard()[0][0]);
        assertEquals(1, board.getBoard()[0][1]);
        assertEquals(1, board.getBoard()[1][0]);
        assertEquals(1, board.getBoard()[1][1]);

        // Limpiar la pieza
        board.limpiarPiezaDelTablero(square, 0, 0);

        // Ahora esas celdas deben ser 0
        assertEquals(0, board.getBoard()[0][0]);
        assertEquals(0, board.getBoard()[0][1]);
        assertEquals(0, board.getBoard()[1][0]);
        assertEquals(0, board.getBoard()[1][1]);
    }


}
