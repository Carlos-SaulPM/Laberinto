package unam.fesaragon.estructuradatos.controladores.vistas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import unam.fesaragon.estructuradatos.controladores.JuegoDeLaVida;

public class MenuController {
    public static final String urlMenuController = "/unam/fesaragon/estructuradatos/vistas/Menu.fxml";

    @FXML
    private AnchorPane aPContenedorMenu;
    @FXML
    private SplitPane splitPane;
    @FXML
    private Text textTitulo1;
    @FXML
    private Text textTitulo2;
    @FXML
    Button boton1;
    @FXML
    Button boton2;

    public SplitPane getSplitPane() {
        return splitPane;
    }

    public AnchorPane getaPContenedorMenu() {
        return aPContenedorMenu;
    }

    public Text getTextTitulo1() {
        return textTitulo1;
    }

    public Text getTextTitulo2() {
        return textTitulo2;
    }

    public Button getBoton1() {
        return boton1;
    }

    public Button getBoton2() {
        return boton2;
    }
}
