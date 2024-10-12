package unam.fesaragon.estructuradatos.controladores;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import unam.fesaragon.estructuradatos.controladores.vistas.CeldaController;
import unam.fesaragon.estructuradatos.controladores.vistas.CuadriculaController;
import unam.fesaragon.estructuradatos.modelos.LaberintoLogica;
import unam.fesaragon.estructuradatos.modelos.adts.ADTArray2D;
import unam.fesaragon.estructuradatos.modelos.adts.ADTStack;
import unam.fesaragon.estructuradatos.modelos.excepciones.ArchivoFXML;

import unam.fesaragon.estructuradatos.modelos.javafx.CuadriculaFX;
import unam.fesaragon.estructuradatos.modelos.javafx.Escena;
import unam.fesaragon.estructuradatos.modelos.laberinto.Coordenada;
import unam.fesaragon.estructuradatos.modelos.laberinto.GridLaberinto;
import unam.fesaragon.estructuradatos.vistas.componentes.Vista;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class JuegoDeLaVida {
    Vista vista;
    LaberintoLogica laberintoLogica;
    Escena escena;
    private boolean menuIniciarLaberinto = true;
    SplitPane splitPaneMenuInicio;
    SplitPane splitPaneParaCargarLaberinto;

    public JuegoDeLaVida(int filas, int columnas) throws ArchivoFXML {
        this.vista = new Vista(filas, columnas);
    }

    public void comenzar() {
        escena = new Escena("El juego de la vida", new Stage());
        escena.cambiarEscena(vista.getMenuDeInicio().getContenedorMenuController());
        configurarBotonesDeLosMenus();
    }

    private void configurarBotonesDeLosMenus() {
        splitPaneMenuInicio = (SplitPane) vista.getMenuDeInicio().getMenuController().getSplitPane();
        splitPaneParaCargarLaberinto = (SplitPane) vista.getMenuParaCargarElLaberinto().getMenuController().getSplitPane();
        vista.getMenuDeInicio().getMenuController().getBoton2().setOnAction(event -> cambiarMenu());
        vista.getMenuDeInicio().getMenuController().getBoton1().setOnAction(event -> iniciarLaberinto());
        vista.getMenuParaCargarElLaberinto().getMenuController().getBoton2().setOnAction(event -> cambiarMenu());
        vista.getMenuParaCargarElLaberinto().getMenuController().getBoton1().setOnAction(event -> {
            try {
                cargarLaberinto();
            } catch (ArchivoFXML e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void iniciarLaberinto() {
        CuadriculaFX cuadriculaFX = vista.getMenuDeInicio().getCuadriculaFX();
        int filas = cuadriculaFX.getFilas();
        int columnas = cuadriculaFX.getColumnas();
        GridLaberinto aux = new GridLaberinto(filas, columnas);
        aux.cargarCoordenadaDeEntradaYSalida(new Coordenada(0, 0), new Coordenada(9, 9));
        ADTArray2D<Coordenada> paredes = new ADTArray2D<>(Coordenada.class, filas, columnas);
        //Obtener el GridLaberinto para pasarlo a la logica.
        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                //Negando la condicion Obtengo solo las paredes
                paredes.set_item(fila, columna, cuadriculaFX.getCuadriculaController().getCelda(fila, columna).getCoordenada());

            }
        }
        aux.cargarParedesDeLaberinto(paredes);
        this.laberintoLogica = new LaberintoLogica(aux);
        laberintoLogica.getCamino().imprimirStack();
        pintarCamino();
    }

    private void pintarCamino() {
        // Invertir la pila
        Stack<Coordenada> caminoPilaInvertida = invertirStack(laberintoLogica.getCamino());

        // Convertir la pila invertida a una cola
        Queue<Coordenada> caminoCola = new LinkedList<>();
        while (!caminoPilaInvertida.isEmpty()) {
            caminoCola.add(caminoPilaInvertida.pop());
        }

        // Usar la cola para pintar las celdas
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (!caminoCola.isEmpty()) {
                Coordenada coordenadaCamino = caminoCola.poll();
                vista.getMenuDeInicio().getCuadriculaFX().getCuadriculaController().getCelda(coordenadaCamino.getFila(), coordenadaCamino.getColumna()).getPanelCelda().setStyle("-fx-background-color: " + "#92c943" + ";");
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private Stack<Coordenada> invertirStack(ADTStack<Coordenada> original) {
        Stack<Coordenada> invertido = new Stack<>();
        while (!original.isEmpty()) {
            invertido.push(original.pop());
        }
        return invertido;
    }




    private void cargarLaberinto() throws ArchivoFXML {
        CuadriculaFX copia = clonarCuadricula(vista.getMenuParaCargarElLaberinto().getCuadriculaFX());
        vista.getMenuDeInicio().setCuadriculaFX(copia);

        // Actualizar visualmente el contenedor
        vista.getMenuDeInicio().getStackPaneDeCuadriculaFX().getChildren().clear();
        vista.getMenuDeInicio().getStackPaneDeCuadriculaFX().getChildren().add(copia.getCuadriculaController().getGridPaneCuadricula());
        vista.getMenuDeInicio().getContenedorMenuController().requestLayout();
    }

    private void cambiarMenu() {
        vista.getMenuDeInicio().getContenedorMenuController().getChildren().clear();
        if (menuIniciarLaberinto) {
            vista.getMenuDeInicio().getContenedorMenuController().getChildren().add(splitPaneParaCargarLaberinto);
            menuIniciarLaberinto = false;
        } else {
            vista.getMenuDeInicio().getContenedorMenuController().getChildren().add(splitPaneMenuInicio);
            menuIniciarLaberinto = true;
        }
    }

    //Copia la grid del menu para cargar el laberinto para mostrarlo al menu de inicio
    private CuadriculaFX clonarCuadricula(CuadriculaFX original) throws ArchivoFXML {
        CuadriculaFX copia = new CuadriculaFX(original.getFilas(), original.getColumnas());

        for (int fila = 0; fila < original.getFilas(); fila++) {
            for (int columna = 0; columna < original.getColumnas(); columna++) {
                CeldaController celdaOriginal = original.getCuadriculaController().getCelda(fila, columna);
                Coordenada coordenadaOriginal = celdaOriginal.getCoordenada();
                CeldaController celdaCopia = copia.getCuadriculaController().getCelda(fila, columna);
                Coordenada coordenadaCopia = new Coordenada(coordenadaOriginal.getFila(), coordenadaOriginal.getColumna(), coordenadaOriginal.isEstado());
                celdaCopia.setCoordenada(coordenadaCopia);
                celdaCopia.getPanelCelda().setStyle(celdaOriginal.getPanelCelda().getStyle());
            }
        }
        return copia;
    }


    public LaberintoLogica getLaberintoLogica() {
        return laberintoLogica;
    }

}
