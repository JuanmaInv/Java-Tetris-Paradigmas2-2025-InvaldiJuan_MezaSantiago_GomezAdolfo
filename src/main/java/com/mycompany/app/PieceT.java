package com.mycompany.app;

import java.util.Random;

public class PieceT extends Piece {
    private int[][] formaArriba = {
        {0, 1, 0},
        {1, 1, 1},
        {0, 0, 0}
    };

    private int[][] formaAbajo = {
        {0, 0, 0},
        {1, 1, 1},
        {0, 1, 0}
    };

    public PieceT() {
        super(new int[][] {
            {0, 1, 0},
            {1, 1, 1},
            {0, 0, 0}
        });
    }

    public void aleatorizarForma() {
        Random rand = new Random();
        if (rand.nextBoolean()) {
            setForma(formaArriba);
        } else {
            setForma(formaAbajo);
        }
    }
}
