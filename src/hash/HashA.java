package hash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
    
    public E search(int key) {
        // Calcular la posición hash usando la función hash
        int hashPos = functionHash(key);
        // Obtener la lista enlazada en la posición hash
        ListLinked<Register<E>> chain = table.get(hashPos);
        // Buscar el registro en la lista enlazada
        int index = chain.search(new Register<>(key, null)); // Crear un Register solo con la clave para buscar
        if (index != -1) {
            // Devolver el valor encontrado en la lista enlazada
            Register<E> reg = chain.get(index).getData(); // Obtener el registro completo
            return reg.getValue();
        } else {
            // No se encontró el registro
            return null;
        }
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
             int m = empleados.size();
             
             // Crear tabla hash para dispersar empleados
             HashA<Empleado> hashA = new HashA<>(m);
             
             // Dispersar los empleados en la tabla hash
             for (Empleado empleado : empleados) {
                 int codigoEmpleado = empleado.getCodigo();
                 int hash = functionHashEmployees(codigoEmpleado, m);
                 hashA.insert(hash, empleado);
             }
             
             // Mostrar la tabla hash resultante
             System.out.println("Tabla de Dispersión:");
             System.out.println(hashA.toString());

             
         } catch (IOException e) {
             e.printStackTrace();
         }
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
