package unam.fesaragon.estructuradatos.controladores;

import unam.fesaragon.estructuradatos.modelos.Laberinto;
import unam.fesaragon.estructuradatos.vistas.Vista;

public class JuegoDeLaVida {
    Vista vista = new Vista();
    Laberinto laberinto = new Laberinto(vista.getGridLaberinto());


}
