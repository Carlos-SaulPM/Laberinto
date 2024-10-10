package unam.fesaragon.estructuradatos.controladores.vistas;

import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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



    public SplitPane getSplitPane() {
        return splitPane;
    }

    public AnchorPane getaPContenedorMenu() {
        return aPContenedorMenu;
    }

    public Text getTextTitulo1() {
        return textTitulo1;
    }

    public void setTextTitulo1(Text textTitulo1) {
        this.textTitulo1 = textTitulo1;
    }

    public Text getTextTitulo2() {
        return textTitulo2;
    }

    public void setTextTitulo2(Text textTitulo2) {
        this.textTitulo2 = textTitulo2;
    }
}
