package com.mycompany.app; 

import java.util.Random; // Para seleccionar una rotacion aleatoria
// Las piezas solo deben encargarse de su forma y rotacion.
// No sabe sobre choques o limites del tablero

public abstract class Piece implements IRotation {
    private int[][] forma; // Matriz que representa la forma de la pieza
    private Random random = new Random(); // Para rotación aleatoria

    // Constructor
    public Piece(int[][] forma) {
        this.forma = forma;
    }

    // Getter para forma
    public int[][] getForma() {
        return forma;
    }

    // Setter para forma
    public void setForma(int[][] forma) {
        this.forma = forma;
    }
    // ===== MÉTODOS ÚTILES PARA LA FORMA =====
    public int getAncho() {
        return forma[0].length; // toma la primera fila y cuenta sus columnas
    }
    
    public int getAlto() {
        return forma.length; // Alto es el número de filas, cuenta cuanta filas tiene
    }

    public boolean esVacia() { // Verifica si la pieza no tiene bloques
        for (int i = 0; i < forma.length; i++) { // Recorre filas
            for (int j = 0; j < forma[i].length; j++) { // Recorre columnas
                if (forma[i][j] != 0) { // Si encuentra un bloque
                    return false; // No está vacía
                }
            }
        }
        return true;
    }
    
    public void aleatorizarForma() {
        // Implementación usando rotación aleatoria
        seleccionarLadoRandom();
    }

    // ===== INTERFAZ ROTACION =====
    public void rotarDerecha() {
        int filas = forma.length;
        int columnas = forma[0].length;
        int[][] nuevaForma = new int[columnas][filas];
        for (int i = 0; i < filas; i++) { // Recorre filas
            for (int j = 0; j < columnas; j++) { // Recorre columnas 
                nuevaForma[j][filas - 1 - i] = forma[i][j]; // Rotación 90 grados a la derecha, la nueva fila es la antigua columna invertida
            }
        }
        setForma(nuevaForma); // Actualiza la forma de la pieza
    }

    public void rotarIzquierda() {
        rotarDerecha();
        rotarDerecha();
        rotarDerecha();
    }

    public void seleccionarLadoRandom() {
        int configuracion = random.nextInt(3);
        if (configuracion == 0) {
            rotarDerecha();
        } else if (configuracion == 1) {
            rotarIzquierda();
        } else {
            // Si es 2, no hace nada. Su forma original se mantiene.
    }
  }

    public int getColumnaActual() {
        return 0; // Placeholder, la columna actual debe ser gestionada por la clase Board
    }
}