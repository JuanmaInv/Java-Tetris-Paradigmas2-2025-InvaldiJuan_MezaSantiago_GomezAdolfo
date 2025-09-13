package com.mycompany.app;

public class PiezaT extends PiezasAbsAll { // Extiende la clase abstracta PiezasAbsAll
    private int[][] forma;
    private String nombre;

    // Constructor
    public PiezaT() {
        super("T");

        // Definir la forma inicial de la pieza T
        this.forma = new int[][] {
            {0, 1, 0},
            {1, 1, 1},
            {0, 0, 0}
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