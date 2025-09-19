package com.mycompany.app;


import java.util.Random;

public class PieceL extends Piece {
    private Random random = new Random();

    private int[][] formaDerecha = {
        {1, 0},
        {1, 0},
        {1, 1}
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
        boolean forma = random.nextBoolean();
        if ( forma == true) {
            setForma(formaDerecha);
        } else {
            setForma(formaIzquierda);
        }
    }
}