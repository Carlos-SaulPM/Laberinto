package unam.fesaragon.estructuradatos.controladores.vistas;

import javafx.fxml.FXML;
import unam.fesaragon.estructuradatos.modelos.laberinto.Coordenada;

public class CeldaController {
    private Coordenada coordenada;

    public CeldaController() {
    }

    @FXML
    private void onClicked(){
        System.out.println("Click");
    }


    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }
}
