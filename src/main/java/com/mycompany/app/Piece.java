package com.mycompany.app; 

import java.util.Random; // Para seleccionar una rotacion aleatoria
// Las piezas solo deben encargarse de su forma y rotacion.
// No sabe sobre choques o limites del tablero

public abstract class Piece implements InterfaceRotation { // Clase abstracta que implementa las dos interfaces (TIPO//atributo)
    private int [][] forma; // Matriz que representa la forma de la pieza (os y 1s)
    private Random random = new Random(); // Para rotacion aleatoria

    public Piece() { // Constructor de cada pieza
    }
        // Permite inicializar la forma desde las subclases
        public void setForma(int[][] forma) {
            this.forma = forma;
        }

    // ===== GETTERS =====
    public int[][] getForma() { // Devuelve la forma de la pieza
        return forma;
    }
    // ===== INTERFAZ ROTACION =====
    public void rotarDerecha() { // Rotacion 90 grados a la derecha
    int filas = forma.length; // Numero de filas
    int columnas = forma[0].length; // Numero de columnas
    int[][] nuevaForma = new int[columnas][filas]; // Nueva matriz con filas y columnas intercambiadas

    for (int i = 0; i < filas; i++) { // Recorro filas
        for (int j = 0; j < columnas; j++) { // Recorro columnas
            nuevaForma[j][filas - 1 - i] = forma[i][j]; // Asigno valores rotados
        }
    }
    setForma(nuevaForma); // Actualizo la forma de la pieza
    }

    public void rotarIzquierda() { // Rotacion 90 grados a la izquierda o lo que es lo mismo... 3 rotaciones a la derecha
                                   // 3 rotaciones a la derecha = 1 rotacion izquierda
    rotarDerecha(); // Primera rotacion derecha
    rotarDerecha(); // Segunda rotacion derecha
    rotarDerecha(); // Tercera rotacion derecha
    }
    
    public void seleccionarLadoRandom() { // Selecciona una rotacion aleatoria
        int configuracion = random.nextInt(3); // Genera un numero aleatorio entre 0 y 2
        if (configuracion == 0) { // Si es 0, rota a la derecha
            rotarDerecha();
        }  if (configuracion == 1) { // Si es 1, rota a la izquierda
            rotarIzquierda();
        }
        else { 
        }
    // Si es 2, no hace nada (mantiene forma original)
    }
    
}