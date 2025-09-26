package com.mycompany.app;

import java.util.Random;

public class PieceT extends Piece {
    private Random random = new Random();
    private int[][] forma1 = {
        {0, 1, 0},
        {1, 1, 1},
    };

    private int[][] forma2 = {
        {1, 1, 1},
        {0, 1, 0},
    };

    public PieceT() {
        super(new int[][] {
            {0, 1, 0},
            {1, 1, 1},
        });
    }

    public void aleatorizarForma() {
        boolean forma = random.nextBoolean();
        if ( forma == true) {
            setForma(forma1);
        } else {
            setForma(forma2);
        }
    }
}
