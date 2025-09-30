package com.mycompany.app;

public class PieceStick extends PieceBase { // Extiende la clase abstracta PiezasAbsAll

    public PieceStick() {
    super (new int [][] {
        {1},
        {1},
        {1},
        {1}
        }, "Piece Stick");
    }

    @Override
    public void aleatorizarForma() {
        // La Stick no tiene variaciones internas en este diseño
    }
}   

