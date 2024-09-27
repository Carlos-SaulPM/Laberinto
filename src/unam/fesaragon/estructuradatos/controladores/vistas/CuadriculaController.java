package unam.fesaragon.estructuradatos.controladores.vistas;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class CuadriculaController {
    @FXML
    private GridPane cuadricula;

    @FXML
    private AnchorPane anchorPaneContenedorGridPane;

    public GridPane getCuadricula() {
        return cuadricula;
    }

    public AnchorPane getAnchorPaneContenedorGridPane() {
        return anchorPaneContenedorGridPane;
    }
}
