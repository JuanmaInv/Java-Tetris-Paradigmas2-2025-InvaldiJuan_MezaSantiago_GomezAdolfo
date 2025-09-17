package com.mycompany.app;


import java.util.Random;

public class PieceL extends Piece {
    private int[][] formaDerecha = {
        {0, 1, 0},
        {0, 1, 0},
        {0, 1, 1}
    };

    private int[][] formaIzquierda = {
        {0, 1, 0},
        {0, 1, 0},
        {1, 1, 0}
    };

    public PieceL() {
        super(new int[][] {
            {0, 1, 0},
            {0, 1, 0},
            {0, 1, 1}
        });
    }

    public void aleatorizarForma() {
        Random rand = new Random();
        if (rand.nextBoolean()) {
            setForma(formaDerecha);
        } else {
            setForma(formaIzquierda);
        }
    }
}