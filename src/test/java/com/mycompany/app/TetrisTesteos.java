package com.mycompany.app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TetrisTesteos {
    
    //Creacion de piezas
    //testear si las piezas se crean o no ✅
    //testear si las piezas tienen la forma correcta ✅
    //testear si las piezas tienen la rotacion correcta ✅
    
    // Tests para verificar creación de piezas
    @Test
    public void testCrearPieceStick(){
        Piece piece = new PieceStick();
        assertTrue(piece != null);
    }

    @Test
    public void testCrearPieceSquare(){
        Piece piece = new PieceSquare();
        assertTrue(piece != null);
    }

    @Test
    public void testCrearPieceDog(){
        Piece piece = new PieceDog();
        assertTrue(piece != null);
        }

    @Test
    public void testCrearPieceL(){
        Piece piece = new PieceL();
        assertTrue(piece != null);
    }

    @Test
    public void testCrearPieceT(){
        Piece piece = new PieceT();
        assertTrue(piece != null); 
    }

    // Tests para verificar formas correctas
    @Test
    public void testFormasCorrectas() {
        // Verificar que las piezas tienen forma inicial correcta
        PieceSquare square = new PieceSquare();
        assertEquals(2, square.getAncho()); //verifica ancho
        assertEquals(2, square.getAlto()); //verifica alto
        assertFalse(square.esVacia()); // El cuadrado no está vacío
        
        PieceStick stick = new PieceStick();
        assertEquals(4, stick.getAncho()); //verifica ancho
        assertEquals(4, stick.getAlto()); //verifica alto
        assertFalse(stick.esVacia()); // El stick no está vacío

        PieceL l = new PieceL();
        assertEquals(3, l.getAncho()); //verifica ancho
        assertEquals(3, l.getAlto()); //verifica alto
        assertFalse(l.esVacia()); // La L no está vacía

        PieceT t = new PieceT();
        assertEquals(3, t.getAncho()); //verifica ancho
        assertEquals(3, t.getAlto()); //verifica alto
        assertFalse(t.esVacia()); // La T no está vacía

        PieceDog dog = new PieceDog();
        assertEquals(3, dog.getAncho()); //verifica ancho
        assertEquals(3, dog.getAlto()); //verifica alto
        assertFalse(dog.esVacia()); // El perro no está vacío
    }
    
    
    // Tests simples para verificar rotaciones correctas
    @Test
    public void testRotacionesSimples() {
        // Crear pieza y verificar que puede rotar sin errores
        PieceStick stick = new PieceStick();
        // Rotar derecha - verificar que la rotación se ejecuta sin errores
        stick.rotarDerecha();
        // Verificar que sigue teniendo una forma válida
        assertFalse(stick.esVacia()); // El stick no está vacío
        
        // Rotar izquierda
        PieceL piece2 = new PieceL(); // Nueva instancia para evitar acumulación de rotaciones
        piece2.rotarIzquierda(); // Rotar izquierda
        assertFalse(piece2.esVacia()); // La L no está vacía

        // El cuadrado debería mantenerse igual al rotar
        PieceSquare square = new PieceSquare();
        int anchoSquare = square.getAncho();
        int altoSquare = square.getAlto();
        square.rotarDerecha();
        assertEquals(anchoSquare, square.getAncho());
        assertEquals(altoSquare, square.getAlto());
    }

    // Tests para verificar creación de piezas    @Test
    public void testPieceLFormaCorrecta() {
        PieceL piece = new PieceL();
        int[][] formaEsperada = {
            {0, 1, 0},
            {0, 1, 0},
            {0, 1, 1}
        };
        
        // Verificar dimensiones
        assertEquals(3, piece.getAncho());
        assertEquals(3, piece.getAlto());
        
        // Verificar forma específica
        int[][] formaActual = piece.getForma();
        for (int i = 0; i < formaEsperada.length; i++) {
            for (int j = 0; j < formaEsperada[i].length; j++) {
                assertEquals(formaEsperada[i][j], formaActual[i][j]);
            }
        }
    }
    

    //testear rotaciones
    @Test
    public void testRotarPiecesDerecha(){
        Piece piece1 = new PieceL();
        Piece piece2 = new PieceT();
        Piece piece3 = new PieceSquare();
        Piece piece4 = new PieceDog();
        Piece piece5 = new PieceStick();
        // Rotar las piezas
        piece1.rotarDerecha();
        piece2.rotarDerecha();
        piece3.rotarDerecha();
        piece4.rotarDerecha();
        piece5.rotarDerecha();
        assertTrue(true); // Solo verifica que el método se ejecuta sin errores
    }

    @Test
    public void testRotarPiecesIzquierda(){
        Piece piece1 = new PieceL();
        Piece piece2 = new PieceT();
        Piece piece3 = new PieceSquare();
        Piece piece4 = new PieceDog();
        Piece piece5 = new PieceStick();
        // Rotar las piezas
        piece1.rotarIzquierda();
        piece2.rotarIzquierda();
        piece3.rotarIzquierda();
        piece4.rotarIzquierda();
        piece5.rotarIzquierda();
        assertTrue(true); // Solo verifica que el método se ejecuta sin errores
    }

    @Test
    public void testRotarPiecesLadoRandom(){
        Piece piece1 = new PieceL();
        Piece piece2 = new PieceT();
        Piece piece3 = new PieceSquare();
        Piece piece4 = new PieceDog();
        Piece piece5 = new PieceStick();
        // Rotar las piezas
        piece1.seleccionarLadoRandom();
        piece2.seleccionarLadoRandom();
        piece3.seleccionarLadoRandom();
        piece4.seleccionarLadoRandom();
        piece5.seleccionarLadoRandom();
        assertTrue(true); // Solo verifica que el método se ejecuta sin errores
    }

    // Test simple para verificar que el stick rota correctamente
    @Test
    public void testStickRotacion() {
        PieceStick stick = new PieceStick();
        
        // Guardar forma original
        int[][] formaOriginal = stick.getForma();
        
        // Rotar derecha
        stick.rotarDerecha();
        
        // Verificar que la forma cambió
        int[][] formaRotada = stick.getForma();
        boolean cambio = false;
        
        // Comparar si algún elemento cambió de posición
        for (int i = 0; i < formaOriginal.length && i < formaRotada.length; i++) {
            for (int j = 0; j < formaOriginal[i].length && j < formaRotada[i].length; j++) {
                if (formaOriginal[i][j] != formaRotada[i][j]) {
                    cambio = true;
                    break;
                }
            }
            if (cambio) break;
        }
        
        assertTrue("La forma del stick debería cambiar al rotar", cambio);
        assertFalse("El stick no debería estar vacío después de rotar", stick.esVacia());
    }
    
    @Test
        public void testPieceSquareRotacion() {
            PieceSquare piece = new PieceSquare();
        
            // Rotar y verificar que sigue siendo válido
            piece.rotarDerecha();
            assertFalse("El cuadrado no debería estar vacío después de rotar", piece.esVacia());
            assertEquals("El cuadrado debería mantener sus dimensiones", 2, piece.getAncho());
            assertEquals("El cuadrado debería mantener sus dimensiones", 2, piece.getAlto());
        }
    
    @Test
    public void rotar_PieceSquare_360_derecha_test() {
        PieceSquare pS1 = new PieceSquare();
        PieceSquare pS2 = new PieceSquare();
        pS1.rotarDerecha();
        pS1.rotarDerecha();
        pS1.rotarDerecha();
        pS1.rotarDerecha();
            for (int i = 0; i < pS1.getForma().length; i++) {// recorre filas
                assertArrayEquals(pS2.getForma()[i], pS1.getForma()[i]);// compara fila por fila
            }
        assertTrue(true); // Solo verifica que el método se ejecuta sin errores
    }

    @Test
    public void rotar_PieceL_360_derecha_test() {
        PieceL l1 = new PieceL();
        PieceL l2 = new PieceL();

        l1.rotarDerecha();
        l1.rotarDerecha();
        l1.rotarDerecha();
        l1.rotarDerecha();

            for (int i = 0; i < l1.getForma().length; i++) {
                assertArrayEquals(l2.getForma()[i], l1.getForma()[i]);
            }
        assertTrue(true); // Solo verifica que el método se ejecuta sin errores
    }

    //Creacion y estado del juego
    //testear si el juego inicia/termina o no ✅
    @Test
    public void testJuegoInicia(){
        Tetris game = new Tetris();
        game.iniciarJuego();
       assertEquals(game.getEstado(), 1);
    }
    
    @Test
    public void testJuegoTermina(){
        Tetris game = new Tetris();
        game.iniciarJuego();
        assertEquals(game.getEstado(), 1);
        game.terminarJuego();
        assertEquals(game.getEstado(), 2);
    }
    
    @Test
    public void testReiniciarJuego(){
        Tetris game = new Tetris();
        game.iniciarJuego();
        assertEquals(game.getEstado(), 1);

        game.reiniciarJuego();
        // Verificar que está iniciado y terminado
        assertTrue(game.getEstado() == 0);

    }
    
    //Creacion y estado del tablero
    //testear si el tablero esta lleno o vacio ✅
    //testear si una pieza puede moverse o no en el tablero ✅
    //testear si una pieza puede rotar o no en el tablero
    //testear si una pieza puede caer al vacio o no en el tablero ✅
    //testear si una linea puede ser eliminada o no en el tablero
    //testear en casos de limites del tablero y colisiones/choques de piezas
    //testear si una pieza puede ser colocada o no en el tablero
    @Test
    public void testTableroVacio() {
        Board tablero = new Board();
        assertTrue(tablero.tableroVacio());
    }

    
    @Test
    public void testTableroLleno(){
        Board tablero = new Board();
        assertFalse(!tablero.tableroVacio());
    }


    @Test
    public void testMoverPiezaEnTablero() {
        Board tablero = new Board();
        Piece piece = new PieceSquare();
        tablero.setPiezaActual(piece);
        tablero.colocarPiezaEnTableroVerificada(piece, 0, 0); // Coloca la pieza en la posición inicial
        // Mover la pieza a la derecha e izquierda
        tablero.moverPieza(piece, 0, 1); // derecha
        tablero.moverPieza(piece, 0, -1); // izquierda
        assertTrue(true); // Solo verifica que los métodos se ejecutan sin error
    }

    @Test
    public void testPiezaCaidaLibre(){
        Board tablero = new Board(); // creo tablero 
        Piece piece = new PieceSquare(); // creo pieza
        tablero.setPiezaActual(piece); // le digo a el tablero cual es la pieza actual
        tablero.colocarPiezaEnTableroVerificada(piece, 0, 0); // coloco la pieza en la posicion inicial
        // Simula la caída libre de la pieza hasta el fondo
        for (int i = 0; i < 10; i++) { // asumo que 10 es suficiente para llegar al fondo
            tablero.caidaLibre(piece); // hace caer la pieza
        }
        // Verifica que la pieza esta en la última fila posible osea que no se salió del tablero
        assertTrue(tablero.getFilaActual() >= 0);
        assertTrue(tablero.getColumnaActual() >= 0);
    }

    @Test
    public void testPiezaDogCaidaLibre(){
        Board tablero = new Board(); // creo tablero
        Piece piece = new PieceDog(); // creo pieza Dog
        tablero.setPiezaActual(piece); // le digo al tablero cuál es la pieza actual
        tablero.colocarPiezaEnTableroVerificada(piece, 0, 0); // coloco la pieza en la posición inicial
        // Simula la caída libre de la pieza hasta el fondo
        for (int i = 0; i < 10; i++) { // asumo que 10 es suficiente para llegar al fondo
            tablero.caidaLibre(piece); // hace caer la pieza
        }
        // Verifica que la pieza está en la última fila posible o sea que no se salió del tablero
        assertTrue(tablero.getFilaActual() >= 0);
        assertTrue(tablero.getColumnaActual() >= 0);
    }

}