package unam.fesaragon.estructuradatos.controladores.vistas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML
    private Button boton1;
    @FXML
    private Button boton2;
    @FXML
    private ImageView imgBoton1;
    @FXML
    private ImageView imgBoton2;

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

    public ImageView getImgBoton1() {
        return imgBoton1;
    }

    public ImageView getImgBoton2() {
        return imgBoton2;
    }

    public void cambiarImageView(String rutaImagen, ImageView componente) {
        Image nuevaImagen = new Image(getClass().getResourceAsStream(rutaImagen));
        componente.setImage(nuevaImagen);
    }
}
