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
        if (this.data.esta_vacia()) {
            System.out.println("La cola está vacía");
        }
        T dato = this.data.obtener(0);
        this.data.eliminar_el_primero();
        return dato;
    }


    public T siguiente() {
        if (this.data.get_tamanio() < 2) {
            System.out.println("No hay un segundo elemento en la cola");
        }
        return this.data.obtener(1);
    }


    @Override
    public String toString() {
        return this.data.toString();
    }
}
