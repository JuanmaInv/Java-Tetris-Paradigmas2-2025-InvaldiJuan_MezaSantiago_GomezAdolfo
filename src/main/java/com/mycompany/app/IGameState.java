package com.mycompany.app;

public interface IGameState {
    void start();
    void end();
    void restart();
    int state();
    void setState(int state);
    boolean isJuegoActivo();
}