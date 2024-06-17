package hash;

//Actividad 1

public class Node <T>{
	private T  data; //Datos del nodo
	private Node<T> next;  //Puntero hacia el siguiente nodo
	
	public Node(T data) { //Constructores
		this(data, null);
	}
	
	Node(T d, Node<T> n) {
		this.data = d;
		this.next = n;
	}
	
	//Getters
	public T getData() {
		return this.data;
	}
	
	public Node<T> getNext() {
		return this.next;
	}
	
	//Setters
	public void setData(T data) {
		this.data = data;
	}
	
	public void setNext(Node<T> n) {
		this.next=n;
	}
	
	//Imprimir
	public String toString() {
		return "Data: "+data+" Puntero: "+ next + "  ";
	}
}
