package com.mycompany.app;

public class PieceT extends PieceAll { // Extiende la clase abstracta PiezasAbsAll

    // Constructor
    public PieceT() {
        super("T");

        // Definir la forma inicial de la pieza T usando el método de la clase base
        setForma(new int[][] {
            {0, 1, 0},
            {1, 1, 1},
            {0, 0, 0}
        });
    }
    
    // Los getters ya están definidos en la clase base
}