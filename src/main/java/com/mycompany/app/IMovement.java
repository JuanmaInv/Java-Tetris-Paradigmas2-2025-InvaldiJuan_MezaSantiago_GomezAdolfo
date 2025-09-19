
package com.mycompany.app;

public interface IMovement {
    // Movimiento genérico: deltaFila y deltaColumna
    void moverPieza(Piece piece, int deltaFila, int deltaColumna); // si delta fila es +1 baja, -1 sube, 0 no cambia; si delta columna es +1 derecha, -1 izquierda, 0 no cambia
    // Caída libre
    void caidaLibre(Piece piece);
}
