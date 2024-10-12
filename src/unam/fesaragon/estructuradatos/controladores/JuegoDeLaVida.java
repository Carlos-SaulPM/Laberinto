package unam.fesaragon.estructuradatos.controladores;

import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
import unam.fesaragon.estructuradatos.controladores.vistas.CeldaController;
import unam.fesaragon.estructuradatos.modelos.LaberintoLogica;
import unam.fesaragon.estructuradatos.modelos.adts.ADTArray2D;
import unam.fesaragon.estructuradatos.modelos.excepciones.ArchivoFXML;

import unam.fesaragon.estructuradatos.modelos.javafx.CuadriculaFX;
import unam.fesaragon.estructuradatos.modelos.javafx.Escena;
import unam.fesaragon.estructuradatos.modelos.laberinto.Coordenada;
import unam.fesaragon.estructuradatos.modelos.laberinto.GridLaberinto;
import unam.fesaragon.estructuradatos.vistas.componentes.Vista;

public class JuegoDeLaVida {
    Vista vista;
    LaberintoLogica laberintoLogica;
    Escena escena;
    private boolean menuIniciarLaberinto = true;
    SplitPane splitPaneMenuInicio;
    SplitPane splitPaneParaCargarLaberinto;

    public JuegoDeLaVida(int filas, int columnas) throws ArchivoFXML {
        this.vista = new Vista(filas, columnas);
        GridLaberinto gridLaberinto = probandoLogica(filas, columnas);
        this.laberintoLogica = new LaberintoLogica(gridLaberinto);
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

    private GridLaberinto probandoLogica(int filas, int columnas) {
        GridLaberinto aux = new GridLaberinto(filas, columnas);
        aux.cargarCoordenadaDeEntradaYSalida(new Coordenada(4, 0), new Coordenada(0, 4));
        ADTArray2D<Coordenada> paredes = new ADTArray2D<>(Coordenada.class, filas, columnas);
        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                if (fila >= 1 && columna >= 1) {
                    paredes.set_item(fila, columna, new Coordenada(fila, columna, false));
                } else {
                    paredes.set_item(fila, columna, new Coordenada(fila, columna));

                }
            }
        }
        aux.cargarParedesDeLaberinto(paredes);
        return aux;
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
