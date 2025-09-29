package com.mycompany.app;

public class PieceL extends PieceBase {

    private int[][] formaA = {
        {1, 0},
        {1, 0},
        {1, 1}
    };

    private int[][] formaB = {
        {0, 1},
        {0, 1},
        {1, 1}
    };

    public PieceL() {
        super(new int[][] {
            {1, 0},
            {1, 0},
            {1, 1}
        });
    }

    @Override
    public void aleatorizarForma() {
        elegirFormaSegunBoolean(formaA, formaB);
    }
}