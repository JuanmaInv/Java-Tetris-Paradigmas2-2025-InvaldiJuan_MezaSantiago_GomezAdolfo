package com.mycompany.app;


import java.util.Random;

public class PieceL extends Piece {
    private Random random = new Random();

    public Random getRandom() {
        if (this.random == null) this.random = new Random();
        return this.random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    private int[][] formaDerecha = {
        {1, 0},
        {1, 0},
        {1, 1}
    };

    private int[][] formaIzquierda = {
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

    public void aleatorizarForma() {
        boolean forma = random.nextBoolean();
        if ( forma == true) {
            setForma(formaDerecha);
        } else {
            setForma(formaIzquierda);
        }
    }
}