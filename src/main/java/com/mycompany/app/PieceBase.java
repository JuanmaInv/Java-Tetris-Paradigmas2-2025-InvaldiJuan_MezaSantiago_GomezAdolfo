package com.mycompany.app; 

import java.util.Random; // Para seleccionar una rotacion aleatoria
// Las piezas solo deben encargarse de su forma y rotacion.
// No sabe sobre choques o limites del tablero

// Clase abstracta base para todas las piezas
public abstract class PieceBase implements IRotator {
    private int[][] forma; // Matriz que representa la forma de la pieza
    private Random random = new Random(); // Para rotación aleatoria y selección de forma
    // Nombre de la pieza (por defecto el nombre de la clase)
    private String name;

// Constructor
    public PieceBase (int[][] forma, String name) {// Recibe la forma inicial de la pieza
        this.forma = forma;// Asigna la forma inicial
        this.name = name; // Nombre por defecto es null, se asigna al pedirlo
    }

    // Nombre por defecto: nombre de la clase concreta
    public String getName() {
        if (this.name == null) {
            this.name = this.getClass().getSimpleName();
        }
        return this.name;
    }
    
//Getters y Setters
  // Getter para forma
    public int[][] getForma() { // Devuelve la forma actual de la pieza
        return forma; // Retorna la matriz que representa la forma de la pieza
    }

  // Setter para forma
    public void setForma(int[][] forma) { // Cambia la forma de la pieza
        this.forma = forma; // Actualiza la forma de la pieza
    }

    // No exponemos el Random directamente para evitar que código externo
    // manipule el estado interno. Subclases pueden usar nextBoolean().

  //Getter para random
    private Random getRandom() { // si no existe, crea uno nuevo
        if (this.random == null) {
            this.random = new Random();
        }
        return this.random; // Retorna el Random ya creado
    }

  // Getters para dimensiones de la pieza
    public int getAncho() { //consulta el ancho (columnas)
        return forma[0].length; // toma la primera fila y cuenta sus columnas
    }
    
    public int getAlto() {
// consulta el alto (filas) y se ocupa en Tetris.java para colocar
// la pieza en una columna aleatoria en la primer fila
        return forma.length;
    }

// Aleatorizaciones
  // obtener boolean aleatorio para decidir entre dos formas
    public boolean nextBoolean() {
        return getRandom().nextBoolean();
    }

       // obtener int aleatorio entre 0 y limite
       public int nextInt(int option_rotation) { //se utiliza para decidir entre 3 rotaciones
           return getRandom().nextInt(option_rotation);
    }

  // elegir entre dos formas según un boolean aleatorio
    public void elegirFormaSegunBoolean(int[][] formaA, int[][] formaB) { // lo que hace es elegir entre dos formas
        boolean elegirA = nextBoolean(); // obtiene un boolean aleatorio
        if (elegirA) { // si es true, elige la forma A
            setForma(formaA);
        } else { // si es false, elige la forma B
            setForma(formaB);
        }
    }

  // Verifica si la pieza creada está vacía (sin bloques)
    public boolean esVacia() { // Verifica si la pieza no tiene bloques
        for (int i = 0; i < forma.length; i++) { // Recorre filas
            for (int j = 0; j < forma[i].length; j++) { // Recorre columnas
                if (forma[i][j] != 0) { // Si encuentra un bloque
                    return false; // No está vacía, retorna false
                }
            }
        } // si no encontró bloques, está vacía
        return true;
    }
    
  // Forzar que cada subclase implemente su propia estrategia de aleatorización
    public abstract void aleatorizarForma();
 //metodo abstracto que obliga a las subclases a definir su propia aleatorizacion

  // ===== INTERFAZ ROTACION =====
    @Override
    public void rotateRight() {
        int filas = forma.length;
        int columnas = forma[0].length;
        int[][] nuevaForma = new int[columnas][filas];
// Nueva matriz con filas y columnas invertidas para la rotacion dado que al rotar, las filas se vuelven columnas y viceversa
        for (int i = 0; i < filas; i++) { // Recorre filas de pieza
            for (int j = 0; j < columnas; j++) { // Recorre columnas de pieza
                nuevaForma[j][filas - 1 - i] = forma[i][j]; // Rotación 90 grados a la derecha, la nueva fila es la antigua columna invertida
            }
        }
        setForma(nuevaForma); // Actualiza la forma de la pieza
    }

    @Override
    public void rotateLeft() {
        rotateRight();  // Rota 90 grados a la derecha
        rotateRight(); //  rota 90 grados a la derecha
        rotateRight(); //  rota 90 grados a la derecha
        // 3 veces rot a derecha = 1 rot a izquierda
    }

    @Override
    public void seleccionarLadoRandom() { // Decide aleatoriamente entre 3 opciones: rotar derecha, izquierda o no rotar
        int configuracion = nextInt(3);// 0=derecha, 1=izquierda, 2=no rota...se inicia el conteo desde 0 y tiene 3 opciones
        if (configuracion == 0) {// si es 0, rota a la derecha
            rotateRight();
        } else if (configuracion == 1) {// si es 1, rota a la izquierda
            rotateLeft();
        } else {
            // Si es 2, no rota y se mantiene con su forma original
        }
    }

}