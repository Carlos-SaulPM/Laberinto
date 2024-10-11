package unam.fesaragon.estructuradatos.controladores;

import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
import unam.fesaragon.estructuradatos.modelos.LaberintoLogica;
import unam.fesaragon.estructuradatos.modelos.adts.ADTArray2D;
import unam.fesaragon.estructuradatos.modelos.excepciones.ArchivoFXML;

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
        escena = new Escena("Prueba 1", new Stage());
        escena.cambiarEscena(vista.getMenuDeInicio().getContenedorMenuController());
        configurarBotonesDeLosMenus();
    }

    private void configurarBotonesDeLosMenus() {
        splitPaneMenuInicio = (SplitPane) vista.getMenuDeInicio().getMenuController().getSplitPane();
        splitPaneParaCargarLaberinto = (SplitPane) vista.getMenuParaCargarElLaberinto().getMenuController().getSplitPane();
        vista.getMenuDeInicio().getMenuController().getBoton2().setOnAction(event -> cambiarMenu());
        vista.getMenuDeInicio().getMenuController().getBoton1().setOnAction(event -> iniciarLaberinto());
        vista.getMenuParaCargarElLaberinto().getMenuController().getBoton2().setOnAction(event -> cambiarMenu());
        vista.getMenuParaCargarElLaberinto().getMenuController().getBoton1().setOnAction(event -> cargarLaberinto());
    }

    private void iniciarLaberinto() {
        System.out.println("Hash code desde Menu de inicio: ");
        System.out.println("Menu de inicio: "+vista.getMenuDeInicio().getCuadriculaFX().hashCode());
        System.out.println("Menu cargar laberinto: "+vista.getMenuParaCargarElLaberinto().getCuadriculaFX().hashCode());
    }

    private void cargarLaberinto() {
        System.out.println("Antes de cambiar cuadriculas FX");
        System.out.println("Menu de inicio: "+vista.getMenuDeInicio().getCuadriculaFX().hashCode());
        System.out.println("Menu cargar laberinto: "+vista.getMenuParaCargarElLaberinto().getCuadriculaFX().hashCode());
        vista.getMenuDeInicio().setCuadriculaFX(vista.getMenuParaCargarElLaberinto().getCuadriculaFX());
        System.out.println("Despues de cambiar cuadriculas FX");
        System.out.println("Menu de inicio: "+vista.getMenuDeInicio().getCuadriculaFX().hashCode());
        System.out.println("Menu cargar laberinto: "+vista.getMenuParaCargarElLaberinto().getCuadriculaFX().hashCode());
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

    public LaberintoLogica getLaberintoLogica() {
        return laberintoLogica;
    }

}
