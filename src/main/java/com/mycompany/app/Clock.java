package com.mycompany.app;

public class Clock extends Tetris implements ITick {
    private int ticks;

    public Clock() {
    }

    public int getTicks() {
        return ticks;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    public void tick() {
       setTicks(getTicks() + 1);
    }

}
