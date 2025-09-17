package com.mycompany.app;

//Para asegurarme de que cada pieza rotee sobre su eje, a la izquierda y a la derecha.
public interface InterfaceRotation { // Contrato que me dice "Todas las piezas deben tener estos metodos"
    void rotarDerecha();
    void rotarIzquierda();
    void seleccionarLadoRandom();
}