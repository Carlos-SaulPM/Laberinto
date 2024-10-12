package unam.fesaragon.estructuradatos.controladores;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import unam.fesaragon.estructuradatos.controladores.vistas.CeldaController;
import unam.fesaragon.estructuradatos.modelos.LaberintoLogica;
import unam.fesaragon.estructuradatos.modelos.adts.ADTArray2D;
import unam.fesaragon.estructuradatos.modelos.excepciones.ArchivoFXML;
import unam.fesaragon.estructuradatos.modelos.javafx.CuadriculaFX;
import unam.fesaragon.estructuradatos.modelos.javafx.Escena;
import unam.fesaragon.estructuradatos.modelos.laberinto.Coordenada;
import unam.fesaragon.estructuradatos.modelos.laberinto.GridLaberinto;
import unam.fesaragon.estructuradatos.vistas.componentes.Vista;

import javax.swing.*;
import java.util.Queue;


public class JuegoDeLaVida {
    private Vista vista;
    private LaberintoLogica laberintoLogica;
    private SplitPane splitPaneMenuInicio;
    private SplitPane splitPaneParaCargarLaberinto;
    private Coordenada coordenadaDeSalida;
    private Coordenada coordenadaDeEntrada;
    private boolean menuIniciarLaberinto = true;
    private boolean seEstaPintando = false;
    private boolean yaSePintoUnaVez = false;
    private double seguntosParaPintarCadaCelda = 0.1;

    public JuegoDeLaVida(int filas, int columnas) throws ArchivoFXML {
        this.vista = new Vista(filas, columnas);
    }

    public JuegoDeLaVida(int filas, int columnas, double seguntosParaPintarCadaCelda) throws ArchivoFXML {
        this.vista = new Vista(filas, columnas);
        this.seguntosParaPintarCadaCelda = seguntosParaPintarCadaCelda;
    }

    public void comenzar() {
        Escena escena = new Escena("El juego de la vida", new Stage());
        escena.cambiarEscena(vista.getMenuDeInicio().getContenedorMenuController());
        configurarBotonesDeLosMenus();
        vista.getMenuDeInicio().getMenuController().getSplitPane().getItems().get(1).setStyle("-fx-background-color: #FFF;");
        vista.getMenuParaCargarElLaberinto().getMenuController().getSplitPane().getItems().get(1).setStyle("-fx-background-color: #FFF;");
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
        //Validaciones
        if (seEstaPintando) {
            JOptionPane.showMessageDialog(null, "Se esta pintando el laberinto", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (coordenadaDeEntrada == null) {
            JOptionPane.showMessageDialog(null, "No haz cargado el laberinto", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        CuadriculaFX cuadriculaFX = vista.getMenuDeInicio().getCuadriculaFX();
        int filas = cuadriculaFX.getFilas();
        int columnas = cuadriculaFX.getColumnas();
        GridLaberinto aux = new GridLaberinto(filas, columnas);
        aux.cargarCoordenadaDeEntradaYSalida(coordenadaDeEntrada, coordenadaDeSalida);
        ADTArray2D<Coordenada> paredes = new ADTArray2D<>(Coordenada.class, filas, columnas);
        //Obtener el GridLaberinto para pasarlo a la logica.
        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                paredes.set_item(fila, columna, cuadriculaFX.getCuadriculaController().getCelda(fila, columna).getCoordenada());

            }
        }
        aux.cargarParedesDeLaberinto(paredes);
        this.laberintoLogica = new LaberintoLogica(aux);
        if (yaSePintoUnaVez) {
            for (int fila = 0; fila < filas; fila++) {
                for (int columna = 0; columna < columnas; columna++) {
                    if (cuadriculaFX.getCuadriculaController().getCelda(fila, columna).getCoordenada().isEstado()) {
                        cuadriculaFX.getCuadriculaController().getCelda(fila, columna).getPanelCelda().setStyle("-fx-background-color: #FFF;");
                    }
                }
            }
        }
        pintarCamino();
    }

    private void pintarCamino() {
        seEstaPintando = true;
        vista.getMenuDeInicio().getCuadriculaFX().getCuadriculaController().getCelda(coordenadaDeEntrada.getFila(), coordenadaDeEntrada.getColumna()).getPanelCelda().setStyle("-fx-background-color: #5486b2;");
        Queue<Coordenada> movimientos = laberintoLogica.getMovimientos();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(seguntosParaPintarCadaCelda), event -> {
            if (!movimientos.isEmpty()) {
                Coordenada coordenadaMovimiento = movimientos.poll();
                CeldaController celda = vista.getMenuDeInicio().getCuadriculaFX().getCuadriculaController().getCelda(coordenadaMovimiento.getFila(), coordenadaMovimiento.getColumna());
                if (coordenadaMovimiento.isEstado()) {
                    celda.getPanelCelda().setStyle("-fx-background-color: #6CB254FF;"); // Verde para avanzar
                    if (movimientos.isEmpty()) {
                        vista.getMenuDeInicio().getCuadriculaFX().getCuadriculaController().getCelda(coordenadaDeSalida.getFila(), coordenadaDeSalida.getColumna()).getPanelCelda().setStyle("-fx-background-color: #5486b2;");
                        seEstaPintando = false;
                        yaSePintoUnaVez = true;
                    }
                }
                // Si el movimiento es retroceder (estado es false), se pinta de rojo
                else {
                    celda.getPanelCelda().setStyle("-fx-background-color: #b25454;"); // Rojo para retroceder
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void cargarLaberinto() throws ArchivoFXML {
        CuadriculaFX copia = clonarCuadricula(vista.getMenuParaCargarElLaberinto().getCuadriculaFX());
        vista.getMenuDeInicio().setCuadriculaFX(copia);
        // Actualizar visualmente el contenedor
        vista.getMenuDeInicio().getStackPaneDeCuadriculaFX().getChildren().clear();
        vista.getMenuDeInicio().getStackPaneDeCuadriculaFX().getChildren().add(copia.getCuadriculaController().getGridPaneCuadricula());
        vista.getMenuDeInicio().getContenedorMenuController().requestLayout();
        coordenadaDeEntrada = cuadroParaIngresarCoordenada("La coordenada de entrada:", "Coordenada de entrada", JOptionPane.QUESTION_MESSAGE);
        coordenadaDeSalida = cuadroParaIngresarCoordenada("La coordenada de salida:", "Coordenada de salida", JOptionPane.QUESTION_MESSAGE);
        cambiarMenu();
    }

    public Coordenada cuadroParaIngresarCoordenada(String mensaje, String titulo, int tipoDeMensaje) {
        boolean datoValido = false;
        Coordenada coordenada = null;

        while (!datoValido) {
            String ingresado = JOptionPane.showInputDialog(null, mensaje, titulo, tipoDeMensaje);
            if (ingresado == null || ingresado.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Coordenada no válida. Por favor, inténtalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (ingresado.matches("\\d+,\\d+")) {
                String[] partes = ingresado.split(",");
                try {
                    int filaInsertada = Integer.parseInt(partes[0].trim());
                    int columnaInsertada = Integer.parseInt(partes[1].trim());
                    if ((filaInsertada >= 0 && filaInsertada < vista.getMenuDeInicio().getCuadriculaFX().getFilas()) && (columnaInsertada >= 0 && columnaInsertada < vista.getMenuDeInicio().getCuadriculaFX().getColumnas())) {
                        coordenada = new Coordenada(filaInsertada, columnaInsertada);
                        datoValido = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Las coordenadas que ingresaste estan fuera de rango", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Formato incorrecto. Por favor, ingresa en el formato '3,2'", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Formato incorrecto. Por favor, ingresa en el formato '3,2'", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return coordenada;
    }


    private void cambiarMenu() {
        vista.getMenuDeInicio().getContenedorMenuController().getChildren().clear();
        if (menuIniciarLaberinto) {
            if (seEstaPintando) {
                JOptionPane.showMessageDialog(null, "Se esta pintando el laberinto", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
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
