package unam.fesaragon.estructuradatos.modelos.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Cuadricula {
    private static String urlFXML = "unam/fesaragon/estructuradatos/vistas/cuadricula.fxml";
    //Componentes de la cuadricula
    private AnchorPane anchorPaneContainerCuadricula;
    //Componentes de la celda
    private GridPane gridPane;
    private Pane pane;


    private void cargarFXML(){
        try {
            FXMLLoader leerFXML = FXMLLoader.load(getClass().getResource(urlFXML));
             anchorPaneContainerCuadricula= leerFXML.load();

        }catch (Exception e ){
            System.out.println("Error al cargar la escena: \n" + e);
        }
    }
}
