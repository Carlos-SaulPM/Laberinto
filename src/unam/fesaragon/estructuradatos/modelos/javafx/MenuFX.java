package unam.fesaragon.estructuradatos.modelos.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import unam.fesaragon.estructuradatos.controladores.vistas.CuadriculaController;
import unam.fesaragon.estructuradatos.controladores.vistas.MenuController;
import unam.fesaragon.estructuradatos.modelos.excepciones.ArchivoFXML;

import java.io.IOException;

public class MenuFX {
    private MenuController menuController;
    private AnchorPane contenedorMenuController;

    public MenuFX() throws ArchivoFXML {
        cargarComponentes();
    }

    private void cargarComponentes() throws ArchivoFXML {
        FXMLLoader loaderCuadricula = new FXMLLoader(getClass().getResource(MenuController.urlMenuController));
        //Cargando el menu
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
}
