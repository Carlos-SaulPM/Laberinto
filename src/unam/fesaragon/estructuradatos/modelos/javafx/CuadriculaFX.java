package unam.fesaragon.estructuradatos.modelos.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;
import unam.fesaragon.estructuradatos.controladores.vistas.CeldaController;
import unam.fesaragon.estructuradatos.controladores.vistas.CuadriculaController;
import unam.fesaragon.estructuradatos.modelos.adts.ColaADT;
import unam.fesaragon.estructuradatos.modelos.excepciones.ArchivoFXML;

import java.io.IOException;

public class CuadriculaFX {
    //Componentes de la cuadricula
    private AnchorPane anchorPaneContainerCuadricula;
    private CuadriculaController cuadriculaController;

    //Atributos de la cuadricula
    private int filas;
    private int columnas;

    public CuadriculaFX(int filas, int columnas) throws ArchivoFXML {
        this.filas = filas;
        this.columnas = columnas;
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
    }

    private void configurarCuadriculaConElTamano() {
        cuadriculaController.getCuadricula().getRowConstraints().clear();
        cuadriculaController.getCuadricula().getColumnConstraints().clear();
        double rowHeight = 50.0;
        double columnWidth = 50.0;
        for (int fila = 0; fila < this.getFilas(); fila++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPrefHeight(rowHeight);
            cuadriculaController.getCuadricula().getRowConstraints().add(rowConstraints);
        }

        for (int columna = 0; columna < this.getColumnas(); columna++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPrefWidth(columnWidth);
            cuadriculaController.getCuadricula().getColumnConstraints().add(columnConstraints);
        }
        //System.out.println(columnWidth * this.getColumnas());
        cuadriculaController.getCuadricula().setPrefSize(columnWidth * this.getColumnas()*0.5, rowHeight * this.getFilas()*0.5);
    }


    private void llenarCuadriculaDeCeldas() throws ArchivoFXML {
        ColaADT<CeldaController> celdas = colaDeCeldas();
        for (int fila = 0; fila < this.getFilas(); fila++) {
            for (int columna = 0; columna < this.getColumnas(); columna++) {
                CeldaController celdaController = celdas.desEncolar();
                Pane panelCelda = celdaController.getPanelCelda();

                // Asegúrate de que el Pane se ajuste al tamaño de la celda
                panelCelda.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
                GridPane.setHgrow(panelCelda, Priority.ALWAYS);
                GridPane.setVgrow(panelCelda, Priority.ALWAYS);

                cuadriculaController.getCuadricula().add(panelCelda, columna, fila);
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
