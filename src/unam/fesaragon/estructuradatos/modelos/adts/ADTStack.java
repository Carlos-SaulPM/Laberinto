package unam.fesaragon.estructuradatos.modelos.adts;

public class ADTStack<T> {
    private ListaDoblementeLigada<T> datos;

    public ADTStack() {
        datos = new ListaDoblementeLigada<T>();
    }
    public boolean isEmpty(){
        return datos.esta_vacia();
    }
    public int length(){
        return datos.get_tamanio();
    }
    public T pop(){
        T datoASacar = datos.obtener(datos.get_tamanio()-1);
        datos.eliminar_el_final();
        return datoASacar;
    }
    public T peek(){
        return datos.obtener(datos.get_tamanio()-1);
    }
    public void push(T datoAInsertar){
        datos.agregar_al_final(datoAInsertar);
    }

    public void imprimirStack(){
        datos.transversal();
    }
}
