
package com.mycompany.app;

public interface IBoardOperations {
    boolean verificarColocacionValida(PieceBase piece, int fila, int columna);
    void colocarPiezaEnTableroVerificada(PieceBase piece, int fila, int columna);
    void limpiarPiezaDelTablero(PieceBase piece, int fila, int columna);
    void verificarYEliminarLineas();
    boolean esFinDelJuego(Board tablero);
    boolean tableroVacio();
    int[][] getBoard();
}

