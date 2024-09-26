package unam.fesaragon.estructuradatos.modelos.adts;

public class ColaADT<T> {
    private ListaDoblementeLigada<T> data;

    public ColaADT() {
        this.data = new ListaDoblementeLigada<>();
    }

    public boolean estaVacia() {
        return this.data.esta_vacia();
    }

    public int longitud() {
        return this.data.get_tamanio();
    }

    public T frente() {
        return this.data.obtener(0);
    }

    public void encolar(T valor) { //enqueue
        this.data.agregar_al_final(valor);
    }

    public T desEncolar() {
        //Obtener el primer elemento
        T dato = this.data.obtener(0);
        //Despues eliminarlo
        this.data.eliminar_el_primero();
        return dato;
    }

    public T siguiente() {
        return this.data.obtener(1);
    }

    @Override
    public String toString() {
        return this.data.toString();
    }
}
