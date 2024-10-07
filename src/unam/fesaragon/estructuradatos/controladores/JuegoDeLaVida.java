package unam.fesaragon.estructuradatos.controladores;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import unam.fesaragon.estructuradatos.modelos.LaberintoLogica;
import unam.fesaragon.estructuradatos.modelos.adts.ADTArray2D;
import unam.fesaragon.estructuradatos.modelos.excepciones.ArchivoFXML;

import unam.fesaragon.estructuradatos.modelos.javafx.Escena;
import unam.fesaragon.estructuradatos.modelos.laberinto.Coordenada;
import unam.fesaragon.estructuradatos.modelos.laberinto.GridLaberinto;
import unam.fesaragon.estructuradatos.vistas.componentes.Vista;

public class JuegoDeLaVida {
    Vista vista;
    LaberintoLogica laberintoLogica;

    public JuegoDeLaVida(int filas, int columnas) throws ArchivoFXML {
        this.vista = new Vista(filas, columnas);
        GridLaberinto gridLaberinto = probandoLogica(filas, columnas);
        this.laberintoLogica = new LaberintoLogica(gridLaberinto);
    }
    public void comenzar(){
        Escena escena = new Escena("Prueba 1", new Stage());
        escena.cambiarEscena(vista.getMenuFX().getContenedorMenuController());

    }

    private GridLaberinto probandoLogica(int filas, int columnas) {
        GridLaberinto aux = new GridLaberinto(filas, columnas);
        aux.cargarCoordenadaDeEntradaYSalida(new Coordenada(4, 0), new Coordenada(0, 4));
        ADTArray2D<Coordenada> paredes = new ADTArray2D<>(Coordenada.class, filas, columnas);
        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                if (fila >= 1 && columna >= 1) {
                    paredes.set_item(fila, columna, new Coordenada(fila, columna, false));
                } else {
                    paredes.set_item(fila, columna, new Coordenada(fila, columna));

                }
            }
        }
        aux.cargarParedesDeLaberinto(paredes);
        return aux;
    }

    public LaberintoLogica getLaberintoLogica() {
        return laberintoLogica;
    }

}
