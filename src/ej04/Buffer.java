package ej04;

import java.util.Queue;
import java.util.LinkedList;

public class Buffer {
	private int[] buffer = new int[10]; 
	
	private int cantidad = 0;
	private int datoIntro = 0;
	private int datoSale = 0;
	
	/* Creo las colas que van a almacenar y ordenar
	 * objetos de tipo hilo, es decir los consumidores y los productores,
	 * LinkedList es una lista doblemente enlazada, formada por nodos conectados por referencias
	 * y implementa lo de Deque, que es por lo que la usamos, porque nos va a permitir usar lo de FIFO en este caso
	 * (First In First Out), es decir, la cola que nos pide el ejercicio.
	 */
	private Queue<Thread> ConsumidorCola = new LinkedList<>();
	private Queue<Thread> ProductorCola = new LinkedList<>();
	
	public synchronized void producir(int dato){
		Thread actualProd = Thread.currentThread();
		ProductorCola.add(actualProd);
		
		try{
			while(cantidad == 10 || ProductorCola.peek() != actualProd) {
			wait();
			}
		
		ProductorCola.poll();
		buffer[datoIntro] = dato;
		datoIntro = (datoIntro + 1) % 10;
		cantidad++;
		
		System.out.println("> Productor inserta " +  dato + " en el buffer. Datos en buffer: " + cantidad);
		notifyAll();
		
		} catch (InterruptedException e) {
			ProductorCola.remove(actualProd);
			notifyAll();
		}
	}
	
	public synchronized int consumir() throws InterruptedException{
		Thread actualCons = Thread.currentThread();
		ConsumidorCola.add(actualCons);
		
		//try {
			while(cantidad == 0 || ConsumidorCola.peek() != actualCons) {
				wait();
			}
			
			ConsumidorCola.poll();
			int dato = buffer[datoSale];
			datoSale = (datoSale + 1) % 10;
			cantidad--;
			
			System.out.println("< Consumidor lee " + dato + " y lo borra. Datos en buffer: " + cantidad);
			notifyAll();
			return dato;

		//} catch (InterruptedException e) {
			//ConsumidorCola.remove(actualCons);
			//notifyAll();
			//throw e;
	}
	
}
