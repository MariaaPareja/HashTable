package hash;

import java.util.ArrayList;

public class HashA<E extends Comparable<E>> {

    protected ArrayList<ListLinked<Register<E>>> table; // La tabla hash, que a diferencia, tiene una LinkedList
    protected int m; // Tamaño de la tabla

    public HashA(int n) { // Constructor
        this.m = n; // Toma el número primo más cercano a n.
        this.table = new ArrayList<>(m); // Crea el arraylist con capacidad m
        for (int i = 0; i < m; i++) { // Agrega listas enlazadas a toda la tabla
            this.table.add(new ListLinked<>());
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
        // Insertar el nuevo registro en la lista enlazada
        chain.insertLast(new Register<>(key, reg));
    }

    
    
    public String toString() { // Imprimir el registro
        StringBuilder s = new StringBuilder("D.Real\tRegister\n");
        for (int i = 0; i < table.size(); i++) {
            s.append(i).append(" -->\t");
            ListLinked<Register<E>> chain = table.get(i);
            if (chain.isEmptyList()) {
                s.append("empty\n");
            } else {
                for (Register<E> element : chain) {
                    s.append(element).append(" ");
                }
                s.append("\n");
            }
        }
        return s.toString();
    }
