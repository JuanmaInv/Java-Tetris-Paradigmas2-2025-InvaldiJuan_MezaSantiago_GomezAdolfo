package com.mycompany.app; 

import java.util.Random; // Para seleccionar una rotacion aleatoria
// Las piezas solo deben encargarse de su forma y rotacion.
// No sabe sobre choques o limites del tablero

public abstract class PiezasAbsAll implements InterfazRotacion, InterfazMovimiento { // Clase abstracta que implementa las dos interfaces (TIPO//atributo)
    private int [][] forma; // Matriz que representa la forma de la pieza (os y 1s)
    private int x, y; // Coordenadas de la posicion en el tablero (fila,columna)
    private String nombre; // Nombre de la pieza (T, L, J, S, Z, Cuadrado, Barra, etc)
    private Random random = new Random(); // Para rotacion aleatoria

    public PiezasAbsAll(String nombre) { // Constructor de la pieza con su nombre y posicion inicial (0,0)
        this.nombre = nombre; // Nombre de la pieza
        this.x = 0; // Fila inicial
        this.y = 0; // Columna inicial
    }

    // ===== GETTERS =====
    public int[][] getForma() { // Devuelve la forma de la pieza
        return forma;
    }

    public String getNombre() { // Devuelve el nombre de la pieza
        return nombre;
    }
    public int getX() { // Devuelve la fila (x)
        return x;
    }
    public int getY() { // Devuelve la columna (y)
        return y;
    }


    // ===== SETTERS =====
    public void setPosicion(int x, int y) { // Establece la posicion de la pieza
        this.x = x; // Fila
        this.y = y; // Columna
    }


    // ===== INTERFAZ ROTACION =====
    @Override // Rotacion a la derecha
    //pq lee numero de fila y columnas y crea una nueva matriz? 
    // 
    public void rotarDerecha() { // Rotacion 90 grados a la derecha
    int filas = forma.length; // Numero de filas
    int columnas = forma[0].length; // Numero de columnas
    int[][] nuevaForma = new int[columnas][filas]; // Nueva matriz con filas y columnas intercambiadas

    for (int i = 0; i < filas; i++) { // Recorro filas
        for (int j = 0; j < columnas; j++) { // Recorro columnas
            nuevaForma[j][filas - 1 - i] = forma[i][j]; // Asigno valores rotados
        }
    }
    forma = nuevaForma; // Actualizo la forma de la pieza
    }

    @Override // Rotacion a la izquierda
    public void rotarIzquierda() { // Rotacion 90 grados a la izquierda o lo que es lo mismo... 3 rotaciones a la derecha
                                   // 3 rotaciones a la derecha = 1 rotacion izquierda
    rotarDerecha(); // Primera rotacion derecha
    rotarDerecha(); // Segunda rotacion derecha
    rotarDerecha(); // Tercera rotacion derecha
    }
    
    @Override
    public void seleccionarLadoRandom() { // Selecciona una rotacion aleatoria
        int configuracion = random.nextInt(3); // Genera un numero aleatorio entre 0 y 2
        if (configuracion == 0) { // Si es 0, rota a la derecha
            rotarDerecha();
        } else if (configuracion == 1) { // Si es 1, rota a la izquierda
            rotarIzquierda();
        }
    // Si es 2, no hace nada (mantiene forma original)
    }
    

    // ===== INTERFAZ MOVIMIENTO =====
    @Override // Movimiento a la izquierda
    public void moverIzquierda() { // Movimiento a la izquierda
        y--; // Decrementa (disminuye) la columna (y) para moverse a la izquierda
    } 
    
    @Override 
    public void moverDerecha() { // Movimiento a la derecha
        y++; // Incrementa (aumenta) la columna (y) para moverse a la derecha
    }
    
    @Override
    public void moverAbajo() {
        x++; // Incrementa (aumenta) la fila (x) para moverse hacia abajo
    }

}