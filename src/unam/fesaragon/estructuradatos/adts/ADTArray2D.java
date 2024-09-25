package unam.fesaragon.estructuradatos.adts;

public class ADTArray2D<T> {
    private int filas;
    private int columnas;
    private T array[][];

    //  Constructor de la clase
    public ADTArray2D(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.array = (T[][]) new Object[filas][columnas];
    }
    //  Metodo limpiar
    public void clear(T dato) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                this.array[i][j] = dato;
            }
        }
    }
    
    //  Colocar item en la coordenada específica
    public void set_item(int renglon, int columna, T dato){
        this.array[renglon][columna] = dato;
    }

    //   Obtener item en la coordenada específica
    public T get_item(int renglon, int columna){
        return this.array[renglon][columna];
    }
    
    //Copiar estado de otro array
    public void copiarEstadoDe(ADTArray2D<T> arrayACopiar){
        for (int filas = 0; filas < arrayACopiar.getFilas(); filas++) {
            for (int columnas = 0; columnas < arrayACopiar.getColumnas(); columnas++) {
                this.array[filas][columnas] = arrayACopiar.get_item(filas,columnas);
            }
        }
    }
    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    @Override
    public String toString() {
        String matriz= "";
        for (T[] elemento : this.array) {
            for (T el : elemento) {
                matriz= matriz+el+" ";
            }
            matriz+="\n";
        }
        return matriz;
    }
}
