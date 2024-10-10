package unam.fesaragon.estructuradatos.controladores.vistas;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import unam.fesaragon.estructuradatos.modelos.laberinto.Coordenada;

public class CeldaController {
    private Coordenada coordenada;
    public static final String urlFXMLDeCelda = "/unam/fesaragon/estructuradatos/vistas/celda.fxml";

    @FXML
    private Pane panelCelda;

    @FXML
    private void onClicked(){
        System.out.println("Click"+ coordenada);
    }


    //GETTERS Y SETTERS
    public Pane getPanelCelda() {
        return panelCelda;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }
    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }
}
