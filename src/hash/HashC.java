package hash;

import java.util.ArrayList;

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
	    // La posición inicial es hashPos
	    int posInit = hashPos;

	    do {
	        Element element = table.get(hashPos); // Obtener el elemento de hashPos
	        // Ver si la posición está vacía
	        if (element.mark == empty) {
	            return null; // Si está vacía, no está en la tabla
	        }
	        // Ver si la key es igual a la que buscamos
	        if (element.reg.getKey() == key) {
	            return element.reg.getValue(); // Return the value associated with the key
	        } else {
	            // Calcular la siguiente posición usando sondeo lineak
	            hashPos = functionHash(key);
	        }
	    } while (hashPos != posInit); // Repetir hasta llegar a la pos inicial

	    return null; // Retornar nulo si no está en la tabla
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
