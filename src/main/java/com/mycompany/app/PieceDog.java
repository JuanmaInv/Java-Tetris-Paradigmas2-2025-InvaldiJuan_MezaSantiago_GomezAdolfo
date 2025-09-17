package com.mycompany.app;

import java.util.Random;
// La pieza "Dog" tiene dos configuraciones: derecha e izquierda
// La pieza puede aleatorizar su forma al ser creada

public class PieceDog extends Piece {
    private int[][] formaDerecha = {
        {0, 1, 1},
        {1, 1, 0},
        {0, 0, 0}
    };

    private int[][] formaIzquierda = {
        {1, 1, 0},
        {0, 1, 1},
        {0, 0, 0}
    };

    public PieceDog() {
        super(new int[][] {
            {0, 1, 1},
            {1, 1, 0},
            {0, 0, 0}
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
