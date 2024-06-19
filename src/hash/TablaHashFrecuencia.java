package hash;

import java.util.ArrayList;
import java.util.ArrayList;

public class TablaHashFrecuencia {
    private int tamaño; // Tamaño de la tabla hash
    private ArrayList<ArrayList<Pair<String, Integer>>> tabla; // Tabla hash representada como una lista de listas de pares (clave, valor)

    // Constructor que inicializa la tabla hash con el tamaño especificado
    public TablaHashFrecuencia(int tamaño) {
        this.tamaño = tamaño;
        tabla = new ArrayList<>(tamaño); // Inicializa el ArrayList con el tamaño dado
        for (int i = 0; i < tamaño; i++) {
            tabla.add(null); // Inicializa cada posición de la tabla como nula al principio
        }
    }

    // Método de hash que devuelve el índice de la tabla para una clave dada
    private int hash(String clave) {
        return Math.abs(clave.hashCode()) % tamaño; // Utiliza el hashCode de la clave para calcular el índice
    }

    // Método para insertar una clave en la tabla hash y contar su frecuencia
    public void insertar(String clave) {
        int indice = hash(clave); // Calcula el índice para la clave dada

        if (tabla.get(indice) == null) { // Si no hay una lista en esa posición, crea una nueva lista
            tabla.set(indice, new ArrayList<>());
        }

        ArrayList<Pair<String, Integer>> lista = tabla.get(indice); // Obtiene la lista de pares en esa posición
        boolean encontrado = false;

        // Recorre la lista de pares para buscar la clave
        for (int i = 0; i < lista.size(); i++) {
            Pair<String, Integer> par = lista.get(i);
            if (par.getKey().equals(clave)) { // Si encuentra la clave, incrementa su valor (frecuencia)
                lista.set(i, new Pair<>(clave, par.getValue() + 1));
                encontrado = true;
                break;
            }
        }

        if (!encontrado) { // Si no encontró la clave, la añade a la lista con frecuencia inicial 1
            lista.add(new Pair<>(clave, 1));
        }
    }

    // Método para obtener la frecuencia de una clave en la tabla hash
    public int frecuencia(String clave) {
        int indice = hash(clave); // Calcula el índice para la clave dada
        ArrayList<Pair<String, Integer>> lista = tabla.get(indice); // Obtiene la lista de pares en esa posición

        if (lista != null) { // Si la lista no es nula, busca la clave y devuelve su valor (frecuencia)
            for (Pair<String, Integer> par : lista) {
                if (par.getKey().equals(clave)) {
                    return par.getValue();
                }
            }
        }

        return 0; // Si la clave no está en la tabla hash, devuelve frecuencia 0
    }
}
