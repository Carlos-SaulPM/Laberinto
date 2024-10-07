package unam.fesaragon.estructuradatos.modelos.laberinto;

import java.util.Objects;

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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Coordenada[" +
                "fila=" + fila +
                ", columna=" + columna +
                ", estado=" + estado +
                ']';
    }
    @Override

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordenada that = (Coordenada) o;
        return fila == that.fila && columna == that.columna;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fila, columna);
    }
}
