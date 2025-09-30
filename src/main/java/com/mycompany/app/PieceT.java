package com.mycompany.app;

public class PieceT extends PieceBase {
    private int[][] formaA = {
        {0, 1, 0},
        {1, 1, 1},
    };

    private int[][] formaB = {
        {1, 1, 1},
        {0, 1, 0},
    };

    public PieceT() {
        super(new int[][] {
            {0, 1, 0},
            {1, 1, 1},
        }, "Piece T");
    }

    @Override
    public void aleatorizarForma() {
        elegirFormaSegunBoolean(formaA, formaB);
    }
}
