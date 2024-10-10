package unam.fesaragon.estructuradatos.modelos.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;
import unam.fesaragon.estructuradatos.controladores.vistas.CeldaController;
import unam.fesaragon.estructuradatos.controladores.vistas.CuadriculaController;
import unam.fesaragon.estructuradatos.modelos.adts.ColaADT;
import unam.fesaragon.estructuradatos.modelos.excepciones.ArchivoFXML;
import unam.fesaragon.estructuradatos.modelos.laberinto.Coordenada;

import java.io.IOException;

public class CuadriculaFX {
    //Componentes de la cuadricula
    private AnchorPane anchorPaneContainerCuadricula;
    private CuadriculaController cuadriculaController;
    private boolean habilitarTouchDeceldas = false;

    //Atributos de la cuadricula
    private int filas;
    private int columnas;

    public CuadriculaFX(int filas, int columnas) throws ArchivoFXML {
        this.filas = filas;
        this.columnas = columnas;
        cargarComponentes();
    }
    public CuadriculaFX(int filas, int columnas, boolean habilitarTouchDeCeldas) throws ArchivoFXML {
        this.filas = filas;
        this.columnas = columnas;
        this.habilitarTouchDeceldas = habilitarTouchDeCeldas;
        cargarComponentes();
    }

    private void cargarComponentes() throws ArchivoFXML {
        FXMLLoader loaderCuadricula = new FXMLLoader(getClass().getResource(CuadriculaController.urlFXMLDeCuadricula));
        //Cargando la cuadricula
        try {
            anchorPaneContainerCuadricula = loaderCuadricula.load();
        } catch (IOException e) {
            throw new ArchivoFXML(e);
        }
        cuadriculaController = loaderCuadricula.getController();
        //Configurar el numero de celdas de la cuadriculaController.
        configurarCuadriculaConElTamano();
        llenarCuadriculaDeCeldas();
        cuadriculaController.getGridPaneCuadricula().setMaxSize(600.0, 600.0);

    }

    private void configurarCuadriculaConElTamano() {
        cuadriculaController.getGridPaneCuadricula().getRowConstraints().clear();
        cuadriculaController.getGridPaneCuadricula().getColumnConstraints().clear();
        double rowHeight = 30.0; // Altura de cada fila
        double columnWidth = 30.0; // Ancho de cada columna

        for (int fila = 0; fila < this.getFilas(); fila++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPrefHeight(rowHeight);
            rowConstraints.setVgrow(Priority.ALWAYS);
            cuadriculaController.getGridPaneCuadricula().getRowConstraints().add(rowConstraints);
        }

        for (int columna = 0; columna < this.getColumnas(); columna++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPrefWidth(columnWidth);
            columnConstraints.setHgrow(Priority.ALWAYS);
            cuadriculaController.getGridPaneCuadricula().getColumnConstraints().add(columnConstraints);
        }

        // Permitir el crecimiento dinámico del GridPane
        cuadriculaController.getGridPaneCuadricula().setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        cuadriculaController.getGridPaneCuadricula().setPrefSize(columnWidth * this.getColumnas(), rowHeight * this.getFilas());
    }


    private void llenarCuadriculaDeCeldas() throws ArchivoFXML {
        ColaADT<CeldaController> celdas = colaDeCeldas();
        for (int fila = 0; fila < this.getFilas(); fila++) {
            for (int columna = 0; columna < this.getColumnas(); columna++) {
                CeldaController celdaController = celdas.desEncolar();
                Pane panelCelda = celdaController.getPanelCelda();
                panelCelda.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                GridPane.setHgrow(panelCelda, Priority.ALWAYS);
                GridPane.setVgrow(panelCelda, Priority.ALWAYS);
                celdaController.setCoordenada(new Coordenada(fila,columna));
                cuadriculaController.getGridPaneCuadricula().add(panelCelda, columna, fila);
            }
        }
    }


    private ColaADT<CeldaController> colaDeCeldas() throws ArchivoFXML {
        ColaADT<CeldaController> celdas = new ColaADT<>();
        //Cargando la celda
        for (int cantidadDeCeldas = 0; cantidadDeCeldas < (filas * columnas); cantidadDeCeldas++) {
            FXMLLoader loaderCelda = new FXMLLoader(getClass().getResource(CeldaController.urlFXMLDeCelda));
            try {
                loaderCelda.load();
            } catch (IOException e) {
                throw new ArchivoFXML(e);
            }
            CeldaController celdaController = loaderCelda.getController();
            if (!habilitarTouchDeceldas) celdaController.setCambiarEstadoDeLasCeldas_Click(false);
            celdas.encolar(celdaController);
        }
        return celdas;
    }

    //GETTERS Y SETTERS
    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public AnchorPane getAnchorPaneContainerCuadricula() {
        return anchorPaneContainerCuadricula;
    }

    public CuadriculaController getCuadriculaController() {
        return cuadriculaController;
    }
}
