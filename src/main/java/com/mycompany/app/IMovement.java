
package com.mycompany.app;

public interface IMovement {
    // Movimiento genérico: deltaFila y deltaColumna
    void moverPieza(Piece piece, int deltaFila, int deltaColumna);
    // Caída libre
    void caidaLibre(Piece piece);
}
