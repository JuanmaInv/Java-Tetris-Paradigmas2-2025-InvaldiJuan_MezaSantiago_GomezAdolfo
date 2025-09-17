package com.mycompany.app;
import com.mycompany.Tetris;

public class Clock extends Tetris {
    public int tick;

    public Clock(){
        
    }

    public int getTick() {
       return tick;
    }

    public void setTick(int tick) {
        this.tick = tick;
    }
    
    public void tick() {
       setTick(getTick() + 1);
    }

}
