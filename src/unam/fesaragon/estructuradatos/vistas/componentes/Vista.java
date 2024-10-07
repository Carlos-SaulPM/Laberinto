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
        //menuFX.getMenuController().getSplitPane().getItems().set(0, getCuadriculaFX().getAnchorPaneContainerCuadricula());
        StackPane stackPane = new StackPane();
        AnchorPane anchorPane = getCuadriculaFX().getAnchorPaneContainerCuadricula();

        // Ajustar las propiedades de anclaje para centrar el AnchorPane
        AnchorPane.setTopAnchor(anchorPane, 0.0);
        AnchorPane.setBottomAnchor(anchorPane, 0.0);
        AnchorPane.setLeftAnchor(anchorPane, 0.0);
        AnchorPane.setRightAnchor(anchorPane, 0.0);

        // Centrar el AnchorPane dentro del StackPane
        StackPane.setAlignment(anchorPane, Pos.CENTER);  // Asegura que esté centrado

        stackPane.getChildren().add(anchorPane);
        stackPane.setStyle("-fx-background-color: lightblue;");

        // Insertar el StackPane en el SplitPane
        menuFX.getMenuController().getSplitPane().getItems().set(0, stackPane);

        // Ajustar el tamaño del StackPane para que ocupe todo el espacio
        stackPane.prefWidthProperty().bind(menuFX.getMenuController().getSplitPane().widthProperty());
        stackPane.prefHeightProperty().bind(menuFX.getMenuController().getSplitPane().heightProperty());
}

    public CuadriculaFX getCuadriculaFX() {
        return cuadriculaFX;
    }

    public MenuFX getMenuFX() {
        return menuFX;
    }
}
