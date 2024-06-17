package hash;

import java.util.ArrayList;

public class HashA<E extends Comparable<E>> {

    protected ArrayList<ListLinked<Register<E>>> table; // La tabla hash, que a diferencia, tiene una LinkedList
    protected int m; // Tamaño de la tabla

    public HashA(int n) {
        this.m = n; // Toma el número primo más cercano a n.
        this.table = new ArrayList<>(m); // Crea el arraylist con capacidad m
        for (int i = 0; i < m; i++) {
            this.table.add(new ListLinked<>()); // Agrega listas enlazadas a toda la tabla
        }
    }

    private int functionHash(int key) { // Función Hash
        return key % m; // Módulo de m
    }

    public void insert(int key, E reg) {
        // Calcular la posición hash usando la función hash
        int hashPos = functionHash(key);
        // Obtener la lista enlazada en la posición hash
        ListLinked<Register<E>> chain = table.get(hashPos); 
        chain.insertLast(new Register<>(key, reg));
    }
    
    public String toString() {
        StringBuilder s = new StringBuilder("D.Real\tD.Hash\tRegister\n");
        for (int i = 0; i < m; i++) {
            s.append(i).append(" -->\t"); // Indica la posición en la tabla hash
            ListLinked<Register<E>> chain = table.get(i);
            if (chain.isEmptyList()) {
                s.append("empty\n");
            } else {
                // Utiliza el método toString() de ListLinked para obtener la representación en cadena
                s.append(chain.toString()).append("\n");
            }
        }
        return s.toString();
    }
 }
