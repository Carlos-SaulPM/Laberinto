module Laberinto {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;

    exports unam.fesaragon.estructuradatos to javafx.graphics;
    opens unam.fesaragon.estructuradatos.controladores.vistas to javafx.fxml;
}
