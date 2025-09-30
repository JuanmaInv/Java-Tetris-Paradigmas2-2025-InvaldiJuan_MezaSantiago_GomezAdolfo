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



}
