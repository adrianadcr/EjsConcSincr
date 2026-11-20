package ej03;

public class RecursoCompartido {
	
	private Integer entero = null;
			
	private boolean escribiendo = false;
	private int numLectores = 0;
	
	//Contadores de las hebras esperando para tener prioridad
	//0 es baja, 1 es media y 2 es alta, que coindice con el ordinal de la enumeración.
	private int[] lectoresEsperando = {0,0,0};
	private int[] escritoresEsperando = {0,0,0};
			
		
		public void leer(String nombreLector, Prioridad p) throws InterruptedException{
			
			accesoLectura(nombreLector, p);
				
			Integer intLeido = this.entero;
			System.out.println("! Lector " + nombreLector + "(" + p + ")" + " está leyendo: valor " + intLeido);
				
			Thread.sleep((long) Math.random()*500);
				
			terminaLectura(nombreLector, p);
		}
			
		public synchronized void accesoLectura(String nombreLector, Prioridad p) throws InterruptedException{
			
			lectoresEsperando[p.ordinal()]++;
			
				while(escribiendo || this.entero == null || EscritoresPrioridad(p)) {
				System.out.println("... Lector " + nombreLector + "(" + p + ")" + " espera para leer.");
				wait(); 
				}
				lectoresEsperando[p.ordinal()]--;
			numLectores++;
			System.out.println("> Lector " + nombreLector + "(" + p + ")" +  " ha empezado a leer.");
		}
				
		public synchronized void terminaLectura(String nombreLector, Prioridad p) throws InterruptedException {
			numLectores--;
			if(numLectores == 0) {
				notifyAll(); 
			}
			System.out.println("-- Lector " + nombreLector + "(" + p + ")" + " ha terminado de leer.");
		}
			
		public void escritura(String nombreEscritor, Prioridad p) throws InterruptedException {
				
			accesoEscritura(nombreEscritor, p);
			
			Integer valAntiguo = this.entero;
			this.entero = (int)(Math.random()*10000);
			System.out.println("! Escritor " + nombreEscritor + "(" + p + ")" +" está escribiendo... valor antiguo: " + valAntiguo + " valor nuevo: " + this.entero);
				
			Thread.sleep((long)Math.random()*500);
				
			terminaEscritura(nombreEscritor, p);
		}
			
		public synchronized void accesoEscritura(String nombreEscritor, Prioridad p) throws InterruptedException {
			
			escritoresEsperando[p.ordinal()]++;
			
				while(escribiendo || numLectores > 0 || AlguienMasPrioridad(p) || lectoresEsperando[p.ordinal()] > 0) {
				System.out.println("... Escritor " + nombreEscritor + "(" + p + ")" + " está esperando para escribir.");
				wait();
				}
	
			escritoresEsperando[p.ordinal()]--;
			escribiendo = true;
			System.out.println("> Escritor " + nombreEscritor +"(" + p + ")"+ " ha empezado a escribir.");
		}
			
		public synchronized void terminaEscritura(String nombreEscritor, Prioridad p) throws InterruptedException {
			escribiendo = false;
			notifyAll();
			System.out.println("-- Escritor " + nombreEscritor + "(" + p + ")" + " ha terminado de escribir.");
		}
		
		private boolean EscritoresPrioridad(Prioridad p) {
			for(int i = p.ordinal() + 1; i<3; i++) {
				if(escritoresEsperando[i]>0) 
					return true;
			}
			return false;
		}
		private boolean AlguienMasPrioridad(Prioridad p) {
			//Los lectores ganan las prioridades
			for(int i = p.ordinal() + 1; i<3; i++) {
				if(lectoresEsperando[i]>0 || escritoresEsperando[i] > 0) 
					return true;
			}
			return false;
		}
		

}
