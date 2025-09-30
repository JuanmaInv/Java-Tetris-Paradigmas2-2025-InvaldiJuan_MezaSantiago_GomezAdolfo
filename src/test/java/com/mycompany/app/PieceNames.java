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
}
