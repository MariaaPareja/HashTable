package hash;

public  class TestHash {
    public static void main(String[] args) {
        HashC<String> hashTable = new HashC<>(11);
        hashTable.insert(34, "Dee");
        hashTable.insert(3, "Kurt");
        hashTable.insert(7, "Charlie");
        hashTable.insert(30, "Ozzy");
        hashTable.insert(11, "Bruce");
        hashTable.insert(8, "Freddy");
        hashTable.insert(7, "Bon");
        hashTable.insert(23, "Corey");
        hashTable.insert(41, "Ville");
        hashTable.insert(16, "Rob");
        hashTable.insert(34, "Jonathan");
        
        //Mostrar tabla
        System.out.println(hashTable.toString());
        
        //Corroborando búsqueda
        System.out.println("Buscar clave 3: " + hashTable.search(3));
        System.out.println("Buscar clave 41: " + hashTable.search(41));
        System.out.println("Buscar clave 10: " + hashTable.search(10));
    }
}