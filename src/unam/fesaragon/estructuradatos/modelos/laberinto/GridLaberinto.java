package unam.fesaragon.estructuradatos.modelos.laberinto;

import unam.fesaragon.estructuradatos.modelos.adts.ADTArray2D;

public class GridLaberinto {
    private ADTArray2D<Coordenada> grid;
    private Coordenada esquinaSuperiorIzquierda;
    private Coordenada esquinaInferiorDerecha;
    private Coordenada coordenadaDeEntrada;
    private Coordenada coordenadaDeSalida;

    public GridLaberinto(int filas, int columnas) {
        this.grid = new ADTArray2D<>(Coordenada.class,filas,columnas);
        iniciarCoordenadas();
    }
    private void iniciarCoordenadas(){
        for (int filas = 0; filas < grid.getFilas(); filas++) {
            for (int columnas = 0; columnas < grid.getColumnas(); columnas++) {
                this.grid.set_item(filas,columnas, new Coordenada(filas,columnas));
            }
        }
        esquinaSuperiorIzquierda = new Coordenada(0,0);
        esquinaInferiorDerecha = new Coordenada(grid.getFilas()-1, grid.getColumnas()-1);
    }

    public void cargarParedesDeLaberinto(ADTArray2D<Coordenada> paredes){
        if (this.grid.getFilas() != paredes.getFilas() && this.grid.getColumnas() != paredes.getFilas()) {System.out.println("Las columnas o filas no coinciden con la grid"); return;}
        for (int filas = 0; filas < this.grid.getFilas(); filas++) {
            for (int columnas = 0; columnas < this.grid.getColumnas(); columnas++) {
                //Comparacion de estados en ambas grid. Grid verdadero y paredesGrid falso
                if (grid.get_item(filas,columnas).isEstado() && !paredes.get_item(filas,columnas).isEstado()){
                    grid.get_item(filas,columnas).setEstado(false);
                }
            }
        }

    }

    public void cargarCoordenadaDeEntradaYSalida(Coordenada coordenadaDeEntrada, Coordenada coordenadaDeSalida){
        this.coordenadaDeEntrada = coordenadaDeEntrada;
        this.coordenadaDeSalida = coordenadaDeSalida;
    }

    public Coordenada getCoordenada(int fila, int columna){
        return grid.get_item(fila,columna);
    }

    public Coordenada getEsquinaSuperiorIzquierda() {
        return esquinaSuperiorIzquierda;
    }

    public Coordenada getEsquinaInferiorDerecha() {
        return esquinaInferiorDerecha;
    }

    public Coordenada getCoordenadaDeEntrada() {
        return coordenadaDeEntrada;
    }

    public Coordenada getCoordenadaDeSalida() {
        return coordenadaDeSalida;
    }

    @Override
    public String toString() {
        return "GridLaberinto{" +
                "grid=" + grid +
                '}';
    }
}
