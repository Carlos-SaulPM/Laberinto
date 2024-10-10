package unam.fesaragon.estructuradatos.vistas.componentes;

import unam.fesaragon.estructuradatos.modelos.excepciones.ArchivoFXML;

import unam.fesaragon.estructuradatos.modelos.javafx.MenuFX;

public class Vista {
    MenuFX menuFX;

    public Vista(int filas, int columnas) throws ArchivoFXML {
        this.menuFX = new MenuFX(filas, columnas);
    }



    public MenuFX getMenuFX() {
        return menuFX;
    }
}
