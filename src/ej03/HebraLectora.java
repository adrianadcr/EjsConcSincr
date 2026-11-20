package ej03;

public class HebraLectora extends Thread{
	private final RecursoCompartido recurso;
	private final String nombre;
	private final Prioridad p;

	public HebraLectora(RecursoCompartido recurso, String nombre, Prioridad p) {
		this.nombre = nombre;
		this.recurso = recurso;
		this.p = p;
	}
	
	@Override
	public void run(){
		try {
			recurso.leer(nombre,p);
		} catch (InterruptedException e) {}
	}
}
