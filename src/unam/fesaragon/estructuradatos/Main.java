package unam.fesaragon.estructuradatos;

import javafx.application.Application;
import javafx.stage.Stage;
import unam.fesaragon.estructuradatos.controladores.Laberinto;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Laberinto laberinto = new Laberinto(10,10,0.1);
        laberinto.comenzar();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
