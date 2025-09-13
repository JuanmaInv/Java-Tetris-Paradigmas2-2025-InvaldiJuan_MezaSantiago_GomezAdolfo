package com.mycompany.app;

public class PiezaJ extends PiezasAbsAll { // Extiende la clase abstracta PiezasAbsAll
    private String nombre;
    private int[][] forma;

    // Constructor
    public PiezaJ() {
        super("J");

        // Definir la forma inicial de la pieza J
        this.forma = new int[][] {
            {0, 0, 1},
            {0, 0, 1},
            {0, 1, 1}
        };
    }

    // Getters
    public int[][] getForma() {
        return forma;
    }
    
    public String getNombre() {
        return nombre;
    }
}
