package unam.fesaragon.estructuradatos.modelos.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import unam.fesaragon.estructuradatos.controladores.vistas.CeldaController;
import unam.fesaragon.estructuradatos.controladores.vistas.CuadriculaController;

import java.io.IOException;

public class Cuadricula {
    public static final String urlFXMLDeCuadricula = "src/unam/fesaragon/estructuradatos/vistas/cuadricula.fxml";
    //Componentes de la cuadricula
    private AnchorPane anchorPaneContainerCuadricula;
    private CuadriculaController cuadriculaController;

    //Atributos de la cuadricula
    private int filas;
    private int columnas;

    public Cuadricula(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        cargarComponentes();
    }

    private void cargarComponentes() {
        FXMLLoader loaderCuadricula = new FXMLLoader(getClass().getResource(Cuadricula.urlFXMLDeCuadricula));
        try {
            //Cargando la cuadricula
            anchorPaneContainerCuadricula = loaderCuadricula.load();
            cuadriculaController = loaderCuadricula.getController();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        llenarCuadriculaDeCeldas();

    }

    private void llenarCuadriculaDeCeldas() {
        //Cargando la celda
        FXMLLoader loaderCelda = new FXMLLoader(getClass().getResource(CeldaController.urlFXMLDeCelda));
        try {
            GridPane gridPane = loaderCelda.load();
            CeldaController celdaController = loaderCelda.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
