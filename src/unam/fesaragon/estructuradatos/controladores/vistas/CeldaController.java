package unam.fesaragon.estructuradatos.controladores.vistas;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import unam.fesaragon.estructuradatos.modelos.laberinto.Coordenada;

public class CeldaController {
    private Coordenada coordenada;
    private static final String colorTrue = "#FFF";
    private static final String colorPared = "#505050";
    public static final String urlFXMLDeCelda = "/unam/fesaragon/estructuradatos/vistas/celda.fxml";
    private boolean cambiarEstadoDeLasCeldas_Click = true;
    @FXML
    private Pane panelCelda;

    @FXML
    private void onClicked() {
        if (!cambiarEstadoDeLasCeldas_Click) return;
        getCoordenada().setEstado(!getCoordenada().isEstado());
        cambiarColor();
    }

    private void cambiarColor() {

        if (getCoordenada().isEstado()) {
            panelCelda.setStyle("-fx-background-color: " + colorTrue + ";");
        } else {
            panelCelda.setStyle("-fx-background-color: " + colorPared + ";");
        }
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

    public boolean isCambiarEstadoDeLasCeldas_Click() {
        return cambiarEstadoDeLasCeldas_Click;
    }

    public void setCambiarEstadoDeLasCeldas_Click(boolean cambiarEstadoDeLasCeldas_Click) {
        this.cambiarEstadoDeLasCeldas_Click = cambiarEstadoDeLasCeldas_Click;
    }
}
