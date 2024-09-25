package unam.fesaragon.estructuradatos.clasesauxiliares;

import unam.fesaragon.estructuradatos.adts.ColaADT;

public class LogicaLaberinto {
    private GridLaberinto gridLaberinto;
    private ColaADT<Coordenada> camino;

    public LogicaLaberinto(GridLaberinto gridLaberinto) {
        this.gridLaberinto = gridLaberinto;
        this.camino = new ColaADT<>();
    }

    //Avanzar a la siguiente coordenada
    public void avanzarSiguienteCoordenada() {
        Coordenada coordenadaSiguiente = obtenerSiguienteCoordenada(this.gridLaberinto.getCoordenadaDeEntrada());
    }

    //Evaluar la coordenada de acuerdo con los criterios
    private Coordenada obtenerSiguienteCoordenada(Coordenada coordenadaDeEntrada) {
        Coordenada siguienteCoordenada = new Coordenada();
        boolean coordenadaEncontrada = false;
        //Izquierda
        if (!coordenadaEncontrada && coordenadaValida(new Coordenada(coordenadaDeEntrada.getFila(), coordenadaDeEntrada.getColumna() - 1))){
            siguienteCoordenada.setFila(coordenadaDeEntrada.getFila());
            siguienteCoordenada.setColumna(coordenadaDeEntrada.getColumna() - 1);
        }
        //Arriba
        if (!coordenadaEncontrada && coordenadaValida(new Coordenada(coordenadaDeEntrada.getFila()-1, coordenadaDeEntrada.getColumna()))){
            siguienteCoordenada.setFila(coordenadaDeEntrada.getFila()-1);
            siguienteCoordenada.setColumna(coordenadaDeEntrada.getColumna());
        }
        //Derecha
        if (!coordenadaEncontrada && coordenadaValida(new Coordenada(coordenadaDeEntrada.getFila(), coordenadaDeEntrada.getColumna()+1))){
            siguienteCoordenada.setFila(coordenadaDeEntrada.getFila());
            siguienteCoordenada.setColumna(coordenadaDeEntrada.getColumna()+1);
        }

        //Abajo
        if (!coordenadaEncontrada && coordenadaValida(new Coordenada(coordenadaDeEntrada.getFila()+1, coordenadaDeEntrada.getColumna()))){
            siguienteCoordenada.setFila(coordenadaDeEntrada.getFila()+1);
            siguienteCoordenada.setColumna(coordenadaDeEntrada.getColumna());
        }


    }

    private boolean coordenadaValida(Coordenada coordenadaAEvaluar) {
        boolean dentroDeLimitesDeColumnas = coordenadaAEvaluar.getColumna() >= gridLaberinto.getEsquinaSuperiorIzquierda().getColumna() && coordenadaAEvaluar.getColumna() <= gridLaberinto.getEsquinaInferiorDerecha().getColumna();
        boolean dentroDeLimitesFilas = coordenadaAEvaluar.getFila() >= gridLaberinto.getEsquinaSuperiorIzquierda().getFila() && coordenadaAEvaluar.getFila() <= gridLaberinto.getEsquinaInferiorDerecha().getFila();
        return dentroDeLimitesDeColumnas && dentroDeLimitesFilas;
    }


}
