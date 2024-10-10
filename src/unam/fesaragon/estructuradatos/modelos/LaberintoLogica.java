package unam.fesaragon.estructuradatos.modelos;

import unam.fesaragon.estructuradatos.modelos.adts.ADTStack;
import unam.fesaragon.estructuradatos.modelos.adts.ColaADT;
import unam.fesaragon.estructuradatos.modelos.laberinto.Coordenada;
import unam.fesaragon.estructuradatos.modelos.laberinto.GridLaberinto;

import java.util.HashSet;
import java.util.Set;

public class LaberintoLogica {
    private GridLaberinto gridLaberintoConParedes;
    private ADTStack<Coordenada> camino;
    private Set<Coordenada> visitadas;

    //gridLaberintoConParedes debe estar previamente configurada:
    //Coordenada de entrada y salida
    //Paredes ya establecidas en false
    //Camino en true, o celdas validas
    public LaberintoLogica(GridLaberinto gridLaberintoConParedes) {
        this.gridLaberintoConParedes = gridLaberintoConParedes;
        this.camino = new ADTStack<>();
        this.visitadas = new HashSet<>();
        Coordenada entrada = this.gridLaberintoConParedes.getCoordenadaDeEntrada();
        this.camino.push(entrada);
        this.visitadas.add(entrada);
        obtenerPilaCoordenadasParaLaSalida();
    }

    public void obtenerPilaCoordenadasParaLaSalida() {
        //Cuando el peek de la pila camino sea igual a la coordenada de salida se detendra
        while (!camino.peek().equals(gridLaberintoConParedes.getCoordenadaDeSalida())) {
            moverseASiguienteCoordenada(this.camino.peek());
        }

    }

    // Avanzar a la siguiente coordenada
    private void moverseASiguienteCoordenada(Coordenada coordenadaACalcularLaSiguiente) {
        Coordenada coordenadaSiguiente = siguienteCoordenada(coordenadaACalcularLaSiguiente);
        if (coordenadaSiguiente == null) {
            // Regresar si no hay coordenada válida
            camino.peek().setEstado(false);
            camino.pop();
        } else {
            this.camino.push(coordenadaSiguiente);
            this.visitadas.add(coordenadaSiguiente);
        }
    }


    private Coordenada siguienteCoordenada(Coordenada coordenadaDeEntrada) {
        ColaADT<Coordenada> coordenadasMovimiento = new ColaADT<>();
        // Agregar las posibles coordenadas vecinas en el orden izquierda, arriba, derecha, abajo
        coordenadasMovimiento.encolar(new Coordenada(coordenadaDeEntrada.getFila(), coordenadaDeEntrada.getColumna() - 1));  // Izquierda
        coordenadasMovimiento.encolar(new Coordenada(coordenadaDeEntrada.getFila() - 1, coordenadaDeEntrada.getColumna()));  // Arriba
        coordenadasMovimiento.encolar(new Coordenada(coordenadaDeEntrada.getFila(), coordenadaDeEntrada.getColumna() + 1));  // Derecha
        coordenadasMovimiento.encolar(new Coordenada(coordenadaDeEntrada.getFila() + 1, coordenadaDeEntrada.getColumna()));  // Abajo

        // Buscar una coordenada válida
        while (!coordenadasMovimiento.estaVacia()) {
            Coordenada candidata = coordenadasMovimiento.frente();
            boolean coordenadaDentroDeLosLimites = coordenadaDentroDeLosLimites(candidata);


            // Verificar que la coordenada no haya sido visitada antes y sea válida
            if (coordenadaDentroDeLosLimites) {
                boolean yaHaRecorridoEsaCelda = visitadas.contains(gridLaberintoConParedes.getCoordenada(candidata.getFila(), candidata.getColumna()));
                boolean noEsUnaParedEsaCoordenada =gridLaberintoConParedes.getCoordenada(candidata.getFila(), candidata.getColumna()).isEstado();
                if (!yaHaRecorridoEsaCelda && noEsUnaParedEsaCoordenada){
                    return candidata;
                }

            }
            coordenadasMovimiento.desEncolar();  // Descartar la coordenada no válida
        }
        return null;  // No se encontró una coordenada válida
    }


    private boolean coordenadaDentroDeLosLimites(Coordenada coordenadaAEvaluar) {
        boolean dentroDeLimitesDeColumnas = coordenadaAEvaluar.getColumna() >= gridLaberintoConParedes.getEsquinaSuperiorIzquierda().getColumna() && coordenadaAEvaluar.getColumna() <= gridLaberintoConParedes.getEsquinaInferiorDerecha().getColumna();
        boolean dentroDeLimitesFilas = coordenadaAEvaluar.getFila() >= gridLaberintoConParedes.getEsquinaSuperiorIzquierda().getFila() && coordenadaAEvaluar.getFila() <= gridLaberintoConParedes.getEsquinaInferiorDerecha().getFila();
        return dentroDeLimitesDeColumnas && dentroDeLimitesFilas;
    }

    public ADTStack<Coordenada> getCamino() {
        return camino;
    }
}