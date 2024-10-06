package unam.fesaragon.estructuradatos.controladores;

import unam.fesaragon.estructuradatos.modelos.LaberintoLogica;
import unam.fesaragon.estructuradatos.modelos.excepciones.ArchivoFXML;
import unam.fesaragon.estructuradatos.modelos.laberinto.Coordenada;
import unam.fesaragon.estructuradatos.modelos.laberinto.GridLaberinto;
import unam.fesaragon.estructuradatos.vistas.componentes.Vista;

public class JuegoDeLaVida {
    Vista vista;
    LaberintoLogica laberintoLogica;

    public JuegoDeLaVida(int filas, int columnas, GridLaberinto gridAResolver) throws ArchivoFXML {
        this.vista = new Vista(filas,columnas);
        this.laberintoLogica = new LaberintoLogica(gridAResolver);
    }

    public static void main(String[] args) throws ArchivoFXML {
        GridLaberinto pruebaGrid = new GridLaberinto(5,5);
        pruebaGrid.cargarCoordenadaDeEntradaYSalida(new Coordenada(4,0), new Coordenada(0,4));
        JuegoDeLaVida prueba1 = new JuegoDeLaVida(5,5, pruebaGrid);
        System.out.println(prueba1.getLaberintoLogica().getCamino());
    }

    public LaberintoLogica getLaberintoLogica() {
        return laberintoLogica;
    }
}
