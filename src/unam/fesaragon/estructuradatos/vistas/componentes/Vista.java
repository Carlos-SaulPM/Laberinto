package unam.fesaragon.estructuradatos.vistas.componentes;

import unam.fesaragon.estructuradatos.modelos.excepciones.ArchivoFXML;

import unam.fesaragon.estructuradatos.modelos.javafx.MenuFX;

public class Vista {
    MenuFX menuDeInicio;
    MenuFX menuParaCargarElLaberinto;

    public Vista(int filas, int columnas) throws ArchivoFXML {
        this.menuDeInicio = new MenuFX(filas, columnas);
        this.menuParaCargarElLaberinto = new MenuFX(filas, columnas,true);

    }

    public MenuFX getMenuDeInicio() {
        return menuDeInicio;
    }

    public MenuFX getMenuParaCargarElLaberinto() {
        return menuParaCargarElLaberinto;
    }
}
