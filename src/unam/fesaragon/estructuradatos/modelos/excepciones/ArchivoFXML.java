package unam.fesaragon.estructuradatos.modelos.excepciones;

import java.io.IOException;

public class ArchivoFXML extends IOException {
    public ArchivoFXML(IOException message) {
        super(message);
    }
}
