package unam.fesaragon.estructuradatos.vistas.componentes;

import javafx.scene.image.ImageView;
import unam.fesaragon.estructuradatos.modelos.excepciones.ArchivoFXML;

import unam.fesaragon.estructuradatos.modelos.javafx.MenuFX;

public class Vista {
    MenuFX menuDeInicio;
    MenuFX menuParaCargarElLaberinto;

    public Vista(int filas, int columnas) throws ArchivoFXML {
        this.menuDeInicio = new MenuFX(filas, columnas);
        this.menuParaCargarElLaberinto = new MenuFX(filas, columnas, true);
        menuParaCargarElLaberinto.getMenuController().cambiarImageView("/unam/fesaragon/estructuradatos/assets/icons/upload2.png", menuParaCargarElLaberinto.getMenuController().getImgBoton1());
        menuParaCargarElLaberinto.getMenuController().cambiarImageView("/unam/fesaragon/estructuradatos/assets/icons/back.png", menuParaCargarElLaberinto.getMenuController().getImgBoton2());
        menuParaCargarElLaberinto.getMenuController().getImgBoton2().setRotate(0);

    }

    public MenuFX getMenuDeInicio() {
        return menuDeInicio;
    }

    public MenuFX getMenuParaCargarElLaberinto() {
        return menuParaCargarElLaberinto;
    }
}
