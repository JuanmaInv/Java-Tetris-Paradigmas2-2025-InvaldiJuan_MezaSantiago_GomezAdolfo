# Documentación del Proyecto: Tetris en Java - Paradigmas de Programación 2025

## Descripción General del Proyecto

Este repositorio, **Java-Tetris-Paradigmas2-2025-InvaldiJuan_MezaSantiago_GomezAdolfo**, contiene un proyecto académico grupal desarrollado por Juan Invaldi, Santiago Meza y Adolfo Gómez para el curso de Paradigmas de Programación en la Universidad Católica de Perú (UCP) durante el año 2025. El objetivo es implementar una versión base del videojuego *Tetris* utilizando **Java** con un enfoque en **Programación Orientada a Objetos (POO)**, aplicando conceptos como herencia, polimorfismo, encapsulación y abstracción.

- **Propósito**: Demostrar artefactos POO mediante un escenario lúdico, enfatizando **Test-Driven Development (TDD)** con cobertura de pruebas unitarias superior al 90%.
- **Alcance**: Código base sin interfaz gráfica, centrado en la lógica del juego (piezas, tablero, descenso, eliminación de líneas).
- Licencia: Open Source.
- **Enlace al Repositorio**: [https://github.com/JuanmaInv/Java-Tetris-Paradigmas2-2025-InvaldiJuan_MezaSantiago_GomezAdolfo](https://github.com/JuanmaInv/Java-Tetris-Paradigmas2-2025-InvaldiJuan_MezaSantiago_GomezAdolfo)

## Miembros del Equipo

| Miembro          | Rol Principal                  | Contribuciones Clave |
|------------------|--------------------------------|----------------------|
| Juan Invaldi    | Líder de Diseño de Clases     | Implementación de piezas y herencia |
| Santiago Meza   | Especialista en Pruebas       | Desarrollo de tests unitarios y TDD |
| Adolfo Gómez    | Integrador de Lógica del Juego| Manejo del tablero y mecánicas de juego |

**Nota**: Evaluación grupal (código) e individual (coloquio). GitHub se usó para colaboración con branches y pull requests.

## Requisitos y Objetivos

El proyecto cumple los requerimientos del escenario *Tetris* propuesto por UCP Video Games Inc., enfocándose en la lógica backend.

### Objetivos Principales
- Crear clases Java con tests JUnit (cobertura >90%).
- Implementar conceptos POO:
  - **Igualdad y Constructores**: Sobrescritura de `equals()` y constructores.
  - **Componentes**: Piezas de 4 bloques.
  - **Encapsulación Doble**: Atributos/métodos privados con getters/setters.
  - **Interfaces**: Comportamientos como `Rotatable`.
  - **Herencia**: `Piece` como clase base abstracta.
  - **Polimorfismo**: Sobrescritura de métodos como `rotate()`.
  - **Sobrecarga**: Múltiples constructores/métodos.
  - **Clases Abstractas**: `Piece` con métodos abstractos.
  - **Pruebas**: Solo asserts (`assertTrue`, `assertEquals`), sin prints.

### Criterios de Evaluación
- Rúbrica: 100 puntos (nota máxima: 10).
- Flujo GitHub: Commits, branches, merges.
- TDD: Pruebas guían desarrollo.
- Entregables: Coloquio, enlace al repositorio, reporte de cobertura (JaCoCo).

## Estructura del Repositorio

```
Java-Tetris-Paradigmas2-2025-InvaldiJuan_MezaSantiago_GomezAdolfo/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── Tetris.java
│   │       ├── Board.java
│   │       ├── Piece.java
│   │       ├── PieceT.java
│   │       ├── PieceSquare.java
│   │       ├── PieceStick.java
│   │       ├── PieceL.java
│   │       └── PieceDog.java
│   └── test/
│       └── java/
│           ├── TetrisTest.java
│           ├── PieceTest.java
│           └── BoardTest.java
├── pom.xml (o build.gradle)
├── README.md
└── coverage-report/
```

- **Imágenes**: Incluye diagramas conceptuales (UML o flujo) en el repositorio.

## Implementación Detallada

### Preparación del Entorno
1. Instalar JDK 17+ e IDE (IntelliJ/Eclipse).
2. Crear proyecto Maven/Gradle con JUnit 5.
3. Validar: Crear `Tetris.java` y `TetrisTest.java` con test básico.

### Requerimientos

#### 1. Creación de Piezas
- Piezas: T, Cuadrado, Palo, L (left/right), Perro (S/Z, left/right).
- Cada pieza: 4 bloques (coordenadas o matriz 4x4).
- **POO**: Herencia de `Piece`, constructores sobrecargados, `equals()`.

#### 2. Rotación de Piezas
- Métodos `rotateLeft()` y `rotateRight()`: Giro 90°.
- 4 orientaciones por pieza; polimorfismo.
- Tests: Verificar posiciones post-rotación.

#### 3. Tablero
- Tamaño: 10x20 (matriz).
- Piezas entran aleatoriamente en la fila superior (válida).
- Descenso: Cada "tick" (e.g., 2s), baja 1 fila si es posible.

#### 4. Detención y Game Over
- Detener pieza si no puede bajar/girar.
- Game Over: Sin espacio en la primera fila.

#### 5. Líneas Completas
- Eliminar líneas llenas; bajar celdas superiores.
- Fin del juego: Completar X líneas (configurable, e.g., 5).

## Pruebas Unitarias

Cobertura >90% con JUnit 5.

| Categoría              | Archivo         | Tests | Cobertura |
|------------------------|-----------------|-------|-----------|
| Creación de Piezas    | PieceTest.java | 6     | 100%      |
| Rotación              | PieceTest.java | 40+   | 95%       |
| Tablero               | BoardTest.java | 10    | 90%       |
| Reloj/Movimiento      | TetrisTest.java| 5     | 100%      |
| Líneas Completas      | BoardTest.java | 3     | 95%       |
| Juego Completo        | TetrisTest.java| 4     | 92%       |

## Configuración y Ejecución

1. Clonar: `git clone https://github.com/JuanmaInv/...`
2. Build: `mvn clean test`
3. Cobertura: `mvn jacoco:report`
4. Ejecutar: `mvn exec:java -Dexec.mainClass="Tetris"`

## Conclusiones y Mejoras

El proyecto demuestra POO robusto y testeable. Mejoras futuras:
- Interfaz gráfica (JavaFX/Swing).
- Más piezas (J, etc.).
- Velocidad configurable.