package com.mycompany.app;

public class PieceSquare extends PieceBase{ 
    public PieceSquare() {

        super (new int [][]{

            {1, 1},
            {1, 1}
        });
    }

    @Override
    public void aleatorizarForma() {
        // El cuadrado no cambia de forma entre rotaciones en este diseño
    }
}