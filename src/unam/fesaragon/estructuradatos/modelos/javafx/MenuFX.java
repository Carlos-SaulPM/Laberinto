package unam.fesaragon.estructuradatos.modelos.javafx;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import unam.fesaragon.estructuradatos.controladores.vistas.MenuController;
import unam.fesaragon.estructuradatos.modelos.excepciones.ArchivoFXML;

import java.io.IOException;

public class MenuFX {
    //Elementos del menuFX
    private MenuController menuController;
    private AnchorPane contenedorMenuController;
    //Elementos de la cuadriculaFX
    private CuadriculaFX cuadriculaFX;
    //Elementos de menuParaCargarCuadricula


    public MenuFX(int filas, int columnas) throws ArchivoFXML {
        this.cuadriculaFX = new CuadriculaFX(filas, columnas);
        cargarComponentes();
        ingresarCuadriculaFXAlMenu();
    }
    /**
     * @param esMenuParaCargarElLaberinto Si el valor es true, se configura para ser un menu para cargar el laberinto
     * */
    public MenuFX(int filas, int columnas, boolean esMenuParaCargarElLaberinto) throws ArchivoFXML {
        this.cuadriculaFX = new CuadriculaFX(filas, columnas, esMenuParaCargarElLaberinto);
        cargarComponentes();
        ingresarCuadriculaFXAlMenu();
        configurarMenuFXParaCargarLaberinto();
    }

    private void configurarMenuFXParaCargarLaberinto() {
        getMenuController().getTextTitulo1().setText("Confirmar Laberinto");
        getMenuController().getTextTitulo2().setText("Regresar");
    }

    private void ingresarCuadriculaFXAlMenu() {
        StackPane stackPane = new StackPane();
        stackPane.setStyle("-fx-background-color: #ADD8E6;");
        stackPane.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        // Añádiendo el GridPane al StackPane
        stackPane.getChildren().add(cuadriculaFX.getCuadriculaController().getGridPaneCuadricula());
        // Centrando el GridPane dentro del StackPane
        StackPane.setAlignment(cuadriculaFX.getCuadriculaController().getGridPaneCuadricula(), Pos.CENTER);
        //Envolver el AnchorPane al StackPane
        AnchorPane.setTopAnchor(stackPane, 0.0);
        AnchorPane.setBottomAnchor(stackPane, 0.0);
        AnchorPane.setLeftAnchor(stackPane, 0.0);
        AnchorPane.setRightAnchor(stackPane, 0.0);
        //Remplazar el StackPane
        this.getMenuController().getSplitPane().getItems().set(0, stackPane);
    }

    private void cargarComponentes() throws ArchivoFXML {
        FXMLLoader loaderCuadricula = new FXMLLoader(getClass().getResource(MenuController.urlMenuController));
        try {
            contenedorMenuController = loaderCuadricula.load();
        } catch (IOException e) {
            throw new ArchivoFXML(e);
        }
        menuController = loaderCuadricula.getController();
    }



    public MenuController getMenuController() {
        return menuController;
    }

    public AnchorPane getContenedorMenuController() {
        return contenedorMenuController;
    }

    public CuadriculaFX getCuadriculaFX() {
        return cuadriculaFX;
    }

    public void setCuadriculaFX(CuadriculaFX cuadriculaFX) {
        this.cuadriculaFX = cuadriculaFX;
    }
}
