package hash;

//Clase para encpasular la clave y datos asociadados a la clave
public class Register<E> implements Comparable<Register<E>> {
	//Atributos de un registro: Clave y valor
	protected int key;
	protected E value;
	//Constructor
	public Register (int key, E value) {
		this.key = key;
		this.value = value;
	}
	
	//Método para realizar la comparación de datos de cualquier tipo
	@Override
	public int compareTo(Register<E> o) {
		//Resta el key del registro con el key que se desea comparar
		return this.key - o.key;
	}
	
	public boolean equals(Object o) {
		
	}
}
