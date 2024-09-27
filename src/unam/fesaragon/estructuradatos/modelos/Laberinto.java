package unam.fesaragon.estructuradatos.modelos;

import unam.fesaragon.estructuradatos.modelos.adts.ADTStack;
import unam.fesaragon.estructuradatos.modelos.adts.ColaADT;
import unam.fesaragon.estructuradatos.modelos.laberinto.Coordenada;
import unam.fesaragon.estructuradatos.modelos.laberinto.GridLaberinto;

public class Laberinto {
    private GridLaberinto gridLaberintoConParedes;
    private ADTStack<Coordenada> camino;

    public Laberinto(GridLaberinto gridLaberintoConParedes) {
        this.gridLaberintoConParedes = gridLaberintoConParedes;
        this.camino = new ADTStack<>();
        this.camino.push(this.gridLaberintoConParedes.getCoordenadaDeEntrada());
    }

    public void obtenerPilaCoordenadasParaLaSalida(){
        while (!camino.peek().equals(gridLaberintoConParedes.getCoordenadaDeSalida())){
            moverseASiguienteCoordenada(this.camino.peek());
        }
    }
    //Avanzar a la siguiente coordenada
    private void moverseASiguienteCoordenada(Coordenada coordenadaACalcularLaSiguiente) {
        Coordenada coordenadaSiguiente = siguienteCoordenada(coordenadaACalcularLaSiguiente);
        if (coordenadaSiguiente.equals(camino.peek())) {
            camino.peek().setEstado(false);
            camino.pop();
        } else {
            this.camino.push(coordenadaSiguiente);
        }

    }

    //Evaluar la coordenada de acuerdo con los criterios
    private Coordenada siguienteCoordenada(Coordenada coordenadaDeEntrada) {
        Coordenada siguienteCoordenada = new Coordenada();
        ColaADT<Coordenada> coordenadasMovimiento = new ColaADT<>();
        coordenadasMovimiento.encolar(new Coordenada(coordenadaDeEntrada.getFila(), coordenadaDeEntrada.getColumna() - 1));
        coordenadasMovimiento.encolar(new Coordenada(coordenadaDeEntrada.getFila() - 1, coordenadaDeEntrada.getColumna()));
        coordenadasMovimiento.encolar(new Coordenada(coordenadaDeEntrada.getFila(), coordenadaDeEntrada.getColumna() + 1));
        coordenadasMovimiento.encolar(new Coordenada(coordenadaDeEntrada.getFila() + 1, coordenadaDeEntrada.getColumna()));
        boolean coordenadaEncontrada = false;
        while (!coordenadaEncontrada) {
            if (coordenadasMovimiento.frente().isEstado() && coordenadaValida(coordenadasMovimiento.frente())) {
                siguienteCoordenada = coordenadasMovimiento.frente();
                coordenadaEncontrada = true;
            } else {
                coordenadasMovimiento.desEncolar();
            }
        }
        return siguienteCoordenada;
    }

    private boolean coordenadaValida(Coordenada coordenadaAEvaluar) {
        boolean dentroDeLimitesDeColumnas = coordenadaAEvaluar.getColumna() >= gridLaberintoConParedes.getEsquinaSuperiorIzquierda().getColumna() && coordenadaAEvaluar.getColumna() <= gridLaberintoConParedes.getEsquinaInferiorDerecha().getColumna();
        boolean dentroDeLimitesFilas = coordenadaAEvaluar.getFila() >= gridLaberintoConParedes.getEsquinaSuperiorIzquierda().getFila() && coordenadaAEvaluar.getFila() <= gridLaberintoConParedes.getEsquinaInferiorDerecha().getFila();
        return dentroDeLimitesDeColumnas && dentroDeLimitesFilas;
    }

    public ADTStack<Coordenada> getCamino() {
        return camino;
    }
}
