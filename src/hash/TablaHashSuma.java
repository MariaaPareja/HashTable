package hash;

import java.util.ArrayList;

public class TablaHashSuma {
    private int tamanio; // Tamaño de la tabla hash
    private ArrayList<Integer>[] tabla; // Array de listas para almacenar los elementos

    // Constructor que inicializa la tabla hash con el tamaño especificado
    public TablaHashSuma(int tamanio) {
        this.tamanio = tamanio;
        tabla = new ArrayList[tamanio]; // Inicializa el array de listas
        for (int i = 0; i < tamanio; i++) {
            tabla[i] = new ArrayList<>(); // Cada posición inicializada como una nueva lista vacía
        }
    }

    // Método de hash que devuelve el índice de la tabla para una clave dada
    private int hash(int clave) {
        return clave % tamanio; // Utiliza el módulo para calcular el índice
    }

    // Método para insertar un elemento en la tabla hash
    public void insertar(int clave) {
        int indice = hash(clave); // Calcula el índice para la clave dada
        tabla[indice].add(clave); // Añade la clave a la lista en la posición calculada
    }

    // Método para buscar si una clave existe en la tabla hash
    public boolean buscar(int clave) {
        int indice = hash(clave); // Calcula el índice para la clave dada
        ArrayList<Integer> lista = tabla[indice]; // Obtiene la lista en esa posición

        if (lista != null) {
            for (int k : lista) {
                if (k == clave) {
                    return true; // Retorna true si encuentra la clave en la lista
                }
            }
        }
        return false; // Retorna false si no encuentra la clave
    }

    // Método para encontrar pares que sumen un valor dado en una lista
    public ArrayList<Pair<Integer, Integer>> encontrarPares(int[] lista, int suma) {
        ArrayList<Pair<Integer, Integer>> pares = new ArrayList<>(); // Lista para almacenar los pares encontrados
        TablaHashSuma tabla = new TablaHashSuma(10); // Tabla hash auxiliar para almacenar los elementos

        for (int numero : lista) {
            int complemento = suma - numero; // Calcula el complemento necesario para formar la suma
            if (tabla.buscar(complemento)) { // Si el complemento está en la tabla, forma un par
                pares.add(new Pair<>(complemento, numero));
            }
            tabla.insertar(numero); // Inserta el número actual en la tabla para futuras comparaciones
        }

        return pares; // Retorna la lista de pares encontrados
    }
    
    @Override
    public String toString() {
        ArrayList<Pair<Integer, Integer>> pares = encontrarPares(new int[0], 0); // No es relevante el valor 0
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < pares.size(); i++) {
            Pair<Integer, Integer> par = pares.get(i);
            sb.append("(").append(par.getKey()).append(", ").append(par.getValue()).append(")");
            if (i < pares.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}

