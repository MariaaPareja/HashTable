package hash;

//Actividad 2

public interface TDAList<T> {
    // Verifica si la lista está vacía
    boolean isEmptyList();
    
    // Obtiene la longitud de la lista
    int length();
    
    // Destruye la lista, liberando todos los recursos
    void destroyList();
    
    // Busca un elemento en la lista y devuelve su posición (si existe)
    // Devuelve -1 si no se encuentra el elemento
    int search(T x);
    
    // Inserta un elemento al principio de la lista
    void insertFirst(T x);
    
    // Inserta un elemento al final de la lista
    void insertLast(T x);
    
    // Elimina un nodo que contenga el elemento dado
    void removeNode(T x);
}

