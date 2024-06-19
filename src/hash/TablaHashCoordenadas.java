package hash;

import java.util.ArrayList;

public class TablaHashCoordenadas <E extends Comparable<E>> {
    private int tamanio;
    private ArrayList<ArrayList<Pair<int[], E>>> tabla;

    public TablaHashCoordenadas(int tamanio) {
        this.tamanio = tamanio;
        tabla = new ArrayList<>(tamanio);
        for (int i = 0; i < tamanio; i++) {
            tabla.add(null);
        }
    }

    // Método de hash
    private int hash(int[] clave) {
        return (clave[0] * 31 + clave[1]) % tamanio;
    }

    // Método para insertar una clave y su valor de tipo E
    public void insertar(int[] clave, E valor) {
        int indice = hash(clave);

        if (tabla.get(indice) == null) {
            tabla.set(indice, new ArrayList<>());
        }

        ArrayList<Pair<int[], E>> lista = tabla.get(indice);
        boolean encontrado = false;

        for (int i = 0; i < lista.size(); i++) {
            Pair<int[], E> par = lista.get(i);
            if (par.getKey()[0] == clave[0] && par.getKey()[1] == clave[1]) {
                lista.set(i, new Pair<>(clave, valor));
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            lista.add(new Pair<>(clave, valor));
        }
    }

    // Método para buscar y obtener el valor asociado a una clave
    public E buscar(int[] clave) {
        int indice = hash(clave);
        ArrayList<Pair<int[], E>> lista = tabla.get(indice);

        if (lista != null) {
            for (Pair<int[], E> par : lista) {
                if (par.getKey()[0] == clave[0] && par.getKey()[1] == clave[1]) {
                    return par.getValue();
                }
            }
        }

        return null;
    }

    // Método para eliminar y obtener el valor asociado a una clave
    public E eliminar(int[] clave) {
        int indice = hash(clave);
        ArrayList<Pair<int[], E>> lista = tabla.get(indice);

        if (lista != null) {
            for (int i = 0; i < lista.size(); i++) {
                Pair<int[], E> par = lista.get(i);
                if (par.getKey()[0] == clave[0] && par.getKey()[1] == clave[1]) {
                    E valor = par.getValue();
                    lista.remove(i);
                    return valor;
                }
            }
        }

        return null;
    }
}