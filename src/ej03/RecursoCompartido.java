package ej03;

public class RecursoCompartido {
			private Integer entero = null;
			
			private boolean escribiendo = false;
			private int numLectores = 0;
			
		public void leer(String nombreLector) throws InterruptedException{
				
				this.accesoLectura(nombreLector);
				
				Integer intLeido = this.entero;
				System.out.println("! Lector " + nombreLector + " está leyendo: valor " + intLeido);
				
				Thread.sleep((long) Math.random()*500);
				
				this.terminaLectura(nombreLector);
			}
			
		public synchronized void accesoLectura(String nombreLector) throws InterruptedException{
				while(escribiendo || this.entero == null) {
					System.out.println("... Lector " + nombreLector + " espera para leer.");
					wait(); 
				}
				numLectores++;
				System.out.println("> Lector " + nombreLector + " ha empezado a leer.");
				}
				
			public synchronized void terminaLectura(String nombreLector) throws InterruptedException {
				numLectores--;
				 if(numLectores == 0) {
				 notifyAll(); 
				 }
				 System.out.println("-- Lector " + nombreLector + " ha terminado de leer.");
			}
			
			public void escritura(String nombreEscritor) throws InterruptedException {
				
				this.accesoEscritura(nombreEscritor);
			
				Integer valAntiguo = this.entero;
				this.entero = (int)(Math.random()*10000);
				System.out.println("! Escritor " + nombreEscritor + " está escribiendo... valor antiguo: " + valAntiguo + " valor nuevo: " + this.entero);
				
				Thread.sleep((long)Math.random()*500);
				
				this.terminaEscritura(nombreEscritor);
			}
			
			public synchronized void accesoEscritura(String nombreEscritor) throws InterruptedException {
				while(escribiendo || numLectores > 0) {
					System.out.println("... Escritor " + nombreEscritor + " está esperando para escribir.");
					wait();
				}
				escribiendo = true;
				System.out.println("> Escritor " + nombreEscritor + " ha empezado a escribir.");
			}
			
			public synchronized void terminaEscritura(String nombreEscritor) throws InterruptedException {
				escribiendo = false;
				notifyAll();
				System.out.println("-- Escritor " + nombreEscritor + " ha terminado de escribir.");
			}

}
