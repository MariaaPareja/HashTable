package hash;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HashC <E extends Comparable<E>> { //Clase Hash Cerrado
	//Utiliza un dato que implemente interfaz Comparable, para que se pueda comparar
	protected class Element{ //Clase interna que contiene los elementos
		int mark; //Estado del elemento
		Register<E> reg; //Registro, contiene dato
		public Element (int mark, Register<E> reg) {
			this.mark = mark;
			this.reg = reg;
		}
	}
	
	protected ArrayList<Element> table; //La tabla hash
	
	public ArrayList<Element> getTable() {
		return table;
	}

	public void setTable(ArrayList<Element> table) {
		this.table = table;
	}

	protected int m; //Tamaño de la tabla
	
	public HashC (int n) { //Constructor
		this.m = n; //Toma el número primo más cercano a n.
		this.table = new ArrayList<Element> (m); //Crea el arraylist con el elemento
		//con capacidad m
		for (int i=0; i<m; i++) { //Agrega elementos a toda la tabla
			this.table.add(new Element (0, null)); //Mark = 0 es vacía y sin elemento
		}
	}
	
	private int functionHash (int key) { //Función Hash
		return key % m; //Módulo de m
	}
	
	public int functionHashS(int key, int N) {
		int digitsOfN = String.valueOf(N).length();
		int digitsToExtract;
	    // Elevar al cuadrado la clave
	    int squaredKey =  key * key;

	    // Convertir el resultado en una cadena
	    String squaredKeyStr = Integer.toString(squaredKey);

	    // Obtener la longitud de la cadena del número N
	    if (N % 10 == 0 ) {
	    	digitsToExtract = digitsOfN-1;
	    } else {
	    	digitsToExtract = digitsOfN;
	    }

	    // Obtener el punto medio para extraer los dígitos centrales
	    int midIndex = squaredKeyStr.length() / 2;
	    int startIndex = midIndex - digitsToExtract;
	    startIndex = Math.max(startIndex, 0);
	    int endIndex = startIndex + digitsToExtract;

	    // Extraer los dígitos centrales como una subcadena
	    String centralDigitsStr = squaredKeyStr.substring(startIndex+1, endIndex+1);

	    // Convertir la subcadena en un número entero
	    int hashPos = Integer.parseInt(centralDigitsStr);
	    return hashPos;
	}
	
	public int functionHashFolding(int key, int N) {
	    // Convertir la clave a una cadena para manipularla
	    String keyStr = Integer.toString(key);
	    int digitsOfN = String.valueOf(N).length();
	    int sum = 0;
	    int digitsToExtract;

	    // Obtener la cantidad de dígitos centrales a extraer
	    if (N % 10 == 0) {
	        digitsToExtract = digitsOfN;
	    } else {
	        digitsToExtract = digitsOfN + 1;
	    }

	    // Inicializar el índice de inicio para extraer
	    int startIndex = keyStr.length() - digitsToExtract;

	    // Iterar sobre la clave dividiéndola en partes y sumarlas
	    do {
	        // Obtener la subcadena de dígitos a sumar
	        String digits = keyStr.substring(startIndex, startIndex + digitsToExtract);
	        
	        // Convertir la subcadena en un número entero y sumarlo
	        sum += Integer.parseInt(digits);

	        // Mover el índice de inicio hacia la izquierda para el siguiente pliegue
	        startIndex -= digitsToExtract;
	        String finalDigit;
			// Si startIndex es menor o igual a 0, debemos manejar el último dígito
		    if (digitsToExtract > 1) {
		    	 finalDigit = keyStr.substring(0, digitsToExtract-1);
		    } else {
		    	finalDigit = keyStr.substring(0);
		    }
	    } while (startIndex>=0);

	    String finalDigit;
		// Si startIndex es menor o igual a 0, debemos manejar el último dígito
	    if (digitsToExtract > 1) {
	    	 finalDigit = keyStr.substring(0, digitsToExtract-1);
	    }
	    else {
	    	finalDigit = keyStr.substring(0);
	    }
	    // Convertir la subcadena en un número entero y sumarlo
	    sum += Integer.parseInt(finalDigit);
	 
	    // Tomar el módulo N para asegurar que hashPos esté dentro del rango de la tabla hash
	    int hashPos = sum % N;

	    return hashPos;
	}

	private int empty = 0; //0 es la marca para una posición vacía

	private int linearProbing(int dressHash, int key) { //Recibe posición inicial hash y key
	    int posInit = dressHash;
	    do {
	        Element element = table.get(dressHash); //Obtener elemento de la posición
	        // Verifica si la posición está vacía
	        // Si el elemento tiene la clave que estamos buscando
	        if (element.mark == empty || element.reg.getKey() == key) {
	            return dressHash; // Retorna la posición vacía o la posición con la clave buscada
	        } else {
	            // Calcula la siguiente posición utilizando sondeo lineal
	            dressHash = (dressHash + 1) % m;
	        }
	    } while (dressHash != posInit); // Repite hasta que vuelva a la posición inicial

	    return -1; // Retorna -1 si no se encuentra una posición vacía o la clave buscada
	}
		
	public void insert(int key, E reg) {
	    // Calcular la posición hash inicial usando la función hash
	    int hashPos = functionHash(key);
	    // Encontrar una posición adecuada usando sondeo lineal
	    int pos = linearProbing(hashPos, key);
	    // Si no se encontró una posición válida, la tabla está llena
	    if (pos == -1) {
	        throw new RuntimeException("La tabla hash está llena");
	    } 
	    // Insertar el nuevo registro en la posición encontrada
	    table.set(pos, new Element(1, new Register<E>(key, reg)));
	}

	
	public E search(int key) {
	    // Calcular la posición inicial
	    int hashPos = functionHash(key);
	    // Guardar la posición inicial
	    int posInit = hashPos;

	    do {
	        Element element = table.get(hashPos); // Obtener el elemento en la posición hashPos
	        
	        // Verificar si la posición está vacía
	        if (element == null || element.mark == empty) {
	            return null; // Si está vacía, el elemento no está en la tabla
	        }
	        
	        // Verificar si la key es igual a la que estamos buscando
	        if (element.reg.getKey() == key) {
	            return element.reg.getValue(); // Devolver el valor asociado a la clave encontrada
	        } else {
	            // Calcular la siguiente posición usando sondeo lineal
	            hashPos = linearProbing(hashPos, key); // Aplicar sondeo lineal circular
	        }
	    } while (hashPos != posInit); // Repetir hasta volver a la posición inicial

	    return null; // Retornar null si no se encontró la clave en la tabla
	}

	 // Función para calcular el valor de m
    public int calcularM(int N) {
        // Calcular m como el siguiente número primo después de la cantidad de elementos + 40%
        int m = (int) (N * 1.4);  // Un 40% adicional
        while (!esPrimo(m)) {
            m++;  // Incrementar hasta encontrar el siguiente primo
        }
        return m;
    }
    
    // Función auxiliar para verificar si un número es primo
    private boolean esPrimo(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }
    
    // Función de dispersión para empleados
    public int functionHashEmployees(int codigoEmpleado, int m) {
        return codigoEmpleado % m;
    }
    
    // Método principal para cargar los datos de empleados y dispersarlos
    public void dispersarEmpleados(String nombreArchivo) {
        ArrayList<Empleado> empleados = new ArrayList<>();

        // Leer archivo y cargar los empleados
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
        	 String linea;
             while ((linea = br.readLine()) != null) {
                 String[] partes = linea.split(",");
                 if (partes.length >= 3) {
                     int codigo = Integer.parseInt(partes[0].trim());
                     String nombre = partes[1].trim().replace("\"", "");
                     String direccion = partes[2].trim().replace("\"", "");
                     empleados.add(new Empleado(codigo, nombre, direccion));
                 }
             }

            // Calcular la cantidad de elementos
            int cantidadElementos = empleados.size();

            // Calcular m
            int m = calcularM(cantidadElementos);

            // Crear tabla hash para dispersar empleados
            HashC<Empleado> hashC = new HashC<>(m);
            ArrayList<HashC<Empleado>.Element> tablaHash = hashC.getTable();
            int[] longitudBusqueda = new int[m];

            // Dispersar los empleados en la tabla hash
            for (Empleado empleado : empleados) {
                int codigoEmpleado = empleado.getCodigo();
                int hash = functionHashEmployees(codigoEmpleado, m);

                // Resolver colisiones con búsqueda cuadrática
                int intentos = 0;
                while (tablaHash.get(hash).mark != 0) {
                    intentos++;
                    hash = (hash + intentos * intentos) % m;
                }

                tablaHash.set(hash, hashC.new Element(1, new Register<>(codigoEmpleado, empleado)));
                longitudBusqueda[hash] = intentos;
            }

            // Mostrar la tabla hash resultante
            System.out.println("Tabla de Dispersión:");
            System.out.println(hashC.toString());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	
	public String toString() { //Imprimir el registro
		String s = "D.Real\tD.Hash\tRegister\n";
		int i = 0;
		for (Element item : table) {
			s+= (i++) + " -->\t";
			if (item.mark == 1) {
				s += functionHash (item.reg.key) + "\t" + item.reg + "\n";
			} else {
				s += "empty\n";
			}
		}
		return s;
	}
	
}
