package com.mycompany.app;

public class PieceStick extends Piece { // Extiende la clase abstracta PiezasAbsAll

    // Constructor
    public PieceStick() {
        int [][] forma = {
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0}
        };
    setForma(forma);
    }
    // Los getters ya están definidos en la clase base
}