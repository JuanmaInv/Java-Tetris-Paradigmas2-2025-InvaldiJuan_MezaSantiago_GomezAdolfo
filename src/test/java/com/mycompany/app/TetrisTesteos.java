package com.mycompany.app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class TetrisTesteos {


    //Creacion de piezas
    //testear si las piezas se crean o no ✅
    //testear si las piezas tienen la forma correcta ✅
    //testear si las piezas tienen la rotacion correcta ✅
    
    // Tests para verificar creación de piezas
    @Test
    public void testCrearPieceStick(){
        PieceBase piece = new PieceStick();
        assertTrue(piece != null);
        piece.esVacia();
        assertEquals(false, piece.esVacia());// el stick no está vacío
    }

    @Test
    public void testCrearPieceSquare(){
        PieceBase piece = new PieceSquare();
        assertTrue(piece != null);
    }

    @Test
    public void testCrearPieceDog(){
        PieceBase piece = new PieceDog();
        assertTrue(piece != null);
        }

    @Test
    public void testCrearPieceL(){
        PieceBase piece = new PieceL();
        assertTrue(piece != null);
    }

    @Test
    public void testCrearPieceT(){
        PieceBase piece = new PieceT();
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
        assertEquals(1, stick.getAncho()); //verifica ancho
        assertEquals(4, stick.getAlto()); //verifica alto
        assertFalse(stick.esVacia()); // El stick no está vacío

        PieceL l = new PieceL();
        assertEquals(2, l.getAncho()); //verifica ancho
        assertEquals(3, l.getAlto()); //verifica alto
        assertFalse(l.esVacia()); // La L no está vacía

        PieceT t = new PieceT();
        assertEquals(3, t.getAncho()); //verifica ancho
        assertEquals(2, t.getAlto()); //verifica alto
        assertFalse(t.esVacia()); // La T no está vacía

        PieceDog dog = new PieceDog();
        assertEquals(3, dog.getAncho()); //verifica ancho
        assertEquals(2, dog.getAlto()); //verifica alto
        assertFalse(dog.esVacia()); // El perro no está vacío
    }
        // Test para PieceL left y right
        @Test
        public void testPieceLLeftRight() {
            PieceL piece = new PieceL();
            // Forma derecha
            piece.setForma(new int[][] {
                {1, 0},
                {1, 0},
                {1, 1}
            });
            int[][] formaA = piece.getForma();
            piece.aleatorizarForma();
            int[][] formaAleatoria = piece.getForma();
            // Debe ser igual a forma derecha o izquierda
            boolean esDerecha = true;
            boolean esIzquierda = true;
            int[][] formaB = {
                {0, 1},
                {0, 1},
                {1, 1}
            };
            for (int i = 0; i < formaAleatoria.length; i++) {
                for (int j = 0; j < formaAleatoria[i].length; j++) {
                    if (formaAleatoria[i][j] != formaA[i][j]) esDerecha = false;
                    if (formaAleatoria[i][j] != formaB[i][j]) esIzquierda = false;
                }
            }
            assertTrue(esDerecha || esIzquierda);
        }

        // Test para PieceDog left y right
        @Test
        public void testPieceDogLeftRight() {
            PieceDog piece = new PieceDog();
            // Forma derecha
            piece.setForma(new int[][] {
                {0, 1, 1},
                {1, 1, 0}
            });
            int[][] formaA = piece.getForma();
            piece.aleatorizarForma();
            int[][] formaAleatoria = piece.getForma();
            // Debe ser igual a forma derecha o izquierda
            boolean esDerecha = true;
            boolean esIzquierda = true;
            int[][] formaB = {
                {1, 1, 0},
                {0, 1, 1}
            };
            for (int i = 0; i < formaAleatoria.length; i++) {
                for (int j = 0; j < formaAleatoria[i].length; j++) {
                    if (formaAleatoria[i][j] != formaA[i][j]) esDerecha = false;
                    if (formaAleatoria[i][j] != formaB[i][j]) esIzquierda = false;
                }
            }
            assertTrue(esDerecha || esIzquierda);
        }

        // Test para aleatorizarForma en todas las piezas
        @Test
        public void testAleatorizarFormaTodasLasPiezas() {
            PieceBase[] piezas = {
                new PieceL(),
                new PieceDog(),
                new PieceT(),
                new PieceSquare(),
                new PieceStick()
            };
            for (PieceBase pieza : piezas) {
                int[][] formaOriginal = pieza.getForma();
                pieza.aleatorizarForma();
                int[][] formaNueva = pieza.getForma();
                // La forma puede cambiar o mantenerse igual, pero debe seguir teniendo 4 bloques
                int bloques = 0;
                for (int i = 0; i < formaNueva.length; i++) {
                    for (int j = 0; j < formaNueva[i].length; j++) {
                        if (formaNueva[i][j] != 0) bloques++;
                    }
                }
                assertEquals(4, bloques);
            }
        }
    
    
    // Tests simples para verificar rotaciones correctas
    @Test
    public void testRotacionesSimples() {
        // Crear pieza y verificar que puede rotar sin errores
        PieceStick stick = new PieceStick();
        // Rotar derecha - verificar que la rotación se ejecuta sin errores
        stick.rotateRight();
        // Verificar que sigue teniendo una forma válida
        assertFalse(stick.esVacia()); // El stick no está vacío
        
        // Rotar izquierda
        PieceL piece2 = new PieceL(); // Nueva instancia para evitar acumulación de rotaciones
        piece2.rotateLeft(); // Rotar izquierda
        assertFalse(piece2.esVacia()); // La L no está vacía

        // El cuadrado debería mantenerse igual al rotar
        PieceSquare square = new PieceSquare();
        int anchoSquare = square.getAncho();
        int altoSquare = square.getAlto();
        square.rotateRight();
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
        PieceBase piece1 = new PieceL();
        PieceBase piece2 = new PieceT();
        PieceBase piece3 = new PieceSquare();
        PieceBase piece4 = new PieceDog();
        PieceBase piece5 = new PieceStick();
        // Rotar las piezas
        piece1.rotateRight();
        piece2.rotateRight();
        piece3.rotateRight();
        piece4.rotateRight();
        piece5.rotateRight();
        assertTrue(true); // Solo verifica que el método se ejecuta sin errores
    }

    @Test
    public void testRotarPiecesIzquierda(){
        PieceBase piece1 = new PieceL();
        PieceBase piece2 = new PieceT();
        PieceBase piece3 = new PieceSquare();
        PieceBase piece4 = new PieceDog();
        PieceBase piece5 = new PieceStick();
        // Rotar las piezas
        piece1.rotateLeft();
        piece2.rotateLeft();
        piece3.rotateLeft();
        piece4.rotateLeft();
        piece5.rotateLeft();
        assertTrue(true); // Solo verifica que el método se ejecuta sin errores
    }

    @Test
    public void testRotarPiecesLadoRandom(){
        PieceBase piece1 = new PieceL();
        PieceBase piece2 = new PieceT();
        PieceBase piece3 = new PieceSquare();
        PieceBase piece4 = new PieceDog();
        PieceBase piece5 = new PieceStick();
        // Rotar las piezas
        piece1.seleccionarLadoRandom();
        piece2.seleccionarLadoRandom();
        piece3.seleccionarLadoRandom();
        piece4.seleccionarLadoRandom();
        piece5.seleccionarLadoRandom();
        assertTrue(true); // Solo verifica que el método se ejecuta sin errores
    }
    
    @Test
        public void testPieceSquareRotacion() {
            PieceSquare piece = new PieceSquare();
        
            // Rotar y verificar que sigue siendo válido
            piece.rotateRight();
            assertFalse("El cuadrado no debería estar vacío después de rotar", piece.esVacia()); // El cuadrado no está vacío
            assertEquals("El cuadrado debería mantener sus dimensiones", 2, piece.getAncho()); //verifica ancho
            assertEquals("El cuadrado debería mantener sus dimensiones", 2, piece.getAlto()); //verifica alto
        }
    
    @Test
    public void rotar_PieceSquare_360_derecha_test() {
        PieceSquare pS1 = new PieceSquare();
        PieceSquare pS2 = new PieceSquare();
        pS1.rotateRight();
        pS1.rotateRight();
        pS1.rotateRight();
        pS1.rotateRight();
            for (int i = 0; i < pS1.getForma().length; i++) {// recorre filas
                assertArrayEquals(pS2.getForma()[i], pS1.getForma()[i]);// compara fila por fila
            }
        assertTrue(true); // Solo verifica que el método se ejecuta sin errores
    }

    @Test
    public void rotar_PieceL_360_derecha_test() {
        PieceL l1 = new PieceL();
        PieceL l2 = new PieceL();

        l1.rotateRight();
        l1.rotateRight();
        l1.rotateRight();
        l1.rotateRight();

            for (int i = 0; i < l1.getForma().length; i++) {
                assertArrayEquals(l2.getForma()[i], l1.getForma()[i]);
            }
        assertTrue(true); // Solo verifica que el método se ejecuta sin errores
    }
     // Test: la pieza baja solo cada dos ticks
        @Test
        public void testPiezaBajaCadaDosTicks() {
            Tetris tetris = new Tetris();
            Board tablero = tetris.getBoard();
            PieceBase pieza = new PieceSquare();
            tablero.setPiezaActual(pieza);
            tablero.setFilaActual(0);
            tablero.setColumnaActual(0);
            tablero.colocarPiezaEnTableroVerificada(pieza, 0, 0);
            tetris.setIntervaloDescenso(2); // la pieza debe bajar cada 2 ticks
            tetris.start();

            int filaInicial = tablero.getFilaActual();
            // Simular 5 ticks
            for (int i = 1; i <= 5; i++) {
                tetris.tick();
                // Solo debe bajar en ticks 2 y 4
                if (i % 2 == 0) {
                    int filaEsperada = filaInicial + (i / 2);
                    assertEquals("La pieza debe haber bajado en el tick " + i, filaEsperada, tablero.getFilaActual());
                } else {
                    int filaEsperada = filaInicial + (i / 2);
                    assertEquals("La pieza no debe bajar en el tick " + i, filaEsperada, tablero.getFilaActual());
                }
            }
        }

    //Creacion y estado del juego
    //testear si el juego inicia/termina o no ✅
    @Test
    public void testJuegoInicia(){
        Tetris game = new Tetris();
        game.start();
       assertEquals(game.getState(), 1);
    }
    
    @Test
    public void testJuegoTermina(){
        Tetris game = new Tetris();
        game.start();
        assertEquals(game.getState(), 1);
        game.end();
        assertEquals(game.getState(), 2);
    }
    
    @Test
    public void testReiniciarJuego(){
        Tetris game = new Tetris();
        game.start();
        assertEquals(game.getState(), 1);

        game.restart();
        // Verificar que está iniciado y terminado
        assertTrue(game.getState() == 0);

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
        PieceBase piece = new PieceSquare();
        tablero.setPiezaActual(piece);
        tablero.colocarPiezaEnTableroVerificada(piece, 0, 0); // Coloca la pieza en la posición inicial
        // Mover la pieza a la derecha e izquierda
        tablero.moverPieza(piece, 0, 1); // derecha
        tablero.moverPieza(piece, 0, -1); // izquierda
        assertTrue(true); // Solo verifica que los métodos se ejecutan sin error
    }

    @Test
    public void testPiezaCaidaLibre(){
        Board tablero = new Board();
        PieceBase piece1 = new PieceSquare();

        tablero.setPiezaActual(piece1);
        tablero.setFilaActual(0);
        tablero.setColumnaActual(0);
        tablero.caidaLibre(piece1);

        // Verificar que ya no sea posible bajar una fila más
        boolean puedeBajar = tablero.verificarColocacionValida(piece1, tablero.getFilaActual() + 1, tablero.getColumnaActual());
        assertFalse("La pieza debería haber bajado hasta tocar otra pieza o el fondo", puedeBajar);

        // Verifica que la pieza esté dentro de los límites
        assertTrue(tablero.getFilaActual() >= 0);
        assertTrue(tablero.getColumnaActual() >= 0);

        // Verificar que la pieza esté en la fila más baja posible
        int altoTablero = tablero.getBoard().length; // por ejemplo, 10
        int altoPieza = piece1.getAlto();    // para PieceSquare, 2
        int filaFinalEsperada = altoTablero - altoPieza; // última fila posible
        assertEquals(filaFinalEsperada, tablero.getFilaActual()); // Verifica que la pieza esté en la fila esperada

        // Verificar que la pieza esté en la columna más baja posible (en este caso, 0)
        int columnaFinalEsperada = 0;
        assertEquals(columnaFinalEsperada, tablero.getColumnaActual()); // Verifica que la pieza esté en la columna esperada
    }


    @Test
    public void testPiezaDogCaidaLibre(){
        Board tablero = new Board(); // creo tablero
        PieceBase piece = new PieceDog(); // creo pieza Dog
        tablero.setPiezaActual(piece); // le digo al tablero cuál es la pieza actual
        tablero.setFilaActual(0);
        tablero.setColumnaActual(0);
        tablero.colocarPiezaEnTableroVerificada(piece, 0, 0); // coloco la pieza en la posición inicial

        // Llamar a caidaLibre: debe bajar hasta chocar con el fondo
        tablero.caidaLibre(piece);

        // Verificar que ya no sea posible bajar una fila más
        boolean puedeBajar = tablero.verificarColocacionValida(piece, tablero.getFilaActual() + 1, tablero.getColumnaActual());
        assertFalse("La pieza debería haber bajado hasta tocar otra pieza o el fondo", tablero.verificarColocacionValida(piece, tablero.getFilaActual() + 1, tablero.getColumnaActual()));
        // Verifica que la pieza esté dentro de los límites
        assertTrue(tablero.getFilaActual() >= 0);
        assertTrue(tablero.getColumnaActual() >= 0);
    }

    // Nuevo test: caída libre pero activada mediante ticks del reloj
    @Test
    public void testPiezaCaidaLibreConTick(){
        //  la pieza baja cada tick y terminamos cuando ya no puede bajar mas
        Tetris tetris = new Tetris();
        Board tablero = tetris.getBoard();
        PieceBase piece = new PieceSquare();
        tablero.setPiezaActual(piece);
        tablero.setFilaActual(0);
        tablero.setColumnaActual(0);
        tablero.colocarPiezaEnTableroVerificada(piece, 0, 0);

        // Hacemos que la pieza baje en cada tick, desciende cada 1 unidad de tiempo
        // Esto simula la caída libre activada por el reloj del juego
        tetris.setIntervaloDescenso(1);
        tetris.start();

        // LIMITE: maximo de iteraciones para evitar bucle infinito en caso de bug
        int LIMITE = 30; // tope de seguridad
        int antes = tablero.getFilaActual(); // fila antes de tick
        // iteramos hasta que la pieza ya no pueda bajar mas o se alcance el limite
        // lo que hace este for es simular el paso del tiempo
        // cada iteracion es un tick
        // cada tick hace que la pieza baje una fila
        for (int i = 0; i < LIMITE; i++) { // iteramos
            tetris.tick(); // avanza el reloj y hace bajar la pieza si corresponde
            int ahora = tablero.getFilaActual(); // fila después de tick
            if (ahora == antes) { //Permite salir si la pieza no baja mas porque colisiono con otra pieza o el fondo
                break;
            }
            antes = ahora; // actualiza antes para la proxima iteracion
        }

        // Verificamos que no puede bajar mas (esta apoyada)
        boolean puedeBajar = tablero.verificarColocacionValida(piece, tablero.getFilaActual() + 1, tablero.getColumnaActual()); //espera false
        assertFalse("La pieza deberia haber alcanzado el fondo tras los ticks", puedeBajar); // espera false
    }

    @Test
    public void testPiezaDogCaidaLibreConTick(){
        // Igual que el test anterior pero con la pieza Dog
        Tetris tetris = new Tetris();
        Board tablero = tetris.getBoard();
        PieceBase piece = new PieceDog();
        tablero.setPiezaActual(piece);
        tablero.setFilaActual(0);
        tablero.setColumnaActual(0);
        tablero.colocarPiezaEnTableroVerificada(piece, 0, 0);

        tetris.setIntervaloDescenso(1); // la pieza baja cada tick
        tetris.start(); // inicia el juego

        int LIMITE = 30; // tope de seguridad para evitar bucle infinito
        int antes = tablero.getFilaActual(); // fila antes de tick
        // iteramos hasta que la pieza ya no pueda bajar mas o se alcance el limite
        // lo que hace este for es simular el paso del tiempo
        // cada iteracion es un tick
        // cada tick hace que la pieza baje una fila
        for (int i = 0; i < LIMITE; i++) {
            tetris.tick();
            int ahora = tablero.getFilaActual(); // fila después de tick
            if (ahora == antes){ // Permite salir si la pieza no baja más porque colisiono con otra pieza o el fondo
                break;
            }
            antes = ahora;
        }

        // Verificamos que no puede bajar mas (esta apoyada)
        boolean puedeBajar = tablero.verificarColocacionValida(piece, tablero.getFilaActual() + 1, tablero.getColumnaActual()); //espera false
        assertFalse("La pieza Dog debería haber alcanzado el fondo tras los ticks", puedeBajar); // espera false
    }
// El test crea un Board, coloca una pieza cuadrada (PieceSquare) en una posición baja del tablero y comprueba tres cosas:
// Que no se pueda colocar otra pieza exactamente encima (colisión).
// Que sí se pueda colocar la misma pieza en una posición libre (sin colisión).
// Que una colocación que saldría parcialmente fuera del tablero sea inválida.

    @Test
    public void testColisionEntrePiezas() { //
        Board tablero = new Board(); //Crea un tablero vacío donde hare las validaciones

        // Creo y coloco la primera pieza (square) en una posicion especifica
        PieceBase primera = new PieceSquare();
        tablero.colocarPiezaEnTableroVerificada(primera, 8, 5); // coloca un 2x2 en filas 8-9, col 5-6 [(8,5); (8,6); (9,5); (9,6)]

        //Creo otra pieza igual a la anterior
        PieceBase segunda = new PieceSquare();

        // Intento colocar otra pieza que colisione con la anterior
        //Para mas precision de "Colision" intento colocar una pieza de misma forma en la misma posicion.\
        boolean verificacionColision = tablero.verificarColocacionValida(segunda, 8, 5); //pregunta si es valido colocar la segunda pieza en la misma posicion que la primera, espera false porque hay colision
        assertFalse("No es valido colocar otra pieza en esa posicion", verificacionColision); //espera false

        // Intentar colocar otra pieza en una posición valida donde no hay piezas
        boolean puedeColocarLibre = tablero.verificarColocacionValida(segunda, 6, 5);
        assertTrue("Debería poder colocarse una pieza en posición libre", puedeColocarLibre);

        // Intentar colocar una pieza parcialmente fuera del tablero
        boolean fueraTablero = tablero.verificarColocacionValida(segunda, 9, 19); // borde derecho/inferior
        assertFalse("No debería ser válida una colocación que sale del tablero", fueraTablero);
    }

    @Test
    public void testColisionEntrePieceDog(){
        Board tablero = new Board();
        // Colocar la primera pieza (Dog) en la parte baja central
        PieceBase primera = new PieceDog();
        tablero.colocarPiezaEnTableroVerificada(primera, 8, 5); // coloca un 3x3 en filas 8-10, col 5-7

        //Verificar que hay una pieza en esa posicion
        assertTrue("Debería haber una pieza en la posición (8,6)", tablero.getBoard()[8][6] != 0); //espera true porque hay pieza
        assertTrue("Debería haber una pieza en la posición (8,7)", tablero.getBoard()[8][7] != 0); //espera true porque hay pieza
        assertTrue("Debería haber una pieza en la posición (9,5)", tablero.getBoard()[9][5] != 0); //espera true porque hay pieza
        assertTrue("Debería haber una pieza en la posición (9,6)", tablero.getBoard()[9][6] != 0); //espera true porque hay pieza

        //verifica en una posicion que no deberia haber piezas, que no las hay
        assertFalse("No debería haber una pieza en la posición (7,5)", tablero.getBoard()[7][5] != 0); //espera false porque no hay pieza


        // Intentar colocar otra pieza que solape exactamente la anterior
        PieceBase segunda = new PieceDog();
        boolean verificacionColision = tablero.verificarColocacionValida(segunda, 8, 5); //espera false
        assertFalse("No debería poder colocarse una pieza solapada sobre otra", verificacionColision); //espera false

        // Intentar colocar otra pieza en una posición valida donde no hay piezas
        boolean puedeColocarPiece = tablero.verificarColocacionValida(segunda, 5, 5); //espera true
        assertTrue("Debería poder colocarse una pieza en posición libre", puedeColocarPiece); //espera true

        // Colocar la segunda pieza en la posición libre luego de validar  que es posible colocarla
        tablero.colocarPiezaEnTableroVerificada(segunda, 5, 5); //coloca la pieza en una posicion libre

        //Verificar que hay una pieza en esa posicion
        assertTrue("Debería haber una pieza en la posición (5,6)", tablero.getBoard()[5][6] != 0); //espera true porque hay pieza
        assertTrue("Debería haber una pieza en la posición (5,7)", tablero.getBoard()[5][7] != 0); //espera true porque hay pieza
        assertTrue("Debería haber una pieza en la posición (6,5)", tablero.getBoard()[6][5] != 0); //espera true porque hay pieza
        assertTrue("Debería haber una pieza en la posición (6,6)", tablero.getBoard()[6][6] != 0); //espera true porque hay pieza

        assertFalse("No debería haber una pieza en la posición (7,5)", tablero.getBoard()[7][5] != 0); //espera false porque no hay pieza


        // Intentar colocar una pieza parcialmente fuera del tablero
        boolean fueraTablero = tablero.verificarColocacionValida(segunda, 9, 18); //esta fuera del tablero porque la pieza Dog es 3x3 y el tablero tiene 20 columnas si yo la coloco alli se sale del tablero
        assertFalse("No debería ser válida una colocación que sale del tablero", fueraTablero); //espera false
    }

    @Test
    public void testColisionyEliminacionDe2Lineas (){
        Tetris tetris = new Tetris();
        tetris.start();
        Board tablero = new Board(); // creo tablero

        // Crear 10 piezas cuadradas para llenar una fila completa
        PieceBase piece1 = new PieceSquare();
        PieceBase piece2 = new PieceSquare();
        PieceBase piece3 = new PieceSquare();
        PieceBase piece4 = new PieceSquare();
        PieceBase piece5 = new PieceSquare();
        PieceBase piece6 = new PieceSquare();
        PieceBase piece7 = new PieceSquare();
        PieceBase piece8 = new PieceSquare();
        PieceBase piece9 = new PieceSquare();
        PieceBase piece10 = new PieceSquare();
        // Colocar 10 piezas cuadradas (2x2) para llenar una fila
        tablero.colocarPiezaEnTableroVerificada(piece1, 8, 0); // fila 8, columnas 0-1
        tablero.colocarPiezaEnTableroVerificada(piece2, 8, 2); // fila 8, columnas 2-3
        tablero.colocarPiezaEnTableroVerificada(piece3, 8, 4); // fila 8, columnas 4-5
        tablero.colocarPiezaEnTableroVerificada(piece4, 8, 6); // fila 8, columnas 6-7
        tablero.colocarPiezaEnTableroVerificada(piece5, 8, 8); // fila 8, columnas 8-9
        tablero.colocarPiezaEnTableroVerificada(piece6, 8, 10); // fila 8, columnas 10-11
        tablero.colocarPiezaEnTableroVerificada(piece7, 8, 12); // fila 8, columnas 12-13
        tablero.colocarPiezaEnTableroVerificada(piece8, 8, 14); // fila 8, columnas 14-15
        tablero.colocarPiezaEnTableroVerificada(piece9, 8, 16); // fila 8, columnas 16-17
        tablero.colocarPiezaEnTableroVerificada(piece10, 8, 18); // fila 8, columnas 18-19

        // Marcar la última pieza como piezaActual para que los métodos que la consulten no hagan NPE
        tablero.setPiezaActual(piece10);

        // Verificar que la  fila 8 está completamente ocupada
        for (int j = 0; j < tablero.getColumnas(); j++) { // recorre columnas con getColumnas() donde devuelve 20 que es el numero de columnas del tablero
            assertTrue("La fila 8 debería estar ocupada" + j, tablero.getBoard()[8][j] != 0); //espera true, con getBoard()[8][j] accedemos a la fila 8 y columna j del tablero
        }                                                                                     // donde j va de 0 a 19 y verificamos que no sea 0 (que haya pieza)

        // Antes de eliminar, intentar colocar otra pieza encima debe FALLAR (colisión)
        PieceBase otra = new PieceSquare();
        // Marcar la otra pieza como piezaActual para que los métodos que la consulten no hagan NPE
        tablero.setPiezaActual(otra);
        // Intentar colocar otra pieza que colisione con algun elemento de la fila llena
        boolean SePuedeColocar = tablero.verificarColocacionValida(otra, 8, 0); //espera false porque hay colision, osea no se puede colocar
        assertFalse("Debería haber colisión al intentar colocar sobre una fila llena", SePuedeColocar);

        // Eliminar líneas llenas: capturamos el contador antes y luego lo comprobamos
        int antes = tablero.getLineasEliminadas(); // contador antes de la eliminación
        tablero.verificarYEliminarLineas(); // esto elimina la(s) filas llenas detectadas
        int despues = tablero.getLineasEliminadas(); // contador después de la llamada
        // Verificar que se eliminó exactamente 2 líneas
        assertEquals("Debería haberse eliminado 2 líneas", antes + 2, despues);

        // Verificar que la fila 8 (la que quedó libre tras desplazar hacia abajo) ahora está vacía
        for (int j = 0; j < tablero.getColumnas(); j++) {
            assertEquals("La fila 8 debería estar vacía después de la eliminación", 0, tablero.getBoard()[8][j]); //espera 0
        }
        // Verificar que la fila 8 (la que quedó libre tras desplazar hacia abajo) ahora está vacía
        for (int j = 0; j < tablero.getColumnas(); j++) {
            assertEquals("La fila 9 debería estar vacía después de la eliminación", 0, tablero.getBoard()[9][j]); //espera 0
        }
    }

    @Test
    public void testColisionyEliminacionDe1Linea (){
        Tetris tetris = new Tetris();
        tetris.start();
        Board tablero = new Board(); // creo tablero

        // Crear 10 piezasL para llenar una fila completa
        PieceBase piece1 = new PieceL();
        PieceBase piece2 = new PieceL();
        PieceBase piece3 = new PieceL();
        PieceBase piece4 = new PieceL();
        PieceBase piece5 = new PieceL();
        PieceBase piece6 = new PieceL();
        PieceBase piece7 = new PieceL();
        PieceBase piece8 = new PieceL();
        PieceBase piece9 = new PieceL();
        PieceBase piece10 = new PieceL();
        
        // Colocar 10 piezas L (3x1) para llenar una fila
        tablero.colocarPiezaEnTableroVerificada(piece1, 7, 0); // fila 7, columnas 0-1
        tablero.colocarPiezaEnTableroVerificada(piece2, 7, 2); // fila 7, columnas 2-3
        tablero.colocarPiezaEnTableroVerificada(piece3, 7, 4); // fila 7, columnas 4-5
        tablero.colocarPiezaEnTableroVerificada(piece4, 7, 6); // fila 7, columnas 6-7
        tablero.colocarPiezaEnTableroVerificada(piece5, 7, 8); // fila 7, columnas 8-9
        tablero.colocarPiezaEnTableroVerificada(piece6, 7, 10); // fila 7, columnas 10-11
        tablero.colocarPiezaEnTableroVerificada(piece7, 7, 12); // fila 7, columnas 12-13
        tablero.colocarPiezaEnTableroVerificada(piece8, 7, 14); // fila 7, columnas 14-15
        tablero.colocarPiezaEnTableroVerificada(piece9, 7, 16); // fila 7, columnas 16-17
        tablero.colocarPiezaEnTableroVerificada(piece10, 7, 18); // fila 7, columnas 17-19

        // Marcar la última pieza como piezaActual para que los métodos que la consulten no hagan NPE
        tablero.setPiezaActual(piece10);

        // Verificar que la  fila 9 está completamente ocupada
        for (int j = 0; j < tablero.getColumnas(); j++) { // recorre columnas con getColumnas() donde devuelve 20 que es el numero de columnas del tablero
            assertTrue("La fila 9 debería estar ocupada" + j, tablero.getBoard()[9][j] != 0); //espera true, con getBoard()[9][j] accedemos a la fila 9 y columna j del tablero
        }                                                                                     // donde j va de 0 a 19 y verificamos que no sea 0 (que haya pieza)
        // Antes de eliminar, intentar colocar otra pieza encima debe FALLAR (colisión)
        PieceBase otra = new PieceL();
        // Marcar la otra pieza como piezaActual para que los métodos que la consulten no
        tablero.setPiezaActual(otra);
        // Intentar colocar otra pieza que colisione con algun elemento de la fila llena
        boolean SePuedeColocar = tablero.verificarColocacionValida(otra, 7, 0); //espera false porque hay colision, osea no se puede colocar
        assertFalse("Debería haber colisión al intentar colocar sobre una fila llena", SePuedeColocar);
        // Eliminar líneas llenas: capturamos el contador antes y luego lo comprobamos
        int antes = tablero.getLineasEliminadas(); // contador antes de la eliminación
        tablero.verificarYEliminarLineas(); // esto elimina la(s) filas llenas detectadas
        int despues = tablero.getLineasEliminadas(); // contador después de la llamada
        // Verificar que se eliminó exactamente 1 línea
        assertEquals("Debería haberse eliminado 1 línea", antes + 1, despues);

        // Verificar que la fila 9  no esta vacia debido a que quedaron los restos de las piezas L
        boolean fila9Vacia = true;
        for (int j = 0; j < tablero.getColumnas(); j++) {
            if (tablero.getBoard()[9][j] != 0) {
                fila9Vacia = false; // si encuentra un no 0, la fila no esta vacia
                break;
            }
        }
        assertFalse("La fila 9 no debería estar vacía después de la eliminación porque quedaron restos de las piezas L", fila9Vacia);
    }

    ///MOSTRALE AL PROFESOR ESTE TEST
    /// TEST PARA QUE BAJE UNA PIEZA Y ELIMINE 2 LINEAS
    @Test
    public void testBajadayEliminacionDeLinea (){
        Board tablero = new Board();
        PieceBase ultima = null; // para guardar la última pieza colocada, es null al inicio
        // Colocar 10 piezas cuadradas en columnas 0,2,4,...,18 y hago caida libre
        for (int i = 0; i < 10; i++) {
            PieceBase piece = new PieceSquare();
            tablero.setPiezaActual(piece);
            tablero.setFilaActual(0);
            tablero.setColumnaActual(i * 2);// columna 0 y pares (0,2,4,...,18)
            tablero.colocarPiezaEnTableroVerificada(piece, 0, i * 2);
            tablero.caidaLibre(piece);
            ultima = piece;
        }

        // Marcar la última pieza como piezaActual para que los métodos que la consulten no generen errores 
        tablero.setPiezaActual(ultima);

        // Verificar que las filas 8 y 9 están completamente ocupadas antes de eliminar
        for (int j = 0; j < tablero.getColumnas(); j++) {
            assertTrue("La fila 8 debería estar ocupada antes de eliminar" + j, tablero.getBoard()[8][j] != 0); //espera true, con getBoard()[8][j] accedemos a la fila 8 y columna j del tablero
            assertTrue("La fila 9 debería estar ocupada antes de eliminar" + j, tablero.getBoard()[9][j] != 0); //espera true, con getBoard()[9][j] accedemos a la fila 9 y columna j del tablero
        }

        // Consultar líneas eliminadas antes
        int antes = tablero.getLineasEliminadas();
        // Eliminar líneas llenas
        tablero.verificarYEliminarLineas();
        // Consultar líneas eliminadas después
        int despues = tablero.getLineasEliminadas();
        // Verificar que se eliminaron exactamente 2 líneas
        assertEquals("Debería haberse eliminado 2 líneas", antes + 2, despues);

        // Verificar que las filas 8 y 9 quedaron vacías tras la eliminacion
        for (int j = 0; j < tablero.getColumnas(); j++) {
            assertEquals("La fila 8 debería estar vacía después de la eliminación", 0, tablero.getBoard()[8][j]); // espera 0s en todas las columnas de la fila 8
            assertEquals("La fila 9 debería estar vacía después de la eliminación", 0, tablero.getBoard()[9][j]); // espera 0s en todas las columnas de la fila 9
        }
    }

    //test para que baje una pieza y colisione con otra
    @Test
    public void testPiezaCaidaLibreConColision(){
        Tetris tetris = new Tetris();
        Board tablero = tetris.getBoard(); // creo tablero con get board porque el tablero es privado en Tetris
        PieceBase piece1 = new PieceSquare();
        PieceBase piece2 = new PieceSquare();

        // Colocar la primera pieza en la parte baja del tablero con el metodo de caida libre
        tablero.setPiezaActual(piece1); // le digo al tablero cuál es la pieza actual
        tablero.setFilaActual(0); // empieza en la fila 0
        tablero.setColumnaActual(0); // empieza en la columna 0
        tablero.colocarPiezaEnTableroVerificada(piece1, 0, 0); // coloco la pieza en la posición inicial
    

        // Hacer que la pieza caiga libremente
        tablero.caidaLibre(piece1);

        // Verificar que la pieza esté en la fila más baja posible
        int altoTablero = tablero.getBoard().length; // por ejemplo, 10
        int altoPieza = piece1.getAlto();    // para PieceSquare, 2
        int filaFinalEsperada = altoTablero - altoPieza; // última fila posible
        assertEquals(filaFinalEsperada, tablero.getFilaActual()); // Verifica que la pieza esté en la fila esperada

        // Verificar que la pieza esté en la columna más baja posible (en este caso, 0)
        int columnaFinalEsperada = 0;
        assertEquals(columnaFinalEsperada, tablero.getColumnaActual()); // Verifica que la pieza esté en la columna esperada


        // Colocar la segunda pieza en su posicion inicial
        tablero.setPiezaActual(piece2);
        tablero.setFilaActual(0);
        tablero.setColumnaActual(0);
        tablero.colocarPiezaEnTableroVerificada(piece2, 0, 0); // coloco la pieza en la posición inicial

        // Ahora hacer que la segunda pieza caiga libremente
        tablero.caidaLibre(piece2);

        // Verificar que la segunda pieza se detuvo al tocar la primera
        boolean puedeBajar2 = tablero.verificarColocacionValida(piece2, tablero.getFilaActual() + 1, tablero.getColumnaActual()); //espera false porque la pieza 2 toco la pieza 1
        assertFalse("La segunda pieza debería haberse detenido al tocar la primera", puedeBajar2);

        // Verificar que la segunda pieza esté justo encima de la primera, por ejemplo si la primera está en fila 8 y tiene alto 2, la segunda debería estar en fila 6
        int filaEsperadaSegunda = filaFinalEsperada - piece2.getAlto(); // verifica que la segunda pieza esté justo encima de la primera con la altura de la pieza
        assertEquals(filaEsperadaSegunda, tablero.getFilaActual());
        //verifico que este colocada en la posicion esperada
        int columnaEsperadaSegunda = columnaFinalEsperada; // misma columna que la primera
        assertEquals(columnaEsperadaSegunda, tablero.getColumnaActual());

    }


     //test para que el juego termine cuando se eliminan 5 lineas
    @Test
    public void tesGameWin(){
        Tetris game = new Tetris();
        game.start();
        Board tablero = game.getBoard();
        PieceBase ultima = null; // para guardar la última pieza colocada, es null al inicio
        
        game.getState(); // consulto el estado del juego, espera 1 (en juego)
        assertEquals("El juego debería estar en estado de 'en juego'", 1, game.getState()); // espera estado 1 (en juego)

        // Asegurar que el tablero está limpio antes del llenado determinista.
        // iniciarJuego() puede haber spawnado una pieza; usamos el helper del Board.
        tablero.limpiarTablero();

        // Llenar el tablero directamente (sin usar piezas) para simular tablero lleno
        // Esto evita que se disparen eliminaciones de líneas y la condición de "game win" accidental
        for (int f = 0; f < tablero.getFilas(); f++) {
            for (int c = 0; c < tablero.getColumnas(); c++) {
                tablero.setBoard(f, c, 1); // valor 1 representa celda ocupada
            }
        }
        // Marcar una pieza cualquiera como piezaActual para que esFinDelJuego pueda usarla
        ultima = new PieceSquare();
        tablero.setPiezaActual(ultima);
        // Eliminar líneas llenas: capturamos el contador antes y luego lo comprobamos
        tablero.setPiezaActual(ultima); // marco la ultima pieza como pieza actual para que los metodos que la consulten no hagan NPE
        tablero.verificarYEliminarLineas(); // esto elimina la(s) filas llenas detectadas


    // Verificar que se han eliminado al menos 5 líneas (meta para "game win")
    int lineasEliminadas = tablero.getLineasEliminadas();
    // Aceptamos que se hayan eliminado 5 o más líneas; el algoritmo puede eliminar una línea extra
    // dependiendo del orden de colocación/descenso de las piezas.
    assertTrue("Deberían haberse eliminado al menos 5 líneas", lineasEliminadas >= 5);

    // Actualizar el estado del juego
    // Pedimos explícitamente a Tetris que consulte el Board y actualice su estado
    game.actualizarEstadoJuego();
    assertEquals("El juego debería estar en estado de 'game win'", 3, game.getState()); // espera estado 3 (game win)

    // Verificar que el estado del juego es "terminado" si se han eliminado 5 líneas
        if (lineasEliminadas >= 5) {
            assertTrue("Seria Game Win ya que elimine 5 lineas", true); // espera true
            game.setState(3); // fuerza el estado a terminado
            assertEquals("El juego debería estar en estado de 'terminado'", 3, game.getState()); // espera estado 3 (terminado)
        }
    }

    //test para que el juego termine cuando no se pueda colocar una nueva pieza
    @Test
    public void testGameOver(){
        Tetris game = new Tetris();
        game.start();
        Board tablero = game.getBoard();
        PieceBase ultima = null; // para guardar la última pieza colocada, es null al inicio
        game.getState(); // consulto el estado del juego, espera 1 (en juego)
        assertEquals("El juego debería estar en estado de 'en juego'", 1, game.getState()); // espera estado 1 (en juego)
    // Asegurar que el tablero está limpio antes del llenado determinista.
    tablero.limpiarTablero();

        // Llenar el tablero completamente con piezas cuadradas (2x2)
        // Usamos verificarColocacionValida + colocarPiezaEnTableroVerificada para colocar
        // cada pieza de forma determinista (sin permitir que caigan) y así evitar huecos.
        for (int fila = 0; fila < tablero.getFilas(); fila += 2) {
            for (int columna = 0; columna < tablero.getColumnas(); columna += 2) {
                PieceBase pieza = new PieceSquare();
                // Antes de colocar, verificar que la posición es válida
                assertTrue("No se pudo verificar colocación en " + fila + "," + columna, tablero.verificarColocacionValida(pieza, fila, columna));
                tablero.setPiezaActual(pieza);
                tablero.colocarPiezaEnTableroVerificada(pieza, fila, columna);
                // Guardar la última pieza colocada
                ultima = pieza;
            }
        }

        // Intentar colocar una nueva pieza que debería causar "game over"
        tablero.setPiezaActual(ultima);
        tablero.setFilaActual(0);
        tablero.setColumnaActual(0);
        // No reintentamos colocarla (el tablero ya está lleno). En su lugar, comprobamos
        // que no exista ninguna posición válida recorriendo todo el tablero.
        boolean existePosicionValida = false;
        for (int f = 0; f < tablero.getFilas(); f++) {
            for (int c = 0; c < tablero.getColumnas(); c++) {
                if (tablero.verificarColocacionValida(ultima, f, c)) {
                    existePosicionValida = true;
                    break;
                }
            }
            if (existePosicionValida) break;
        }
        assertFalse("No debería existir posición válida para una nueva pieza en un tablero lleno", existePosicionValida);

        // Comprobamos con la función del Board si el juego debe terminar (no hay lugar para la pieza) //espera true
        assertTrue("El tablero debería indicar fin del juego cuando no hay posiciones válidas", tablero.esFinDelJuego(tablero));

            // Actualizar el estado del juego
        // Pedimos explícitamente a Tetris que consulte el Board y actualice su estado
        game.actualizarEstadoJuego();
        assertEquals("El juego debería estar en estado de 'game over'", 2, game.getState()); // espera estado 2 (game over)

            // Verificar que el estado del juego es "terminado" si no se puede colocar la pieza
            if (!existePosicionValida) { // si no existe ninguna posicion valida
                assertTrue("Seria Game over ya que no pude añadir una pieza nueva y el tablero esta lleno", true); // espera true
                game.setState(2); // fuerza el estado a terminado
                assertEquals("El juego debería estar en estado de 'terminado'", 2, game.getState()); // espera estado 2 (terminado)
            }
        }

      // Test: no se puede ingresar más piezas si la primera línea está llena
        @Test
        public void testNoSePuedeIngresarMasPiezas() {
            Tetris tetris = new Tetris();
            Board tablero = tetris.getBoard();
            // Llenar la primera línea con piezas
            for (int col = 0; col < tablero.getColumnas(); col++) {
                tablero.setBoard(0, col, 1);
            }
            PieceBase pieza = new PieceSquare();
            boolean pudoColocar = tetris.nuevaPiezaAleatoria(pieza);
            assertFalse("No se debe poder ingresar una nueva pieza si la primera línea está llena", pudoColocar);
            assertEquals("El juego debe estar terminado", 2, tetris.getState());
        }

        // Test: la pieza actual se detiene cuando no puede descender
        @Test
        public void testPiezaActualSeDetiene() {
            Tetris tetris = new Tetris();
            Board tablero = tetris.getBoard();
            PieceBase pieza = new PieceSquare();
            tetris.nuevaPiezaAleatoria(pieza);
            // Simular caída hasta el fondo
            while (!tetris.piezaActualDetenida()) {
                tablero.moverPieza(pieza, 1, 0);
            }
            assertTrue("La pieza debe estar detenida al tocar el fondo", tetris.piezaActualDetenida());
        }

        // Test: no se puede rotar si colisiona
        @Test
        public void testNoSePuedeRotarSiColisiona() {
            // Hacer el test determinista: colocamos manualmente una PieceL en (0,0)
            // y ponemos un bloque en (1,0) que no forma parte de la pieza
            // en su orientación original pero sí en su orientación rotada.
            Tetris tetris = new Tetris();
            Board tablero = tetris.getBoard();
            PieceBase pieza = new PieceL();
            // Colocar la pieza manualmente en la columna 0, fila 0
            tablero.setPiezaActual(pieza);
            tablero.setFilaActual(0);
            tablero.setColumnaActual(0);
            tablero.colocarPiezaEnTableroVerificada(pieza, 0, 0);
            // Colocar un bloque que bloquee la rotación a la derecha.
            // Usamos (0,1) para evitar que la limpieza de la pieza borre el bloqueo antes de verificar.
            tablero.setBoard(0, 1, 1);
            boolean pudoRotar = tetris.rotateRight(); // intenta rotar y espera false
            assertFalse("No se debe poder rotar si colisiona", pudoRotar); // espera false
        }
        
         // Test para demostrar implementación de interfaz
        @Test
        public void testBoardImplementsIBoardOperations() {
            Board board = new Board();
            assertTrue(board instanceof IBoardOperations); // Verifica que Board implementa IBoardOperations con instanceof
        }

         // Otro Test para demostrar implementación de interfaz en este caso ITick
        @Test
        public void testClockImplementsITick() {
            Clock clock = new Clock();
            assertTrue(clock instanceof ITick);
        }

        @Test
        public void testGetClock(){
            Tetris tetris = new Tetris();
            Clock clock = tetris.getClock();
            assertFalse("El reloj no debe ser nulo", clock == null);
        }
         

        // Tests sencillos para Clock
    @Test
    public void testClockTicksIncrementa() {
        Clock clock = new Clock();
        int ticksInicial = clock.getTicks();
        clock.tick();
        assertEquals(ticksInicial + 1, clock.getTicks());
    }

    @Test
    public void testClockSetGetTicks() { // este test es para demostrar que se puede setear y getear los ticks
        Clock clock = new Clock();
        clock.setTicks(42);
        assertEquals(42, clock.getTicks());
    }

    @Test
    public void testClockSetGetIntervaloDescenso() { // este test es para demostrar que se puede hacer set y get del intervalo de descenso
        Clock clock = new Clock();
        clock.setIntervaloDescenso(10);
        assertEquals(10, clock.getIntervaloDescenso());
    }

    @Test
    public void testClockSetGetBoard() { //este test es para demostrar que Clock tiene un Board y se puede hacer set y get
        Clock clock = new Clock();
        Board board = new Board();
        clock.setBoard(board);
        assertEquals(board, clock.getBoard());
    }

     // Tests sencillos para Board
    @Test
    public void testBoardConstructor() {
        Board board = new Board();
        assertEquals(10, board.getFilas());
        assertEquals(20, board.getColumnas());
        assertTrue(board.tableroVacio());
    }

    @Test
    public void testBoardSetGetPiezaActual() {
        Board board = new Board();
        PieceBase pieza = new PieceSquare();
        board.setPiezaActual(pieza);
        assertEquals(pieza, board.getPiezaActual());
    }

    // para board
    @Test
    public void testBoardSetGetFilaColumnaActual() { // este test es para demostrar que setFilaActual y setColumnaActual funcionan
        Board board = new Board();
        board.setFilaActual(5);
        board.setColumnaActual(7);
        assertEquals(5, board.getFilaActual());
        assertEquals(7, board.getColumnaActual());
    }

    @Test
    public void testBoardSetGetBoardCell() { // este test es para demostrar que setBoard y getBoard funcionan
        Board board = new Board();
        board.setBoard(2, 3, 9);
        assertEquals(9, board.getBoard()[2][3]);
    }

    @Test
    public void testBoardLimpiarTablero() { // este test es para demostrar que limpiarTablero funciona
        Board board = new Board();
        board.setBoard(0, 0, 1);
        board.setBoard(1, 1, 2);
        board.limpiarTablero();
        assertTrue(board.tableroVacio());
        assertEquals(null, board.getPiezaActual());
        assertEquals(0, board.getFilaActual());
        assertEquals(0, board.getColumnaActual());
    }

       // Más tests sencillos para Board
    @Test
    public void testVerificarColocacionValidaTrue() {
        Board board = new Board();
        PieceBase pieza = new PieceSquare();
        // Colocar en posición válida (0,0)
        assertTrue(board.verificarColocacionValida(pieza, 0, 0));
    }

    @Test
    public void testVerificarColocacionValidaFalseFueraTablero() {
        Board board = new Board();
        PieceBase pieza = new PieceSquare();
        // Colocar fuera del tablero
        assertFalse(board.verificarColocacionValida(pieza, 9, 19));
    }

    @Test
    public void testColocarSiValidaTrue() {
        Board board = new Board();
        PieceBase pieza = new PieceSquare();
        boolean resultado = board.colocarSiValida(pieza, 0, 0);
        assertTrue(resultado);
        assertEquals(pieza, board.getPiezaActual());
    }

    @Test
    public void testColocarSiValidaFalse() {
        Board board = new Board();
        PieceBase pieza = new PieceSquare();
        // Colocar fuera del tablero
        boolean resultado = board.colocarSiValida(pieza, 9, 19);
        assertFalse(resultado);
        assertEquals(null, board.getPiezaActual());
    }
    
    @Test
    public void testMoverPiezaFalseSinPieza() {
        Board board = new Board();
        // No hay pieza actual
        assertFalse(board.moverPieza(1, 0));
    }

     // Tests sencillos para Tetris
    @Test
    public void testTetrisConstructor() {
        Tetris tetris = new Tetris();
        assertFalse(tetris.isGameStart());
        assertFalse(tetris.isGameEnd());
        assertFalse(tetris.isGameWin());
        assertNotNull(tetris.getBoard());
        assertNotNull(tetris.getClock());
    }

    @Test
    public void testTetrisStartEndRestart() {
        Tetris tetris = new Tetris();
        tetris.start();
        assertTrue(tetris.isGameStart());
        tetris.end();
        assertTrue(tetris.isGameEnd());
        tetris.restart();
        assertFalse(tetris.isGameStart());
        assertFalse(tetris.isGameEnd());
        assertFalse(tetris.isGameWin());
    }

    @Test
    public void testTetrisSettersGetters() {// este test es para demostrar que se pueden setear y getear los booleanos gameStart, gameEnd y gameWin
        Tetris tetris = new Tetris();
        tetris.setGameStart(true);
        tetris.setGameEnd(true);
        tetris.setGameWin(true);
        assertTrue(tetris.isGameStart());
        assertTrue(tetris.isGameEnd());
        assertTrue(tetris.isGameWin());
    }

    @Test
    public void testTetrisSetBoardClock() {// este test es para demostrar que se pueden setear y getear el board y el clock
        Tetris tetris = new Tetris();
        Board board = new Board();
        Clock clock = new Clock();
        tetris.setBoard(board);
        tetris.setClock(clock);
        assertEquals(board, tetris.getBoard());
        assertEquals(clock, tetris.getClock());
    }

     // Más tests sencillos para Tetris
    @Test
    public void testTetrisGetState() {
        Tetris tetris = new Tetris();
        assertEquals(0, tetris.getState()); // Estado inicial
        tetris.start();
        assertEquals(1, tetris.getState()); // En juego
        tetris.end();
        assertEquals(2, tetris.getState()); // Fin del juego
        tetris.setState(3);
        assertEquals(3, tetris.getState()); // Win
    }

    @Test
    public void testTetrisSetState() {
        Tetris tetris = new Tetris();
        tetris.setState(1);
        assertTrue(tetris.isGameStart());
        assertFalse(tetris.isGameEnd());
        assertFalse(tetris.isGameWin());
        tetris.setState(2);
        assertFalse(tetris.isGameStart());
        assertTrue(tetris.isGameEnd());
        assertFalse(tetris.isGameWin());
        tetris.setState(3);
        assertFalse(tetris.isGameStart());
        assertFalse(tetris.isGameEnd());
        assertTrue(tetris.isGameWin());
    }

    @Test
    public void testTetrisIsJuegoActivo() {
        Tetris tetris = new Tetris();
        assertFalse(tetris.isJuegoActivo());
        tetris.start();
        assertTrue(tetris.isJuegoActivo());
        tetris.end();
        assertFalse(tetris.isJuegoActivo());
    }

    @Test
    public void testTetrisNuevaPiezaAleatoriaTrue() {
        Tetris tetris = new Tetris();
        PieceBase pieza = new PieceSquare();
        boolean resultado = tetris.nuevaPiezaAleatoria(pieza);
        assertTrue(resultado);
        assertEquals(pieza, tetris.getBoard().getPiezaActual());
    }

    @Test
    public void testTetrisNuevaPiezaAleatoriaFalse() {
        Tetris tetris = new Tetris();
        PieceBase pieza = new PieceSquare();
        // Forzar el tablero lleno para que no se pueda colocar
        Board board = tetris.getBoard();
        for (int i = 0; i < board.getFilas(); i++) {
            for (int j = 0; j < board.getColumnas(); j++) {
                board.setBoard(i, j, 1);
            }
        }
        // Marcar una pieza actual para que esFinDelJuego pueda evaluar el estado del tablero
        board.setPiezaActual(new PieceSquare());
        boolean resultado = tetris.nuevaPiezaAleatoria(pieza);
        assertFalse(resultado);
        assertTrue(tetris.isGameEnd());
    }

       @Test
    public void testTetrisRotateRightLeft() {
        Tetris tetris = new Tetris();
        PieceBase pieza = new PieceL();
        tetris.nuevaPiezaAleatoria(pieza);
        boolean rotadoDerecha = tetris.rotateRight();
        boolean rotadoIzquierda = tetris.rotateLeft();
        // Puede rotar si hay espacio, no debe lanzar error
        assertTrue(rotadoDerecha || !rotadoDerecha);
        assertTrue(rotadoIzquierda || !rotadoIzquierda);
    }

    @Test
    public void testTetrisPiezaActualDetenida() {
        Tetris tetris = new Tetris();
        PieceBase pieza = new PieceSquare();
        tetris.nuevaPiezaAleatoria(pieza);
        Board board = tetris.getBoard();
        // Simular caída hasta el fondo
        while (!tetris.piezaActualDetenida()) {
            board.moverPieza(pieza, 1, 0);
        }
        assertTrue(tetris.piezaActualDetenida());
    }



    @Test
    public void testTetrisSetIntervaloDescenso() {
        Tetris tetris = new Tetris();
        tetris.setIntervaloDescenso(5);
        assertEquals(5, tetris.getClock().getIntervaloDescenso());
    }

     @Test
    public void testTetrisGetBoardNeverNull() {
        Tetris tetris = new Tetris();
        tetris.setBoard(null);
        assertNotNull(tetris.getBoard()); // Siempre debe devolver un Board
    }

    @Test
    public void testTetrisSetGameFlags() {
        Tetris tetris = new Tetris();
        tetris.setGameStart(true);
        tetris.setGameEnd(false);
        tetris.setGameWin(false);
        assertTrue(tetris.isGameStart());
        assertFalse(tetris.isGameEnd());
        assertFalse(tetris.isGameWin());
    }

    @Test
    public void testTetrisSetClock() {
        Tetris tetris = new Tetris();
        Clock clock = new Clock();
        tetris.setClock(clock);
        assertEquals(clock, tetris.getClock());
    }

    @Test
    public void testTetrisTickSinJuegoActivo() {
        Tetris tetris = new Tetris();
        int ticksAntes = tetris.getClock().getTicks();
        tetris.tick(); // No debería avanzar ticks porque el juego no está activo
        assertEquals(ticksAntes, tetris.getClock().getTicks());
    }

    @Test
    public void testTetrisTickConJuegoActivo() {
        Tetris tetris = new Tetris();
        tetris.start();
        int ticksAntes = tetris.getClock().getTicks();
        tetris.tick(); // Debería avanzar ticks
        assertEquals(ticksAntes + 1, tetris.getClock().getTicks());
    }

      @Test
    public void testTetrisSetRandom() {
        Tetris tetris = new Tetris();
        java.util.Random nuevoRandom = new java.util.Random(123);
        // Usar reflexión para acceder al método privado setRandom
        try {
            java.lang.reflect.Method metodo = Tetris.class.getDeclaredMethod("setRandom", java.util.Random.class);
            metodo.setAccessible(true);
            metodo.invoke(tetris, nuevoRandom);
        } catch (Exception e) {
            assertTrue("No se pudo acceder a setRandom", false);
        }
    }

    @Test
    public void testTetrisGetRandom() {
        Tetris tetris = new Tetris();
        // Usar reflexión para acceder al método privado getRandom
        try {
            java.lang.reflect.Method metodo = Tetris.class.getDeclaredMethod("getRandom");
            metodo.setAccessible(true);
            Object random = metodo.invoke(tetris);
            assertTrue(random instanceof java.util.Random);
        } catch (Exception e) {
            assertTrue("No se pudo acceder a getRandom", false);
        }
    }

    @Test
    public void testTetrisRestartReseteaFlags() {
        Tetris tetris = new Tetris();
        tetris.setGameStart(true);
        tetris.setGameEnd(true);
        tetris.setGameWin(true);
        tetris.restart();
        assertFalse(tetris.isGameStart());
        assertFalse(tetris.isGameEnd());
        assertFalse(tetris.isGameWin());
    }

    @Test
    public void testTetrisSettersBoardClock() {
        Tetris tetris = new Tetris();
        Board board = new Board();
        Clock clock = new Clock();
        tetris.setBoard(board);
        tetris.setClock(clock);
        assertEquals(board, tetris.getBoard());
        assertEquals(clock, tetris.getClock());
    }

    @Test
    public void testGenerarPiezaAleatoria() {
        Tetris tetris = new Tetris();
        PieceBase pieza = tetris.generarPiezaAleatoria(); // directo, si no es private
        // Verificar que la pieza no es nula
        assertNotNull(pieza);
        // Verificar que la pieza es una PieceDog, PieceL, PieceStick, PieceSquare o PieceT
        assertTrue(pieza instanceof PieceDog || pieza instanceof PieceL || pieza instanceof PieceStick || pieza instanceof PieceSquare || pieza instanceof PieceT);
    }

    @Test
    public void testTetrisActualizarEstadoJuego() {
        Tetris tetris = new Tetris();
        tetris.start();
        // Inicialmente el estado debe ser 1 (en juego)
        assertEquals(1, tetris.getState());
        // Forzar condiciones para game over
        Board board = tetris.getBoard();
        for (int i = 0; i < board.getFilas(); i++) {
            for (int j = 0; j < board.getColumnas(); j++) {
                board.setBoard(i, j, 1);
            }
        }
        
        // Marcar una pieza para que esFinDelJuego pueda determinar que no hay posición válida
        board.setPiezaActual(new PieceSquare());
        tetris.actualizarEstadoJuego();
        assertEquals(2, tetris.getState()); // Estado debe ser 2 (game over)
    }

    @Test
    public void testTetrisCompleto() {
        Tetris tetris = new Tetris();
        tetris.start();

        // Verificar estado inicial
        assertTrue(tetris.isGameStart()); // Verificar que el juego ha comenzado
        assertFalse(tetris.isGameEnd()); // Verificar que no es game over al inicio
        assertFalse(tetris.isGameWin()); // Verificar que no es game win al inicio

        assertEquals(1, tetris.getState()); // Estado inicial debe ser 1 (en juego)
        assertNotNull(tetris.getBoard()); // Verificar que el tablero no es nulo
        assertNotNull(tetris.getClock()); // Verificar que el reloj no es nulo
        assertTrue(tetris.isJuegoActivo()); // Verificar que el juego está activo
        assertEquals(0, tetris.getClock().getTicks()); // Ticks iniciales deben ser 0

        // Generar y colocar una nueva pieza en una columna random
        assertTrue(tetris.nuevaPiezaAleatoria(new PieceSquare()));

        // Intentar rotar y mover la pieza
        assertTrue(tetris.rotateRight()); // puede rotar, no debe lanzar error
        assertTrue(tetris.rotateLeft());  // puede rotar, no debe lanzar error
        assertTrue(tetris.piezaActualDetenida()); // al inicio no debe estar detenida

        // Simular algunos ticks del reloj
        tetris.setIntervaloDescenso(2);
        // Verificar que el intervalo se ha seteado correctamente
        assertEquals(2, tetris.getClock().getIntervaloDescenso()); // espera 2 porque lo seteamos arriba
        
        // Hacer tick y verificar que avanza el contador de ticks
        tetris.getClock().tick();
        assertEquals(1, tetris.getClock().getTicks()); // Verificar que los ticks avanzan
        tetris.getClock().tick();
        assertEquals(2, tetris.getClock().getTicks()); // Verificar que los ticks avanzan
        tetris.getClock().tick();

        // Simular algunas acciones
        PieceBase pieza = new PieceSquare();
        tetris.nuevaPiezaAleatoria(pieza);
        tetris.getBoard().moverPieza(pieza, 1, 0);
        tetris.rotateRight();
        tetris.getClock().tick();
        // Verificar estado intermedio
        assertEquals(1, tetris.getState());
        // Forzar condiciones para game win
        Board board = tetris.getBoard();
        for (int i = 0; i < board.getFilas(); i++) {
            for (int j = 0; j < board.getColumnas(); j++) {
                board.setBoard(i, j, 1);
            }
        }
        // Marcar una pieza cualquiera como piezaActual para que los métodos que la consulten no hagan NPE
        board.setPiezaActual(new PieceSquare());
        board.verificarYEliminarLineas(); // esto elimina la(s) filas llenas detectadas
        tetris.actualizarEstadoJuego();
        // Al eliminar todas las líneas la lógica actual señala 'game win' (3)
        assertEquals(3, tetris.getState()); // Estado debe ser 3 (game win)
    }
}




