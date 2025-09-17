package com.mycompany.app;

// Clase que representa el tablero de Tetris
public class Board {
    private int filas; // Filas del tablero
    private int columnas; // Columna del tablero
    private int[][] matriz; // Matriz que representa el estado del tablero (0 = vacio, 1 = ocupado)
    private PieceAll piezaActual; // La pieza que esta actualmente en el tablero

    public Board() {
        this.filas = 10; // Filas del tablero
        this.columnas = 20; // Columnas del tablero
        this.matriz = new int[filas][columnas]; // Inicializar la matriz del tablero con ceros (vacio)
        this.piezaActual = null; // Inicialmente no hay pieza en el tablero
    }

    // Verifica si el tablero esta vacio
    public boolean tableroVacio() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (matriz[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // Coloca una nueva pieza en el tablero
    public void colocarPieza(PieceAll pieza) {
        this.piezaActual = pieza;
        // Aqui podriamos inicializar la posicion de la pieza en la matriz
    }

    // Mueve la pieza actual hacia abajo si es posible
    public boolean moverPiezaAbajo() {
        if (piezaActual != null) {
            piezaActual.moverAbajo();
            // Aqui podriamos actualizar la matriz segun la nueva posicion de la pieza
            return true;
        }
        return false;
    }

    // Elimina la pieza actual del tablero
    public void eliminarPiezaActual() {
        this.piezaActual = null;
        // También podrías limpiar la matriz si lo necesitas
    }

    // Getter para la pieza actual
    public PieceAll getPiezaActual() {
        return piezaActual;
    }

    // Getter para la matriz del tablero
    public int[][] getMatriz() {
        return matriz;
    }

    // Puedes agregar más métodos para detectar líneas completas, rotar piezas, etc.
}
