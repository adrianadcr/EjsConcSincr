package ej01;

public class RecursoCompartido {
	
	// Recurso compartido
	private Integer entero = null;
	
	private boolean escribiendo = false;
	private int numLectores = 0;
	

	//Para leer el entero una vez e imprimirlo por pantalla
	public void leer(String nombreLector) throws InterruptedException{
		//PASO 1: acceder al entero
		this.accesoLectura(nombreLector);
		
		//PASO 2: leer el entero e imprimirlo
		Integer intLeido = this.entero;
		System.out.println("! Lector " + nombreLector + " está leyendo: valor " + intLeido);
		
		/* Ahora espera un poco hasta terminar, para que no salga todo de 
		 * golpe, para que la impresión se cargue poco a poco
		 * Se da tiempo entre procesos para que se pueda gestionar lo de acceder y todo eso
		 */
		Thread.sleep((long) Math.random()*500);
		
		//PASO 3: terminar la lectura para que otra hebra pueda acceder
		this.terminaLectura(nombreLector);
	}
	
	public synchronized void accesoLectura(String nombreLector) throws InterruptedException{
		/* La hebra lectora accederá a leer el entero siempre y cuando
		 * haya una hebra escribiendo (porque eso significa que ya no hay
		 * una leyendo) o que el entero no sea null 
		 */
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
		 notifyAll(); //se notifican a todas las hebras que están esperando.
		 }
		 System.out.println("-- Lector " + nombreLector + " ha terminado de leer.");
	}
	
	public void escritura(String nombreEscritor) throws InterruptedException {
		//PASO 1. Se le da acceso a la escritura
		this.accesoEscritura(nombreEscritor);
		
		//PASO 2. Se sustituye el valor por uno aleatorio entre 0 y 10000
		Integer valAntiguo = this.entero;
		this.entero = (int)(Math.random()*10000);
		
		System.out.println("! Escritor " + nombreEscritor + " está escribiendo... valor antiguo: " + valAntiguo + " valor nuevo: " + this.entero);
		
		//PASO 3. Se pausa unos milisegundos
		Thread.sleep((long)Math.random()*500);
		
		//PASO 4. Se notifica que el hilo ha terminado de escribir
		this.terminaEscritura(nombreEscritor);
	}
	
	public synchronized void accesoEscritura(String nombreEscritor) throws InterruptedException {
		/*Mientras que no haya nadie escribiendo o el numero de lectores
		 * sea mayor que cero, significará que la hebra está leyendo y que
		 * hay que esperar a que acabe de leer el número para
		 * poderlo escribir.
		 */
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
