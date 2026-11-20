package ej04;

public class Main {
	
	public static void main(String[] args) {
		
		int productor  = 3;
		int consumidor = 2;
		
		/*Una vez que hemos diseñado como van
		 * a funcionar los productores y consumidores, creamos
		 * una instancia de la clase para que monitorice las actividades
		 * de los mismos, es como el gerente.
		 */
		Buffer monitor = new Buffer();
		
		/*Cuando no hemos creado una clase que sean las propias hebras
		 * para lanzarlas lo haremos con un Runnable que será con programación lambda
		 */
		for(int i=1; i<=productor; i++){
			final int idProductor = i;
			new Thread(() -> {
				
				try {
					//vamos a hacer un bucle para que repita la acción 5 veces como en la respuesta
					for(int j=0; j<5; j++) {
						int dato = (int)(Math.random()*10000);
						monitor.producir(dato);
						System.out.println("... " + Thread.currentThread().getName() + " leyó el dato " + dato);
						//para que no salgan todos a la vez vamos a añadirle un sleep
						Thread.sleep((long)(Math.random()*500));
					}
				}catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}, "Productor_" + idProductor).start();
		}
		
		//ahora vamos a hacer lo mismo para los consumidores
		for(int i=1; i<=consumidor; i++) {
			final int idConsumidor = i;
			new Thread(() -> {
				
				try {
					for(int j=0; j<7; j++) {
						int dato = monitor.consumir();
						System.out.println("... " + Thread.currentThread().getName() + " procesó el dato: " + dato);
						 Thread.sleep((long) (Math.random() * 500));
					}
				}catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}, "Consumidor_" + idConsumidor).start();
		}
	}
}
