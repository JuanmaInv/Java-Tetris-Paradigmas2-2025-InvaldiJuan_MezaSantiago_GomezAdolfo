package com.mycompany.app;

import java.util.Random;
// La pieza "Dog" tiene dos configuraciones: derecha e izquierda
// La pieza puede aleatorizar su forma al ser creada

public class PieceDog extends Piece {
    private Random random = new Random();
    
    private int[][] formaDerecha = {
        {0, 1, 1},
        {1, 1, 0},
    };

    private int[][] formaIzquierda = {
        {1, 1, 0},
        {0, 1, 1},
    };
    
    public PieceDog() {
        super(new int[][] {
            {0, 1, 1},
            {1, 1, 0}
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
