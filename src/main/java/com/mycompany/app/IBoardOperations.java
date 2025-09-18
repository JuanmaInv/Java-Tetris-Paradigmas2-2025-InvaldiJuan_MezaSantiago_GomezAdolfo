
package com.mycompany.app;

public interface IBoardOperations {
    boolean verificarColocacionValida(Piece piece, int fila, int columna);
    void colocarPiezaEnTableroVerificada(Piece piece, int fila, int columna);
    void limpiarPiezaDelTablero(Piece piece, int fila, int columna);
    void verificarYEliminarLineas();
    boolean esFinDelJuego(Piece piece);
    boolean tableroVacio();
    int[][] getBoard();
}

