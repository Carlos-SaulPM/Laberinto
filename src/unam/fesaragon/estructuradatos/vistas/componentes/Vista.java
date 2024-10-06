package unam.fesaragon.estructuradatos.vistas.componentes;

import unam.fesaragon.estructuradatos.modelos.excepciones.ArchivoFXML;
import unam.fesaragon.estructuradatos.modelos.javafx.CuadriculaFX;

public class Vista {
    CuadriculaFX cuadriculaFX;

    public Vista(int filas, int columnas) throws ArchivoFXML {
        this.cuadriculaFX = new CuadriculaFX(filas, columnas);
    }

    public CuadriculaFX getCuadriculaFX() {
        return cuadriculaFX;
    }
}
