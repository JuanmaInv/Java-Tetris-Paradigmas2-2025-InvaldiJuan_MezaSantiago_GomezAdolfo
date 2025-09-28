package com.mycompany.app;

import java.util.Random;

public class PieceT extends Piece {
    private Random random = new Random();

    public Random getRandom() {
        if (this.random == null) this.random = new Random();
        return this.random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }
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

    @Override
    public void aleatorizarForma() {
        boolean forma = random.nextBoolean();
        if ( forma == true) {
            setForma(forma1);
        } else {
            setForma(forma2);
        }
    }
}
