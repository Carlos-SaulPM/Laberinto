package unam.fesaragon.estructuradatos.clasesauxiliares;

public class Coordenada {
    private int fila;
    private int columna;
    private boolean estado;

    public Coordenada(int fila, int columna, boolean estado) {
        this.fila = fila;
        this.columna = columna;
        this.estado = estado;
    }

    public Coordenada(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.estado=true;
    }

    public Coordenada() {
        this.estado=true;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
}
