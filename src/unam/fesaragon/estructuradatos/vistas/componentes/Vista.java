package unam.fesaragon.estructuradatos.vistas.componentes;

import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import unam.fesaragon.estructuradatos.controladores.vistas.MenuController;
import unam.fesaragon.estructuradatos.modelos.excepciones.ArchivoFXML;
import unam.fesaragon.estructuradatos.modelos.javafx.CuadriculaFX;
import unam.fesaragon.estructuradatos.modelos.javafx.MenuFX;

public class Vista {
    CuadriculaFX cuadriculaFX;
    MenuFX menuFX;

    public Vista(int filas, int columnas) throws ArchivoFXML {
        this.cuadriculaFX = new CuadriculaFX(filas, columnas);
        this.menuFX = new MenuFX();
        ingresarCuadriculaFXAlMenu();
    }

    private void ingresarCuadriculaFXAlMenu() {
        StackPane stackPane = new StackPane();
        stackPane.setStyle("-fx-background-color: #ADD8E6;");
        stackPane.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        // Añádiendo el GridPane al StackPane
        stackPane.getChildren().add(getCuadriculaFX().getCuadriculaController().getCuadricula());
        // Centrando el GridPane dentro del StackPane
        StackPane.setAlignment(getCuadriculaFX().getCuadriculaController().getCuadricula(), Pos.CENTER);
        //Envolver el AnchorPane al StackPane
        AnchorPane.setTopAnchor(stackPane, 0.0);
        AnchorPane.setBottomAnchor(stackPane, 0.0);
        AnchorPane.setLeftAnchor(stackPane, 0.0);
        AnchorPane.setRightAnchor(stackPane, 0.0);
        //Remplazar el StackPane
        menuFX.getMenuController().getSplitPane().getItems().set(0, stackPane);
    }


    public CuadriculaFX getCuadriculaFX() {
        return cuadriculaFX;
    }

    public MenuFX getMenuFX() {
        return menuFX;
    }
}