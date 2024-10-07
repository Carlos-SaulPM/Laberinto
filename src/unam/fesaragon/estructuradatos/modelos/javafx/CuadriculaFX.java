package unam.fesaragon.estructuradatos.modelos.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
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
        for (int filas = 0; filas < this.getFilas(); filas++) {
            cuadriculaController.getCuadricula().getRowConstraints().add(new RowConstraints());
        }
        for (int columnas = 0; columnas < this.getColumnas(); columnas++) {
            cuadriculaController.getCuadricula().getColumnConstraints().add(new ColumnConstraints());

        }
    }

    private void llenarCuadriculaDeCeldas() throws ArchivoFXML {
        ColaADT<CeldaController> celdas = colaDeCeldas();
        for (int fila = 0; fila < this.getFilas(); fila++) {
            for (int columna = 0; columna < this.getColumnas(); columna++) {
                CeldaController celdaController = celdas.desEncolar();
                Pane panelCelda = celdaController.getPanelCelda();
                cuadriculaController.getCuadricula().add(celdaController.getPanelCelda(),columna,fila);
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
}
