package unam.fesaragon.estructuradatos.controladores.vistas;

import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

public class MenuController {
    public static final String urlMenuController = "/unam/fesaragon/estructuradatos/vistas/Menu.fxml";
    @FXML
    private AnchorPane aPContenedorMenu;
    @FXML
    private SplitPane splitPane;
    @FXML
    private AnchorPane panelIzquierdo;


    public AnchorPane getPanelIzquierdo() {
        return panelIzquierdo;
    }

    public SplitPane getSplitPane() {
        return splitPane;
    }

    public AnchorPane getaPContenedorMenu() {
        return aPContenedorMenu;
    }
}
