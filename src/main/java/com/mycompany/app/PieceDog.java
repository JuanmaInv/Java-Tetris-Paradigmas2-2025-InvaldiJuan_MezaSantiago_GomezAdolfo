package com.mycompany.app;

// La pieza "Dog" tiene dos configuraciones: derecha e izquierda
// La pieza puede aleatorizar su forma al ser creada

public class PieceDog extends PieceBase {
    
    private int[][] formaA = {
        {0, 1, 1},
        {1, 1, 0},
    };

    private int[][] formaB = {
        {1, 1, 0},
        {0, 1, 1},
    };
    
    public PieceDog() {
        super(new int[][] {
            {0, 1, 1},
            {1, 1, 0}
        }, "Piece Dog");
    }

    @Override
    public void aleatorizarForma() {
        elegirFormaSegunBoolean(formaA, formaB);
    }
}
