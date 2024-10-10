package unam.fesaragon.estructuradatos.controladores.vistas;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class CuadriculaController {
    public static final String urlFXMLDeCuadricula = "/unam/fesaragon/estructuradatos/vistas/cuadricula.fxml";
    @FXML
    private GridPane gridPaneCuadricula;


    public GridPane getGridPaneCuadricula() {
        return gridPaneCuadricula;
    }


}
