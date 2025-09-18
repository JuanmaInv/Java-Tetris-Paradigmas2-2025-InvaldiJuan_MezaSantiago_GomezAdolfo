package com.mycompany.app;

public interface IGameState {
    void iniciarJuego();
    void terminarJuego();
    void reiniciarJuego();
    int getEstado();
    void setEstado(int estado);
    boolean isJuegoActivo();
}