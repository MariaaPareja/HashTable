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
        
        //Creando una hastTable Abierta
        
        HashA<String> hashTableA = new HashA<>(11);
        hashTableA.insert(34, "Dee");
        hashTableA.insert(3, "Kurt");
        hashTableA.insert(7, "Charlie");
        hashTableA.insert(30, "Ozzy");
        hashTableA.insert(11, "Bruce");
        hashTableA.insert(8, "Freddy");
        hashTableA.insert(7, "Bon");
        hashTableA.insert(23, "Corey");
        hashTableA.insert(41, "Ville");
        hashTableA.insert(16, "Rob");
        hashTableA.insert(34, "Jonathan");
        
        //Mostrar tabla
        System.out.println(hashTableA.toString());
        
        //Corroborando búsqueda
        System.out.println("Buscar clave 3: " + hashTableA.search(3));
        System.out.println("Buscar clave 41: " + hashTableA.search(41));
        System.out.println("Buscar clave 10: " + hashTableA.search(10));
        
        //Probando funciones Hash: Pliegue y cuadrado
        System.out.println(hashTable.functionHashS(7259,100));
        System.out.println(hashTable.functionHashS(9359,100));
        System.out.println(hashTable.functionHashFolding(33242546,300));
        System.out.println(hashTable.functionHashFolding(20123876,300));
        
        //Probando dispersión de EMPLEADO.txt con Hash Cerrado
        //Función modular y resolución cuadrática
        HashC<Empleado> hashEmpleados = new HashC<>(11); 
        hashEmpleados.dispersarEmpleados("EMPLEADO.TXT");

        //Probando dispersión de EMPLEADO.txt con Hash Abierto
        HashA<Empleado> hashEmpleadosA = new HashA<> (11);
        hashEmpleadosA.dispersarEmpleados("EMPLEADO.TXT");
        
        //Probando TablaHashFrecuencia para ver la frecuencia de palabras
        TablaHashFrecuencia tabla = new TablaHashFrecuencia(10); // Tamaño de la tabla hash
        String[] palabras = {"hola", "mundo", "hola", "adiós", "mundo", "mundo"};

        // Insertar palabras en la tabla hash y contar frecuencias
        for (String palabra : palabras) {
            tabla.insertar(palabra.toLowerCase()); // Insertar palabras en minúsculas
        }

        // Ejemplo de obtener frecuencias
        System.out.println("Frecuencia de 'hola': " + tabla.frecuencia("hola"));
        System.out.println("Frecuencia de 'mundo': " + tabla.frecuencia("mundo"));
        System.out.println("Frecuencia de 'java': " + tabla.frecuencia("java"));
        System.out.println("Frecuencia de 'adiós': " + tabla.frecuencia("adiós"));
    }
}