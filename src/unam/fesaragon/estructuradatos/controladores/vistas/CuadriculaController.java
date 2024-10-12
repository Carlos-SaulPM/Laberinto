package unam.fesaragon.estructuradatos.controladores.vistas;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class CuadriculaController {
    public static final String urlFXMLDeCuadricula = "/unam/fesaragon/estructuradatos/vistas/cuadricula.fxml";
    @FXML
    private GridPane gridPaneCuadricula;


    public GridPane getGridPaneCuadricula() {
        return gridPaneCuadricula;
    }
    public CeldaController getCelda(int fila, int columna) {
        Pane panelCelda = (Pane) gridPaneCuadricula.getChildren().get(fila * gridPaneCuadricula.getColumnCount() + columna);
        return (CeldaController) panelCelda.getUserData(); // Asumiendo que has guardado el CeldaController como userData
    }


}
