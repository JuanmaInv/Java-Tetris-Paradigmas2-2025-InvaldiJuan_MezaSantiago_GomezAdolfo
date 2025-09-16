package com.mycompany.app;

public class PiezaJ extends PiezasAbsAll { // Extiende la clase abstracta PiezasAbsAll

    // Constructor
    public PiezaJ() {
        super("J");

        // Definir la forma inicial de la pieza J usando el método de la clase base
        setForma(new int[][] {
            {0, 0, 1},
            {0, 0, 1},
            {0, 1, 1}
        });
    }

    // Los getters ya están definidos en la clase base
}
