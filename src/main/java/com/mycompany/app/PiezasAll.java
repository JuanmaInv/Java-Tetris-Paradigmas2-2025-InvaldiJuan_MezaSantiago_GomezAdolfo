package com.mycompany.app;

public abstract class PiezasAll {
    private final int ancho;
    private final int alto;
    private final String forma;
    //atributos de pieza
    //private para que solo la clase Pieza pueda acceder a ellos

    protected PiezasAll(int ancho, int alto, String forma) { //Constructor protegido, solo acceden subclases
        this.ancho = ancho;
        this.alto = alto;
        this.forma = forma;
    }

    //Getters para los atributos
    public String getForma() {
        return forma;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    //Metodos abstractos que se declaran aqui pero que deben ("si o si") implementar las subclases
    // Que hacen las piezas, pero no como lo hacen
    public abstract String rotar();
    public abstract String moverAbajo();
    public abstract String moverIzquierda();
    public abstract String moverDerecha();
    public abstract String moverArriba();
    
}
