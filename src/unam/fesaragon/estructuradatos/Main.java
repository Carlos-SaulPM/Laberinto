package unam.fesaragon.estructuradatos;

import javafx.application.Application;
import javafx.stage.Stage;
import unam.fesaragon.estructuradatos.controladores.JuegoDeLaVida;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        JuegoDeLaVida juegoDeLaVida = new JuegoDeLaVida(10,10);
        juegoDeLaVida.comenzar();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
